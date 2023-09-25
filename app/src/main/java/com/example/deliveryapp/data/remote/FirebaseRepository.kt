package com.example.deliveryapp.data.remote

import com.example.deliveryapp.data.remote.repositories.CartRepository
import com.example.deliveryapp.data.remote.repositories.FoodRepository
import com.example.deliveryapp.data.remote.repositories.HistoryRepository
import com.example.deliveryapp.data.remote.repositories.ImagesRepository
import com.example.deliveryapp.data.remote.repositories.OfferRepository
import com.example.deliveryapp.data.remote.repositories.OrderRepository
import com.example.deliveryapp.data.remote.repositories.UserRepository

interface FirebaseRepository : CartRepository, FoodRepository, ImagesRepository,
    HistoryRepository, OfferRepository, OrderRepository, UserRepository