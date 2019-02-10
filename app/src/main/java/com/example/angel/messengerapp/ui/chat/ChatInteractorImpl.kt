package com.example.angel.messengerapp.ui.chat

import android.annotation.SuppressLint
import android.content.Context
import com.example.angel.messengerapp.data.local.AppPreferences
import com.example.angel.messengerapp.data.remote.repository.ConversationRepository
import com.example.angel.messengerapp.data.remote.repository.ConversationRepositoryImpl
import com.example.angel.messengerapp.data.remote.request.MessageRequestObject
import com.example.angel.messengerapp.service.MessengerApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ChatInteractorImpl(context: Context): ChatInteractor {

    private val preferences = AppPreferences.create(context)
    private val service: MessengerApiService = MessengerApiService.getInstance()
    private val conversationsRepository: ConversationRepository = ConversationRepositoryImpl(context)

    @SuppressLint("CheckResult")
    override fun sendMessage(recipientId: Long,
                             message: String,
                             listener: ChatInteractor.OnMessageSendFinishedListener) {
        service.createMessage(MessageRequestObject(recipientId, message), preferences.accessToken as String)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                listener.onSendSuccess()
            }, { error ->
                listener.onSendError()
                error.printStackTrace()
            })
    }

    @SuppressLint("CheckResult")
    override fun loadMessages(conversationId: Long, listener: ChatInteractor.onMessageLoadFinishedListener) {
        conversationsRepository.findConversationById(conversationId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res ->
                listener.onLoadSuccess(res)
            }, {error ->
                listener.onLoadError()
                error.printStackTrace()
            })
    }
}