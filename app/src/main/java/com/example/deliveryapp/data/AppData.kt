package com.example.deliveryapp.data

import com.example.deliveryapp.data.local.database.AppDatabase
import com.example.deliveryapp.data.local.database.AppDbHelper
import com.example.deliveryapp.data.local.database.DbHelper
import com.example.deliveryapp.data.remote.CartRepository
import com.example.deliveryapp.data.remote.FoodRepository
import com.example.deliveryapp.data.remote.FoodRepositoryImp
import com.example.deliveryapp.data.remote.HistoryRepository
import com.example.deliveryapp.data.remote.ImagesRepository
import com.example.deliveryapp.data.remote.OfferRepository
import com.example.deliveryapp.data.remote.OrderRepository
import com.example.deliveryapp.data.remote.UserRepository
import com.example.deliveryapp.data.remote.UserRepositoryImp
import com.example.deliveryapp.model.Cart
import com.example.deliveryapp.model.Food
import com.example.deliveryapp.model.History
import com.example.deliveryapp.model.ImageFood
import com.example.deliveryapp.model.Offer
import com.example.deliveryapp.model.Order
import com.example.deliveryapp.model.Restaurant
import com.example.deliveryapp.model.User
import com.example.deliveryapp.utils.UiState
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import java.util.Date
import javax.inject.Inject

class AppData @Inject constructor(
    val cartRepository: CartRepository,
    val foodRepository: FoodRepository,
    val historyRepository: HistoryRepository,
    val imagesRepository: ImagesRepository,
    val offerRepository: OfferRepository,
    val orderRepository: OrderRepository,
    val userRepository: UserRepository,
    val dbHelper: DbHelper
) : DataManager {
    override suspend fun getAllUsersLocal(): List<User?>? {
        return dbHelper.getAllUsersLocal()
    }

    override suspend fun insertUserLocal(user: User) {
        dbHelper.insertUserLocal(user)
    }

    override suspend fun insertAllUserLocal(users: List<User>) {
        dbHelper.insertAllUserLocal(users)
    }

    override suspend fun getUserByIdLocal(id: String): User? {
        return dbHelper.getUserByIdLocal(id)
    }

    override suspend fun deleteUserByIdLocal(id: String) {
        dbHelper.deleteUserByIdLocal(id)
    }

    override suspend fun updateUserLocal(user: User) {
        dbHelper.updateUserLocal(user)
    }

    override suspend fun getAllFoodsLocal(): List<Food?>? {
        return dbHelper.getAllFoodsLocal()
    }

    override suspend fun insertFoodLocal(food: Food) {
        dbHelper.insertFoodLocal(food)
    }

    override suspend fun insertAllFoodLocal(foods: List<Food>) {
        dbHelper.insertAllFoodLocal(foods)
    }

    override suspend fun getFoodByIdLocal(id: String): Food? {
        return dbHelper.getFoodByIdLocal(id)
    }

    override suspend fun deleteFoodByIdLocal(id: String) {
        dbHelper.deleteFoodByIdLocal(id)
    }

    override suspend fun updateFoodLocal(food: Food) {
        dbHelper.updateFoodLocal(food)
    }

    override suspend fun getHistoryLocal(uid: String): History? {
        return dbHelper.getHistoryLocal(uid)
    }

    override suspend fun updateHistoryLocal(history: History) {
        dbHelper.updateHistoryLocal(history)
    }

    override suspend fun getAllHistoryLocal(): List<History?>? {
        return dbHelper.getAllHistoryLocal()
    }

    override suspend fun getCart(userId: String): UiState<Cart?> {
        TODO("Not yet implemented")
    }

    override suspend fun addToCart(userId: String, foodId: String, quantity: Int): UiState<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun removeFromCart(userId: String, foodId: String): UiState<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun updateCartItemQuantity(
        userId: String,
        foodId: String,
        quantity: Int
    ): UiState<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun clearCart(userId: String): UiState<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun update(food: Food, foodId: String): UiState<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun addFood(food: Food, result: (UiState<String>) -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun addListFood(foods: List<Food>, result: (UiState<String>) -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun removeFood(foodId: String, result: (UiState<String>) -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun getFoodList(result: (UiState<List<Food>>) -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun getFoodById(foodId: String, result: (UiState<Food?>) -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun getSearchHistory(userId: String, result: (UiState<History>) -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun addSearchQuery(
        userId: String,
        query: Map<String, Date>,
        result: (UiState<String>) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun clearSearchHistory(userId: String, result: (UiState<String>) -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun updateHistory(
        userId: String,
        newQuery: String,
        timestamp: String,
        result: (UiState<String>) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun getImagesByFoodId(foodId: String, result: (UiState<ImageFood>) -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun addImage(
        foodId: String,
        newUrl: Map<String, String>,
        result: (UiState<String>) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun removeImage(
        foodId: String,
        urlImage: String,
        result: (UiState<String>) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun getRestaurantOffers(
        restaurantId: String,
        result: (UiState<Offer>) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun getFoodOffers(foodId: String, result: (UiState<Offer>) -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun getFreeShippingOffers(): Flow<Result<List<Offer>>> {
        TODO("Not yet implemented")
    }

    override suspend fun addOffer(offer: Offer, result: (UiState<String>) -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun updateOffer(
        offerId: String,
        offer: Offer,
        result: (UiState<String>) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteOffer(offerId: String, result: (UiState<String>) -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun updateStatusOrder(
        newStatus: String,
        userId: String,
        result: (UiState<String>) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun getOrder(userId: String, result: (UiState<Order>) -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun addOrder(order: Order, userId: String, result: (UiState<String>) -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun removeOrder(orderId: String, result: (UiState<String>) -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun getUserById(userId: String, result: (UiState<User>) -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllUser(result: (UiState<List<User>>) -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun loginUser(email: String, password: String): Flow<Result<User>> {
        TODO("Not yet implemented")
    }

    override suspend fun registerUser(email: String, password: String): Flow<Result<User>> {
        TODO("Not yet implemented")
    }

    override suspend fun logoutUser(userId: String): Flow<UiState<Unit>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(userId: String, result: (UiState<String>) -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(userId: String, user: User, result: (UiState<String>) -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun insertUser(user: User, result: (UiState<String>) -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun insertListUser(users: List<User>, result: (UiState<String>) -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun getUserByName(name: String, result: (UiState<User>) -> Unit) {
        TODO("Not yet implemented")
    }


}
