package dedi.tsm.testdevelopment.api


import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.HTTP
import retrofit2.http.FormUrlEncoded
import retrofit2.http.PUT
import retrofit2.http.POST
import retrofit2.http.GET

import dedi.tsm.testdevelopment.model.Kontak
import dedi.tsm.testdevelopment.model.PostPutDelKontak


interface ApiService {
    @GET("kontak")
    fun getKontak() : Call<List<Kontak>>

    @FormUrlEncoded
    @POST("kontak")
    fun postKontak(
        @Field("nama") nama: String,
        @Field("nilai") nilai: String,
        @Field("jurusan") jurusan: String
    ): Call<PostPutDelKontak>

    @FormUrlEncoded
    @PUT("kontak")
    fun putKontak(
        @Field("id") id: String,
        @Field("nama") nama: String,
        @Field("nilai") nilai: String,
        @Field("jurusan") jurusan: String
    ): Call<PostPutDelKontak>

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "kontak", hasBody = true)
    fun deleteKontak(@Field("id") id: String): Call<PostPutDelKontak>
}