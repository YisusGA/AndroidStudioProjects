package es.dam2.sumanumeros_rosachacel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var num1: TextInputEditText
    private lateinit var num2: TextInputEditText
    private lateinit var boton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        num1 = findViewById<TextInputEditText>(R.id.textInput01)
        num2 = findViewById<TextInputEditText>(R.id.textInput02)
        boton = findViewById<Button>(R.id.btnSumar)

        boton.setOnClickListener {
            val intent = Intent(this@MainActivity, WelcomeScreen::class.java)
            intent.putExtra("num1", num1.text.toString().toInt())
            intent.putExtra("num2", num2.text.toString().toInt())
            startActivity(intent)
        }
    }
}