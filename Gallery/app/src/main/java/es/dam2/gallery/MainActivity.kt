package es.dam2.gallery

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    private lateinit var imgLake : ImageView
    private lateinit var imgBeach : ImageView
    private lateinit var btnNext : ImageButton
    private lateinit var txtName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        imgLake = findViewById<ImageView>(R.id.imgLake)
        imgBeach = findViewById<ImageView>(R.id.imgBeach)
        btnNext = findViewById<ImageButton>(R.id.btnNext)
        txtName = findViewById<TextView>(R.id.txtName)

        btnNext.setOnClickListener {
            toggleVisibility()
        }
    }

    private fun toggleVisibility() {
        if (imgLake.visibility == View.VISIBLE) {
            imgLake.visibility = View.GONE
            imgBeach.visibility = View.VISIBLE
            txtName.text = "Beach"
        } else {
            imgLake.visibility = View.VISIBLE
            imgBeach.visibility = View.GONE
            txtName.text = "Lake"
        }
    }
}