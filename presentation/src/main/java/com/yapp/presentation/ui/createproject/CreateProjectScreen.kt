package com.yapp.presentation.ui.createproject

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
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
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yapp.presentation.ui.createproject.onestep.CreateProjectOneStepScreen
import com.yapp.presentation.ui.createproject.twostep.CreateProjectTwoStepScreen

@Composable
fun CreateProjectScreen(
    navController: NavHostController = rememberNavController()
) {
    val viewModel: CreateProjectViewModel = hiltViewModel()
    var progress by remember { mutableStateOf(0.5f) }
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
    )

    Scaffold(
        topBar = {
            AppBar()
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(contentPadding),
                progress = animatedProgress
            )

            NavHost(
                navController = navController,
                startDestination = CreateProjectScreenRoute.STEP_ONE.route
            ) {
                composable(CreateProjectScreenRoute.STEP_ONE.route) {
                    CreateProjectOneStepScreen(viewModel) {
                        progress = 1f
                        navController.navigate(CreateProjectScreenRoute.STEP_TWO.route)
                    }
                }
                composable(CreateProjectScreenRoute.STEP_TWO.route) {
                    CreateProjectTwoStepScreen(viewModel) {
                    }
                }
            }
        }
    }
}

@Composable
private fun AppBar() {
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
                .clickable {}
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