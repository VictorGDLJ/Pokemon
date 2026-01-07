package com.example.pokemon.Model

import java.io.Serializable

data class Pokemon(
    val id: Int,
    val numero: String,
    val nombre: String,
    val tipo1: String,
    val tipo2: String?,
    val descripcion: String,
    val imagen: Int,
    var esFavorito: Boolean = false
) : Serializable