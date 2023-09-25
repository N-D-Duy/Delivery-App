package com.example.deliveryapp.domain

import com.example.deliveryapp.data.local.database.DbHelper
import com.example.deliveryapp.data.remote.FirebaseRepository
import com.example.deliveryapp.model.Cart
import com.example.deliveryapp.model.Food
import com.example.deliveryapp.model.History
import com.example.deliveryapp.model.ImageFood
import com.example.deliveryapp.model.Offer
import com.example.deliveryapp.model.Order
import com.example.deliveryapp.model.User
import com.example.deliveryapp.utils.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Date
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val firebaseRepository: FirebaseRepository,
    private val dbHelper: DbHelper
) : DataManager {
    override fun getCart(userId: String): Flow<UiState<Cart?>> {
        return firebaseRepository.getCart(userId)
    }

    override fun addToCart(userId: String, foodId: String, quantity: Int): Flow<UiState<String>> {
        return firebaseRepository.addToCart(userId, foodId, quantity)
    }

    override fun removeFromCart(userId: String, foodId: String): Flow<UiState<String>> {
        return firebaseRepository.removeFromCart(userId, foodId)
    }

    override fun updateCartItemQuantity(
        userId: String,
        foodId: String,
        quantity: Int
    ): Flow<UiState<String>> {
        return firebaseRepository.updateCartItemQuantity(userId, foodId, quantity)
    }

    override fun clearCart(userId: String): Flow<UiState<String>> {
        return firebaseRepository.clearCart(userId)
    }

    override fun updateFood(food: Food, foodId: String): Flow<UiState<String>> {
        dbHelper.updateFoodLocal(food)
        return firebaseRepository.updateFood(food, foodId)
    }

    override fun addFood(food: Food): Flow<UiState<String>> {
        dbHelper.insertFoodLocal(food)
        return firebaseRepository.addFood(food)
    }

    override fun addListFood(foods: List<Food>): Flow<UiState<String>> {
        dbHelper.insertAllFoodLocal(foods)
        return firebaseRepository.addListFood(foods)
    }

    override fun removeFood(foodId: String): Flow<UiState<String>> {
        dbHelper.deleteFoodByIdLocal(foodId)
        return firebaseRepository.removeFood(foodId)
    }

    override fun getFoodList(): Flow<UiState<List<Food?>?>> {
        //check local data before get it from firebase
        val foods = dbHelper.getAllFoodsLocal()
        return if (foods.isNullOrEmpty()) {
            firebaseRepository.getFoodList()
        } else {
            flow {
                emit(UiState.Success(foods))
            }
        }
    }

    override fun getFoodById(foodId: String): Flow<UiState<Food?>> {
        val food = dbHelper.getFoodByIdLocal(foodId)
        return if(food == null){
            firebaseRepository.getFoodById(foodId)
        } else{
            flow {
                emit(UiState.Success(food))
            }
        }
    }

    override fun getImagesByFoodId(foodId: String): Flow<UiState<ImageFood?>> {
        return firebaseRepository.getImagesByFoodId(foodId)
    }

    override fun addImage(foodId: String, newUrl: String): Flow<UiState<String>> {
        return firebaseRepository.addImage(foodId, newUrl)
    }

    override fun removeImage(foodId: String, urlImage: String): Flow<UiState<String>> {
        return firebaseRepository.removeImage(foodId, urlImage)
    }

    override fun getSearchHistory(userId: String): Flow<UiState<History?>> {
        val history = dbHelper.getHistoryLocal(userId)
        return if(history == null){
            firebaseRepository.getSearchHistory(userId)
        } else{
            flow {
                emit(UiState.Success(history))
            }
        }
    }

    override fun addSearchQuery(userId: String, query: MutableMap<String, Date>): Flow<UiState<String>> {
        val history = History(uid = userId, query = query)
        dbHelper.updateHistoryLocal(history)
        return firebaseRepository.addSearchQuery(userId, query)
    }

    override fun clearSearchHistory(userId: String): Flow<UiState<String>> {
        val history = History(userId, null)
        dbHelper.updateHistoryLocal(history)
        return firebaseRepository.clearSearchHistory(userId)
    }

    override fun updateHistory(
        userId: String,
        newQuery: String,
        timestamp: Date
    ): Flow<UiState<String>> {
        val map = mutableMapOf(
            newQuery to timestamp
        )
        val history = History(userId, map)
        dbHelper.updateHistoryLocal(history)
        return firebaseRepository.updateHistory(userId, newQuery, timestamp)
    }

    override fun getRestaurantOffers(restaurantId: String): Flow<UiState<Offer>> {
        return firebaseRepository.getRestaurantOffers(restaurantId)
    }

    override fun getFoodOffers(foodId: String): Flow<UiState<Offer>> {
        return firebaseRepository.getFoodOffers(foodId)
    }

    override fun getFreeShippingOffers(): Flow<UiState<List<Offer>>> {
        return firebaseRepository.getFreeShippingOffers()
    }

    override fun addOffer(offer: Offer): Flow<UiState<String>> {
        return firebaseRepository.addOffer(offer)
    }

    override fun updateOffer(offerId: String, offer: Offer): Flow<UiState<String>> {
        return firebaseRepository.updateOffer(offerId, offer)
    }

    override fun deleteOffer(offerId: String): Flow<UiState<String>> {
        return firebaseRepository.deleteOffer(offerId)
    }

    override fun updateStatusOrder(newStatus: String, userId: String): Flow<UiState<String>> {
        return firebaseRepository.updateStatusOrder(newStatus, userId)
    }

    override fun getOrder(userId: String): Flow<UiState<Order?>> {
        return firebaseRepository.getOrder(userId)
    }

    override fun addOrder(order: Order, userId: String): Flow<UiState<String>> {
        return firebaseRepository.addOrder(order, userId)
    }

    override fun removeOrder(orderId: String): Flow<UiState<String>> {
        return firebaseRepository.removeOrder(orderId)
    }

    override fun getUserById(userId: String): Flow<UiState<User?>> {
        val user = dbHelper.getUserByIdLocal(userId)
        return if (user == null){
            firebaseRepository.getUserById(userId)
        } else{
            flow {
                emit(UiState.Success(user))
            }
        }
    }

    override fun getAllUser(): Flow<UiState<List<User?>?>> {
        val users = dbHelper.getAllUsersLocal()
        return if(users.isNullOrEmpty()){
            firebaseRepository.getAllUser()
        } else{
            flow {
                emit(UiState.Success(users))
            }
        }
    }

    override fun loginUser(email: String, password: String): Flow<UiState<User?>> {
        return firebaseRepository.loginUser(email, password)
    }

    override fun registerUser(email: String, password: String): Flow<UiState<User>> {
        return firebaseRepository.registerUser(email, password)
    }

    override fun logoutUser(userId: String): Flow<UiState<String>> {
        return firebaseRepository.logoutUser(userId)
    }

    override fun deleteUser(userId: String): Flow<UiState<String>> {
        dbHelper.deleteUserByIdLocal(userId)
        return firebaseRepository.deleteUser(userId)
    }

    override fun updateUser(userId: String, user: User): Flow<UiState<String>> {
        dbHelper.updateUserLocal(user)
        return firebaseRepository.updateUser(userId, user)
    }

    override fun insertUser(user: User): Flow<UiState<String>> {
        dbHelper.insertUserLocal(user)
        return firebaseRepository.insertUser(user)
    }

    override fun insertListUser(users: List<User>): Flow<UiState<String>> {
        dbHelper.insertAllUserLocal(users)
        return firebaseRepository.insertListUser(users)
    }

    override fun getUserByName(name: String): Flow<UiState<User?>> {
        return firebaseRepository.getUserByName(name)
    }

}
