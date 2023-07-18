package com.example.deliveryapp.dragger

import com.example.deliveryapp.repository.CartRepository
import com.example.deliveryapp.repository.CartRepositoryImp
import com.example.deliveryapp.repository.FoodRepository
import com.example.deliveryapp.repository.FoodRepositoryImp
import com.example.deliveryapp.repository.HistoryRepository
import com.example.deliveryapp.repository.HistoryRepositoryImp
import com.example.deliveryapp.repository.ImagesRepository
import com.example.deliveryapp.repository.ImagesRepositoryImp
import com.example.deliveryapp.repository.OfferRepository
import com.example.deliveryapp.repository.OfferRepositoryImp
import com.example.deliveryapp.repository.OrderRepository
import com.example.deliveryapp.repository.OrderRepositoryImp
import com.example.deliveryapp.repository.UserRepository
import com.example.deliveryapp.repository.UserRepositoryImp
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCartRepository(
        database: FirebaseFirestore
    ): CartRepository{
        return CartRepositoryImp(database)
    }

    fun provideFoodRepository(
        database: FirebaseFirestore
    ): FoodRepository {
        return FoodRepositoryImp(database)
    }

    fun provideHistoryRepository(
        database: FirebaseFirestore
    ): HistoryRepository{
        return HistoryRepositoryImp(database)
    }

    fun provideImageRepository(
        database: FirebaseFirestore
    ): ImagesRepository{
        return ImagesRepositoryImp(database)
    }

    fun provideOfferRepository(
        database: FirebaseFirestore
    ): OfferRepository{
        return OfferRepositoryImp(database)
    }

    fun provideOrderRepository(
        database: FirebaseFirestore
    ): OrderRepository{
        return OrderRepositoryImp(database)
    }

    fun provideUserRepository(
        database: FirebaseFirestore
    ): UserRepository{
        return UserRepositoryImp(database)
    }

}