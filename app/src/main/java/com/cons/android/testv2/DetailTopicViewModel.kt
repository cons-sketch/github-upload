package com.cons.android.testv2

import androidx.lifecycle.ViewModel

class DetailTopicViewModel : ViewModel() {

    var topic = Topic()
    var headers: MutableList<String> = mutableListOf(String())
    var bodies: MutableList<String> = mutableListOf(String())

    init {
            topic.title = "Title"
            topic.author = "Author"
            topic.content = ""

    }

}