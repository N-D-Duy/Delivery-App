package com.example.deliveryapp.data

import com.example.deliveryapp.data.local.database.DbHelper
import com.example.deliveryapp.data.remote.CartRepository
import com.example.deliveryapp.data.remote.FoodRepository
import com.example.deliveryapp.data.remote.HistoryRepository
import com.example.deliveryapp.data.remote.ImagesRepository
import com.example.deliveryapp.data.remote.OfferRepository
import com.example.deliveryapp.data.remote.OrderRepository
import com.example.deliveryapp.data.remote.UserRepository
import com.example.deliveryapp.model.Cart
import com.example.deliveryapp.model.Food
import com.example.deliveryapp.model.History
import com.example.deliveryapp.model.ImageFood
import com.example.deliveryapp.model.Offer
import com.example.deliveryapp.model.Order
import com.example.deliveryapp.model.Restaurant
import com.example.deliveryapp.model.User
import com.example.deliveryapp.utils.UiState
import kotlinx.coroutines.flow.Flow

interface DataManager: DbHelper, CartRepository, FoodRepository, HistoryRepository, ImagesRepository, OfferRepository, OrderRepository, UserRepository{
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
    /*suspend fun getUsersDM(): UiState<List<User?>?>

    suspend fun getUserByIdDM(uid: String): Flow<User?>
    suspend fun getUserByNameDM(name: String): Flow<User?>


        //Food
    suspend fun getFoodsDM(): Flow<List<Food>?>

    suspend fun getFoodByIdDM(foodId: String): Flow<UiState<Food?>>

    suspend fun getFoodByNameDM(name: String): Flow<Food?>


        //History
    suspend fun getAllHistoryDM(): Flow<List<History?>?>

    suspend fun getHistoryForUserDM(uid: String): Flow<User?>

        //Cart
    suspend fun getCartByUserIdDM(uid: String): Flow<Cart?>

        //Offer
    suspend fun getOfferForFoodDM(foodId: String): Flow<Offer?>

    suspend fun getOfferForRestaurantDM(resId: String): Flow<Restaurant?>

    suspend fun getFreeShipDM()
        //Order
    suspend fun getOrderByUserIdDM(uid: String): Flow<Order?>




    //set data
        //Food
    suspend fun insertFoodDM(food: Food?)

    suspend fun insertFoodsDM(foods: List<Food?>?)

    suspend fun updateFoodsDM(vararg foods: Food?)

        //User
    suspend fun insertUserDM(user: User?)

    suspend fun insertUsersDM(users: List<User?>?)

    suspend fun updateUsersDM(vararg user: User?)

        //History
    suspend fun updateOrSetDefaultUserDM(history: History)

        //Cart
    suspend fun updateCartDM(userId: String, foodId: String, quantity: Int)

    suspend fun addFoodIntoCartDM(userId: String, foodId: String, quantity: Int)

        //Image
    suspend fun updateImageDM()

        //Offer
    suspend fun updateOfferDM(offerId: String, offer: Offer)

    suspend fun addOfferDM(offer: Offer)

        //Order
    suspend fun updateOrderDM(newStatus: String, userId: String)

    suspend fun addOrderDM(order: Order, userId: String)





    //clear data
    suspend fun deleteUser(userId: String?)

    suspend fun deleteImage()

    suspend fun deleteFood(foodId: String)

    suspend fun deleteHistory(uid: String)

    suspend fun deleteOffer(offerId: String)

    suspend fun deleteOrder(orderId: String)

    suspend fun deleteCart(uid: String)
    */


    //others
    /*suspend fun login(mode: LoggedInMode?)

    suspend fun logout()

    suspend fun register():User
*/
}