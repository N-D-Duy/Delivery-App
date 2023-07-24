package com.example.deliveryapp.data.local.prefs

import com.example.deliveryapp.data.DataManager

interface Preferences {
    fun getAccessToken(): String?

    fun setAccessToken(accessToken: String?)

    fun getCurrentUserEmail(): String?

    fun setCurrentUserEmail(email: String?)

    fun getCurrentUserId(): Long?

    fun setCurrentUserId(userId: Long?)

    fun getCurrentUserLoggedInMode(): Int

    fun setCurrentUserLoggedInMode(mode: DataManager.LoggedInMode?)

    fun getCurrentUserName(): String?

    fun setCurrentUserName(userName: String?)

    fun getCurrentUserProfilePicUrl(): String?

    fun setCurrentUserProfilePicUrl(profilePicUrl: String?)
}