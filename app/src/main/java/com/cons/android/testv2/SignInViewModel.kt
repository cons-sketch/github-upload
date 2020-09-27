package com.cons.android.testv2

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.*

import com.cons.android.testv2.api.AuthBody
import com.cons.android.testv2.api.Repo
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {


    /*val signedIn: MutableLiveData<Int> = MutableLiveData()*/
    private val repo = Repo()
    var signedIn: LiveData<Int> = liveData {
        val result = repo.validateToken()
        if (result) emit(1)
        else emit(0)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun signIn(authBody: AuthBody){
        signedIn = liveData {
            val result =repo.login(authBody)
            if(result) emit(1)
            else emit(2)
        }
    }
}
