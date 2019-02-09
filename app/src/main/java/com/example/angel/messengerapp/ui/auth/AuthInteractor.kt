package com.example.angel.messengerapp.ui.auth

import com.example.angel.messengerapp.data.local.AppPreferences
import com.example.angel.messengerapp.data.vo.UserVO

interface AuthInteractor {

    var userDetails: UserVO
    var accessToken: String
    var submittedUsername: String
    var submittedPassword: String

    interface onAuthFinishedListener {
        fun onAuthSuccess()
        fun onAuthError()
        fun onUsernameError()
        fun onPasswordError()
    }

    fun persistAccessToken(preferences: AppPreferences)

    fun persistUserDetails(preferences: AppPreferences)

}