package com.example.myapp

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var etIotIp: EditText
    private lateinit var btnSaveIp: Button
    private lateinit var tvConnectionStatus: TextView
    private lateinit var viewStatusDot: View
    private lateinit var tvTemperature: TextView
    private lateinit var tvHumidity: TextView
    private lateinit var switchLight: SwitchCompat
    private lateinit var ivLightIcon: ImageView
    private lateinit var tvLightStatus: TextView
    private lateinit var btnViewHistory: Button

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]


        // Inisialisasi Views
        etIotIp = findViewById(R.id.etIotIp)
        btnSaveIp = findViewById(R.id.btnSaveIp)
        tvConnectionStatus = findViewById(R.id.tvConnectionStatus)
        viewStatusDot = findViewById(R.id.viewStatusDot)
        tvTemperature = findViewById(R.id.tvTemperature)
        tvHumidity = findViewById(R.id.tvHumidity)
        switchLight = findViewById(R.id.switchLight)
        ivLightIcon = findViewById(R.id.ivLightIcon)
        tvLightStatus = findViewById(R.id.tvLightStatus)
        btnViewHistory = findViewById(R.id.btnViewHistory)

        // Observasi State dari ViewModel
        observeViewModel()

        // Listener Tombol Simpan/Hubungkan IP
        btnSaveIp.setOnClickListener {
            val ip = etIotIp.text.toString().trim()
            if (viewModel.saveIpAddress(ip)) {
                Toast.makeText(this, "IP IoT berhasil disimpan: $ip", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Format IP Address tidak valid!", Toast.LENGTH_SHORT).show()
            }
        }

        // Listener Sakelar Lampu
        switchLight.setOnClickListener {
            viewModel.toggleLight(switchLight.isChecked)
        }

        // Listener Tombol Riwayat
        btnViewHistory.setOnClickListener {
            val intent = android.content.Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }
    }

    private fun observeViewModel() {
        // Toast Messages dari ViewModel
        viewModel.toastMessage.observe(this) { message ->
            if (message != null) {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                viewModel.clearToastMessage()
            }
        }

        // IP Address

        viewModel.ipAddress.observe(this) { ip ->
            if (etIotIp.text.toString() != ip) {
                etIotIp.setText(ip)
            }
        }

        // Status Koneksi Teks
        viewModel.connectionStatus.observe(this) { status ->
            tvConnectionStatus.text = status
        }

        // Warna Indikator Koneksi
        viewModel.isConnected.observe(this) { isConnected ->
            val color = if (isConnected) {
                Color.parseColor("#10B981") // Hijau Emerald
            } else {
                Color.parseColor("#EF4444") // Merah
            }
            tvConnectionStatus.setTextColor(color)
            viewStatusDot.backgroundTintList = ColorStateList.valueOf(color)
        }

        // Telemetri Suhu
        viewModel.temperature.observe(this) { temp ->
            tvTemperature.text = temp
        }

        // Telemetri Kelembapan
        viewModel.humidity.observe(this) { hum ->
            tvHumidity.text = hum
        }

        // Status Lampu
        viewModel.isLightOn.observe(this) { isOn ->
            if (switchLight.isChecked != isOn) {
                switchLight.isChecked = isOn
            }
            
            val statusText = if (isOn) "ON" else "OFF"
            tvLightStatus.text = "Status: $statusText"
            
            val iconTint = if (isOn) {
                Color.parseColor("#F59E0B") // Kuning Amber
            } else {
                Color.parseColor("#64748B") // Grey
            }
            ivLightIcon.imageTintList = ColorStateList.valueOf(iconTint)
        }
    }
}
