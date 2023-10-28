package com.example.missioncontrolcenterinterface.network

import com.example.missioncontrolcenterinterface.model.Station
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MissionAPI {
    @GET("Station")
    suspend fun getStation(): Station
    @POST("Station/name")
    suspend fun changeName(@Body name: String): Call<Unit>

    @POST("Station/linearSpeed")
    suspend fun changeLinearSpeed(@Body linearSpeed: Map<String,Double>): Call<Unit>

    @POST("Station/rotationSpeed")
    suspend fun changeRotationSpeed(@Body rotationSpeed: Map<String,Double>): Call<Unit>

    companion object RetrofitBuilder{
        private const val BASE_URL = "http://192.168.29.106:2023/api/"

        private fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        val api: MissionAPI = getRetrofit().create()
    }
}