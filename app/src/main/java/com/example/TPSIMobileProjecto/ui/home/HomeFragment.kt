package com.example.TPSIMobileProjecto.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.TPSIMobileProjecto.databinding.FragmentHomeBinding
import com.example.TPSIMobileProjecto.ui.home.stockFragments.simpleCard.SimpleCardFragment
import com.example.TPSIMobileProjecto.R
import com.example.TPSIMobileProjecto.ui.home.stockFragments.checklist.ChecklistFragment
import com.example.TPSIMobileProjecto.ui.home.stockFragments.detailedCard.DetailedCardFragment
import com.example.TPSIMobileProjecto.ui.home.stockFragments.sharedViewModels.SimpleChecklistSharedViewModel
import retrofit.TickerSummary

class HomeFragment : Fragment(){
    val simpleFragment = SimpleCardFragment()
    val checkListFragment = ChecklistFragment()
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



        val button: Button = root.findViewById(R.id.btnChecklist)
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

        childFragmentManager.addOnBackStackChangedListener {
            // Update the button text based on the currently displayed fragment
            val currentFragment = childFragmentManager.findFragmentById(R.id.display_fragment)
            if (currentFragment is SimpleCardFragment) {
                button.text = "Edit Checklist"
            } else if (currentFragment is ChecklistFragment) {
                button.text = "Exit Checklist"
            }
        }



        return root
    }

    override fun onResume() {
        super.onResume()
        Log.e("Lifecycle", "HomeFragment onResume()")
        val button: Button = requireView().findViewById(R.id.btnChecklist)
        // Update the button text based on the currently displayed fragment
        val currentFragment = childFragmentManager.findFragmentById(R.id.display_fragment)
        if (currentFragment is SimpleCardFragment) {
            button.text = "Edit Checklist"
        } else if (currentFragment is ChecklistFragment) {
            button.text = "Exit Checklist"
        }
    }
    override fun onStart() {
        super.onStart()
        Log.e("Lifecycle", "HomeFragment onStart()")
    }

    override fun onPause() {
        super.onPause()
        Log.e("Lifecycle", "HomeFragment onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.e("Lifecycle", "HomeFragment onStop()")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.e("Lifecycle", "HomeFragment OnDestroyView()")
    }


}