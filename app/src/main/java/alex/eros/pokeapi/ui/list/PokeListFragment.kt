package alex.eros.pokeapi.ui.list

import alex.eros.pokeapi.core.Resource
import alex.eros.pokeapi.data.remote.PokeDataSource
import alex.eros.pokeapi.databinding.FragmentPokeListBinding
import alex.eros.pokeapi.presentation.PokeViewModel
import alex.eros.pokeapi.presentation.PokeViewModelFactory
import alex.eros.pokeapi.repository.PokeRepositoryImpl
import alex.eros.pokeapi.repository.RetrofitClient
import alex.eros.pokeapi.ui.adapter.PokeListAdapter
import alex.eros.pokeapi.ui.listeners.OnClickListener
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

class PokeListFragment : Fragment(),OnClickListener {

    private lateinit var binding:FragmentPokeListBinding
    private val viewModel by viewModels<PokeViewModel> { PokeViewModelFactory(PokeRepositoryImpl(
        PokeDataSource(RetrofitClient.webservice)
    )) }
    private var pokeNames = mutableListOf<String>()
    private lateinit var adapter:PokeListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPokeListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAlllist().observe(viewLifecycleOwner,{ result->
            when(result){
                is Resource.Loading -> {
                    Log.d("LiveData","Loading...")
                }
                is Resource.Success -> {
                    Log.d("LiveData","${result.data}")
                    result.data.results.forEach {
                        pokeNames.add(it.name)
                    }
                    initRecyclerView()
                }
                is Resource.Failure -> {
                    Log.d("LiveData","${result.exception}")
                }
            }

        })
    }

    private fun initRecyclerView() {
        binding.RVPokeList.layoutManager = LinearLayoutManager(context)
        adapter = PokeListAdapter(pokeNames,this)
        binding.RVPokeList.adapter = adapter
    }

    override fun onClick(name: String) {
        val action = PokeListFragmentDirections.actionPokeListFragmentToPokeDetailFragment(name)
        findNavController().navigate(action)
    }

}