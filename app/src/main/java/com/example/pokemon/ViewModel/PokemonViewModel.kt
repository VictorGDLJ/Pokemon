package com.example.pokemon.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemon.Model.Pokemon
import com.example.pokemon.repository.PokemonRepository

class PokemonViewModel : ViewModel() {

    private val repository = PokemonRepository()
    private var listaCompleta: MutableList<Pokemon> = mutableListOf()

    val pokemons = MutableLiveData<MutableList<Pokemon>>()
    val pokemonSeleccionado = MutableLiveData<Pokemon?>()

    fun cargarPokemons() {
        listaCompleta = repository.getPokemons()
        pokemons.value = ArrayList(listaCompleta)
    }

    // Carga solo los que el favorito = true
    fun cargarSoloFavoritos() {
        listaCompleta = repository.getPokemons()
        val favoritos = listaCompleta.filter { it.esFavorito }
        pokemons.value = ArrayList(favoritos)
    }

    fun seleccionarPokemon(pokemon: Pokemon) {
        pokemonSeleccionado.value = pokemon
    }

    fun filtrar(texto: String, esPantallaFavoritos: Boolean) {
        val listaBase = repository.getPokemons()

        val listaAFiltrar = if (esPantallaFavoritos) {
            listaBase.filter { it.esFavorito }
        } else {
            listaBase
        }

        if (texto.isEmpty()) {
            pokemons.value = ArrayList(listaAFiltrar)
        } else {
            val filtrada = listaAFiltrar.filter {
                it.nombre.lowercase().contains(texto.lowercase())
            }
            pokemons.value = ArrayList(filtrada)
        }
    }

    fun toggleFavorito(pokemon: Pokemon) {
        val item = repository.getPokemons().find { it.numero == pokemon.numero }
        item?.let {
            it.esFavorito = !it.esFavorito
        }
    }

    fun eliminarPokemon(pokemon: Pokemon) {
        repository.getPokemons().remove(pokemon)
        val listaActual = pokemons.value
        listaActual?.remove(pokemon)
        pokemons.value = listaActual
    }
}