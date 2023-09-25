package com.example.deliveryapp.domain

import com.example.deliveryapp.data.remote.FirebaseRepository
import com.example.deliveryapp.data.remote.repositories.CartRepository
import com.example.deliveryapp.data.remote.repositories.FoodRepository
import com.example.deliveryapp.data.remote.repositories.HistoryRepository
import com.example.deliveryapp.data.remote.repositories.ImagesRepository
import com.example.deliveryapp.data.remote.repositories.OfferRepository
import com.example.deliveryapp.data.remote.repositories.OrderRepository
import com.example.deliveryapp.data.remote.repositories.UserRepository

interface DataManager: FirebaseRepository {
    enum class LoggedInMode(val type: Int) {
        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_GOOGLE(1),
        LOGGED_IN_MODE_FB(2),
        LOGGED_IN_MODE_SERVER(3);

        private var mType = 0

        init {
            mType = type
        }
    }
    //others func
}