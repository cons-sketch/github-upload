package com.cons.android.testv2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cons.android.testv2.api.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListViewModel : ViewModel() {


    var topics = mutableListOf<Topic>()
    private val repo = Repo()
    val progress: MutableLiveData<Int> = MutableLiveData()


    init {
        progress.value = 0
        viewModelScope.launch {
            val result = repo.getData()
            handleResult(result)
        }
    }
    private fun handleResult(result: List<Model.Title>){
        for (mResult in result) {
            val topic = Topic()
            topic.title = mResult.title?.rendered ?: ""
            topic.id = mResult.id
            topics.plusAssign(topic)
        }
        progress.value = 1
    }
}