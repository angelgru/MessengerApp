package com.example.angel.messengerapp.data.remote.repository

import com.example.angel.messengerapp.data.vo.ConversationListVO
import com.example.angel.messengerapp.data.vo.ConversationVO
import io.reactivex.Observable

interface ConversationRepository {

    fun findConversationById(id: Long): Observable<ConversationVO>

    fun all(): Observable<ConversationListVO>
}