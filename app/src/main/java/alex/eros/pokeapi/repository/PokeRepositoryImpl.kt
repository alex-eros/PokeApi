package alex.eros.pokeapi.repository

import alex.eros.pokeapi.data.model.PokeItemListModel
import alex.eros.pokeapi.data.model.PokeModel
import alex.eros.pokeapi.data.remote.PokeDataSource

class PokeRepositoryImpl(private val dataSource: PokeDataSource):PokeRepository {

    override suspend fun getPokeList(): PokeItemListModel = dataSource.getPokelist()
    override suspend fun getPokemon(url:String): PokeModel = dataSource.getPokemon(url)

}