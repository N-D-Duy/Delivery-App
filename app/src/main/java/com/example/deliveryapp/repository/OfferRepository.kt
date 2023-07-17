package com.example.deliveryapp.repository

import com.example.deliveryapp.model.Offer
import com.example.deliveryapp.util.UiState
import kotlinx.coroutines.flow.Flow

interface OfferRepository {
    fun getRestaurantOffers(restaurantId: String): Flow<Result<List<Offer>>>

    fun getFoodOffers(foodId: String): Flow<Result<List<Offer>>>

    fun getFreeShippingOffers(): Flow<Result<List<Offer>>>

    fun addOffer(offer: Offer): Flow<UiState<Unit>>

    fun updateOffer(offer: Offer): Flow<UiState<Unit>>

    fun deleteOffer(offerId: String): Flow<UiState<Unit>>


}