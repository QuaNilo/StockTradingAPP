package com.example.TPSIMobileProjecto.ui.News

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.TPSIMobileProjecto.R
import com.example.TPSIMobileProjecto.databinding.FragmentNewsBinding

class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null

    private  lateinit var newsViewModel: NewsViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(

    inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.e("Lifecycle", "NewsFragment onCreateView()")
        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        val root: View = binding.root


        newsViewModel.newsList.observe(viewLifecycleOwner) { newsList ->
            view?.findViewById<RecyclerView>(R.id.newsrecyleview)?.let { recyclerView ->
                recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
                recyclerView.adapter = NewsRecyclerAdapter(newsList)
            }
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up the callback
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



}