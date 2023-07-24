package com.example.deliveryapp.data.repository

import com.example.deliveryapp.model.Food
import com.example.deliveryapp.model.Offer
import com.example.deliveryapp.model.Restaurant
import com.example.deliveryapp.util.FirebaseCollections
import com.example.deliveryapp.util.UiState
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow

class OfferRepositoryImp(val database :FirebaseFirestore): OfferRepository {
    //to do
    override fun getRestaurantOffers(restaurantId: String): Flow<Result<Offer>> = callbackFlow {
        var docRes = Restaurant()
        database.collection(FirebaseCollections.RESTAURANT).document(restaurantId)
            .get()
            .addOnSuccessListener {
                docRes = it.toObject(Restaurant::class.java)!!
            }
            .addOnFailureListener {exception ->
                channel.trySend(Result.failure(Throwable("update failed ${exception.message}"))).isSuccess
                channel.close()
            }
        if(docRes.offerId != null){
            database.collection(FirebaseCollections.OFFER).document(docRes.offerId.toString())
                .get()
                .addOnSuccessListener {
                    val offerValue = it.toObject(Offer::class.java)!!
                    channel.trySend(Result.success(offerValue))
                    channel.close()
                }
                .addOnFailureListener {exception ->
                    channel.trySend(Result.failure(Throwable("update failed ${exception.message}"))).isSuccess
                    channel.close()
                }
        }
        awaitClose()
    }

    override fun getFoodOffers(foodId: String): Flow<Result<Offer>> = callbackFlow {
        var docFood = Food()
        database.collection(FirebaseCollections.FOOD).document(foodId)
            .get()
            .addOnSuccessListener {
                docFood = it.toObject(Food::class.java)!!
            }
            .addOnFailureListener {exception ->
                channel.trySend(Result.failure(Throwable("update failed ${exception.message}"))).isSuccess
                channel.close()
            }
        if(docFood.offerId != null){
            database.collection(FirebaseCollections.OFFER).document(docFood.offerId.toString())
                .get()
                .addOnSuccessListener {
                    val offerValue = it.toObject(Offer::class.java)!!
                    channel.trySend(Result.success(offerValue))
                    channel.close()
                }
                .addOnFailureListener {exception ->
                    channel.trySend(Result.failure(Throwable("update failed ${exception.message}"))).isSuccess
                    channel.close()
                }
        }
        awaitClose()
    }

    override fun getFreeShippingOffers(): Flow<Result<List<Offer>>> {
        return flow {

        }
    }

    override fun addOffer(offer: Offer): Flow<UiState<Unit>> = callbackFlow {
        val offerRef = database.collection(FirebaseCollections.OFFER).document()
        offerRef.set(offer)
            .addOnSuccessListener {
                channel.trySend(UiState.Success(Unit)).isSuccess
                channel.close()
            }
            .addOnFailureListener { exception ->
                channel.trySend(UiState.Error("update failed ${exception.message}")).isSuccess
                channel.close()
            }
        awaitClose()
    }

    override fun updateOffer(offer: Offer): Flow<UiState<Unit>> {
        return flow {

        }
    }

    override fun deleteOffer(offerId: String): Flow<UiState<Unit>> = callbackFlow  {
        val offerRef = database.collection(FirebaseCollections.OFFER).document(offerId)
        offerRef.delete()
            .addOnSuccessListener {
                channel.trySend(UiState.Success(Unit))
                channel.close()
            }
            .addOnFailureListener { e ->
                channel.trySend(UiState.Error("remove failed ${e.message}")).isSuccess
                channel.close()
            }
        awaitClose()
    }




}