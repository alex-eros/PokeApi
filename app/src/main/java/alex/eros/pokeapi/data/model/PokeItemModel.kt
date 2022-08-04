package alex.eros.pokeapi.data.model

data class PokeItemModel (val name:String)

data class PokeItemListModel(val results:MutableList<PokeItemModel> = mutableListOf())

