package com.example.TPSIMobileProjecto.ui.home.stockFragments.detailedCard

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.TPSIMobileProjecto.R

class DetailedCardFragment : Fragment() {

    companion object {
        fun newInstance() = DetailedCardFragment()
    }

    private lateinit var viewModel: DetailedCardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detailed_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailedCardViewModel::class.java)
        // TODO: Use the ViewModel
    }
}
