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
    override suspend fun getImagesByFoodId(foodId: String, result: (UiState<ImageFood>)->Unit){
        //tham chieu den document image can
        database.collection(FirebaseCollections.IMAGE).document(foodId)
            .get()
            .addOnSuccessListener {
                result.invoke(UiState.Success(it.toObject(ImageFood::class.java)!!))
            }
            .addOnFailureListener {e->
                result.invoke(UiState.Error("get image failed: ${e.message}"))
            }
    }

    override suspend fun addImage(foodId: String, newUrl: Map<String, String>, result: (UiState<String>)->Unit){
        //tham chieu den collection images
        val imageRef = database.collection(FirebaseCollections.IMAGE)
            .document(foodId)
        imageRef.update("url", newUrl)
            .addOnSuccessListener {
                result.invoke(UiState.Success("add image success"))
            }.addOnFailureListener {e->
                result.invoke(UiState.Error("add image failed: ${e.message}"))
            }
    }

    override suspend fun removeImage(foodId: String, urlImage:String, result: (UiState<String>) -> Unit){
        val updateMap = mapOf<String, Any>(
            "url.${urlImage}" to FieldValue.delete()
        )
        database.collection(FirebaseCollections.IMAGE).document(foodId)
            .update(updateMap)
            .addOnSuccessListener {
                result.invoke(UiState.Success("remove image success"))
            }.addOnFailureListener {e->
                result.invoke(UiState.Error("remove image failed: ${e.message}"))
            }
    }
}