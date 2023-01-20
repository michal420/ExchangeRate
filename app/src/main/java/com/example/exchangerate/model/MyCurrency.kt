package com.example.exchangerate.model

import kotlinx.serialization.Serializable


//data class MyCurrency(val map: Map<String, String>) {
//    val symbol by map
//    val name by map
//}

//data class MyCurrency(val map: Map<String, String>) {
//    companion object {
//        fun from (map: Map<String, String>) = object {
//            val symbol by map
//            val name by map
//            val cur = MyCurrency(symbol, name)
//        }
//    }
//}

//data class MyCurrency(val symbol: String, val name: String) {
//    companion object {
//        fun from(map: Map<String, String>) = object {
//            val symbol by map
//            val name by map
//
//            val data = MyCurrency(symbol, name)
//        }.data
//    }
//}

@Serializable
data class MyCurrency(
    val symbol: String,
    val name: String
//
////    val AUD: String
////    val symbol: String,
////    val name:String
//
////    @SerializedName("currencies")
////    val currencies: Map<String, String>
//
////    @field:Json(name = "symbol") val symbol: String,
////    @field:Json(name = "name") val name: String
)