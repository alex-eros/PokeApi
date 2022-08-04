package alex.eros.pokeapi.ui.adapter

import alex.eros.pokeapi.databinding.PokeItemBinding
import alex.eros.pokeapi.ui.listeners.OnClickListener
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PokeListAdapter (var pokeNames:MutableList<String> = mutableListOf(),var listener:OnClickListener):RecyclerView.Adapter<PokeListAdapter.PokelistHolder>() {

    inner class PokelistHolder(val binding:PokeItemBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(name:String){
            binding.PokemonName.text = name
        }

        fun setListener(name:String){
            with(binding.root){
                setOnClickListener{listener.onClick(name)}
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokelistHolder {
        val pokeItemBinding = PokeItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PokelistHolder(pokeItemBinding)
    }

    override fun onBindViewHolder(holder: PokelistHolder, position: Int) {
      val name = pokeNames[position]

        with(holder){
            bind(name)
            setListener(name)
        }

    }

    override fun getItemCount(): Int = pokeNames.size

}