package com.example.recyclerviewapi

import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {
    @GET("/people/")
    fun Getname(): Call<ArrayList<ActivityDetails>>?
}

class ActivityDetails(var name: String?)