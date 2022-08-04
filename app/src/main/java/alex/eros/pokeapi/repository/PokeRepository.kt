package alex.eros.pokeapi.repository

import alex.eros.pokeapi.data.model.PokeItemListModel
import alex.eros.pokeapi.data.model.PokeModel

interface PokeRepository {

    /*La funcion se hace suspend por que son metodos que iran a buscar informacion al server
    * y no sabemos en que momento este nos puede responder*/

   suspend fun getPokeList():PokeItemListModel
   suspend fun getPokemon(url:String):PokeModel
}