package com.example.deliveryapp.data.remote

import com.example.deliveryapp.model.ImageFood
import com.example.deliveryapp.utils.FirebaseCollections
import com.example.deliveryapp.utils.UiState
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class ImagesRepositoryImp(val database: FirebaseFirestore) : ImagesRepository {
    //fetch data
    override fun getImagesByFoodId(foodId: String): Flow<Result<ImageFood>> = callbackFlow {
        //tham chieu den document image can
        val imageRef = database.collection(FirebaseCollections.IMAGE).document(foodId)
            .get()
            .addOnSuccessListener {
                channel.trySend(Result.success(it.toObject(ImageFood::class.java)!!))
                channel.close()
            }
            .addOnFailureListener {e->
                channel.trySend(Result.failure(Throwable("get image failed: ${e.message}")))
                channel.close()
            }
        awaitClose()
    }

    override fun addImage(foodId: String, newUrl: Map<String, String>): Flow<UiState<Unit>> = callbackFlow {
        //tham chieu den collection images
        val imageRef = database.collection(FirebaseCollections.IMAGE)
            .document(foodId)
        imageRef.update("url", newUrl)
            .addOnSuccessListener {
                channel.trySend(UiState.Success(Unit))
                channel.close()
            }.addOnFailureListener {e->
                channel.trySend(UiState.Error("add image failed: ${e.message}"))
                channel.close()
            }
        awaitClose()
    }

    override fun removeImage(foodId: String, urlImage:String): Flow<UiState<Unit>> = callbackFlow{
        val updateMap = mapOf<String, Any>(
            "url.${urlImage}" to FieldValue.delete()
        )
        database.collection(FirebaseCollections.IMAGE).document(foodId)
            .update(updateMap)
            .addOnSuccessListener {
                channel.trySend(UiState.Success(Unit))
                channel.close()
            }.addOnFailureListener {e->
                channel.trySend(UiState.Error("remove image failed: ${e.message}"))
                channel.close()
            }
        awaitClose()
    }
}