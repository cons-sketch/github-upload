package com.cons.android.testv2

import androidx.lifecycle.ViewModel

class EditTextViewModel : ViewModel() {
    var topic = Topic()

    init {
        topic.title = "Title"
        topic.author = "Author"
        topic.content = ""

    }
}