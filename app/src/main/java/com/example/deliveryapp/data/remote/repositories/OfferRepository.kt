package com.example.deliveryapp.data.remote.repositories

import com.example.deliveryapp.model.Offer
import com.example.deliveryapp.utils.UiState
import kotlinx.coroutines.flow.Flow

interface OfferRepository {
    fun getRestaurantOffers(restaurantId: String): Flow<UiState<Offer>>

    fun getFoodOffers(foodId: String): Flow<UiState<Offer>>

    fun getFreeShippingOffers(): Flow<UiState<List<Offer>>>

    fun addOffer(offer: Offer): Flow<UiState<String>>

    fun updateOffer(offerId: String,offer: Offer): Flow<UiState<String>>

    fun deleteOffer(offerId: String): Flow<UiState<String>>
}