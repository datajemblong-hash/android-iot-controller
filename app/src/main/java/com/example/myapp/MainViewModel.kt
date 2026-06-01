package com.example.myapp

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.util.Locale

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val sharedPreferences = application.getSharedPreferences("iot_prefs", Context.MODE_PRIVATE)
    private val database = AppDatabase.getDatabase(application)
    private val iotRepository = IotRepository(database.sensorDataDao())


    private var lastSaveTimestamp: Long = 0

    private val _ipAddress = MutableLiveData<String>()
    val ipAddress: LiveData<String> = _ipAddress

    private val _connectionStatus = MutableLiveData<String>()
    val connectionStatus: LiveData<String> = _connectionStatus

    private val _isConnected = MutableLiveData<Boolean>()
    val isConnected: LiveData<Boolean> = _isConnected

    private val _temperature = MutableLiveData<String>()
    val temperature: LiveData<String> = _temperature

    private val _humidity = MutableLiveData<String>()
    val humidity: LiveData<String> = _humidity

    private val _isLightOn = MutableLiveData<Boolean>()
    val isLightOn: LiveData<Boolean> = _isLightOn

    private val _toastMessage = MutableLiveData<String?>()
    val toastMessage: LiveData<String?> = _toastMessage

    private var activeIp: String = ""

    init {
        // Load saved IP Address on initialization
        val savedIp = sharedPreferences.getString("key_ip_address", "") ?: ""
        _ipAddress.value = savedIp
        
        if (savedIp.isNotEmpty()) {
            startWebSocket(savedIp)
        } else {
            _connectionStatus.value = "Status: Terputus"
            _isConnected.value = false
            _temperature.value = "-- °C"
            _humidity.value = "-- %"
        }
        
        _isLightOn.value = false
    }

    /**
     * Menyimpan IP address ke SharedPreferences jika valid
     */
    fun saveIpAddress(ip: String): Boolean {
        if (validateIpAddress(ip)) {
            sharedPreferences.edit().putString("key_ip_address", ip).apply()
            _ipAddress.value = ip
            
            // Putuskan WS lama dan hubungkan ke IP baru
            startWebSocket(ip)
            return true
        }
        return false
    }

    /**
     * Memulai koneksi WebSocket untuk monitoring telemetri
     */
    private fun startWebSocket(ip: String) {
        activeIp = ip
        _connectionStatus.postValue("Status: Menghubungkan...")
        
        iotRepository.connectWebSocket(
            ip = ip,
            onMessage = { text ->
                try {
                    val json = JSONObject(text)
                    val temp = json.optDouble("temperature", Double.NaN)
                    val hum = json.optDouble("humidity", Double.NaN)
                    
                    if (!temp.isNaN()) {
                        _temperature.postValue(String.format(Locale.US, "%.1f °C", temp))
                    }
                    if (!hum.isNaN()) {
                        _humidity.postValue(String.format(Locale.US, "%.1f %%", hum))
                    }

                    // Auto-Save telemetri berkala (setiap 10 detik)
                    if (!temp.isNaN() && !hum.isNaN()) {
                        val currentTime = System.currentTimeMillis()
                        if (currentTime - lastSaveTimestamp >= 10000) {
                            lastSaveTimestamp = currentTime
                            viewModelScope.launch {
                                iotRepository.insertSensorData(temp, hum)
                            }
                        }
                    }
                } catch (e: Exception) {
                    // Gagal parsing JSON
                }
            },
            onStatusChanged = { isConnected ->
                _isConnected.postValue(isConnected)
                if (isConnected) {
                    _connectionStatus.postValue("Status: Terhubung")
                    syncLightStatus(ip)
                } else {
                    _connectionStatus.postValue("Status: Terputus (Mencoba menghubungkan kembali...)")
                    triggerReconnect()
                }
            }
        )
    }

    /**
     * Sinkronisasi status lampu fisik saat awal terhubung
     */
    private fun syncLightStatus(ip: String) {
        viewModelScope.launch {
            val result = iotRepository.fetchLightStatus(ip)
            result.onSuccess { isOn ->
                _isLightOn.postValue(isOn)
            }
        }
    }


    /**
     * Logika rekoneksi otomatis dengan jeda 5 detik
     */
    private fun triggerReconnect() {
        viewModelScope.launch {
            delay(5000)
            if (_isConnected.value != true && activeIp.isNotEmpty()) {
                startWebSocket(activeIp)
            }
        }
    }

    /**
     * Mengatur state lampu secara asinkron lewat jaringan
     */
    fun toggleLight(isOn: Boolean) {
        val ip = _ipAddress.value ?: ""
        if (ip.isEmpty()) {
            _toastMessage.value = "Harap simpan IP alamat perangkat IoT terlebih dahulu!"
            _isLightOn.value = !isOn // Revert switch state
            return
        }

        viewModelScope.launch {
            val result = iotRepository.controlLight(ip, isOn)
            result.onSuccess {
                _isLightOn.value = isOn
            }.onFailure { exception ->
                _isLightOn.value = !isOn // Revert switch state jika gagal
                _toastMessage.value = "Gagal mengirim perintah: ${exception.localizedMessage}"
            }
        }
    }

    fun clearToastMessage() {
        _toastMessage.value = null
    }

    /**
     * Mengosongkan IP address tersimpan
     */
    fun clearIpAddress() {
        iotRepository.disconnectWebSocket()
        activeIp = ""
        sharedPreferences.edit().remove("key_ip_address").apply()
        _ipAddress.value = ""
        _connectionStatus.value = "Status: Terputus"
        _isConnected.value = false
        _temperature.value = "-- °C"
        _humidity.value = "-- %"
    }

    /**
     * Validasi format alamat IPv4
     */
    private fun validateIpAddress(ip: String): Boolean {
        val ipRegex = Regex("^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}$")
        if (!ip.matches(ipRegex)) return false
        val parts = ip.split(".")
        return parts.all { it.toInt() in 0..255 }
    }

    override fun onCleared() {
        super.onCleared()
        iotRepository.disconnectWebSocket()
    }
}
