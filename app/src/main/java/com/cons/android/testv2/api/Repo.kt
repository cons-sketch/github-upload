package com.cons.android.testv2.api

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.cons.android.testv2.Model
import java.time.Instant
import kotlin.Exception

private const val TAG = "Repo"

class Repo {
    private val myServerTest by lazy {
        MyServerTest.create()
    }

    suspend fun getData(): List<Model.Title>{
        val result = myServerTest.getFirstTitle("title,id").await()
        Log.d(TAG,"result received")
        return result
    }

    suspend fun firstPost():List<Model.Result>{
        Log.d(TAG, "firstPost started")
        val result = myServerTest.getFirstPost("content").await()
        return result
    }

    suspend fun getPost(id: Int):Model.Result{
        Log.d(TAG, "fetching post with id $id")
        val result = myServerTest.getPost(id,"content").await()
        return result
    }

    suspend fun validateToken():Boolean{
        try {
            val result = myServerTest.validateToken("Bearer "+ AccessToken.token).await()
            return result.data?.status == 200
        } catch (e: Exception){
            Log.e(TAG, e.toString())
            return false
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun login(authBody: AuthBody): Boolean {
        try {
            val result = myServerTest.login(authBody).await()
            AccessToken.token = result.token ?: ""
            AccessToken.expiresIn = Instant.now().plusSeconds(259200)
            Log.d(TAG, AccessToken.token)
            return true

        }catch (e: Exception){
            Log.e(TAG, e.toString())
            return false
        }
    }
}