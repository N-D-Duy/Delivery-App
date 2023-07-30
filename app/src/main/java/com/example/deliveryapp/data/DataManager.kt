package com.example.deliveryapp.data

import com.example.deliveryapp.data.local.database.DbHelper
import com.example.deliveryapp.model.Cart
import com.example.deliveryapp.model.Food
import com.example.deliveryapp.model.History
import com.example.deliveryapp.model.ImageFood
import com.example.deliveryapp.model.Offer
import com.example.deliveryapp.model.Order
import com.example.deliveryapp.model.Restaurant
import com.example.deliveryapp.model.User
import kotlinx.coroutines.flow.Flow

interface DataManager{
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
    suspend fun getUsers(): Flow<List<User>?>

    suspend fun getUserById(uid: String): Flow<User?>
    suspend fun getUserByName(name: String): Flow<User?>


        //Food
    suspend fun getFoods(): Flow<List<Food>?>

    suspend fun getFoodById(foodId: String): Flow<Food?>

    suspend fun getFoodByName(name: String): Flow<Food?>


        //History
    suspend fun getAllHistory(): Flow<List<History?>?>

    suspend fun getHistoryForUser(uid: String): Flow<User?>

        //Cart
    suspend fun getCartByUserId(uid: String): Flow<Cart?>

        //Offer
    suspend fun getOfferForFood(foodId: String): Flow<Offer?>

    suspend fun getOfferForRestaurant(resId: String): Flow<Restaurant?>

    suspend fun getFreeShip()
        //Order
    suspend fun getOrderByUserId(uid: String): Flow<Order?>




    //set data
        //Food
    suspend fun insertFood(food: Food?)

    suspend fun insertFoods(foods: List<Food?>?)

    suspend fun updateFoods(vararg foods: Food?)

        //User
    suspend fun insertUser(user: User?)

    suspend fun insertUsers(users: List<User?>?)

    suspend fun updateUsers(vararg user: User?)

        //History
    suspend fun updateOrSetDefaultUser(history: History)

        //Cart
    suspend fun updateCart(userId: String, foodId: String, quantity: Int)

    suspend fun addFoodIntoCart(userId: String, foodId: String, quantity: Int)

        //Image
    suspend fun updateImage()

        //Offer
    suspend fun updateOffer(offerId: String, offer: Offer)

    suspend fun addOffer(offer: Offer)

        //Order
    suspend fun updateOrder(newStatus: String, userId: String)

    suspend fun addOrder(order: Order, userId: String)





    //clear data
    suspend fun deleteUser(userId: String?)

    suspend fun deleteImage()

    suspend fun deleteFood(foodId: String)

    suspend fun deleteHistory(uid: String)

    suspend fun deleteOffer(offerId: String)

    suspend fun deleteOrder(orderId: String)

    suspend fun deleteCart(uid: String)


    //others
    suspend fun login(mode: LoggedInMode?)

    suspend fun logout()

    suspend fun register():User

}