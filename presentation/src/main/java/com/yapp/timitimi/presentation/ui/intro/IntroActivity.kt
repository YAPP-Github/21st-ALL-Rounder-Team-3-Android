package com.yapp.timitimi.presentation.ui.intro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.yapp.timitimi.theme.AllRounder3Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IntroActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AllRounder3Theme {
                IntroScreen()
            }
        }
    }
}
