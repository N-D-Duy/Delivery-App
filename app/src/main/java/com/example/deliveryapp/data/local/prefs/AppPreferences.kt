package com.example.deliveryapp.data.local.prefs

import android.content.Context
import android.content.SharedPreferences
import com.example.deliveryapp.data.DataManager.LoggedInMode
import com.example.deliveryapp.utils.Constants
import javax.inject.Inject


class AppPreferences : Preferences{
    private val PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN"

    private val PREF_KEY_CURRENT_USER_EMAIL = "PREF_KEY_CURRENT_USER_EMAIL"

    private val PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID"

    private val PREF_KEY_CURRENT_USER_NAME = "PREF_KEY_CURRENT_USER_NAME"

    private val PREF_KEY_CURRENT_USER_PROFILE_PIC_URL = "PREF_KEY_CURRENT_USER_PROFILE_PIC_URL"

    private val PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE"

    private var mPrefs: SharedPreferences? = null

    @Inject
    fun appPreferencesHelper(context: Context, prefFileName: String?) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)
    }

    override fun getAccessToken(): String? {
        return mPrefs!!.getString(PREF_KEY_ACCESS_TOKEN, null)
    }

    override fun setAccessToken(accessToken: String?) {
        mPrefs!!.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply()
    }

    override fun getCurrentUserEmail(): String? {
        return mPrefs!!.getString(PREF_KEY_CURRENT_USER_EMAIL, null)
    }

    override fun setCurrentUserEmail(email: String?) {
        mPrefs!!.edit().putString(PREF_KEY_CURRENT_USER_EMAIL, email).apply()
    }

    override fun getCurrentUserId(): Long? {
        val userId = mPrefs!!.getLong(PREF_KEY_CURRENT_USER_ID, Constants().NULL_INDEX)
        return if (userId == Constants().NULL_INDEX) null else userId
    }

    override fun setCurrentUserId(userId: Long?) {
        val id = userId ?: Constants().NULL_INDEX
        mPrefs!!.edit().putLong(PREF_KEY_CURRENT_USER_ID, id).apply()
    }

    override fun getCurrentUserLoggedInMode(): Int {
        return mPrefs!!.getInt(
            PREF_KEY_USER_LOGGED_IN_MODE,
            LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.type
        )
    }

    override fun setCurrentUserLoggedInMode(mode: LoggedInMode?) {
        mPrefs!!.edit().putInt(PREF_KEY_USER_LOGGED_IN_MODE, mode!!.type).apply()
    }
    override fun getCurrentUserName(): String? {
        return mPrefs!!.getString(PREF_KEY_CURRENT_USER_NAME, null)
    }

    override fun setCurrentUserName(userName: String?) {
        mPrefs!!.edit().putString(PREF_KEY_CURRENT_USER_NAME, userName).apply()
    }

    override fun getCurrentUserProfilePicUrl(): String? {
        return mPrefs!!.getString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, null)
    }

    override fun setCurrentUserProfilePicUrl(profilePicUrl: String?) {
        mPrefs!!.edit().putString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, profilePicUrl).apply()
    }
}