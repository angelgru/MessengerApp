package com.example.angel.messengerapp.ui.signup

import com.example.angel.messengerapp.ui.auth.AuthInteractor

interface SignUpInteractor: AuthInteractor {

    interface OnSignUpFinishedListener {
        fun onSuccess()
        fun onError()
        fun onUsernameError()
        fun onPasswordError()
        fun onPhoneNumberError()
    }

    fun signUp(username: String, phoneNumber: String, password: String, listener: OnSignUpFinishedListener)

    fun getAuthorization(listener: AuthInteractor.onAuthFinishedListener)

}