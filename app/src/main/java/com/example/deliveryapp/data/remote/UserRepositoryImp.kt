package com.example.deliveryapp.data.remote

import com.example.deliveryapp.model.User
import com.example.deliveryapp.utils.UiState
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryImp(val database: FirebaseFirestore): UserRepository {
    //implement here and fetch data,...
    override fun getUser(userId: String): Flow<Result<User>> {
        return flow {

        }
    }

    override fun loginUser(email: String, password: String): Flow<Result<User>> {
        return flow {

        }
    }

    override fun registerUser(email: String, password: String): Flow<Result<User>> {
        return flow {

        }
    }

    override fun logoutUser(userId: String): Flow<UiState<Unit>> {
        return flow {

        }
    }

    override fun deleteUser(userId: String): Flow<UiState<Unit>> {
        return flow {

        }
    }

    override fun updateUser(userId: String, user: User): Flow<UiState<Unit>> {
        return flow {

        }
    }
}