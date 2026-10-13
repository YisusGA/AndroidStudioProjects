package es.dam2.practicalayouts

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    private lateinit var btnCambiarImagen: Button
    private lateinit var btnComprobar: Button
    private lateinit var imgPerroGatoNegro: ImageView
    private lateinit var imgPerroGatoSiames: ImageView
    private lateinit var txtMensaje: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnCambiarImagen = findViewById<Button>(R.id.btnCambiarImagen)
        btnComprobar = findViewById<Button>(R.id.btnComprobar)
        imgPerroGatoNegro = findViewById<ImageView>(R.id.imgPerroGatoNegro)
        imgPerroGatoSiames = findViewById<ImageView>(R.id.imgPerroGatoSiames)
        txtMensaje = findViewById<TextView>(R.id.txtMensaje)

        btnCambiarImagen.setOnClickListener {
            if (imgPerroGatoSiames.isVisible) {
                imgPerroGatoSiames.visibility = View.GONE
                imgPerroGatoNegro.visibility = View.VISIBLE
            } else {
                imgPerroGatoSiames.visibility = View.VISIBLE
                imgPerroGatoNegro.visibility = View.GONE
            }
        }

        btnComprobar.setOnClickListener {
            val intent = Intent(this@MainActivity, CheckWindow::class.java)
            if (imgPerroGatoSiames.isVisible) {
                intent.putExtra("numImagen", 1)
            } else {
                intent.putExtra("numImagen", 2)
            }
            startActivity(intent)
        }
    }

    override fun onRestart() {
        super.onRestart()
        imgPerroGatoSiames.visibility = View.VISIBLE
        imgPerroGatoNegro.visibility = View.GONE
        txtMensaje.text = "Volvemooooos!!!"
    }
}