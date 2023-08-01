package com.example.deliveryapp.data

import com.example.deliveryapp.data.local.database.DbHelper
import com.example.deliveryapp.data.remote.FoodRepositoryImp
import com.example.deliveryapp.data.remote.UserRepositoryImp
import com.example.deliveryapp.model.Cart
import com.example.deliveryapp.model.Food
import com.example.deliveryapp.model.History
import com.example.deliveryapp.model.Offer
import com.example.deliveryapp.model.Order
import com.example.deliveryapp.model.Restaurant
import com.example.deliveryapp.model.User
import com.example.deliveryapp.utils.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

class AppData(
    private val localDatabase: DbHelper
) : DataManager {
    override suspend fun getUsers(): Flow<UiState<List<User?>?>> = callbackFlow{
        channel.trySend(UiState.Loading)
        var localUsers: List<User?>? = arrayListOf()
        localDatabase.getAllUsers{result->
            when(result){
                is UiState.Success ->{
                    localUsers = result.data
                }
                is UiState.Error ->{
                    //tra ve loi
                }

                else -> {

                }
            }
        }
        if(localUsers != null){
            channel.trySend(UiState.Success(localUsers))
            channel.close()
        }else{
            UserRepositoryImp().getAllUser {result->
                when(result){
                    is UiState.Success->{
                        channel.trySend(UiState.Success(result.data))
                        channel.close()
                    }
                    is UiState.Error ->{
                        //tra ve loi
                    }
                    else->{
                    }
                }
            }
        }
    }

    override suspend fun getUserById(uid: String): Flow<User?> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserByName(name: String): Flow<User?> {
        TODO("Not yet implemented")
    }

    override suspend fun getFoods(): Flow<List<Food>?> {
        TODO("Not yet implemented")
    }

    override suspend fun getFoodById(foodId: String): Flow<UiState<Food?>> = callbackFlow{

    }

    override suspend fun getFoodByName(name: String): Flow<Food?> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllHistory(): Flow<List<History?>?> {
        TODO("Not yet implemented")
    }

    override suspend fun getHistoryForUser(uid: String): Flow<User?> {
        TODO("Not yet implemented")
    }

    override suspend fun getCartByUserId(uid: String): Flow<Cart?> {
        TODO("Not yet implemented")
    }

    override suspend fun getOfferForFood(foodId: String): Flow<Offer?> {
        TODO("Not yet implemented")
    }

    override suspend fun getOfferForRestaurant(resId: String): Flow<Restaurant?> {
        TODO("Not yet implemented")
    }

    override suspend fun getFreeShip() {
        TODO("Not yet implemented")
    }

    override suspend fun getOrderByUserId(uid: String): Flow<Order?> {
        TODO("Not yet implemented")
    }

    override suspend fun insertFood(food: Food?) {
        TODO("Not yet implemented")
    }

    override suspend fun insertFoods(foods: List<Food?>?) {
        TODO("Not yet implemented")
    }

    override suspend fun updateFoods(vararg foods: Food?) {
        TODO("Not yet implemented")
    }

    override suspend fun insertUser(user: User?) {
        TODO("Not yet implemented")
    }

    override suspend fun insertUsers(users: List<User?>?) {
        TODO("Not yet implemented")
    }

    override suspend fun updateUsers(vararg user: User?) {
        TODO("Not yet implemented")
    }

    override suspend fun updateOrSetDefaultUser(history: History) {
        TODO("Not yet implemented")
    }

    override suspend fun updateCart(userId: String, foodId: String, quantity: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun addFoodIntoCart(userId: String, foodId: String, quantity: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun updateImage() {
        TODO("Not yet implemented")
    }

    override suspend fun updateOffer(offerId: String, offer: Offer) {
        TODO("Not yet implemented")
    }

    override suspend fun addOffer(offer: Offer) {
        TODO("Not yet implemented")
    }

    override suspend fun updateOrder(newStatus: String, userId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun addOrder(order: Order, userId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(userId: String?) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteImage() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFood(foodId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteHistory(uid: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteOffer(offerId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteOrder(orderId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCart(uid: String) {
        TODO("Not yet implemented")
    }

    override suspend fun login(mode: DataManager.LoggedInMode?) {
        TODO("Not yet implemented")
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }

    override suspend fun register(): User {
        TODO("Not yet implemented")
    }


}
