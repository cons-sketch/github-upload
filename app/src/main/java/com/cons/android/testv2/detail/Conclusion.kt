package com.cons.android.testv2.detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cons.android.testv2.R

class Conclusion : Fragment() {

    companion object {
        fun newInstance() = Conclusion()
    }

    private lateinit var viewModel: ConclusionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.conclusion_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ConclusionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
