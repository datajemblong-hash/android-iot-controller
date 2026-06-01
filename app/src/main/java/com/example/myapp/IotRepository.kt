package com.example.myapp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import org.json.JSONObject
import java.io.IOException
import java.util.concurrent.TimeUnit

class IotRepository(private val sensorDataDao: SensorDataDao) {

    private val client = OkHttpClient.Builder()
        .connectTimeout(3, TimeUnit.SECONDS)
        .writeTimeout(3, TimeUnit.SECONDS)
        .readTimeout(3, TimeUnit.SECONDS)
        .build()

    private val mediaTypeJson = "application/json; charset=utf-8".toMediaType()
    private var webSocket: WebSocket? = null

    /**
     * Mengirim request HTTP POST untuk mengontrol lampu perangkat IoT.
     * Endpoint: http://<IP_IOT>/api/light
     * Payload: {"status": "ON"} atau {"status": "OFF"}
     */
    suspend fun controlLight(ip: String, turnOn: Boolean): Result<Boolean> = withContext(Dispatchers.IO) {
        val url = "http://$ip/api/light"
        val statusValue = if (turnOn) "ON" else "OFF"
        val jsonPayload = "{\"status\":\"$statusValue\"}"
        
        val requestBody = jsonPayload.toRequestBody(mediaTypeJson)
        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()
            
        try {
            client.newCall(request).execute().use { response ->
                if (response.isSuccessful) {
                    Result.success(true)
                } else {
                    Result.failure(IOException("Server HTTP IoT mengembalikan kode status: ${response.code}"))
                }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Mengambil status lampu saat ini dari perangkat IoT via HTTP GET.
     * Endpoint: http://<IP_IOT>/api/light
     * Mengembalikan: Result<Boolean> (true jika ON, false jika OFF)
     */
    suspend fun fetchLightStatus(ip: String): Result<Boolean> = withContext(Dispatchers.IO) {
        val url = "http://$ip/api/light"
        val request = Request.Builder()
            .url(url)
            .get()
            .build()
            
        try {
            client.newCall(request).execute().use { response ->
                if (response.isSuccessful) {
                    val bodyString = response.body?.string() ?: ""
                    val json = JSONObject(bodyString)
                    val status = json.optString("status", "OFF")
                    Result.success(status == "ON")
                } else {
                    Result.failure(IOException("Server HTTP IoT mengembalikan kode status: ${response.code}"))
                }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Menyimpan data sensor ke database lokal Room secara asinkron
     */
    suspend fun insertSensorData(temperature: Double, humidity: Double) = withContext(Dispatchers.IO) {
        val entity = SensorDataEntity(
            timestamp = System.currentTimeMillis(),
            temperature = temperature,
            humidity = humidity
        )
        sensorDataDao.insertSensorData(entity)
    }

    /**
     * Membuka koneksi WebSocket ke ws://<IP_IOT>/ws
     */
    fun connectWebSocket(
        ip: String,
        onMessage: (String) -> Unit,
        onStatusChanged: (Boolean) -> Unit
    ) {
        // Putuskan koneksi lama jika ada
        disconnectWebSocket()

        val request = Request.Builder()
            .url("ws://$ip/ws")
            .build()

        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                onStatusChanged(true)
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                onMessage(text)
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                onStatusChanged(false)
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                onStatusChanged(false)
            }
        })
    }

    /**
     * Memutuskan koneksi WebSocket aktif
     */
    fun disconnectWebSocket() {
        webSocket?.close(1000, "Aplikasi memutus koneksi")
        webSocket = null
    }
}
