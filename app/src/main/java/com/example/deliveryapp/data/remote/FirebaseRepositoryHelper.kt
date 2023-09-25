package com.example.deliveryapp.data.remote

import com.example.deliveryapp.model.Cart
import com.example.deliveryapp.model.Food
import com.example.deliveryapp.model.History
import com.example.deliveryapp.model.ImageFood
import com.example.deliveryapp.model.Offer
import com.example.deliveryapp.model.Order
import com.example.deliveryapp.model.User
import com.example.deliveryapp.utils.FirebaseCollections
import com.example.deliveryapp.utils.UiState
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpException
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import java.io.IOException
import java.util.Date

class FirebaseRepositoryHelper(val database: FirebaseFirestore) : FirebaseRepository {
    override fun getCart(userId: String): Flow<UiState<Cart?>> = flow {
        emit(UiState.Loading)
        try {
            val cartSnapshot = database.collection(FirebaseCollections.CART)
                .whereEqualTo("user_id", userId)
                .get()
                .await()

            if (!cartSnapshot.isEmpty) {
                emit(
                    UiState.Success(cartSnapshot.documents[0].toObject(Cart::class.java))
                )
            }
        } catch (e: HttpException) {
            emit(UiState.Error(message = e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(
                UiState.Error(message = e.localizedMessage ?: "Check Internet Connection")
            )
        } catch (e: Exception) {
            emit(UiState.Error(message = e.localizedMessage ?: ""))
        }
    }.flowOn(Dispatchers.IO)

    override fun addToCart(userId: String, foodId: String, quantity: Int): Flow<UiState<String>> =
        flow {
            emit(UiState.Loading)
            try {
                val cartSnapshot = database.collection(FirebaseCollections.CART)
                    .whereEqualTo("user_id", userId)
                    .get()
                    .await()

                if (!cartSnapshot.isEmpty) {
                    val cart = cartSnapshot.documents[0].toObject(Cart::class.java)
                    val newFood: MutableMap<String, Int> = mutableMapOf(
                        foodId to quantity
                    )
                    val cartId = cart!!.cartId!!
                    database.collection(FirebaseCollections.CART).document(cartId)
                        .update("food_list", newFood)
                    emit(UiState.Success("add to cart successful"))
                }
            } catch (e: HttpException) {
                emit(UiState.Error(message = e.localizedMessage ?: "Unknown Error"))
            } catch (e: IOException) {
                emit(
                    UiState.Error(message = e.localizedMessage ?: "Check Internet Connection")
                )
            } catch (e: Exception) {
                emit(UiState.Error(message = e.localizedMessage ?: ""))
            }
        }.flowOn(Dispatchers.IO)

    override fun removeFromCart(userId: String, foodId: String): Flow<UiState<String>> = flow {
        emit(UiState.Loading)
        try {
            val cartSnapshot = database.collection(FirebaseCollections.CART)
                .whereEqualTo("user_id", userId)
                .get()
                .await()

            if (!cartSnapshot.isEmpty) {
                val cart = cartSnapshot.documents[0].toObject(Cart::class.java)
                val cartId = cart!!.cartId!!
                val updateMap = mapOf<String, Any>(
                    "food_list.$foodId" to FieldValue.delete()
                )
                database.collection(FirebaseCollections.CART).document(cartId)
                    .update(updateMap)
                emit(UiState.Success("remove food from cart successful"))
            }
        } catch (e: HttpException) {
            emit(UiState.Error(message = e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(
                UiState.Error(message = e.localizedMessage ?: "Check Internet Connection")
            )
        } catch (e: Exception) {
            emit(UiState.Error(message = e.localizedMessage ?: ""))
        }
    }.flowOn(Dispatchers.IO)

    override fun updateCartItemQuantity(
        userId: String,
        foodId: String,
        quantity: Int
    ): Flow<UiState<String>> = flow {
        emit(UiState.Loading)
        try {

        } catch (e: HttpException) {
            emit(UiState.Error(message = e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(
                UiState.Error(message = e.localizedMessage ?: "Check Internet Connection")
            )
        } catch (e: Exception) {
            emit(UiState.Error(message = e.localizedMessage ?: ""))
        }
    }.flowOn(Dispatchers.IO)

    override fun clearCart(userId: String): Flow<UiState<String>> = flow {
        emit(UiState.Loading)
        try {
            val cartSnapshot = database.collection(FirebaseCollections.CART)
                .whereEqualTo("user_id", userId)
                .get()
                .await()
            //ref to food list and update
            if (!cartSnapshot.isEmpty) {
                val cart = cartSnapshot.documents[0].toObject(Cart::class.java)
                val cartId = cart!!.cartId!!
                val updateMap = mapOf<String, Any>(
                    "food_list" to emptyMap<String, Int>()
                )
                database.collection(FirebaseCollections.CART).document(cartId)
                    .update(updateMap)
                emit(UiState.Success("clear cart successful"))
            }
        } catch (e: HttpException) {
            emit(UiState.Error(message = e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(
                UiState.Error(message = e.localizedMessage ?: "Check Internet Connection")
            )
        } catch (e: Exception) {
            emit(UiState.Error(message = e.localizedMessage ?: ""))
        }
    }.flowOn(Dispatchers.IO)

    override fun updateFood(food: Food, foodId: String): Flow<UiState<String>> = flow {
        emit(UiState.Loading)
        try {
            val foodSnapshot = database.collection(FirebaseCollections.FOOD)
                .document(foodId)
                .get()
                .await()
            val updateFields = mutableMapOf<String, Any>()
            //check what kinda data that user wanna update

            if (food.cartId != null) {
                updateFields["cart_id"] = food.cartId
            }
            if (food.available != null) {
                updateFields["available"] = food.available
            }
            if (food.foodName != null) {
                updateFields["food_name"] = food.foodName
            }
            if (food.category != null) {
                updateFields["category"] = food.category
            }
            if (food.description != null) {
                updateFields["description"] = food.description
            }
            if (food.nutrition != null) {
                updateFields["nutrition"] = food.nutrition
            }
            if (food.offerId != null) {
                updateFields["offer_id"] = food.offerId
            }
            if (food.price != null) {
                updateFields["price"] = food.price
            }
            if (food.rate != null) {
                updateFields["rate"] = food.rate
            }
            if (food.restaurantId != null) {
                updateFields["restaurant_id"] = food.restaurantId
            }

            if (foodSnapshot.exists()) {
                //if found-> update
                database.collection(FirebaseCollections.FOOD)
                    .document(foodId)
                    .update(updateFields).await()
                emit(UiState.Success("update food success"))
            }
        } catch (e: HttpException) {
            emit(UiState.Error(message = e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(
                UiState.Error(message = e.localizedMessage ?: "Check Internet Connection")
            )
        } catch (e: Exception) {
            emit(UiState.Error(message = e.localizedMessage ?: ""))
        }
    }.flowOn(Dispatchers.IO)

    override fun addFood(food: Food): Flow<UiState<String>> = flow {
        emit(UiState.Loading)
        try {
            val foodRef = database.collection(FirebaseCollections.FOOD).document()
            foodRef.set(food).await()
        } catch (e: HttpException) {
            emit(UiState.Error(message = e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(
                UiState.Error(message = e.localizedMessage ?: "Check Internet Connection")
            )
        } catch (e: Exception) {
            emit(UiState.Error(message = e.localizedMessage ?: ""))
        }
    }.flowOn(Dispatchers.IO)

    override fun addListFood(foods: List<Food>): Flow<UiState<String>> = flow {
        emit(UiState.Loading)
        try {

        } catch (e: HttpException) {
            emit(UiState.Error(message = e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(
                UiState.Error(message = e.localizedMessage ?: "Check Internet Connection")
            )
        } catch (e: Exception) {
            emit(UiState.Error(message = e.localizedMessage ?: ""))
        }
    }.flowOn(Dispatchers.IO)

    override fun removeFood(foodId: String): Flow<UiState<String>> = flow {
        emit(UiState.Loading)
        try {
            val foodRef = database.collection(FirebaseCollections.FOOD).document(foodId)
            foodRef.delete().await()
        } catch (e: HttpException) {
            emit(UiState.Error(message = e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(
                UiState.Error(message = e.localizedMessage ?: "Check Internet Connection")
            )
        } catch (e: Exception) {
            emit(UiState.Error(message = e.localizedMessage ?: ""))
        }
    }.flowOn(Dispatchers.IO)

    override fun getFoodList(): Flow<UiState<List<Food?>?>> = flow {
        emit(UiState.Loading)
        try {
            val querySnapshot = database.collection(FirebaseCollections.FOOD)
                .get()
                .await()

            if (!querySnapshot.isEmpty) {
                val list = arrayListOf<Food>()
                for (food in querySnapshot) {
                    list.add(food.toObject(Food::class.java))
                }
                emit(UiState.Success(list))
            }
        } catch (e: HttpException) {
            emit(UiState.Error(message = e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(
                UiState.Error(message = e.localizedMessage ?: "Check Internet Connection")
            )
        } catch (e: Exception) {
            emit(UiState.Error(message = e.localizedMessage ?: ""))
        }
    }.flowOn(Dispatchers.IO)

    override fun getFoodById(foodId: String): Flow<UiState<Food?>> = flow {
        emit(UiState.Loading)
        try {
            val foodRef = database.collection(FirebaseCollections.FOOD).document(foodId)
                .get()
                .await()
            if (foodRef.exists()) {
                val food = foodRef.toObject(Food::class.java)
                emit(UiState.Success(food))
            }
        } catch (e: HttpException) {
            emit(UiState.Error(message = e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(
                UiState.Error(message = e.localizedMessage ?: "Check Internet Connection")
            )
        } catch (e: Exception) {
            emit(UiState.Error(message = e.localizedMessage ?: ""))
        }
    }.flowOn(Dispatchers.IO)

    override fun getImagesByFoodId(foodId: String): Flow<UiState<ImageFood?>> = flow {
        emit(UiState.Loading)
        try {
            val snapshot = database.collection(FirebaseCollections.IMAGE).document(foodId)
                .get()
                .await()
            if (snapshot.exists()) {
                val image = snapshot.toObject(ImageFood::class.java)
                emit(UiState.Success(image))
            }
        } catch (e: HttpException) {
            emit(UiState.Error(message = e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(
                UiState.Error(message = e.localizedMessage ?: "Check Internet Connection")
            )
        } catch (e: Exception) {
            emit(UiState.Error(message = e.localizedMessage ?: ""))
        }
    }.flowOn(Dispatchers.IO)

    override fun addImage(foodId: String, newUrl: String): Flow<UiState<String>> =
        flow {
            emit(UiState.Loading)
            try {
                //ref
                val imageRef = database.collection(FirebaseCollections.IMAGE)
                val imageFoodQuery = imageRef.document(foodId)
                    .get()
                    .await()
                //copy map cu
                val currentUrls =
                    imageFoodQuery.get("url") as? MutableMap<String, String> ?: hashMapOf()

                //add cap gia tri k-v moi
                val key = System.currentTimeMillis().toString()
                currentUrls[key] = newUrl
                //update firebase
                imageRef.document(foodId).update("url", currentUrls).await()
            } catch (e: HttpException) {
                emit(UiState.Error(message = e.localizedMessage ?: "Unknown Error"))
            } catch (e: IOException) {
                emit(
                    UiState.Error(message = e.localizedMessage ?: "Check Internet Connection")
                )
            } catch (e: Exception) {
                emit(UiState.Error(message = e.localizedMessage ?: ""))
            }
        }.flowOn(Dispatchers.IO)

    override fun removeImage(foodId: String, urlImage: String): Flow<UiState<String>> = flow {
        emit(UiState.Loading)
        try {
            val updateMap = hashMapOf<String, Any>(
                "url.${urlImage}" to FieldValue.delete()
            )
            database.collection(FirebaseCollections.IMAGE).document(foodId)
                .update(updateMap)
                .await()
        } catch (e: HttpException) {
            emit(UiState.Error(message = e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(
                UiState.Error(message = e.localizedMessage ?: "Check Internet Connection")
            )
        } catch (e: Exception) {
            emit(UiState.Error(message = e.localizedMessage ?: ""))
        }
    }.flowOn(Dispatchers.IO)

    override fun getSearchHistory(userId: String): Flow<UiState<History?>> = flow {
        emit(UiState.Loading)
        try {
            val historyRef = database.collection(FirebaseCollections.HISTORY)
                .document(userId)
                .get()
                .await()
            if (historyRef.exists()) {
                val history = historyRef.toObject(History::class.java)
                emit(UiState.Success(history))
            }
        } catch (e: HttpException) {
            emit(UiState.Error(message = e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(
                UiState.Error(message = e.localizedMessage ?: "Check Internet Connection")
            )
        } catch (e: Exception) {
            emit(UiState.Error(message = e.localizedMessage ?: ""))
        }
    }.flowOn(Dispatchers.IO)

    override fun addSearchQuery(userId: String, query: MutableMap<String, Date>): Flow<UiState<String>> =
        flow {
            emit(UiState.Loading)
            try {
                //todo
            } catch (e: HttpException) {
                emit(UiState.Error(message = e.localizedMessage ?: "Unknown Error"))
            } catch (e: IOException) {
                emit(
                    UiState.Error(message = e.localizedMessage ?: "Check Internet Connection")
                )
            } catch (e: Exception) {
                emit(UiState.Error(message = e.localizedMessage ?: ""))
            }
        }.flowOn(Dispatchers.IO)

    override fun clearSearchHistory(userId: String): Flow<UiState<String>> = flow {
        emit(UiState.Loading)
        try {
            val historyRef = database.collection(FirebaseCollections.HISTORY)
                .document(userId)
            historyRef.delete().await()

            emit(UiState.Success("clear history successful"))
        } catch (e: HttpException) {
            emit(UiState.Error(message = e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(
                UiState.Error(message = e.localizedMessage ?: "Check Internet Connection")
            )
        } catch (e: Exception) {
            emit(UiState.Error(message = e.localizedMessage ?: ""))
        }
    }.flowOn(Dispatchers.IO)

    override fun updateHistory(
        userId: String,
        newQuery: String,
        timestamp: Date
    ): Flow<UiState<String>> = flow {
        emit(UiState.Loading)
        try {
            val historyRef = database.collection(FirebaseCollections.HISTORY)
                .document(userId)
            val historySnapshot = historyRef.get().await()
            if(historySnapshot.exists()){
                val historyData = historySnapshot.toObject(History::class.java)
                // Update the queries map with the new query and timestamp
                historyData?.query?.put(newQuery, timestamp)

                // Update the "queries" field in the Fire-store document
                historyRef.set(historyData as History)

                emit(UiState.Success("update history successful"))
            }
        } catch (e: HttpException) {
            emit(UiState.Error(message = e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(
                UiState.Error(message = e.localizedMessage ?: "Check Internet Connection")
            )
        } catch (e: Exception) {
            emit(UiState.Error(message = e.localizedMessage ?: ""))
        }
    }.flowOn(Dispatchers.IO)

    override fun getRestaurantOffers(restaurantId: String): Flow<UiState<Offer>> = flow {
        emit(UiState.Loading)
        try {

        } catch (e: HttpException) {
            emit(UiState.Error(message = e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(
                UiState.Error(message = e.localizedMessage ?: "Check Internet Connection")
            )
        } catch (e: Exception) {
            emit(UiState.Error(message = e.localizedMessage ?: ""))
        }
    }.flowOn(Dispatchers.IO)

    override fun getFoodOffers(foodId: String): Flow<UiState<Offer>> = flow {
        emit(UiState.Loading)
        try {

        } catch (e: HttpException) {
            emit(UiState.Error(message = e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(
                UiState.Error(message = e.localizedMessage ?: "Check Internet Connection")
            )
        } catch (e: Exception) {
            emit(UiState.Error(message = e.localizedMessage ?: ""))
        }
    }.flowOn(Dispatchers.IO)

    override fun getFreeShippingOffers(): Flow<UiState<List<Offer>>> = flow {
        emit(UiState.Loading)
        try {

        } catch (e: HttpException) {
            emit(UiState.Error(message = e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(
                UiState.Error(message = e.localizedMessage ?: "Check Internet Connection")
            )
        } catch (e: Exception) {
            emit(UiState.Error(message = e.localizedMessage ?: ""))
        }
    }.flowOn(Dispatchers.IO)

    override fun addOffer(offer: Offer): Flow<UiState<String>> = flow {
        emit(UiState.Loading)
        try {

        } catch (e: HttpException) {
            emit(UiState.Error(message = e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(
                UiState.Error(message = e.localizedMessage ?: "Check Internet Connection")
            )
        } catch (e: Exception) {
            emit(UiState.Error(message = e.localizedMessage ?: ""))
        }
    }.flowOn(Dispatchers.IO)

    override fun updateOffer(offerId: String, offer: Offer): Flow<UiState<String>> = flow {
        emit(UiState.Loading)
        try {

        } catch (e: HttpException) {
            emit(UiState.Error(message = e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(
                UiState.Error(message = e.localizedMessage ?: "Check Internet Connection")
            )
        } catch (e: Exception) {
            emit(UiState.Error(message = e.localizedMessage ?: ""))
        }
    }.flowOn(Dispatchers.IO)

    override fun deleteOffer(offerId: String): Flow<UiState<String>> = flow {
        emit(UiState.Loading)
        try {

        } catch (e: HttpException) {
            emit(UiState.Error(message = e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(
                UiState.Error(message = e.localizedMessage ?: "Check Internet Connection")
            )
        } catch (e: Exception) {
            emit(UiState.Error(message = e.localizedMessage ?: ""))
        }
    }.flowOn(Dispatchers.IO)

    override fun updateStatusOrder(newStatus: String, userId: String): Flow<UiState<String>> =
        flow {
            emit(UiState.Loading)
            try {
                val orderRef = database.collection(FirebaseCollections.ORDER)
                    .document(userId)

                val dataMap = mapOf(
                    "status" to newStatus
                )

                orderRef.update(dataMap).await()

                emit(UiState.Success("update order successful"))
            } catch (e: HttpException) {
                emit(UiState.Error(message = e.localizedMessage ?: "Unknown Error"))
            } catch (e: IOException) {
                emit(
                    UiState.Error(message = e.localizedMessage ?: "Check Internet Connection")
                )
            } catch (e: Exception) {
                emit(UiState.Error(message = e.localizedMessage ?: ""))
            }
        }.flowOn(Dispatchers.IO)

    override fun getOrder(userId: String): Flow<UiState<Order?>> = flow {
        emit(UiState.Loading)
        try {
            val orderSnapshot = database.collection(FirebaseCollections.USER).document(userId)
                .get()
                .await()

            if(orderSnapshot.exists()){
                val userData = orderSnapshot.toObject(Order::class.java)
                emit((UiState.Success(userData)))
            }

        } catch (e: HttpException) {
            emit(UiState.Error(message = e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(
                UiState.Error(message = e.localizedMessage ?: "Check Internet Connection")
            )
        } catch (e: Exception) {
            emit(UiState.Error(message = e.localizedMessage ?: ""))
        }
    }.flowOn(Dispatchers.IO)

    override fun addOrder(order: Order, userId: String): Flow<UiState<String>> = flow {
        emit(UiState.Loading)
        try {
            val orderRef = database.collection(FirebaseCollections.ORDER).document()
            val orderSnapshot = orderRef.get().await()
            if(orderSnapshot.exists()){
                orderRef.set(order).await()
                emit(UiState.Success("add order successful"))
            }
        } catch (e: HttpException) {
            emit(UiState.Error(message = e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(
                UiState.Error(message = e.localizedMessage ?: "Check Internet Connection")
            )
        } catch (e: Exception) {
            emit(UiState.Error(message = e.localizedMessage ?: ""))
        }
    }.flowOn(Dispatchers.IO)

    override fun removeOrder(orderId: String): Flow<UiState<String>> = flow {
        emit(UiState.Loading)
        try {
            val orderRef = database.collection(FirebaseCollections.ORDER)
                .document(orderId)

            orderRef.delete().await()
            emit(UiState.Success("remove order successful"))
        } catch (e: HttpException) {
            emit(UiState.Error(message = e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(
                UiState.Error(message = e.localizedMessage ?: "Check Internet Connection")
            )
        } catch (e: Exception) {
            emit(UiState.Error(message = e.localizedMessage ?: ""))
        }
    }.flowOn(Dispatchers.IO)

    override fun getUserById(userId: String): Flow<UiState<User?>> = flow {
        emit(UiState.Loading)
        try {
            val userSnapshot = database.collection(FirebaseCollections.USER).document(userId)
                .get()
                .await()
            if(userSnapshot.exists()){
                val userData = userSnapshot.toObject(User::class.java)
                emit(UiState.Success(userData))
            }
        } catch (e: HttpException) {
            emit(UiState.Error(message = e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(
                UiState.Error(message = e.localizedMessage ?: "Check Internet Connection")
            )
        } catch (e: Exception) {
            emit(UiState.Error(message = e.localizedMessage ?: ""))
        }
    }.flowOn(Dispatchers.IO)

    override fun getAllUser(): Flow<UiState<List<User?>?>> = flow {
        emit(UiState.Loading)
        try {

        } catch (e: HttpException) {
            emit(UiState.Error(message = e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(
                UiState.Error(message = e.localizedMessage ?: "Check Internet Connection")
            )
        } catch (e: Exception) {
            emit(UiState.Error(message = e.localizedMessage ?: ""))
        }
    }.flowOn(Dispatchers.IO)

    override fun loginUser(email: String, password: String): Flow<UiState<User>> = flow {
        emit(UiState.Loading)
        try {

        } catch (e: HttpException) {
            emit(UiState.Error(message = e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(
                UiState.Error(message = e.localizedMessage ?: "Check Internet Connection")
            )
        } catch (e: Exception) {
            emit(UiState.Error(message = e.localizedMessage ?: ""))
        }
    }.flowOn(Dispatchers.IO)

    override fun registerUser(email: String, password: String): Flow<UiState<User>> = flow {
        emit(UiState.Loading)
        try {

        } catch (e: HttpException) {
            emit(UiState.Error(message = e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(
                UiState.Error(message = e.localizedMessage ?: "Check Internet Connection")
            )
        } catch (e: Exception) {
            emit(UiState.Error(message = e.localizedMessage ?: ""))
        }
    }.flowOn(Dispatchers.IO)

    override fun logoutUser(userId: String): Flow<UiState<String>> = flow {
        emit(UiState.Loading)
        try {

        } catch (e: HttpException) {
            emit(UiState.Error(message = e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(
                UiState.Error(message = e.localizedMessage ?: "Check Internet Connection")
            )
        } catch (e: Exception) {
            emit(UiState.Error(message = e.localizedMessage ?: ""))
        }
    }.flowOn(Dispatchers.IO)

    override fun deleteUser(userId: String): Flow<UiState<String>> = flow {
        emit(UiState.Loading)
        try {
            val userRef = database.collection(FirebaseCollections.USER)
                .document(userId)

            userRef.delete().await()
            emit(UiState.Success("delete user successful"))
        } catch (e: HttpException) {
            emit(UiState.Error(message = e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(
                UiState.Error(message = e.localizedMessage ?: "Check Internet Connection")
            )
        } catch (e: Exception) {
            emit(UiState.Error(message = e.localizedMessage ?: ""))
        }
    }.flowOn(Dispatchers.IO)

    override fun updateUser(userId: String, user: User): Flow<UiState<String>> = flow {
        emit(UiState.Loading)
        try {
            val userRef = database.collection(FirebaseCollections.USER)
                .document(userId)

            val dataMap = mapOf(
                "address" to user.address,
                "avatar_url" to user.avatarUrl,
                "cart_id" to user.cartId,
                "email" to user.email,
                "phone" to user.phone
            )

            userRef.update(dataMap).await()

            emit(UiState.Success("update user successful"))
        } catch (e: HttpException) {
            emit(UiState.Error(message = e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(
                UiState.Error(message = e.localizedMessage ?: "Check Internet Connection")
            )
        } catch (e: Exception) {
            emit(UiState.Error(message = e.localizedMessage ?: ""))
        }
    }.flowOn(Dispatchers.IO)

    override fun insertUser(user: User): Flow<UiState<String>> = flow {
        emit(UiState.Loading)
        try {

        } catch (e: HttpException) {
            emit(UiState.Error(message = e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(
                UiState.Error(message = e.localizedMessage ?: "Check Internet Connection")
            )
        } catch (e: Exception) {
            emit(UiState.Error(message = e.localizedMessage ?: ""))
        }
    }.flowOn(Dispatchers.IO)

    override fun insertListUser(users: List<User>): Flow<UiState<String>> = flow {
        emit(UiState.Loading)
        try {

        } catch (e: HttpException) {
            emit(UiState.Error(message = e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(
                UiState.Error(message = e.localizedMessage ?: "Check Internet Connection")
            )
        } catch (e: Exception) {
            emit(UiState.Error(message = e.localizedMessage ?: ""))
        }
    }.flowOn(Dispatchers.IO)

    override fun getUserByName(name: String): Flow<UiState<User?>> = flow {
        emit(UiState.Loading)
        try {

        } catch (e: HttpException) {
            emit(UiState.Error(message = e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(
                UiState.Error(message = e.localizedMessage ?: "Check Internet Connection")
            )
        } catch (e: Exception) {
            emit(UiState.Error(message = e.localizedMessage ?: ""))
        }
    }.flowOn(Dispatchers.IO)
}