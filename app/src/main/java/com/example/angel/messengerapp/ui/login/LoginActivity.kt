package com.example.angel.messengerapp.ui.login

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.angel.messengerapp.R
import com.example.angel.messengerapp.data.local.AppPreferences
import com.example.angel.messengerapp.ui.main.MainActivity
import com.example.angel.messengerapp.ui.signup.SignUpActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginView, View.OnClickListener {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignUp: Button
    private lateinit var progressBar: ProgressBar

    private lateinit var presenter: LoginPresenter
    private lateinit var preferences: AppPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Log.e("ANGEL", "CREATED")
        preferences = AppPreferences.create(this)
        if(preferences.accessToken != null)
            navigateToHome()
        presenter = LoginPresenterImpl(this)
        bindViews()
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun setUsernameError() {
        etUsername.error = "Username field cannot be empty"
        Toast.makeText(this, "hellou", Toast.LENGTH_LONG).show()
    }

    override fun setPasswordError() {
        etPassword.error = "Password field cannot be empty"
    }

    override fun navigateToSignUp() {
        startActivity(Intent(this, SignUpActivity::class.java))
    }

    override fun navigateToHome() {
        Log.e("Angel", "Starting Main Activity")
        finish()
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun bindViews() {
        etUsername = findViewById(R.id.et_username)
        etPassword = findViewById(R.id.et_password)
        btnLogin = findViewById(R.id.btn_login)
        btnSignUp = findViewById(R.id.btn_sign_up)
        progressBar = findViewById(R.id.progress_bar)
        btnLogin.setOnClickListener(this)
        btnSignUp.setOnClickListener(this)
    }

    override fun getContext(): Context {
        return this
    }

    override fun showAuthError() {
        Toast.makeText(this, "Invalid username and password combination.", Toast.LENGTH_LONG).show()
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.btn_login) {
            presenter.executeLogin(et_username.text.toString(), et_password.text.toString())
        } else if(v?.id == R.id.btn_sign_up) {
            navigateToSignUp()
        }
    }
}
