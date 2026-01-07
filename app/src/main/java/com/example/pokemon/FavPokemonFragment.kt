package com.example.pokemon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.Adapter.PokemonAdapter
import com.example.pokemon.ViewModel.PokemonViewModel
import com.example.pokemon.databinding.FragmentPokemonBinding // Reusamos el layout xml

class FavPokemonFragment : Fragment() {

    private var _binding: FragmentPokemonBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: PokemonViewModel
    private lateinit var adapter: PokemonAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPokemonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[PokemonViewModel::class.java]

        adapter = PokemonAdapter(requireContext(), null,
            onClick = { pokemon ->
                viewModel.seleccionarPokemon(pokemon)
                findNavController().navigate(R.id.action_favPokemonFragment_to_detallePokemonFragment)
            },
            onFavoriteClick = { pokemon ->
                viewModel.toggleFavorito(pokemon)

                viewModel.cargarSoloFavoritos()
            }
        )

        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerView.adapter = adapter

        val swipeCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(r: RecyclerView, v: RecyclerView.ViewHolder, t: RecyclerView.ViewHolder) = false
            override fun onSwiped(v: RecyclerView.ViewHolder, d: Int) {
                val pokemon = adapter.getPokemonAt(v.adapterPosition)
                viewModel.eliminarPokemon(pokemon)
                viewModel.cargarSoloFavoritos()
            }
        }
        ItemTouchHelper(swipeCallback).attachToRecyclerView(binding.recyclerView)

        viewModel.pokemons.observe(viewLifecycleOwner) { lista ->
            adapter.establecerLista(lista)
        }

        viewModel.cargarSoloFavoritos()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false
            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.filtrar(newText ?: "", esPantallaFavoritos = true)
                return true
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}