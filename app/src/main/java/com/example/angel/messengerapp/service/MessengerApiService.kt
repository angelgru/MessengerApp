package com.example.angel.messengerapp.service

import com.example.angel.messengerapp.data.remote.request.LoginRequestObject
import com.example.angel.messengerapp.data.remote.request.MessageRequestObject
import com.example.angel.messengerapp.data.remote.request.StatusUpdateRequest
import com.example.angel.messengerapp.data.remote.request.UserRequestObject
import com.example.angel.messengerapp.data.vo.*
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface MessengerApiService {

    @POST("login")
    @Headers("Content-Type: application/json")
    fun login(@Body user: LoginRequestObject): Observable<Response<ResponseBody>>

    @POST("users/registrations")
    fun createUser(@Body user: UserRequestObject): Observable<UserVO>

    @GET("users")
    fun listUsers(@Header("Authorization") authorization: String): Observable<UserListVO>

    @PUT("users")
    fun updateUserStatus(@Body request: StatusUpdateRequest,
                         @Header("Authorization") authorization: String): Observable<UserVO>

    @GET("users/{userId}")
    fun showUser(@Path("userId") userId: Long,
                 @Header("Authorization") authorization: String): Observable<UserVO>

    @GET("users/details")
    fun echoDetails(@Header("Authorization") authorization: String): Observable<UserVO>

    @POST("messages")
    fun createMessage(@Body messageRequestObject: MessageRequestObject,
                      @Header("Authorization") authorization: String): Observable<MessageVO>

    @GET("conversations")
    fun listConversations(@Header("Authorization") authorization: String): Observable<ConversationListVO>

    @GET("conversations/{conversationId}")
    fun showConversation(@Path("conversationId") conversationId: Long,
                         @Header("Authorization") authorization: String): Observable<ConversationVO>

//    Singleton Pattern
    companion object Factory {
        private var service: MessengerApiService? = null

        fun getInstance(): MessengerApiService {
            if(service == null) {
                val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://192.168.2.102:8080")
                    .build()

                service = retrofit.create(MessengerApiService::class.java)
            }

            return service as MessengerApiService
        }
    }
}