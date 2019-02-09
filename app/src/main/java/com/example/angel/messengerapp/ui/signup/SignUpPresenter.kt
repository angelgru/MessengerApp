package com.example.angel.messengerapp.ui.signup

import com.example.angel.messengerapp.data.local.AppPreferences

interface SignUpPresenter {

    var preferences: AppPreferences

    fun executeSignUp(username: String,
                      phoneNumber: String,
                      password: String)

}
