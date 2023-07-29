package com.example.deliveryapp.data

import com.example.deliveryapp.model.Cart
import com.example.deliveryapp.model.Food
import com.example.deliveryapp.model.History
import com.example.deliveryapp.model.Offer
import com.example.deliveryapp.model.Order
import com.example.deliveryapp.model.Restaurant
import com.example.deliveryapp.model.User

class DataManagerHelper: DataManager {
    override fun getUsers(): List<User>? {
        TODO("Not yet implemented")
    }

    override fun getUserById(uid: String): User? {
        TODO("Not yet implemented")
    }

    override fun getUserByName(name: String): User? {
        TODO("Not yet implemented")
    }

    override fun getFoods(): List<Food>? {
        TODO("Not yet implemented")
    }

    override fun getFoodById(foodId: String): Food? {
        TODO("Not yet implemented")
    }

    override fun getFoodByName(name: String): Food? {
        TODO("Not yet implemented")
    }

    override fun getAllHistory(): List<History?>? {
        TODO("Not yet implemented")
    }

    override fun getHistoryForUser(uid: String): User? {
        TODO("Not yet implemented")
    }

    override fun getCartByUserId(uid: String): Cart? {
        TODO("Not yet implemented")
    }

    override fun getOfferForFood(foodId: String): Offer? {
        TODO("Not yet implemented")
    }

    override fun getOfferForRestaurant(resId: String): Restaurant? {
        TODO("Not yet implemented")
    }

    override fun getFreeShip() {
        TODO("Not yet implemented")
    }

    override fun getOrderByUserId(uid: String): Order? {
        TODO("Not yet implemented")
    }

    override fun insertFood(food: Food?) {
        TODO("Not yet implemented")
    }

    override fun insertFoods(foods: List<Food?>?) {
        TODO("Not yet implemented")
    }

    override fun updateFoods(vararg foods: Food?) {
        TODO("Not yet implemented")
    }

    override fun insertUser(user: User?) {
        TODO("Not yet implemented")
    }

    override fun insertUsers(users: List<User?>?) {
        TODO("Not yet implemented")
    }

    override fun updateUsers(vararg user: User?) {
        TODO("Not yet implemented")
    }

    override fun updateOrSetDefaultUser(history: History) {
        TODO("Not yet implemented")
    }

    override fun updateCart(userId: String, foodId: String, quantity: Int) {
        TODO("Not yet implemented")
    }

    override fun addFoodIntoCart(userId: String, foodId: String, quantity: Int) {
        TODO("Not yet implemented")
    }

    override fun updateImage() {
        TODO("Not yet implemented")
    }

    override fun updateOffer(offerId: String, offer: Offer) {
        TODO("Not yet implemented")
    }

    override fun addOffer(offer: Offer) {
        TODO("Not yet implemented")
    }

    override fun updateOrder(newStatus: String, userId: String) {
        TODO("Not yet implemented")
    }

    override fun addOrder(order: Order, userId: String) {
        TODO("Not yet implemented")
    }

    override fun deleteUser(userId: String?) {
        TODO("Not yet implemented")
    }

    override fun deleteImage() {
        TODO("Not yet implemented")
    }

    override fun deleteFood(foodId: String) {
        TODO("Not yet implemented")
    }

    override fun deleteHistory(uid: String) {
        TODO("Not yet implemented")
    }

    override fun deleteOffer(offerId: String) {
        TODO("Not yet implemented")
    }

    override fun deleteOrder(orderId: String) {
        TODO("Not yet implemented")
    }

    override fun deleteCart(uid: String) {
        TODO("Not yet implemented")
    }

    override fun login(mode: DataManager.LoggedInMode?) {
        TODO("Not yet implemented")
    }

    override fun logout() {
        TODO("Not yet implemented")
    }

    override fun register(): User {
        TODO("Not yet implemented")
    }

}