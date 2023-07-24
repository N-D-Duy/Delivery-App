package com.example.deliveryapp.utils.validate

interface ValidUserInfo {
    fun isEmailValid(email: String): Boolean
    fun isPasswordValid(password: String): Boolean
}