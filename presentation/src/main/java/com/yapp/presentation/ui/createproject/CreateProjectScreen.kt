package com.yapp.presentation.ui.createproject

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yapp.presentation.theme.Gray6
import com.yapp.presentation.ui.createproject.base.CreateProjectIntent
import com.yapp.presentation.ui.createproject.base.CreateProjectSingleEvent
import com.yapp.presentation.ui.createproject.onestep.CreateProjectOneStepScreen
import com.yapp.presentation.ui.createproject.twostep.CreateProjectTwoStepScreen
import com.yapp.presentation.ui.main.MainActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun CreateProjectScreen(
    navController: NavHostController = rememberNavController(),
    context: Context = LocalContext.current
) {
    val viewModel: CreateProjectViewModel = hiltViewModel()
    var progress by remember { mutableStateOf(0.5f) }
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
    )

    LaunchedEffect(viewModel.singleEventFlow) {
        viewModel.singleEventFlow
            .onEach { event ->
                when (event) {
                    CreateProjectSingleEvent.NavigateToMain -> {
                        context.startActivity(Intent(context, MainActivity::class.java))
                        (context as? Activity)?.finish()
                    }

                    CreateProjectSingleEvent.NavigateToTwoStepPage -> {
                        progress = 1f
                        navController.navigate(CreateProjectScreenRoute.STEP_TWO.route)
                    }

                    CreateProjectSingleEvent.NavigateOneStopPage -> {
                        progress = 0.5f
                        navController.popBackStack()
                    }

                    CreateProjectSingleEvent.Exit -> {
                        (context as? Activity)?.finish()
                    }

                    is CreateProjectSingleEvent.ShowToast -> {
                        Toast.makeText(context, event.msg, Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .launchIn(this)
    }

    Scaffold(
        topBar = {
            AppBar {
                viewModel.dispatch(CreateProjectIntent.ClickAppBarBackButton(progress))
            }
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(contentPadding),
                progress = animatedProgress,
                backgroundColor = Gray6
            )

            NavHost(
                navController = navController,
                startDestination = CreateProjectScreenRoute.STEP_ONE.route
            ) {
                composable(CreateProjectScreenRoute.STEP_ONE.route) {
                    CreateProjectOneStepScreen(viewModel)
                }
                composable(CreateProjectScreenRoute.STEP_TWO.route) {
                    CreateProjectTwoStepScreen(viewModel)
                }
            }
        }
    }
}

@Composable
private fun AppBar(
    onBackIconClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            tint = Color.Unspecified,
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(30.dp))
                .clickable {
                    onBackIconClicked()
                }
        )

        Text(
            text = "",
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .align(Alignment.CenterVertically)
                .padding(start = 20.dp, end = 20.dp),
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}