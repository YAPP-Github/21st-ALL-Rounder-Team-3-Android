package com.yapp.timitimi.presentation.ui.intro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.yapp.timitimi.presentation.helper.FirebaseDynamicLineHelper
import com.yapp.timitimi.theme.AllRounder3Theme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class IntroActivity : ComponentActivity() {
    @Inject
    lateinit var firebaseDynamicLineHelper: FirebaseDynamicLineHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AllRounder3Theme {
                IntroScreen()
            }
        }

        firebaseDynamicLineHelper.handleDynamicLinks()
    }
}
