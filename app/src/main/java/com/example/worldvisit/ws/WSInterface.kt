package com.example.worldvisit.ws

import retrofit2.Call
import retrofit2.http.GET

interface WSInterface {
    // appel get :
    @GET("get-pays.php")
    fun wsGet() : Call<List<Pays>>

}