package com.example.TPSIMobileProjecto.ui.News

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.TPSIMobileProjecto.R
import com.example.TPSIMobileProjecto.databinding.FragmentNewsBinding
import com.example.TPSIMobileProjecto.ui.home.stockFragments.simpleCard.SimpleRecyclerAdapter

class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        val root: View = binding.root


        newsViewModel.newsList.observe(viewLifecycleOwner) { newsList ->
            val itemAdapter = NewsRecyclerAdapter(newsList) // Initialize the adapter
            val recyclerView: RecyclerView = requireView().findViewById(R.id.newsrecyleview)
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = itemAdapter
        }
        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}