package es.dam2.actividadrutas

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.dam2.actividadrutas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var customAdapter: CustomAdapter
    private var posicionSeleccionada: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        customAdapter = CustomAdapter(RepositorioRutas.dataset) { posicion ->
            posicionSeleccionada = posicion
            val intent = Intent(this, DetalleRuta::class.java)
            intent.putExtra("PosicionRutaSeleccionada", posicion)
            startActivity(intent)
        }

        binding.txtRutasDisponibles.text = "${customAdapter.itemCount} rutas disponibles"

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = customAdapter
    }

    override fun onResume() {
        super.onResume()
        if (posicionSeleccionada != -1 && ::customAdapter.isInitialized) {
            customAdapter.notifyItemChanged(posicionSeleccionada)
        }
    }
}