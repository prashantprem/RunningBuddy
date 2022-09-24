package com.app.runningbuddy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.runningbuddy.R
import com.app.runningbuddy.room.RunDAO
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var runDAO: RunDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}