package com.example.pokemon.repository

import com.example.pokemon.Model.Pokemon
import com.example.pokemon.R

class PokemonRepository {

    companion object {
        private var instanciaLista: MutableList<Pokemon>? = null
    }
    fun getPokemons(): MutableList<Pokemon> {
        if (instanciaLista == null) {
            instanciaLista = mutableListOf(
                Pokemon(
                    1,
                    "Nº 001",
                    "Bulbasaur",
                    "PLANTA",
                    "VENENO",
                    "Este Pokémon nace con una semilla en el lomo.",
                    R.drawable.bulbasaur,
                    false
                ),
                Pokemon(
                    2,
                    "Nº 002",
                    "Ivysaur",
                    "PLANTA",
                    "VENENO",
                    "Cuando le crece el bulbo, pierde la capacidad de pararse en dos patas.",
                    R.drawable.ivysaur,
                    false
                ),
                Pokemon(
                    3,
                    "Nº 003",
                    "Venusaur",
                    "PLANTA",
                    "VENENO",
                    "Venusaur es un Pokémon gigante con una planta en su espalda.",
                    R.drawable.venasaur,
                    false
                ),
                Pokemon(
                    4,
                    "Nº 004",
                    "Charmander",
                    "FUEGO",
                    null,
                    "Prefiere las cosas calientes. Dicen que cuando llueve le sale vapor de la cola.",
                    R.drawable.charmander,
                    false
                ),
                Pokemon(
                    5,
                    "Nº 005",
                    "Charmeleon",
                    "FUEGO",
                    null,
                    "Es de naturaleza bárbara. En la batalla se agita y lanza llamas.",
                    R.drawable.charmileon,
                    false
                ),
                Pokemon(
                    6,
                    "Nº 006",
                    "Charizard",
                    "FUEGO",
                    "VOLADOR",
                    "Escupe fuego tan caliente que funde las rocas.",
                    R.drawable.charizard,
                    false
                ),
                Pokemon(
                    7,
                    "Nº 007",
                    "Squirtle",
                    "AGUA",
                    null,
                    "Cuando retrae su largo cuello en el caparazón, dispara agua a una presión increíble.",
                    R.drawable.squirtle,
                    false
                ),
                Pokemon(
                    8,
                    "Nº 008",
                    "Wartortle",
                    "AGUA",
                    null,
                    "Se lo considera un símbolo de longevidad.",
                    R.drawable.wartortle,
                    false
                ),
                Pokemon(
                    9,
                    "Nº 009",
                    "Pikachu",
                    "Electrico",
                    null,
                    "Protagonista de la serie",
                    R.drawable.pika,
                    false
                )
            )
        }
        return instanciaLista!!
    }
}