package com.example.angel.messengerapp.ui.signup

import com.example.angel.messengerapp.ui.auth.AuthView
import com.example.angel.messengerapp.ui.base.BaseView

interface SignUpView: BaseView, AuthView {

    fun showProgress()
    fun showSignUpError()
    fun hideProgress()
    fun setUsernameError()
    fun setPhoneNumberError()
    fun setPasswordError()
    fun navigateToHome()
}