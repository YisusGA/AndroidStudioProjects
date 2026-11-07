package es.dam2.actividadrutas

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.dam2.actividadrutas.databinding.ActivityDetalleRutaBinding

class DetalleRuta : AppCompatActivity() {
    private lateinit var binding: ActivityDetalleRutaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetalleRutaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val posicion = intent.getIntExtra("PosicionRutaSeleccionada", -1)


        if (posicion != -1) {
            val ruta = RepositorioRutas.dataset[posicion]

            binding.imgRutaDetalle.setImageResource(ruta.image)
            binding.txtTituloDetalle.text = ruta.titulo
            binding.txtDistanciaDetalle.text = ruta.distancia
            binding.txtDificultadDetalle.text = ruta.dificultad
            binding.txtDuracionDetalle.text = ruta.duracion
            binding.txtDescripcionDetalle.text = ruta.descripcion
            binding.imgFavoritaDetalle.setImageResource(if (ruta.esFavorita) R.drawable.ic_favorito_on else R.drawable.ic_favorito_off)
            binding.imgFavoritaDetalle.setOnClickListener {
                ruta.esFavorita = !ruta.esFavorita
                binding.imgFavoritaDetalle.setImageResource(if (ruta.esFavorita) R.drawable.ic_favorito_on else R.drawable.ic_favorito_off)
            }
        }

        binding.btnVolver.setOnClickListener {
            this.finish()
        }
    }
}