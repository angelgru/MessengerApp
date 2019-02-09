package com.example.angel.messengerapp.ui.login

import com.example.angel.messengerapp.data.local.AppPreferences
import com.example.angel.messengerapp.ui.auth.AuthInteractor

interface LoginInteractor: AuthInteractor {

    interface OnDetailsRetrivalFinishedListener {
        fun onDetailsRetrievalSuccess()
        fun onDetailsRetrievalError()
    }

    fun login(username: String, password: String, listener: AuthInteractor.onAuthFinishedListener)

    fun retrieveDetails(preferences: AppPreferences, listener: OnDetailsRetrivalFinishedListener)
}