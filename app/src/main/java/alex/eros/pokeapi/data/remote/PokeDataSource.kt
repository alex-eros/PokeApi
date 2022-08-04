package alex.eros.pokeapi.data.remote

import alex.eros.pokeapi.data.model.PokeItemListModel
import alex.eros.pokeapi.data.model.PokeModel
import alex.eros.pokeapi.repository.WebService

class PokeDataSource(private val webservice:WebService) {

   suspend fun getPokelist():PokeItemListModel{
        return webservice.getPokeList()
    }

    suspend fun getPokemon(url:String):PokeModel{
        return webservice.getPokemon(url)
    }

}