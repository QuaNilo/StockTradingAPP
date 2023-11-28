package com.example.TPSIMobileProjecto.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.TPSIMobileProjecto.databinding.FragmentHomeBinding
import com.example.TPSIMobileProjecto.ui.home.stockFragments.simpleCard.SimpleCardFragment
import com.example.TPSIMobileProjecto.R
import androidx.activity.OnBackPressedCallback

class HomeFragment : Fragment(){
    val simpleFragment = SimpleCardFragment(mutableListOf(), false)
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
        childFragmentManager.beginTransaction()
            .replace(R.id.display_fragment, simpleFragment)
            .addToBackStack(null)
            .commit()
        return root
        
        
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    
        // Set up the callback
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Handle the back button event
                val currentFragment = childFragmentManager.findFragmentById(R.id.display_fragment)
                Log.e("Fragment: ", currentFragment.toString())
                if (currentFragment is SimpleCardFragment) {
                    isEnabled = false
                    return
                }
    
                if (childFragmentManager.backStackEntryCount > 0 ) {
                    childFragmentManager.popBackStack()
                } else {
                    isEnabled = false
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }


    override fun onResume() {
        super.onResume()
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