package es.dam2.controles_entrada_ejercicio02

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.graphics.toColorInt
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.transition.Visibility
import es.dam2.controles_entrada_ejercicio02.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var esFavorita = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var colorStateListHint = binding.txtInputNombre.hintTextColors

        binding.txtInputNombre.setOnClickListener {
            binding.txtInputNombre.setHintTextColor(colorStateListHint)
            binding.txtInputNombre.hint = "Nombre"
        }
        binding.btnActualizar.setOnClickListener {
            if (!binding.txtInputNombre.text.isNullOrBlank()) {
                binding.txtNombreResult.text = binding.txtInputNombre.text
                binding.txtFraseResult.text = if (!binding.txtInputFrase.text.isNullOrBlank()) binding.txtInputFrase.text else "-"
            } else {
                binding.txtInputNombre.setHintTextColor("#FF0000".toColorInt())
                binding.txtInputNombre.hint = "No valido"
            }
        }

        // Esto fuerza que se haga un cambio entre el themes.xml claro y el themes.xml oscuro
        // (values-night/themes.xml), los cuales tengo configurados para que apliquen la paleta de
        // colores que quiero
        binding.toggleButtonTema.setOnClickListener {
            val currentMode = AppCompatDelegate.getDefaultNightMode()
            if (currentMode == AppCompatDelegate.MODE_NIGHT_YES) {
                // Cambiar a modo claro
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                // Cambiar a modo oscuro
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }

        binding.switchIcono.setOnClickListener {
            if(binding.switchIcono.isChecked) {
                binding.imgFavoritoResult.visibility = View.VISIBLE
            } else {
                binding.imgFavoritoResult.visibility = View.INVISIBLE
            }
        }


        binding.imgFavorito.setOnClickListener {
            if (esFavorita) {
                binding.imgFavorito.setImageResource(android.R.drawable.btn_star_big_off)
                esFavorita = false
                binding.txtFavoritaResult.text = ""
            } else {
                binding.imgFavorito.setImageResource(android.R.drawable.btn_star_big_on)
                esFavorita = true
                binding.txtFavoritaResult.text = "Favorita"
            }
        }

        binding.btnRestablecer.setOnClickListener {
            // Cambia al modo claro
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            binding.txtInputNombre.setText("")
            binding.txtInputFrase.setText("")
            binding.txtNombreResult.text = ""
            binding.txtFraseResult.text = ""
            binding.toggleButtonTema.isChecked = false
            binding.switchIcono.isChecked = false
            binding.imgFavoritoResult.visibility = View.INVISIBLE
            binding.imgFavorito.setImageResource(android.R.drawable.btn_star_big_off)
            esFavorita = false
            binding.txtFavoritaResult.text = ""
            binding.txtInputNombre.setHintTextColor(colorStateListHint)
            binding.txtInputNombre.hint = "Nombre"
        }

    }
}