package com.yapp.timitimi.presentation.ui.createproject.screen

import android.app.Activity
import android.content.Context
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yapp.timitimi.presentation.helper.FirebaseDynamicLinkHelper
import com.yapp.timitimi.presentation.ui.createproject.CreateProjectScreenRoute
import com.yapp.timitimi.presentation.ui.createproject.redux.CreateProjectIntent
import com.yapp.timitimi.presentation.ui.createproject.redux.CreateProjectSingleEvent
import com.yapp.timitimi.presentation.ui.createproject.viewmodel.CreateProjectViewModel
import com.yapp.timitimi.presentation.ui.main.MainActivity
import com.yapp.timitimi.theme.Gray200
import com.yapp.timitimi.ui.startActivityWithAnimation
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun CreateProjectScreen(
    navController: NavHostController = rememberNavController(),
    context: Context = LocalContext.current,
    firebaseDynamicLinkHelper: FirebaseDynamicLinkHelper
) {
    val viewModel: CreateProjectViewModel = hiltViewModel()
    var progress by remember { mutableStateOf(0.5f) }
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
    )
    val activity = context as Activity

    LaunchedEffect(viewModel.singleEventFlow) {
        viewModel.singleEventFlow
            .onEach { event ->
                when (event) {
                    CreateProjectSingleEvent.NavigateToMain -> {
                        activity.startActivityWithAnimation<MainActivity>(
                            withFinish = true
                        )
                    }

                    is CreateProjectSingleEvent.NavigateToTwoStepPage -> {
                        progress = 1f
                        viewModel.setCurrentProjectId(event.id)
                        navController.navigate(CreateProjectScreenRoute.STEP_TWO.route)
                    }

                    CreateProjectSingleEvent.NavigateOneStopPage -> {
                        progress = 0.5f
                        navController.popBackStack()
                    }

                    CreateProjectSingleEvent.Exit -> {
                        (context as? Activity)?.finish()
                    }

                   is CreateProjectSingleEvent.ShowChooser -> {
                       firebaseDynamicLinkHelper.createDynamicLink(event.id)
                    }
                }
            }
            .launchIn(this)
    }

    Scaffold(
        topBar = {
            AppBar {
                viewModel.dispatch(CreateProjectIntent.ClickBackButton(progress))
            }
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .padding(contentPadding),
                progress = animatedProgress,
                backgroundColor = Gray200
            )

            NavHost(
                navController = navController,
                startDestination = CreateProjectScreenRoute.STEP_ONE.route
            ) {
                composable(CreateProjectScreenRoute.STEP_ONE.route) {
                    CreateProjectOneStepScreen(viewModel) {
                        viewModel.dispatch(CreateProjectIntent.ClickBackButton(progress))
                    }
                }
                composable(CreateProjectScreenRoute.STEP_TWO.route) {
                    CreateProjectTwoStepScreen(viewModel) {
                        viewModel.dispatch(CreateProjectIntent.ClickBackButton(progress))
                    }
                }
            }
        }
    }
}

@Composable
fun AppBar(
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
        Image(
            painter = painterResource(com.yapp.timitimi.designsystem.R.drawable.icon_arrow_left),
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