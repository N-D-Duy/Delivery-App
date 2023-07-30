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
    override fun getUserById(userId: String): Flow<Result<User>> = callbackFlow {
        database.collection(FirebaseCollections.USER).document(userId)
            .get()
            .addOnSuccessListener {
                val userData = it.toObject(User::class.java)!!
                channel.trySend(Result.success(userData))
                channel.close()
            }
            .addOnFailureListener { exception ->
                channel.trySend(Result.failure(Throwable("update failed ${exception.message}"))).isSuccess
                channel.close()
            }
        awaitClose()
    }

    override fun getAllUser(): Flow<List<User>> = flow{


    }

    override fun loginUser(email: String, password: String): Flow<Result<User>>  = callbackFlow{
        val userRef = database.collection(FirebaseCollections.USER).whereEqualTo("email", email)
        userRef.get()
            .addOnSuccessListener {
                if(!it.isEmpty){
                    val documentSnapshot = it.documents[0].toObject(User::class.java)!!
                }
            }
            .addOnFailureListener { exception ->
                channel.trySend(Result.failure(Throwable("update failed ${exception.message}"))).isSuccess
                channel.close()
            }
        awaitClose()
    }

    override fun registerUser(email: String, password: String): Flow<Result<User>> = callbackFlow {
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

    override fun logoutUser(userId: String): Flow<UiState<Unit>> = callbackFlow {
        val logoutRef = database.collection(FirebaseCollections.USER).document(userId)

    }

    override fun deleteUser(userId: String): Flow<UiState<Unit>> = callbackFlow {
        val userRef = database.collection(FirebaseCollections.USER)
            .document(userId)

        userRef.delete()
            .addOnSuccessListener {
                channel.trySend(UiState.Success(Unit)).isSuccess
                channel.close()
            }
            .addOnFailureListener { exception ->
                channel.trySend(UiState.Error("delete user failed ${exception.message}")).isSuccess
                channel.close()
            }
        awaitClose()
    }

    override fun updateUser(userId: String, user: User): Flow<UiState<Unit>> = callbackFlow {
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
                channel.trySend(UiState.Success(Unit)).isSuccess
                channel.close()
            }
            .addOnFailureListener { exception ->
                channel.trySend(UiState.Error("update user failed ${exception.message}")).isSuccess
                channel.close()
            }
        awaitClose()
    }

    override fun insertUser(user: User): Flow<UiState<Unit>> = flow{

    }

    override fun insertListUser(users: List<User>): Flow<UiState<Unit>> = flow{

    }

    override fun getUserByName(name: String): Flow<Result<User>> = flow{

    }
}