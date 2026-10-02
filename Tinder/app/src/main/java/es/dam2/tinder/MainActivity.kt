package es.dam2.tinder

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class MainActivity : AppCompatActivity() {

    private lateinit var imgPerson: ImageView
    private lateinit var imgHeart: ImageButton
    private lateinit var txtName: TextView
    private lateinit var txtSavedProfile: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        imgPerson = findViewById<ImageView>(R.id.imgPerson)
        imgHeart = findViewById<ImageButton>(R.id.imgHeart)
        txtName = findViewById<TextView>(R.id.txtName)
        txtSavedProfile = findViewById<TextView>(R.id.txtSavedProfile)

        txtSavedProfile.visibility = View.GONE

        var isFavorite = false

        imgHeart.setOnClickListener {
            if (!isFavorite) {
                imgHeart.setImageResource(R.drawable.ic_favorito_relleno)
                txtSavedProfile.visibility = View.VISIBLE
                isFavorite = true
                // Lanza una corrutina en el ciclo de vida de la actividad
                lifecycleScope.launch {
                    // Espera 3 segundos (3000 ms) sin bloquear el hilo principal
                    delay(3000.milliseconds)
                    txtSavedProfile.visibility = View.GONE
                }
                } else {
                    imgHeart.setImageResource(R.drawable.ic_favorito)
                    isFavorite = false
                }
            }
        }
    }
