package com.example.TPSIMobileProjecto.ui.News

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.TPSIMobileProjecto.R
import com.example.TPSIMobileProjecto.databinding.FragmentNewsBinding

class NewsFragment : Fragment() {
    lateinit var itemAdapter : NewsRecyclerAdapter
    private var _binding: FragmentNewsBinding? = null
    private lateinit var noDataFoundTV : TextView
    private  lateinit var newsViewModel: NewsViewModel
    lateinit var progressBar: ProgressBar // Import ProgressBar if not done already

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
    inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.e("Lifecycle", "NewsFragment onCreateView()")

        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        //Init progressBar and show
        progressBar = view.findViewById(R.id.progressBarNews)
        showProgressBar()

        //Init Text View In case no data is retrieved from API
        noDataFoundTV = view.findViewById<TextView>(R.id.tvNewsFound)
        //Set is as gone initially and set to visible in case there's no data
        noDataFoundTV.visibility = View.GONE

        newsViewModel.newsList.observe(viewLifecycleOwner) { newsList ->
            if (newsList.isEmpty() || newsList == null) {
                noDataFoundTV.visibility = View.VISIBLE
            }
            else{
                noDataFoundTV.visibility = View.GONE
                itemAdapter = NewsRecyclerAdapter(newsList)
                val recyclerView: RecyclerView = view.findViewById(R.id.newsrecyleview)
                recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
                recyclerView.adapter = NewsRecyclerAdapter(newsList)
            }
            hideProgressBar()
        }

        val callback = object : OnBackPressedCallback(true ) {
            override fun handleOnBackPressed() {
                // Handle the back button event
                if (parentFragmentManager.backStackEntryCount > 0) {
                    parentFragmentManager.popBackStack()
                } else {
                    isEnabled = false
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    override fun onStart() {
        super.onStart()
        Log.e("Lifecycle", "NewsFragment onStart()")
    }

    override fun onPause() {
        super.onPause()
        Log.e("Lifecycle", "NewsFragment onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.e("Lifecycle", "NewsFragment onStop()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

        Log.e("Lifecycle", "NewsFragment onDestroyView()")
    }

    fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

}