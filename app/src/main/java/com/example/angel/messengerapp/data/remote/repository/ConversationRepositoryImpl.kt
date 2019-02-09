package com.example.angel.messengerapp.data.remote.repository

import android.content.Context
import com.example.angel.messengerapp.data.local.AppPreferences
import com.example.angel.messengerapp.data.vo.ConversationListVO
import com.example.angel.messengerapp.data.vo.ConversationVO
import com.example.angel.messengerapp.service.MessengerApiService
import io.reactivex.Observable

class ConversationRepositoryImpl(context: Context): ConversationRepository {

    private val preferences: AppPreferences = AppPreferences.create(context)
    private val service: MessengerApiService = MessengerApiService.getInstance()

    override fun findConversationById(id: Long): Observable<ConversationVO>
            = service.showConversation(id, preferences.accessToken as String)

    override fun all(): Observable<ConversationListVO>
            = service.listConversations(preferences.accessToken as String)
}