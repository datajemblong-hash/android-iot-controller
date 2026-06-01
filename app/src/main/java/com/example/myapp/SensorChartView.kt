package com.example.myapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Shader
import android.util.AttributeSet
import android.view.View

class SensorChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var dataList: List<SensorDataEntity> = emptyList()

    private val gridPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#E2E8F0")
        strokeWidth = 2f
        style = Paint.Style.STROKE
    }

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#64748B")
        textSize = 28f
    }

    private val tempPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#EF4444") // Red
        strokeWidth = 6f
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
    }

    private val humPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#0284C7") // Blue
        strokeWidth = 6f
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
    }

    private val fillTempPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }

    private val fillHumPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }

    fun setData(data: List<SensorDataEntity>) {
        // Balikkan list karena database menyimpan DESC (terbaru dulu), tapi grafik melukis dari kiri (terlama) ke kanan (terbaru)
        this.dataList = data.reversed()
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val w = width.toFloat()
        val h = height.toFloat()

        val paddingLeft = 80f
        val paddingRight = 40f
        val paddingTop = 60f
        val paddingBottom = 60f

        val chartWidth = w - paddingLeft - paddingRight
        val chartHeight = h - paddingTop - paddingBottom

        // Gambarkan Grid Belakang
        val gridLines = 5
        for (i in 0 until gridLines) {
            val y = paddingTop + (chartHeight / (gridLines - 1)) * i
            canvas.drawLine(paddingLeft, y, w - paddingRight, y, gridPaint)
            
            // Draw percentage values for grid
            val labelVal = 100 - (100 / (gridLines - 1)) * i
            canvas.drawText("$labelVal", 10f, y + 10f, textPaint)
        }

        if (dataList.isEmpty()) {
            val emptyText = "Belum Ada Riwayat Data"
            val textWidth = textPaint.measureText(emptyText)
            canvas.drawText(emptyText, (w - textWidth) / 2f, h / 2f, textPaint)
            return
        }

        // Cari Min dan Max
        var maxTemp = 50.0
        var minTemp = 0.0
        var maxHum = 100.0
        var minHum = 0.0

        for (data in dataList) {
            if (data.temperature > maxTemp) maxTemp = data.temperature
            if (data.temperature < minTemp) minTemp = data.temperature
            if (data.humidity > maxHum) maxHum = data.humidity
            if (data.humidity < minHum) minHum = data.humidity
        }

        // Rentang nilai
        val tempRange = if (maxTemp == minTemp) 1.0 else maxTemp - minTemp
        val humRange = if (maxHum == minHum) 1.0 else maxHum - minHum

        val size = dataList.size
        val xStep = if (size > 1) chartWidth / (size - 1) else chartWidth

        val tempPath = Path()
        val humPath = Path()
        val tempFillPath = Path()
        val humFillPath = Path()

        for (i in dataList.indices) {
            val data = dataList[i]
            val x = paddingLeft + i * xStep

            // Konversi nilai sensor ke koordinat Y
            val tempY = paddingTop + chartHeight - (((data.temperature - minTemp) / tempRange) * chartHeight).toFloat()
            val humY = paddingTop + chartHeight - (((data.humidity - minHum) / humRange) * chartHeight).toFloat()

            if (i == 0) {
                tempPath.moveTo(x, tempY)
                humPath.moveTo(x, humY)
                tempFillPath.moveTo(x, paddingTop + chartHeight)
                tempFillPath.lineTo(x, tempY)
                humFillPath.moveTo(x, paddingTop + chartHeight)
                humFillPath.lineTo(x, humY)
            } else {
                tempPath.lineTo(x, tempY)
                humPath.lineTo(x, humY)
                tempFillPath.lineTo(x, tempY)
                humFillPath.lineTo(x, humY)
            }

            if (i == size - 1) {
                tempFillPath.lineTo(x, paddingTop + chartHeight)
                tempFillPath.close()
                humFillPath.lineTo(x, paddingTop + chartHeight)
                humFillPath.close()
            }
        }

        // Setup Gradient fill
        fillTempPaint.shader = LinearGradient(
            0f, paddingTop, 0f, paddingTop + chartHeight,
            Color.parseColor("#33EF4444"), Color.parseColor("#00EF4444"),
            Shader.TileMode.CLAMP
        )
        fillHumPaint.shader = LinearGradient(
            0f, paddingTop, 0f, paddingTop + chartHeight,
            Color.parseColor("#330284C7"), Color.parseColor("#000284C7"),
            Shader.TileMode.CLAMP
        )

        // Draw fills first so lines sit on top
        canvas.drawPath(tempFillPath, fillTempPaint)
        canvas.drawPath(humFillPath, fillHumPaint)

        // Draw paths
        canvas.drawPath(tempPath, tempPaint)
        canvas.drawPath(humPath, humPaint)
    }
}
