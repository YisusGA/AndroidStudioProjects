package es.dam2.ejemplorecyclerview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val dataset = mutableListOf<Dato>(Dato("Titulo 1", "2021"),
            Dato("Titulo 2", "2022"),
            Dato("Titulo 3", "2023"),
            Dato("Titulo 4", "2024"),
            Dato("Titulo 5", "2025"),
            Dato("Titulo 6", "2026"),
            Dato("Titulo 7", "2027"),
            Dato("Titulo 8", "2028"),
            Dato("Titulo 9", "2029"),
            Dato("Titulo 10", "2030"),
            Dato("Titulo 11", "2031"),
            Dato("Titulo 12", "2032"),
            Dato("Titulo 13", "2033"),
            Dato("Titulo 14", "2034"),
            Dato("Titulo 15", "2035"),
            Dato("Titulo 16", "2036"),
            Dato("Titulo 17", "2037"),
            Dato("Titulo 18", "2038"),
            Dato("Titulo 19", "2039"),
            Dato("Titulo 20", "2040"),
            Dato("Titulo 21", "2041"),
            Dato("Titulo 22", "2042"),
            Dato("Titulo 23", "2043"),
            Dato("Titulo 24", "2044"),
            Dato("Titulo 25", "2045"),
            Dato("Titulo 26", "2046"),
            Dato("Titulo 27", "2047"),
            Dato("Titulo 28", "2048"),
            Dato("Titulo 29", "2049"),
            Dato("Titulo 30", "2050"))

        val customAdapter = CustomAdapter(dataset)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        //Así es cómo se pondría si queremos verlo como un LinearLayout en lugar de como un
        // GridLayout
//        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = customAdapter
    }
}