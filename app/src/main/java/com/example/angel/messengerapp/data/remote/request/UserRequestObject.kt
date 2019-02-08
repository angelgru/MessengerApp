package com.example.angel.messengerapp.data.remote.request

data class UserRequestObject(val username: String,
                             val password: String,
                             val phoneNumber: String = "")