package com.cons.android.testv2

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cons.android.testv2.api.CurrentPost
import com.cons.android.testv2.api.Repo
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.*
import okhttp3.Headers
import java.lang.ClassCastException


private const val TAG = "DetailTopicFragment"
// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailTopicFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val args: DetailTopicFragmentArgs by navArgs()
    private var myJob: Job? = null
    private lateinit var listener: OnDetailTopicStarted
    private lateinit var recyclerView: RecyclerView
    private var adapter: SynAdapter? = null

    private lateinit var webView: WebView
    private val repo = Repo()

    private val detailTopicViewModel : DetailTopicViewModel by lazy {
        DetailTopicViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_topic_detail, container, false)
        recyclerView = view.findViewById<RecyclerView>(R.id.syn_recycler_view) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        updateUI()
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is DetailTopicFragment.OnDetailTopicStarted) {
            listener = context
            listener.detailTopicStarted()
        } else {
            throw ClassCastException(context.toString() + " must implement OnSignIn")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //webView = view.findViewById<WebView>(R.id.post_content)
        myJob = CoroutineScope(Dispatchers.IO).launch {
            val result = repo.getPost(args.id)
            withContext(Dispatchers.Main) {
                handleResult(result)
            }
        }
    }


    override fun onDestroy() {
        myJob?.cancel()
        super.onDestroy()
    }

    private fun handleResult(result: Model.Result){
        var str = result.content?.rendered ?: return
        var tmp = str.split("//thesis")
        CurrentPost.syn = tmp[0]
        Log.d(TAG,"syn = ${CurrentPost.syn}")
        tmp = tmp[1].split("//antithesis")
        CurrentPost.the = tmp[0]
        Log.d(TAG,"the = ${CurrentPost.the}")
        tmp = tmp[1].split("//analysis")
        CurrentPost.ant = tmp[0]
        Log.d(TAG,"ant = ${CurrentPost.ant}")
        tmp = tmp[1].split("//conclusion")
        CurrentPost.ana = tmp[0]
        Log.d(TAG,"ana = ${CurrentPost.ana}")
        CurrentPost.con = tmp[1]
        Log.d(TAG,"con = ${CurrentPost.con}")
        displaySynthesis()

        /*detailTopicViewModel.topic.content=result.content?.rendered ?: ""
        Log.d(TAG,result.content?.rendered ?: "null result")
        val wedData: String =  detailTopicViewModel.topic.content
            .replace("localhost","192.168.178.31")
        val mimeType: String = "text/html"
        val utfType: String = "UTF-8"
        webView.loadData(wedData,mimeType,utfType)*/
    }

    private fun updateUI(){
        adapter = SynAdapter(detailTopicViewModel.headers, detailTopicViewModel.bodies)
        recyclerView.adapter = adapter
    }

    interface OnDetailTopicStarted {
        fun detailTopicStarted()
    }
    private fun displaySynthesis(){
        val text = CurrentPost.syn?.split("<h2>","</h2>")
        text?.forEachIndexed {
                i,e -> if(i%2 == 0) {
            detailTopicViewModel.bodies.add(e)
            Log.d(TAG,e)
        }
        else detailTopicViewModel.headers.add(e)
        }

    }

    private inner class SynHolder(view: View) : RecyclerView.ViewHolder(view) {
        val headerTextView = itemView.findViewById<TextView>(R.id.header)
        val paraTextView = itemView.findViewById<TextView>(R.id.paragraph)
    }

    private inner class SynAdapter(var headers: MutableList<String>, var bodies: MutableList<String>) : RecyclerView.Adapter<SynHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SynHolder {
            val view = layoutInflater.inflate(R.layout.detail_linear_layout,parent,false)
            return SynHolder(view)
        }

        override fun getItemCount(): Int {
            return headers.size
        }

        override fun onBindViewHolder(holder: SynHolder, position: Int) {
            holder.apply {
                headerTextView.text = headers[position]
                Log.d(TAG,headers[position])
                paraTextView.text = bodies[position]
            }
        }

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SecondFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailTopicFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
