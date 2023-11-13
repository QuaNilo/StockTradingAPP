package com.example.TPSIMobileProjecto.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.TPSIMobileProjecto.databinding.FragmentHomeBinding
import com.example.TPSIMobileProjecto.ui.home.stockFragments.simpleCard.SimpleCardFragment
import com.example.TPSIMobileProjecto.R
import com.example.TPSIMobileProjecto.ui.home.stockFragments.checklist.ChecklistFragment

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
        Log.e("Lifecycle", "HomeFragment onCreateView()")
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root




        val simpleFragment = SimpleCardFragment()
        val checkListFragment = ChecklistFragment()
        val button: Button = root.findViewById(R.id.btnCheckList)
        button.text = "Edit Checklist"

        childFragmentManager.beginTransaction()
            .replace(R.id.display_fragment, simpleFragment, "TAG_SIMPLE_FRAGMENT")
            .commit()


        button.setOnClickListener {
            val targetFragment = if (button.text == "Edit Checklist") {
                button.text = "Exit Checklist"
                checkListFragment
            } else {
                button.text = "Edit Checklist"
                simpleFragment
            }

            childFragmentManager.beginTransaction()
                .replace(R.id.display_fragment, targetFragment)
                .addToBackStack(null)
                .commit()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.e("Lifecycle", "HomeFragment OnDestroyView()")
    }
}