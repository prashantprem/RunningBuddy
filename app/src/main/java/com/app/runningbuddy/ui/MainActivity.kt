package com.app.runningbuddy.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.app.runningbuddy.other.Constants.Companion.ACTION_SHOW_TRACKING_FRAGMENT
import com.app.runningbuddy.R
import com.app.runningbuddy.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var name: String

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.rootView)
        binding.apply {
            setSupportActionBar(toolbar)
            val navController = findNavController(R.id.navHostFragment)
            bottomNavigationView.setupWithNavController(navController)
            bottomNavigationView.setOnNavigationItemReselectedListener { /* NO-OP */ }
            navigateToTrackingFragmentIfNeeded(intent)
            if(name.isNotEmpty()) {
                val toolbarTitle = "Let's go, $name!"
                tvToolbarTitle.text = toolbarTitle
            }

            navController
                .addOnDestinationChangedListener { _, destination, _ ->
                    when (destination.id) {
                        R.id.setupFragment2, R.id.trackingFragment -> bottomNavigationView.visibility =
                            View.GONE
                        else -> bottomNavigationView.visibility = View.VISIBLE
                    }
                }

        }

    }


    //Checks if we launched the activity from the notification
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        navigateToTrackingFragmentIfNeeded(intent)
    }

    private fun navigateToTrackingFragmentIfNeeded(intent: Intent?) {
        if(intent?.action == ACTION_SHOW_TRACKING_FRAGMENT) {
            findNavController(R.id.navHostFragment).navigate(R.id.action_global_trackingFragment)
        }
    }

}