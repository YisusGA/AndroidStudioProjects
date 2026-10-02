package es.dam2.sumanumeros_rosachacel

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class WelcomeScreen : AppCompatActivity() {
    private lateinit var result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        result = findViewById<TextView>(R.id.textResult)
        val num1 = intent.getIntExtra("num1", 1)
        val num2 = intent.getIntExtra("num2", 1)
        // Si sólo quisiéramos poner la variable, pondríamos, por ejemplo, $num1
        result.text = "${num1 + num2}"
    }
}