package com.app.runningbuddy.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.runningbuddy.other.Constants.Companion.KEY_NAME
import com.app.runningbuddy.other.Constants.Companion.KEY_WEIGHT
import com.app.runningbuddy.R
import com.app.runningbuddy.databinding.FragmentSettingsBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.fragment_settings) {

    @Inject
    lateinit var sharedPref: SharedPreferences
    private  lateinit var binding: FragmentSettingsBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            loadFieldsFromSharedPref()

            btnApplyChanges.setOnClickListener {
                val success = applyChangesToSharedPref()
                if(success) {
                    Snackbar.make(requireView(), "Saved changes", Snackbar.LENGTH_SHORT).show()
                } else {
                    Snackbar.make(requireView(), "Please fill out all the fields", Snackbar.LENGTH_SHORT).show()
                }
            }

        }

    }

    private fun loadFieldsFromSharedPref() {
        binding.apply {
            val name = sharedPref.getString(KEY_NAME, "")
            val weight = sharedPref.getFloat(KEY_WEIGHT, 80f)
            etName.setText(name)
            etWeight.setText(weight.toString())
        }
    }

    private fun applyChangesToSharedPref(): Boolean {
        binding.apply {
            val nameText = etName.text.toString()
            val weightText = etWeight.text.toString()
            if(nameText.isEmpty() || weightText.isEmpty()) {
                return false
            }
            sharedPref.edit()
                .putString(KEY_NAME, nameText)
                .putFloat(KEY_WEIGHT, weightText.toFloat())
                .apply()
            val toolbarText = "Let's go, $nameText!"
//            requireActivity().tvToolbarTitle.text = toolbarText
            return true
        }

    }
}