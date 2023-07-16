package com.example.deliveryapp.pojo

data class Offers(
    val id:String? = null,
    val discountFood:String? = null,
    val discountRestaurant: String? = null,
    val freeShip:Boolean? = false
){
    public fun finalPrice(){}
}