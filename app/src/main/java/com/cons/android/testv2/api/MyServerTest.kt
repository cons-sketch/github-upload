package com.cons.android.testv2.api

import com.cons.android.testv2.Model
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface MyServerTest {

    @GET("wp/v2/posts?")
    fun getFirstTitle(@Query("_fields") fields: String) :
            Deferred<List<Model.Title>>

    @GET("wp/v2/posts?")
    fun getFirstPost(@Query("_fields") fields: String) :
            Deferred<List<Model.Result>>

    @GET("wp/v2/posts/{id}")
    fun getPost(@Path("id") id: Int,
                @Query("_fields") fields: String) :
            Deferred<Model.Result>

    @POST("jwt-auth/v1/token/validate")
    fun validateToken(@Header("Authorization") authHeader: String) :
            Deferred<Model.ValResult>

    @POST("jwt-auth/v1/token")
    fun login(@Body() authBody: AuthBody) :
            Deferred<Model.ValResult>




    companion object {
        fun create() : MyServerTest {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.178.31/wp-json/")
                .build()

            return  retrofit.create(MyServerTest::class.java)
        }
    }
}


