package com.example.angel.messengerapp.ui.chat

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.angel.messengerapp.R
import com.example.angel.messengerapp.data.local.AppPreferences
import com.example.angel.messengerapp.utils.Message
import com.stfalcon.chatkit.messages.MessageInput
import com.stfalcon.chatkit.messages.MessagesList
import com.stfalcon.chatkit.messages.MessagesListAdapter
import java.util.*

class ChatActivity : AppCompatActivity(), ChatView, MessageInput.InputListener {

    private var recipiendId: Long = -1
    private lateinit var messageList: MessagesList
    private lateinit var messageInput: MessageInput
    private lateinit var preferences: AppPreferences
    private lateinit var presenter: ChatPresenter
    private lateinit var messageListAdapter: MessagesListAdapter<Message>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = intent.getStringExtra("RECIPIENT_NAME")

        preferences = AppPreferences.create(this)
        messageListAdapter = MessagesListAdapter(
            preferences.userDetails.id.toString(),
            null)
        presenter = ChatPresenterImpl(this)
        bindViews()

        val conversationId = intent.getLongExtra("CONVERSATION_ID", -1)
        recipiendId = intent.getLongExtra("RECIPIENT_ID", -1)

        if(conversationId != -1L) {
            presenter.loadMessages(conversationId)
        }
    }

    override fun showConversationLoadError() {
        Toast.makeText(this,
            "Unable to load thread.Please try again later.",
            Toast.LENGTH_LONG).show()
    }

    override fun showMessageSendError() {
        Toast.makeText(this,
            "Unable to send message. Please try again later.",
            Toast.LENGTH_LONG).show()
    }

    override fun getMessageListAdapter(): MessagesListAdapter<Message> {
        return messageListAdapter
    }

    override fun bindViews() {
        messageList = findViewById(R.id.messages_list)
        messageInput = findViewById(R.id.message_input)

        messageList.setAdapter(messageListAdapter)
        messageInput.setInputListener(this)
    }

    override fun getContext(): Context {
        return this
    }

    override fun onSubmit(input: CharSequence?): Boolean {
        messageListAdapter.addToStart(Message(preferences.userDetails.id, input.toString(), Date()), true)
        presenter.sendMessage(recipiendId, input.toString())

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home)
            onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
