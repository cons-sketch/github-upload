package com.cons.android.testv2.detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cons.android.testv2.R

class Synopsis : Fragment() {

    companion object {
        fun newInstance() = Synopsis()
    }

    private lateinit var viewModel: SynopsisViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.synopsis_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SynopsisViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
