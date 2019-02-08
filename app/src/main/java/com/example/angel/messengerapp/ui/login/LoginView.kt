package com.example.angel.messengerapp.ui.login

import com.example.angel.messengerapp.ui.auth.AuthView
import com.example.angel.messengerapp.ui.base.BaseView

interface LoginView: BaseView, AuthView {

    fun showProgress()
    fun hideProgress()
    fun setUsernameError()
    fun setPasswordError()
    fun navigateToSignUp()
    fun navigateToHome()
}