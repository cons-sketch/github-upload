package com.cons.android.testv2.detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cons.android.testv2.R

private const val TAG = "Analysis"

class Analysis : Fragment() {

    companion object {
        fun newInstance() = Analysis()
    }

    private lateinit var viewModel: AnalysisViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG,"CreateView called")
        return inflater.inflate(R.layout.analysis_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AnalysisViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
