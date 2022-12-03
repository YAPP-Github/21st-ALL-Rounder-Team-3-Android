package com.yapp.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.channels.Channel

@Composable
fun MainScreen(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    viewModel: MainViewModel = MainViewModel() //todo should change by inject viewModel()
) {
    val navController = rememberNavController()
    val state = viewModel.viewState.collectAsState()

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                viewModel.dispatch(MainIntent.CompleteLoading)
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(state.value.text)
        Button(
            onClick = { viewModel.dispatch(MainIntent.ChangeTopButtonText) },
        ) {
            Text(text = state.value.topButtonText)
        }

        Button(
            onClick = { viewModel.dispatch(MainIntent.ChangeBottomButtonText) },
        ) {
            Text(text = state.value.bottomButtonText)
        }
    }
}