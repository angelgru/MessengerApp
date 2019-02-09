package com.example.angel.messengerapp.ui.login

import android.util.Log
import com.example.angel.messengerapp.data.local.AppPreferences
import com.example.angel.messengerapp.data.remote.request.LoginRequestObject
import com.example.angel.messengerapp.data.vo.UserVO
import com.example.angel.messengerapp.service.MessengerApiService
import com.example.angel.messengerapp.ui.auth.AuthInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginInteractorImpl: LoginInteractor {

    override lateinit var userDetails: UserVO
    override lateinit var accessToken: String
    override lateinit var submittedUsername: String
    override lateinit var submittedPassword: String

    private val service: MessengerApiService = MessengerApiService.getInstance()

    override fun login(username: String, password: String, listener: AuthInteractor.onAuthFinishedListener) {
        when {
            username.isBlank() -> listener.onUsernameError()
            password.isBlank() -> listener.onPasswordError()
            else -> {
                submittedUsername = username
                submittedPassword = password
                val requestObject = LoginRequestObject(submittedUsername, submittedPassword)

                Log.e("RESTAPI", "Do tuka raboti 1")
                service.login(requestObject)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({response ->
                        Log.e("RESTAPI", "Do tuka raboti")
                        if(response.code() != 403) {
                            accessToken = response.headers()["Authorization"] as String
                            listener.onAuthSuccess()
                        } else{
                            listener.onAuthError()
                        }
                    }, {error ->
                        Log.e("RESTAPI", error.message)
                        listener.onAuthError()
                        error.printStackTrace()
                    })
            }
        }
    }

    override fun retrieveDetails(preferences: AppPreferences,
        listener: LoginInteractor.OnDetailsRetrivalFinishedListener) {
        service.echoDetails(preferences.accessToken as String)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({response ->
                userDetails = response
                listener.onDetailsRetrievalSuccess()
            },{ error ->
                listener.onDetailsRetrievalError()
                error.printStackTrace()
            })
    }

    override fun persistAccessToken(preferences: AppPreferences)
            = preferences.storeAccessToken(accessToken)

    override fun persistUserDetails(preferences: AppPreferences)
            = preferences.storeUserDetails(userDetails)
}