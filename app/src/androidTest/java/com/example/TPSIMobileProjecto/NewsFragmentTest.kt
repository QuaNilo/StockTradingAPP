package com.example.TPSIMobileProjecto

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.TPSIMobileProjecto.ui.News.NewsFragment
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import com.example.TPSIMobileProjecto.R

@RunWith(AndroidJUnit4::class)
class NewsFragmentTest {

    @Test
    fun recyclerViewItemCountGreaterThanZero() {

        // Launch the main activity first
        val activityScenario: ActivityScenario<MainActivity> = ActivityScenario.launch(MainActivity::class.java)

        // Launch the fragment in the test container
        val scenario: FragmentScenario<NewsFragment> = launchFragmentInContainer()

        // Use the fragment instance to access its views and data
        scenario.onFragment { fragment ->
            // Assuming that the recyclerView has an id of R.id.newsrecyleview
            val recyclerView: RecyclerView = fragment.requireView().findViewById(R.id.newsrecyleview)

            // Check if the recyclerView has items
            assertTrue((recyclerView.adapter?.itemCount ?: 0) > 0)
        }
        activityScenario.close()
    }
}
