package com.yapp.timitimi.presentation.ui.mypage.edit

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.yapp.timitimi.component.BottomSheetLayout
import com.yapp.timitimi.component.TimiBody1Medium
import com.yapp.timitimi.component.TimiBody3Regular
import com.yapp.timitimi.component.TimiTopAppBar
import com.yapp.timitimi.modifier.timiClickable
import com.yapp.timitimi.presentation.R
import com.yapp.timitimi.presentation.ui.createproject.screen.BottomLargeButton
import com.yapp.timitimi.presentation.ui.createproject.screen.Spacing
import com.yapp.timitimi.presentation.ui.createproject.screen.TimiInputField
import com.yapp.timitimi.presentation.ui.mypage.edit.redux.EditUserInfoIntent
import com.yapp.timitimi.presentation.ui.mypage.edit.redux.EditUserInfoSingleEvent
import com.yapp.timitimi.theme.Caption1_Regular
import com.yapp.timitimi.theme.Gray200
import com.yapp.timitimi.theme.Gray700
import com.yapp.timitimi.theme.Purple500
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EditUserInfoScreen(
    context: Context = LocalContext.current,
    viewModel: EditUserInfoViewModel = hiltViewModel()
) {
    val state = viewModel.viewState.collectAsState()

    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    val focusManager = LocalFocusManager.current

    val bottomSheetModalBottomSheetState =
        rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)

    LaunchedEffect(viewModel.singleEventFlow) {
        viewModel.singleEventFlow
            .onEach { event ->
                when (event) {
                    EditUserInfoSingleEvent.ShowBottomSheet -> {
                        bottomSheetModalBottomSheetState.show()
                    }

                    EditUserInfoSingleEvent.NavigateToBackScreen -> {
                        (context as Activity).finish()
                    }
                }
            }
            .launchIn(this)
    }

    Scaffold(
        topBar = {
            TimiTopAppBar(
                isTextCenterAlignment = true,
                onClickBackButton = {
                    viewModel.dispatch(EditUserInfoIntent.ClickBackButton)
                },
                title = "프로필 수정",
            )
        }
    ) { padding ->
        BottomSheetLayout(
            modalBottomSheetState = bottomSheetModalBottomSheetState,
            sheetContent = { ChangeProfileImageBottomSheetContent() }
        ) {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacing(24.dp)

                if (state.value.userProfile.imageUrl.isBlank()) {
                    Image(
                        modifier = Modifier
                            .size(120.dp),
                        painter = painterResource(id = R.drawable.default_profile_image),
                        contentDescription = "kakao profile image"
                    )
                } else {
                    AsyncImage(
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(state.value.userProfile.imageUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = "timitimi profile image",
                    )
                }
                Spacing(12.dp)
                Text(
                    modifier = Modifier
                        .timiClickable(onClick = {
                            viewModel.dispatch(EditUserInfoIntent.ClickUserProfileImageChanged)
                        }),
                    text = "프로필 이미지 바꾸기",
                    style = Caption1_Regular,
                    color = Purple500,
                    textDecoration = TextDecoration.Underline
                )

                TimiInputField(
                    focusRequester = focusRequester,
                    focusManager = focusManager,
                    title = "이름",
                    placeholder = "가연",
                    strokeColor = if (state.value.hasNicknameFieldFocused) Purple500 else Gray200,
                    input = state.value.userProfile.nickname,
                    onFocusChanged = {
                        viewModel.dispatch(EditUserInfoIntent.ChangeNicknameTextFieldFocused(it))
                    },
                    onTextCleared = {
                        viewModel.dispatch(EditUserInfoIntent.ClearNicknameField)
                    },
                    onInputChange = {
                        viewModel.dispatch(EditUserInfoIntent.SaveUserInfo)
                    }
                )

                Spacing()

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 16.dp)
                ) {
                    TimiBody3Regular(
                        text = "이메일"
                    )
                    Spacing()
                    TimiBody1Medium(
                        text = state.value.userProfile.email,
                        color = Gray700
                    )
                }
            }

            BottomLargeButton(
                backgroundColor = Purple500,
                title = "저장하기",
                isEnabled = true,
                onClick = { viewModel.dispatch(EditUserInfoIntent.SaveUserInfo) }
            )
        }
    }
}


@Composable
fun ChangeProfileImageBottomSheetContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
    ) {
        Row(
            modifier = Modifier
                .wrapContentSize()
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = com.yapp.timitimi.designsystem.R.drawable.icon_refresh),
                contentDescription = "revert image"
            )

            TimiBody1Medium(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(start = 16.dp),
                text = "카카오톡 프로필로 변경하기",
                color = Gray700
            )
        }

        Divider(
            color = Gray200
        )

        Row(
            modifier = Modifier
                .wrapContentSize()
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = com.yapp.timitimi.designsystem.R.drawable.icon_smile),
                contentDescription = "revert image"
            )

            TimiBody1Medium(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(start = 16.dp),
                text = "팀플 유형 캐릭터로 변경하기",
                color = Gray700
            )
        }
    }
}