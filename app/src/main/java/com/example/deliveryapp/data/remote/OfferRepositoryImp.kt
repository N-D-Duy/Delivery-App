package com.example.deliveryapp.data.remote

import com.example.deliveryapp.model.Food
import com.example.deliveryapp.model.History
import com.example.deliveryapp.model.ImageFood
import com.example.deliveryapp.model.Offer
import com.example.deliveryapp.model.Restaurant
import com.example.deliveryapp.utils.FirebaseCollections
import com.example.deliveryapp.utils.UiState
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class OfferRepositoryImp(val database:FirebaseFirestore): OfferRepository {
    //to do
    override suspend fun getRestaurantOffers(restaurantId: String, result: (UiState<Offer>)->Unit){
        var docRes = Restaurant()
        database.collection(FirebaseCollections.RESTAURANT).document(restaurantId)
            .get()
            .addOnSuccessListener {
                docRes = it.toObject(Restaurant::class.java)!!
            }
            .addOnFailureListener {exception ->
                result.invoke(UiState.Error("get res offer failed ${exception.message}"))
            }
        if(docRes.offerId != null){
            database.collection(FirebaseCollections.OFFER).document(docRes.offerId.toString())
                .get()
                .addOnSuccessListener {
                    val offerValue = it.toObject(Offer::class.java)!!
                    result.invoke(UiState.Success(offerValue))
                }
                .addOnFailureListener {exception ->
                    result.invoke(UiState.Error("get res offer failed ${exception.message}"))
                }
        }
    }

    override suspend fun getFoodOffers(foodId: String, result: (UiState<Offer>) -> Unit) {

        val docFood = database.collection(FirebaseCollections.FOOD).document(foodId)
            .get()
            .await()
            .toObject(Food::class.java)

        if (docFood?.offerId != null) {
            database.collection(FirebaseCollections.OFFER).document(docFood.offerId.toString())
                .get()
                .addOnSuccessListener {
                    val offerValue = it.toObject(Offer::class.java)!!
                    result.invoke(UiState.Success(offerValue))
                }
                .addOnFailureListener { exception ->
                    result.invoke(UiState.Error("get food offer failed ${exception.message}"))
                }
        }
    }

    override suspend fun getFreeShippingOffers(): Flow<Result<List<Offer>>> {
        return flow {

        }
    }

    override suspend fun addOffer(offer: Offer, result: (UiState<String>) -> Unit) {
        val offerRef = database.collection(FirebaseCollections.OFFER).document()
        offerRef.set(offer)
            .addOnSuccessListener {
                result.invoke(UiState.Success("add offer success"))
            }
            .addOnFailureListener { exception ->
                result.invoke(UiState.Error("add offer failed ${exception.message}"))
            }
    }

    override suspend fun updateOffer(offerId: String, offer: Offer, result: (UiState<String>) -> Unit) {
        val offerRef = database.collection(FirebaseCollections.OFFER)
            .document(offerId)

        val dataMap = mapOf(
            "discount_food" to offer.discountFood,
            "discount_restaurant" to offer.discountRestaurant,
            "free_ship" to offer.freeShip
        )
        offerRef.update(dataMap)
            .addOnSuccessListener {
            result.invoke(UiState.Success("update offer success"))
            }
            .addOnFailureListener { exception ->
            result.invoke(UiState.Error("update offer failed ${exception.message}"))
            }
    }

    override suspend fun deleteOffer(offerId: String, result: (UiState<String>) -> Unit) {
        val offerRef = database.collection(FirebaseCollections.OFFER)
            .document(offerId)

        offerRef.delete()
            .addOnSuccessListener {
                result.invoke(UiState.Success("delete offer success"))
            }
            .addOnFailureListener { exception ->
                result.invoke(UiState.Error("delete offer failed ${exception.message}"))
            }
    }

}