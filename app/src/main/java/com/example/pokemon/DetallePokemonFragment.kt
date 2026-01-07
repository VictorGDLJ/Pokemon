package com.example.pokemon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pokemon.ViewModel.PokemonViewModel
import com.example.pokemon.databinding.FragmentDetallePokemonBinding

class DetallePokemonFragment : Fragment() {

    private var _binding: FragmentDetallePokemonBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: PokemonViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDetallePokemonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[PokemonViewModel::class.java]

        viewModel.pokemonSeleccionado.observe(viewLifecycleOwner) { pokemon ->
            if (pokemon != null) {
                binding.tvNombreDetalle.text = pokemon.nombre
                binding.tvNumeroDetalle.text = pokemon.numero
                binding.tvDescripcion.text = pokemon.descripcion
                binding.ivDetalle.setImageResource(pokemon.imagen)

                binding.tvTipo1.text = pokemon.tipo1
                if (pokemon.tipo2 != null) {
                    binding.tvTipo2.text = pokemon.tipo2
                    binding.tvTipo2.visibility = View.VISIBLE
                } else {
                    binding.tvTipo2.visibility = View.GONE
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}