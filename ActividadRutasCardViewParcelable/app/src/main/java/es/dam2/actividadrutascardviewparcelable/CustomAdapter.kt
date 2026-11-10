package es.dam2.actividadrutascardviewparcelable

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(
    private val dataSet: MutableList<Dato>,
    private val onItemClick: (Dato) -> Unit
) : RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imagen: ImageView
        val titulo: TextView
        val distancia: TextView
        val dificultad: TextView
        val duracion: TextView
        val favorita: ImageView

        init {
            // Define click listener for the ViewHolder's View
            titulo = view.findViewById(R.id.txtTituloItem)
            imagen = view.findViewById(R.id.imgRutaItem)
            distancia = view.findViewById(R.id.txtDistanciaItem)
            dificultad = view.findViewById(R.id.txtDificultadItem)
            duracion = view.findViewById(R.id.txtDuracionItem)
            favorita = view.findViewById(R.id.imgFavoritaItem)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CustomViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item, viewGroup, false)

        return CustomViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(customViewHolder: CustomViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        customViewHolder.titulo.text = dataSet[position].titulo
        customViewHolder.imagen.setImageResource(dataSet[position].image)
        customViewHolder.distancia.text = dataSet[position].distancia
        customViewHolder.dificultad.text = dataSet[position].dificultad
        customViewHolder.duracion.text = dataSet[position].duracion
        customViewHolder.favorita.setImageResource(if (dataSet[position].esFavorita) R.drawable.ic_favorito_on else R.drawable.ic_favorito_off)

        // Esta es una forma más sencilla que la que vimos en clase de poner un ClickListener a un
        // elemento del Item (como una estrella de favorito), y no al item entero
        customViewHolder.favorita.setOnClickListener {
            dataSet[customViewHolder.bindingAdapterPosition].esFavorita = !dataSet[customViewHolder.bindingAdapterPosition].esFavorita
            // Se puede cambiar manualmente la imagen (con el if-else comentado aquí debajo)
            // o notificar al recycler que el item en esa posición ha cambiado
//            if (dataSet[position].esFavorita)
//                customViewHolder.favorita.setImageResource(R.drawable.ic_favorito_on)
//            else
//                customViewHolder.favorita.setImageResource(R.drawable.ic_favorito_off)
            notifyItemChanged(customViewHolder.bindingAdapterPosition)
        }


        // Al hacer click en un item, se manda la posición de ese item, para poder cargarlo
        // luego desde RepositorioRutas.dataset
        customViewHolder.itemView.setOnClickListener {
            onItemClick(dataSet[customViewHolder.bindingAdapterPosition])
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}