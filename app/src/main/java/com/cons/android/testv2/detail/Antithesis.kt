package com.cons.android.testv2.detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cons.android.testv2.R

class Antithesis : Fragment() {

    companion object {
        fun newInstance() = Antithesis()
    }

    private lateinit var viewModel: AntithesisViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.antithesis_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AntithesisViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
