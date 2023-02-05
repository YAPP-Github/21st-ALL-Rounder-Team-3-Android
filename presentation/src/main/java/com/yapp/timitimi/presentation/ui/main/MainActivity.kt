package com.yapp.timitimi.presentation.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import com.yapp.timitimi.modifier.timiClickable
import com.yapp.timitimi.presentation.ui.main.redux.MainIntent
import com.yapp.timitimi.presentation.ui.main.screen.MainContainerScreen
import com.yapp.timitimi.theme.AllRounder3Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.dispatch(MainIntent.CheckNewUser())
        setContent {
            AllRounder3Theme {
                MainContainerScreen()
            }
        }
    }
}

@Composable
fun GuideImage(
    onClick: () -> Unit,
    painter: Painter,
    contentDescription: String,
) {
    Image(
        modifier = Modifier
            .fillMaxSize()
            .timiClickable(
                onClick = onClick,
                rippleEnabled = false
            ),
        painter = painter,
        contentDescription = contentDescription,
        contentScale = ContentScale.FillBounds,
    )
}

