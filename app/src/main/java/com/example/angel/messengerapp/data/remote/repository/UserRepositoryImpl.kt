package com.example.angel.messengerapp.data.remote.repository

import android.content.Context
import com.example.angel.messengerapp.data.local.AppPreferences
import com.example.angel.messengerapp.data.vo.UserListVO
import com.example.angel.messengerapp.data.vo.UserVO
import com.example.angel.messengerapp.service.MessengerApiService
import io.reactivex.Observable

class UserRepositoryImpl(context: Context): UserRepository {

    private val preferences: AppPreferences = AppPreferences.create(context)
    private val service: MessengerApiService = MessengerApiService.getInstance()

    override fun findById(id: Long): Observable<UserVO>
            = service.showUser(id, preferences.accessToken as String)

    override fun all(): Observable<UserListVO>
            = service.listUsers(preferences.accessToken as String)

    override fun echoDetails(): Observable<UserVO>
            = service.echoDetails(preferences.accessToken as String)
}