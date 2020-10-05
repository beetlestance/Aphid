package com.beetlestance.spoonacular_kotlin.services

import com.beetlestance.spoonacular_kotlin.models.request.user.RequestConnectUser
import com.beetlestance.spoonacular_kotlin.models.response.user.ConnectUser
import com.beetlestance.spoonacular_kotlin.services.endpoints.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {

    /**
     * In order to call user-specific endpoints, you need to connect your app's users
     * to spoonacular users.
     * @param requestConnectUser All fields in the request JSON object are optional.
     * You can also simply send an empty object {}
     * @return ConnectUser
     * */
    @POST(User.CONNECT_USER)
    fun connectUser(@Body requestConnectUser: RequestConnectUser): Call<ConnectUser>

}
