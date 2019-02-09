package com.example.angel.messengerapp.ui.signup

import com.example.angel.messengerapp.data.local.AppPreferences
import com.example.angel.messengerapp.data.remote.request.LoginRequestObject
import com.example.angel.messengerapp.data.remote.request.UserRequestObject
import com.example.angel.messengerapp.data.vo.UserVO
import com.example.angel.messengerapp.service.MessengerApiService
import com.example.angel.messengerapp.ui.auth.AuthInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SignUpInteractorImpl: SignUpInteractor {

    override lateinit var userDetails: UserVO
    override lateinit var accessToken: String
    override lateinit var submittedUsername: String
    override lateinit var submittedPassword: String

    private val service: MessengerApiService = MessengerApiService.getInstance()

    override fun signUp(
        username: String,
        phoneNumber: String,
        password: String,
        listener: SignUpInteractor.OnSignUpFinishedListener) {
        submittedUsername = username
        submittedPassword = password
        val userRequestObject = UserRequestObject(username, password, phoneNumber)

        when {
            username.isBlank() -> listener.onUsernameError()
            phoneNumber.isBlank() -> listener.onPhoneNumberError()
            password.isBlank() -> listener.onPasswordError()
            else -> {
                service.createUser(userRequestObject)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({res ->
                        userDetails = res
                        listener.onSuccess()
                    }, {error ->
                        listener.onError()
                        error.printStackTrace()
                    })
            }
        }
    }

    override fun getAuthorization(listener: AuthInteractor.onAuthFinishedListener) {
        val userRequestObject = LoginRequestObject(submittedUsername, submittedPassword)
        service.login(userRequestObject)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res ->
                accessToken = res.headers()["Authorization"] as String
                listener.onAuthSuccess()
            }, { error ->
                listener.onAuthError()
                error.printStackTrace()
            })
    }

    override fun persistAccessToken(preferences: AppPreferences) {
        preferences.storeAccessToken(accessToken)
    }

    override fun persistUserDetails(preferences: AppPreferences) {
        preferences.storeUserDetails(userDetails)
    }
}