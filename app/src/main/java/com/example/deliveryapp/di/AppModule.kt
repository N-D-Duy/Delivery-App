package com.example.deliveryapp.di

import android.app.Application
import android.content.Context
import com.example.deliveryapp.data.local.database.AppDatabase
import com.example.deliveryapp.data.local.database.AppDbHelper
import com.example.deliveryapp.data.local.database.DbHelper
import com.example.deliveryapp.data.remote.FirebaseRepository
import com.example.deliveryapp.data.remote.FirebaseRepositoryHelper
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {
   /* @Provides
    @Singleton
    fun provideCartRepository(
        database: FirebaseFirestore
    ): CartRepository {
        return CartRepositoryImp(database)
    }

    @Provides
    @Singleton
    fun provideFoodRepository(
        database: FirebaseFirestore
    ): FoodRepository {
        return FoodRepositoryImp(database)
    }

    @Provides
    @Singleton
    fun provideHistoryRepository(
        database: FirebaseFirestore
    ): HistoryRepository {
        return HistoryRepositoryImp(database)
    }

    @Provides
    @Singleton
    fun provideImagesRepository(
        database: FirebaseFirestore
    ): ImagesRepository {
        return ImagesRepositoryImp(database)
    }

    @Provides
    @Singleton
    fun provideOfferRepository(
        database: FirebaseFirestore
    ): OfferRepository {
        return OfferRepositoryImp(database)
    }

    @Provides
    @Singleton
    fun provideOrderRepository(
        database: FirebaseFirestore
    ): OrderRepository {
        return OrderRepositoryImp(database)
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        database: FirebaseFirestore
    ): UserRepository {
        return UserRepositoryImp(database)
    }*/

    @Provides
    @Singleton
    fun provideRemoteDatabase(): FirebaseFirestore{
        val firebaseApp: FirebaseApp = FirebaseApp.getInstance("default")
        return FirebaseFirestore.getInstance(firebaseApp)
    }

    @Provides
    @Singleton
    fun provideDbHelper(appDbHelper: AppDbHelper?): DbHelper? {
        return appDbHelper
    }

    @Provides
    @Singleton
    fun provideGson(): Gson? {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    @Provides
    fun providePreferenceName(): String {
        return "order_app_pref"
    }

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideLocalDatabase(@ApplicationContext context: Context): AppDatabase{
        return AppDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideFirebaseRepository(database: FirebaseFirestore): FirebaseRepository{
        return FirebaseRepositoryHelper(database)
    }
}