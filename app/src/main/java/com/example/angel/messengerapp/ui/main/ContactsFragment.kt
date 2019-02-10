package com.example.angel.messengerapp.ui.main


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

import com.example.angel.messengerapp.R
import com.example.angel.messengerapp.data.vo.UserVO
import com.example.angel.messengerapp.ui.chat.ChatActivity
import com.example.angel.messengerapp.ui.chat.ChatView

class ContactsFragment : Fragment() {

    private lateinit var activity: MainActivity
    private lateinit var rvContacts: RecyclerView
    var contacts: ArrayList<UserVO> = ArrayList()
    lateinit var contactsAdapter: ContactsAdapter

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val baseLayout = inflater.inflate(R.layout.fragment_contacts, container, false)
        rvContacts = baseLayout.findViewById(R.id.rv_contacts)
        rvContacts.layoutManager = LinearLayoutManager(getActivity()?.baseContext)
        contactsAdapter = ContactsAdapter(getActivity() as Context, contacts)
        rvContacts.adapter = contactsAdapter
        return baseLayout
    }

    fun setActivity(activity: MainActivity) {
        this.activity = activity
    }

    class ContactsAdapter(private val context: Context,
                          private val dataSet:List<UserVO>):
        RecyclerView.Adapter<ContactsAdapter.ViewHolder>(),
        ChatView.ChatAdapter {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val liContainer = LayoutInflater.from(context)
                .inflate(R.layout.vh_contacts, parent, false).findViewById<LinearLayout>(R.id.ll_container)
            return ViewHolder(liContainer)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = dataSet[position]
            val itemLayout = holder.itemLayout

            itemLayout.findViewById<TextView>(R.id.tv_username).text = item.username
            itemLayout.findViewById<TextView>(R.id.tv_phone).text = item.phoneNumber
            itemLayout.findViewById<TextView>(R.id.tv_status).text = item.status

            itemLayout.setOnClickListener {
                navigateToChat(item.username, item.id)
            }
        }

        override fun getItemCount(): Int = dataSet.size

        override fun navigateToChat(recipientName: String,
                                    recipientId: Long, conversationId: Long?) {
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra("RECIPIENT_ID", recipientId)
            intent.putExtra("RECIPIENT_NAME", recipientName)
            context.startActivity(intent)
        }

        class ViewHolder(val itemLayout: View): RecyclerView.ViewHolder(itemLayout)
    }
}
