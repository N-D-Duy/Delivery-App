package com.example.deliveryapp.utils.validate

class ValidUserInfoImp: ValidUserInfo {
    override fun isEmailValid(email: String): Boolean {
        val ePattern =
            "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$"
        val regex = Regex(ePattern)
        return regex.matches(email)
    }

    override fun isPasswordValid(password: String): Boolean {
        return password.length > 4
    }
}