package com.example.deliveryapp.data

import com.example.deliveryapp.model.Cart
import com.example.deliveryapp.model.Food
import com.example.deliveryapp.model.History
import com.example.deliveryapp.model.ImageFood
import com.example.deliveryapp.model.Offer
import com.example.deliveryapp.model.Order
import com.example.deliveryapp.model.Restaurant
import com.example.deliveryapp.model.User

interface DataManager {
    enum class LoggedInMode(val type: Int) {
        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_GOOGLE(1),
        LOGGED_IN_MODE_FB(2),
        LOGGED_IN_MODE_SERVER(3);

        private var mType = 0

        init {
            mType = type
        }
    }

    // get data

        //User
    fun getUsers(): List<User>?

    fun getUserById(uid: String): User?

    fun getUserByName(name: String): User?


        //Food
    fun getFoods(): List<Food>?

    fun getFoodById(foodId: String): Food?

    fun getFoodByName(name: String): Food?


        //History
    fun getAllHistory(): List<History?>?

    fun getHistoryForUser(uid: String): User?

        //Cart
    fun getCartByUserId(uid: String): Cart?

        //Offer
    fun getOfferForFood(foodId: String): Offer?

    fun getOfferForRestaurant(resId: String): Restaurant?

    fun getFreeShip()
        //Order
    fun getOrderByUserId(uid: String): Order?




    //set data
        //Food
    fun insertFood(food: Food?)

    fun insertFoods(foods: List<Food?>?)

    fun updateFoods(vararg foods: Food?)

        //User
    fun insertUser(user: User?)

    fun insertUsers(users: List<User?>?)

    fun updateUsers(vararg user: User?)

        //History
    fun updateOrSetDefaultUser(history: History)

        //Cart
    fun updateCart(userId: String, foodId: String, quantity: Int)

    fun addFoodIntoCart(userId: String, foodId: String, quantity: Int)

        //Image
    fun updateImage()

        //Offer
    fun updateOffer(offerId: String, offer: Offer)

    fun addOffer(offer: Offer)

        //Order
    fun updateOrder(newStatus: String, userId: String)

    fun addOrder(order: Order, userId: String)





    //clear data
    fun deleteUser(userId: String?)

    fun deleteImage()

    fun deleteFood(foodId: String)

    fun deleteHistory(uid: String)

    fun deleteOffer(offerId: String)

    fun deleteOrder(orderId: String)

    fun deleteCart(uid: String)


    //others
    fun login(mode: LoggedInMode?)

    fun logout()

    fun register():User

}