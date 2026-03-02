package com.example.qrcodegeneratorapp

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etInput = findViewById<EditText>(R.id.etInput)
        val btnGenerate = findViewById<Button>(R.id.btnGenerate)
        val ivQRCode = findViewById<ImageView>(R.id.ivQRCode)

        btnGenerate.setOnClickListener {

            val text = etInput.text.toString()

            if (text.isEmpty()) {
                Toast.makeText(this, "Enter text first", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            try {
                val writer = QRCodeWriter()
                val bitMatrix = writer.encode(text, BarcodeFormat.QR_CODE, 500, 500)
                val width = bitMatrix.width
                val height = bitMatrix.height
                val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)

                for (x in 0 until width) {
                    for (y in 0 until height) {
                        bitmap.setPixel(
                            x,
                            y,
                            if (bitMatrix.get(x, y)) android.graphics.Color.BLACK
                            else android.graphics.Color.WHITE
                        )
                    }
                }

                ivQRCode.setImageBitmap(bitmap)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}