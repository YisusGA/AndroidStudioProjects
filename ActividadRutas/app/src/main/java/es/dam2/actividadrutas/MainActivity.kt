package es.dam2.actividadrutas

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
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

        // A la derecha de los corchetes, se define el comportamiento de la función onItemClick
        // que pusimos como parámetro de CustomAdapter. Cuando se pasa sólo una función como
        // parámetro, se puede pasar o bien dentro de los paréntesis, entre llaves y separada por
        // coma del parámetro anterior, o bien como se ve aquí debajo
        customAdapter = CustomAdapter(RepositorioRutas.dataset) { posicion ->
            posicionSeleccionada = posicion
            val intent = Intent(this, DetalleRuta::class.java)
            intent.putExtra("PosicionRutaSeleccionada", posicion)
            startActivity(intent)
        }

        binding.txtRutasDisponibles.text = "${customAdapter.itemCount} rutas disponibles"

        binding.customRecyclerView.layoutManager = LinearLayoutManager(this)
        // Si se quisiera usar un GridLayoutManager, comentar la línea de arriba y descomentar la
        // de abajo
//        binding.customRecyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.customRecyclerView.adapter = customAdapter
    }

    override fun onResume() {
        super.onResume()
        if (posicionSeleccionada != -1 && ::customAdapter.isInitialized) {
            customAdapter.notifyItemChanged(posicionSeleccionada)
        }
    }
}