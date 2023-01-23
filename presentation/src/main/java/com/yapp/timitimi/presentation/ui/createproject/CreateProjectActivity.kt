package com.yapp.timitimi.presentation.ui.createproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.yapp.timitimi.presentation.helper.FirebaseDynamicLineHelper
import com.yapp.timitimi.presentation.ui.createproject.screen.CreateProjectScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CreateProjectActivity: ComponentActivity() {
    @Inject
    lateinit var firebaseDynamicLineHelper: FirebaseDynamicLineHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CreateProjectScreen(
                firebaseDynamicLineHelper = firebaseDynamicLineHelper
            )
        }
    }
}