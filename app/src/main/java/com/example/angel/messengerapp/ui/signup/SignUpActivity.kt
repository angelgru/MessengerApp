package com.example.angel.messengerapp.ui.signup

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.angel.messengerapp.R
import com.example.angel.messengerapp.data.local.AppPreferences
import com.example.angel.messengerapp.ui.main.MainActivity

class SignUpActivity : AppCompatActivity(), SignUpView, View.OnClickListener {

    private lateinit var etUsername: EditText
    private lateinit var etPhoneNumber: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnSignUp: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: SignUpPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        presenter = SignUpPresenterImpl(this)
        bindViews()
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun showSignUpError() {
        Toast.makeText(
            this,
            "An unexpected error occurred. Please try again later.",
            Toast.LENGTH_LONG)
            .show()
    }

    override fun hideProgress() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun setUsernameError() {
        etUsername.error = "Username field cannot be empty"
    }

    override fun setPhoneNumberError() {
        etPhoneNumber.error = "Phone Number field cannot be empty"
    }

    override fun setPasswordError() {
        etPassword.error = "Password field cannot be empty"
    }

    override fun navigateToHome() {
        finish()
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun bindViews() {
        etUsername = findViewById(R.id.et_username)
        etPhoneNumber = findViewById(R.id.et_phone)
        etPassword = findViewById(R.id.et_password)
        btnSignUp = findViewById(R.id.btn_sign_up)
        btnSignUp.setOnClickListener(this)
        progressBar = findViewById(R.id.progress_bar)
    }

    override fun getContext(): Context {
        return this
    }

    override fun showAuthError() {
        Toast.makeText(
            this,
            "An authorization error occurred. Please try again later.",
            Toast.LENGTH_LONG).show()
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.btn_sign_up) {
            presenter.executeSignUp(etUsername.text.toString(),
                etPhoneNumber.text.toString(),
                etPassword.text.toString())
        }
    }
}
