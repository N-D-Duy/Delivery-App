package com.example.deliveryapp.pojo

data class Order(
    val orderId:String? = null,
    val date:String? = null,
    var status:String? = null,
    val paid:Boolean? = false,
    val foodId:String? = null,
    val userId:String? = null
){
    public fun updateStatus(newStatus:String){
        if(newStatus == "received"){
            this.status = "received"
        }
    }
}