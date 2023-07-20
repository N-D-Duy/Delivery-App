package com.example.deliveryapp.data.repository

import com.example.deliveryapp.model.Offer
import com.example.deliveryapp.util.UiState
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OfferRepositoryImp(val database:FirebaseFirestore): OfferRepository {
    //to do
    override fun getRestaurantOffers(restaurantId: String): Flow<Result<List<Offer>>> {
        return flow {

        }
    }

    override fun getFoodOffers(foodId: String): Flow<Result<List<Offer>>> {
        return flow {

        }
    }

    override fun getFreeShippingOffers(): Flow<Result<List<Offer>>> {
        return flow {

        }
    }

    override fun addOffer(offer: Offer): Flow<UiState<Unit>> {
        return flow {

        }
    }

    override fun updateOffer(offer: Offer): Flow<UiState<Unit>> {
        return flow {

        }
    }

    override fun deleteOffer(offerId: String): Flow<UiState<Unit>> {
        return flow {

        }
    }

}