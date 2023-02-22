package com.yapp.timitimi.presentation.ui.invite

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yapp.timitimi.component.TimiBody2Medium
import com.yapp.timitimi.component.TimiH1SemiBold
import com.yapp.timitimi.presentation.R
import com.yapp.timitimi.presentation.helper.FirebaseDynamicLinkHelper
import com.yapp.timitimi.presentation.ui.createproject.redux.CreateProjectIntent
import com.yapp.timitimi.presentation.ui.createproject.screen.AppBar
import com.yapp.timitimi.presentation.ui.createproject.screen.BottomLargeButton
import com.yapp.timitimi.presentation.ui.createproject.screen.Spacing
import com.yapp.timitimi.presentation.ui.createproject.screen.TimiInputField
import com.yapp.timitimi.presentation.ui.invite.redux.InviteProjectIntent
import com.yapp.timitimi.presentation.ui.invite.redux.InviteProjectSingleEvent
import com.yapp.timitimi.presentation.ui.main.MainActivity
import com.yapp.timitimi.theme.Black
import com.yapp.timitimi.theme.Gray200
import com.yapp.timitimi.theme.Gray600
import com.yapp.timitimi.theme.Purple200
import com.yapp.timitimi.theme.Purple500
import com.yapp.timitimi.ui.startActivityWithAnimation
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@Composable
fun InviteProjectScreen(
    viewModel: InviteProjectViewModel = hiltViewModel(),
    context: Context = LocalContext.current,
    firebaseDynamicLinkHelper: FirebaseDynamicLinkHelper
    ) {
    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    val focusManager = LocalFocusManager.current
    val state = viewModel.viewState.collectAsState()

    LaunchedEffect(viewModel.singleEventFlow) {
        viewModel.singleEventFlow
            .onEach { event ->
                when (event) {
                    InviteProjectSingleEvent.NavigateToMain -> {
                        (context as Activity).startActivityWithAnimation<MainActivity>()
                    }

                    InviteProjectSingleEvent.Exit -> {
                        (context as Activity).finish()
                    }

                    InviteProjectSingleEvent.ValidateUrl -> {
                        firebaseDynamicLinkHelper.parseDynamicLinks(
                            state.value.projectLinkUrl
                        ) { projectId ->
                            viewModel.participateProject(projectId)
                        }
                    }

                }
            }
            .launchIn(this)
    }

    Scaffold(
        topBar = {
            AppBar {
                 viewModel.dispatch(InviteProjectIntent.ClickBackButton)
            }
        }
    ) { _ ->
        Column(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .background(Color.White)
                .fillMaxWidth()
        ) {
            TimiH1SemiBold(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                text = stringResource(id = R.string.invite_project_title),
                color = Black
            )
            Spacing(10.dp)
            TimiBody2Medium(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = stringResource(id = R.string.invite_project_description),
                color = Gray600
            )
            Spacing(30.dp)
            TimiInputField(
                focusRequester = focusRequester,
                focusManager = focusManager,
                title = "",
                placeholder = stringResource(id = R.string.invite_project_placeholder),
                strokeColor = if (state.value.hasProjectLinkUrlFieldFocused) Purple500 else Gray200,
                input = state.value.projectLinkUrl,
                onFocusChanged = {
                    viewModel.dispatch(InviteProjectIntent.ChangeProjectLinkTextFieldFocused(it))
                },
                onTextCleared = {
                    viewModel.dispatch(InviteProjectIntent.ClearProjectLink)
                },
                onInputChange = {
                    viewModel.dispatch(InviteProjectIntent.InputProjectLink(it))
                }
            )

            BottomLargeButton(
                backgroundColor = if (state.value.isButtonEnabled) Purple500 else Purple200,
                isEnabled = state.value.isButtonEnabled,
                onClick = { viewModel.dispatch(InviteProjectIntent.ClickCompleteButton) },
                title = stringResource(id = R.string.complete_button),
            )
        }
    }
}


@Preview
@Composable
fun InviteProjectScreenPreview() {
   // InviteProjectScreen()
}