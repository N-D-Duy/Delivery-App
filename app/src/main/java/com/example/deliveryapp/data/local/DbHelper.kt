package com.example.deliveryapp.data.local

import android.database.Observable
import com.example.deliveryapp.model.User

public interface DbHelper {
    fun getAllUsers(): Observable<List<User>>

    fun insertUser(user: User): Observable<Boolean>

}
