package com.example.deliveryapp.pojo

data class User(
    val userId:String? = null,
    val username: String? = null,
    val cartId:String? = null,
    val email:String? = null,
    val phone: String? = null
){
    public fun login(){}
    public fun register(){}

    public fun logout(){}

    public fun update(){}

}