package com.yapp.timitimi.presentation.ui.createproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.yapp.timitimi.presentation.ui.createproject.screen.CreateProjectScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateProjectActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CreateProjectScreen()
        }
    }
}