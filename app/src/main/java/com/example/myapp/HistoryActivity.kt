package com.example.myapp

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {

    private lateinit var sensorChartView: SensorChartView
    private lateinit var btnClearHistory: Button
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        // Setup Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbarHistory)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Initialize DB & Views
        database = AppDatabase.getDatabase(this)
        sensorChartView = findViewById(R.id.sensorChartView)
        btnClearHistory = findViewById(R.id.btnClearHistory)

        // Ambil Data dari Room DB secara reaktif (Flow)
        lifecycleScope.launch {
            database.sensorDataDao().getSensorHistory().collect { historyList ->
                sensorChartView.setData(historyList)
            }
        }

        // Tombol Hapus Riwayat
        btnClearHistory.setOnClickListener {
            lifecycleScope.launch {
                database.sensorDataDao().clearHistory()
                Toast.makeText(this@HistoryActivity, "Seluruh riwayat telemetri berhasil dihapus", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
