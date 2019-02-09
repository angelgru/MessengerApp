package com.example.angel.messengerapp.data.remote.repository

import com.example.angel.messengerapp.data.vo.UserListVO
import com.example.angel.messengerapp.data.vo.UserVO
import io.reactivex.Observable

interface UserRepository {

    fun findById(id: Long): Observable<UserVO>

    fun all(): Observable<UserListVO>

    fun echoDetails(): Observable<UserVO>
}