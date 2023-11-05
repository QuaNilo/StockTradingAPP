package com.example.TPSIMobileProjecto.ui.home.stockFragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.TPSIMobileProjecto.R

class ChecklistFragment : Fragment() {

    companion object {
        fun newInstance() = ChecklistFragment()
    }

    private lateinit var viewModel: ChecklistViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_checklist, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ChecklistViewModel::class.java)
        // TODO: Use the ViewModel
    }

}