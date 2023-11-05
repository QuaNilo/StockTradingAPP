package com.example.TPSIMobileProjecto.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.TPSIMobileProjecto.databinding.FragmentHomeBinding
import com.example.TPSIMobileProjecto.ui.home.stockFragments.DetailedCardFragment
import com.example.TPSIMobileProjecto.ui.home.stockFragments.SimpleCardFragment
import com.example.TPSIMobileProjecto.R

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val detailedFragment = DetailedCardFragment()
        val simpleFragment = SimpleCardFragment()

        childFragmentManager.beginTransaction()
            .replace(R.id.simple_card_fragment_container, simpleFragment)
            .replace(R.id.detailed_card_fragment_container, detailedFragment)
            .commit()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}