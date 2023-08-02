package com.example.deliveryapp.data.di
import com.example.deliveryapp.data.remote.CartRepository
import com.example.deliveryapp.data.remote.CartRepositoryImp
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
    fun provideNoteRepository(
        database: FirebaseFirestore
    ): CartRepository{
        return CartRepositoryImp(database)
    }
}