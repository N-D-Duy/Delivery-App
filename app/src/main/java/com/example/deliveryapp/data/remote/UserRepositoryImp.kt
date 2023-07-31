package com.example.deliveryapp.data.remote

import com.example.deliveryapp.model.Food
import com.example.deliveryapp.model.User
import com.example.deliveryapp.utils.FirebaseCollections
import com.example.deliveryapp.utils.UiState
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class UserRepositoryImp(val database: FirebaseFirestore): UserRepository {
    //implement here and fetch data,...
    override suspend fun getUserById(userId: String, result: (UiState<User>)->Unit){
        database.collection(FirebaseCollections.USER).document(userId)
            .get()
            .addOnSuccessListener {
                val userData = it.toObject(User::class.java)!!
                result.invoke(UiState.Success(userData))
            }
            .addOnFailureListener { exception ->
                result.invoke(UiState.Error("update failed ${exception.message}"))
            }
    }

    override suspend fun getAllUser(result: (UiState<List<User>>) -> Unit){

    }

    override suspend fun loginUser(email: String, password: String): Flow<Result<User>> = flow{

    }

    override suspend fun registerUser(email: String, password: String): Flow<Result<User>> = callbackFlow {
        /*val registerRef = database.collection(FirebaseCollections.USER).document()
        val newUser = User(email,password)
        registerRef.set(newUser)
            .addOnSuccessListener {
                channel.trySend(Result.success(newUser))
                channel.close()
            }
            .addOnFailureListener { exception ->
                channel.trySend(Result.failure(Throwable("update failed ${exception.message}"))).isSuccess
                channel.close()
            }
        awaitClose()*/
    }

    override suspend fun logoutUser(userId: String): Flow<UiState<Unit>> = callbackFlow {
        val logoutRef = database.collection(FirebaseCollections.USER).document(userId)

    }

    override suspend fun deleteUser(userId: String, result: (UiState<String>) -> Unit){
        val userRef = database.collection(FirebaseCollections.USER)
            .document(userId)

        userRef.delete()
            .addOnSuccessListener {
                result.invoke(UiState.Success("delete user success"))
            }
            .addOnFailureListener { exception ->
                result.invoke(UiState.Error("delete user failed ${exception.message}"))
            }
    }

    override suspend fun updateUser(userId: String, user: User, result: (UiState<String>) -> Unit){
        val userRef = database.collection(FirebaseCollections.USER)
            .document(userId)

        val dataMap = mapOf(
            "address" to user.address,
            "avatar_url" to user.avatarUrl,
            "cart_id" to user.cartId,
            "email" to user.email,
            "phone" to user.phone
        )

        userRef.update(dataMap)
            .addOnSuccessListener {
                result.invoke(UiState.Success("update user success"))
            }
            .addOnFailureListener { exception ->
                result.invoke(UiState.Error("update user failed ${exception.message}"))
            }
    }

    override suspend fun insertUser(user: User, result: (UiState<String>) -> Unit){

    }

    override suspend fun insertListUser(users: List<User>, result: (UiState<String>) -> Unit){

    }

    override suspend fun getUserByName(name: String, result: (UiState<User>) -> Unit){

    }
}