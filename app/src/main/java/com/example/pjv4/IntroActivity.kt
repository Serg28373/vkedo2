package com.example.pjv4

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import com.example.pjv4.AppCardActivity

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        val btnRegister = findViewById<Button>(R.id.btnRegister)
        btnRegister.setOnClickListener {
            // ИЗМЕНЕНО: Теперь мы переходим на AppCardActivity для теста
            startActivity(Intent(this, AppCardActivity::class.java))
        }
    }
}