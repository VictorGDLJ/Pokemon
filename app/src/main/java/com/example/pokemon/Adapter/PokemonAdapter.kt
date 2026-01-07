package com.example.pokemon.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.Model.Pokemon
import com.example.pokemon.R
import com.example.pokemon.databinding.ViewholderPokemonBinding

class PokemonAdapter(
    private val context: Context,
    private var lista: List<Pokemon>?,
    private val onClick: (Pokemon) -> Unit,
    private val onFavoriteClick: (Pokemon) -> Unit
) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_pokemon, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = lista?.get(position)

        if (pokemon != null) {
            holder.binding.tvNombre.text = pokemon.nombre
            holder.binding.tvNumero.text = pokemon.numero
            holder.binding.ivPokemon.setImageResource(pokemon.imagen)

            // Poner el icono de favorito en funcion del estado.
            if (pokemon.esFavorito) {
                holder.binding.btnFavorito.setImageResource(R.drawable.ic_star_filled)
            } else {
                holder.binding.btnFavorito.setImageResource(R.drawable.ic_star_border)
            }

            // Ir a detalle
            holder.itemView.setOnClickListener {
                onClick(pokemon)
            }

            // Indicar si un pokemon es fav
            holder.binding.btnFavorito.setOnClickListener {
                onFavoriteClick(pokemon)

                //hacemos que cambie el icono en el momento.
                notifyItemChanged(holder.bindingAdapterPosition)
            }
        }
    }

    override fun getItemCount(): Int = lista?.size ?: 0

    fun establecerLista(nuevaLista: List<Pokemon>) {
        this.lista = nuevaLista
        notifyDataSetChanged()
    }

    fun getPokemonAt(position: Int): Pokemon = lista!![position]

    class PokemonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ViewholderPokemonBinding.bind(view)
    }
}