package com.example.deliveryapp.data.remote

import com.example.deliveryapp.model.Offer
import com.example.deliveryapp.utils.UiState
import kotlinx.coroutines.flow.Flow

interface OfferRepository {
    suspend fun getRestaurantOffers(restaurantId: String, result: (UiState<Offer>)->Unit)

    suspend fun getFoodOffers(foodId: String, result: (UiState<Offer>) -> Unit)

    suspend fun getFreeShippingOffers(): Flow<Result<List<Offer>>>

    suspend fun addOffer(offer: Offer, result: (UiState<String>) -> Unit)

    suspend fun updateOffer(offerId: String,offer: Offer, result: (UiState<String>) -> Unit)

    suspend fun deleteOffer(offerId: String, result: (UiState<String>) -> Unit)


}