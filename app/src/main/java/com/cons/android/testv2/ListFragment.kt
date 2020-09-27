package com.cons.android.testv2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "ListFragment"

class ListFragment : Fragment() {

    private lateinit var topicRecyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private var adapter: TopicAdapter? = null

    private val listViewModel: ListViewModel by lazy {
        ViewModelProvider(this).get(ListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val navHostFragment = requireFragmentManager().findFragmentById(R.id.nav_host_fragment)
        val backStackEntryCount = navHostFragment?.childFragmentManager?.backStackEntryCount
        super.onCreate(savedInstanceState)
        Log.d(TAG,"Total topics: ${listViewModel.topics.size}")
        Log.d(TAG, backStackEntryCount.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_fragment, container, false)

        topicRecyclerView = view.findViewById(R.id.list_recycler_view) as RecyclerView
        topicRecyclerView.layoutManager = LinearLayoutManager(context)
        progressBar = view.findViewById(R.id.progress_bar) as ProgressBar

        listViewModel.progress.observe(viewLifecycleOwner, Observer {
            progress -> if (progress == 1) {
            topicRecyclerView.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        } else if (progress == 0) {
            topicRecyclerView.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
        }
        })

        updateUI()
        return view
    }

    private fun updateUI(){
        val topics = listViewModel.topics
        adapter = TopicAdapter(topics)
        topicRecyclerView.adapter = adapter
    }

    private inner class TopicHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener{

        val titleTextView = itemView.findViewById<TextView>(R.id.topic_title)
        val authorTextView = itemView.findViewById<TextView>(R.id.topic_author)
        var id: Int = 0

        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            val action = ListFragmentDirections.actionListFragmentToDetailTopicFragment(id)
            v?.findNavController()?.navigate(action)
        }
    }

    private inner class TopicAdapter(var topics: List<Topic>) : RecyclerView.Adapter<TopicHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicHolder {
            val view =
                layoutInflater.inflate(R.layout.list_fragment_item, parent, false)
            return TopicHolder(view)
        }

        override fun getItemCount() = topics.size

        override fun onBindViewHolder(holder: TopicHolder, position: Int) {
            val topic = topics[position]
            holder.apply {
                titleTextView.text = topic.title
                authorTextView.text = topic.author
                id = topic.id ?: 0
            }
        }
    }

    companion object {

        fun newInstance(): ListFragment {
            return ListFragment()
        }
    }


}


