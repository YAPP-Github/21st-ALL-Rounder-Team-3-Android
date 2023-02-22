package com.yapp.timitimi.presentation.ui.invite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.yapp.timitimi.presentation.ui.createproject.screen.CreateProjectTwoStepScreen
import com.yapp.timitimi.presentation.ui.createproject.viewmodel.CreateProjectViewModel
import com.yapp.timitimi.theme.AllRounder3Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InviteUserActivity: ComponentActivity() {
    val viewModel: CreateProjectViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AllRounder3Theme {
                CreateProjectTwoStepScreen(
                    viewModel
                ) {
                    finish()
                }
            }
        }
    }
}