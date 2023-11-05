package com.example.TPSIMobileProjecto.ui.Home.StockFragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.TPSIMobileProjecto.R

class SimpleCardFragment : Fragment() {

    companion object {
        fun newInstance() = SimpleCardFragment()
    }

    private lateinit var viewModel: SimpleCardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_simple_card, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SimpleCardViewModel::class.java)
        // TODO: Use the ViewModel
    }

}