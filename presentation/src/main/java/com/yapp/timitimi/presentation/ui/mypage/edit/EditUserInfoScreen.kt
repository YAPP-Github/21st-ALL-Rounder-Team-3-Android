package com.yapp.timitimi.presentation.ui.mypage.edit

import android.app.Activity
import android.content.Context
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.yapp.timitimi.border.TimiBorder
import com.yapp.timitimi.component.BottomSheetLayout
import com.yapp.timitimi.component.TimiBody1Medium
import com.yapp.timitimi.component.TimiBody3Regular
import com.yapp.timitimi.component.TimiH2SemiBold
import com.yapp.timitimi.component.TimiHalfRoundedCaption2Badge
import com.yapp.timitimi.component.TimiTopAppBar
import com.yapp.timitimi.modifier.timiClickable
import com.yapp.timitimi.presentation.R
import com.yapp.timitimi.presentation.ui.createproject.screen.BottomLargeButton
import com.yapp.timitimi.presentation.ui.createproject.screen.Spacing
import com.yapp.timitimi.presentation.ui.createproject.screen.TimiInputField
import com.yapp.timitimi.presentation.ui.mypage.edit.redux.EditUserInfoIntent
import com.yapp.timitimi.presentation.ui.mypage.edit.redux.EditUserInfoSingleEvent
import com.yapp.timitimi.theme.Caption1_Regular
import com.yapp.timitimi.theme.Gray100
import com.yapp.timitimi.theme.Gray200
import com.yapp.timitimi.theme.Gray300
import com.yapp.timitimi.theme.Gray600
import com.yapp.timitimi.theme.Gray700
import com.yapp.timitimi.theme.Purple500
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

enum class EditUserInfoBottomSheetType {
    PROFILE_CHOICE, TIMITIMI_CHARACTER
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EditUserInfoScreen(
    context: Context = LocalContext.current,
    viewModel: EditUserInfoViewModel = hiltViewModel()
) {
    val state = viewModel.viewState.collectAsState()

    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    val focusManager = LocalFocusManager.current

    val changeProfileBottomSheetState =
        rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)

    var bottomSheetType by remember {
        mutableStateOf(EditUserInfoBottomSheetType.PROFILE_CHOICE)
    }

    LaunchedEffect(viewModel.singleEventFlow) {
        viewModel.singleEventFlow
            .onEach { event ->
                when (event) {
                    EditUserInfoSingleEvent.ShowChangeProfileBottomSheet -> {
                        changeProfileBottomSheetState.show()
                    }

                    EditUserInfoSingleEvent.NavigateToBackScreen -> {
                        (context as Activity).finish()
                    }

                    EditUserInfoSingleEvent.ShowChangeTimiTimiImageBottomSheet -> {
                        bottomSheetType = EditUserInfoBottomSheetType.TIMITIMI_CHARACTER
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
            modalBottomSheetState = changeProfileBottomSheetState,
            sheetContent = {
                if (bottomSheetType == EditUserInfoBottomSheetType.PROFILE_CHOICE) {
                    ChangeProfileImageBottomSheetContent(
                        changeDefaultProfileClicked = {
                            viewModel.dispatch(EditUserInfoIntent.ClickDefaultProfileChanged)
                        },
                        changeTimiTimiProfileClicked = {
                            viewModel.dispatch(EditUserInfoIntent.ClickTimiTimiImageChanged)
                        }
                    )
                } else {
                    ChangeTimiTimiImageBottomSheetContent(
                        onCompleteButtonClicked = {}
                    )
                }
            }
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
fun ChangeProfileImageBottomSheetContent(
    changeDefaultProfileClicked: () -> Unit,
    changeTimiTimiProfileClicked: () -> Unit,
) {
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
                    .padding(start = 16.dp)
                    .clickable {
                        changeDefaultProfileClicked()
                    },
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
                    .padding(start = 16.dp)
                    .clickable {
                        changeTimiTimiProfileClicked()
                    },
                text = "팀플 유형 캐릭터로 변경하기",
                color = Gray700
            )
        }
    }
}

@Composable
fun ChangeTimiTimiImageBottomSheetContent(
    onCompleteButtonClicked: () -> Unit,
) {
    var selectedCharacterId by remember {
        mutableStateOf(0)
    }

    val map = listOf(
        0 to R.drawable.image_mbti_emoji_1,
        1 to R.drawable.image_mbti_emoji_2,
        2 to R.drawable.image_mbti_emoji_3,
        3 to R.drawable.image_mbti_emoji_4,
        4 to R.drawable.image_mbti_emoji_5,
        5 to R.drawable.image_mbti_emoji_6,
        6 to R.drawable.image_mbti_emoji_7,
        7 to R.drawable.image_mbti_emoji_8,
        8 to R.drawable.image_mbti_emoji_9,
        9 to R.drawable.image_mbti_emoji_10,
        10 to R.drawable.image_mbti_emoji_11,
        11 to R.drawable.image_mbti_emoji_12,
        12 to R.drawable.image_mbti_emoji_13,
        13 to R.drawable.image_mbti_emoji_14,
        14 to R.drawable.image_mbti_emoji_15,
        16 to R.drawable.image_mbti_emoji_16,
        )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val selectedImage = map.find {
            it.first == selectedCharacterId
        }?.second ?: R.drawable.default_profile_image
        
        Image(
            painter = painterResource(selectedImage),
            modifier = Modifier
                .size(120.dp),
            contentDescription = null,
        )
        Spacing(20.dp)
        TimiH2SemiBold(text = "톡톡 아이디어 뱅크")
        Spacing(16.dp)
        Badges()
        Spacing(50.dp)
        LazyVerticalGrid(
            modifier = Modifier
                .wrapContentSize()
                .background(color = Gray100)
                .padding(top = 16.dp, bottom = 72.dp, start = 11.dp, end = 11.dp),
            columns = GridCells.Fixed(4)
        ) {
            items(map) {
                MbtiImage(
                    id = it.first,
                    isSelected = selectedCharacterId == it.first,
                    res = it.second
                ) {
                    selectedCharacterId = it
                }
            }
        }
    }

    BottomLargeButton(
        backgroundColor = Purple500,
        isEnabled = true,
        onClick = onCompleteButtonClicked,
        title = "선택완료",
    )
}

@Composable
private fun Badges() {
    Row(
        modifier = Modifier.wrapContentSize()
    ) {
        TimiHalfRoundedCaption2Badge(
            text = "#브레인스토밍 마스터",
            border = TimiBorder(
                color = Gray300,
            ),
            backgroundColor = Gray200,
            fontColor = Gray600,
        )

        Spacer(modifier = Modifier.size(4.dp))

        TimiHalfRoundedCaption2Badge(
            text = "#정열",
            border = TimiBorder(
                color = Gray300,
            ),
            backgroundColor = Gray200,
            fontColor = Gray600,
        )

        Spacer(modifier = Modifier.size(4.dp))

        TimiHalfRoundedCaption2Badge(
            text = "#에너지",
            border = TimiBorder(
                color = Gray300,
            ),
            backgroundColor = Gray200,
            fontColor = Gray600,
        )
    }

    Spacing(6.dp)

    Row(
        modifier = Modifier.wrapContentSize()
    ) {
        TimiHalfRoundedCaption2Badge(
            text = "#활기",
            border = TimiBorder(
                color = Gray300,
            ),
            backgroundColor = Gray200,
            fontColor = Gray600,
        )

        Spacer(modifier = Modifier.size(4.dp))

        TimiHalfRoundedCaption2Badge(
            text = "#창의력",
            border = TimiBorder(
                color = Gray300,
            ),
            backgroundColor = Gray200,
            fontColor = Gray600,
        )
    }
}

@Composable
private fun MbtiImage(
    id: Int,
    isSelected: Boolean = false,
    @DrawableRes res: Int,
    onClickCharacter: (Int) -> Unit
) {
    val borderColors = if (isSelected) listOf(
        Color(0Xff8075F9),
        Color(0Xff1AC694)
    ) else listOf(Color.Transparent, Color.Transparent)

    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(5.dp)
            .border(
                width = 2.dp,
                brush = Brush.horizontalGradient(borderColors),
                shape = CircleShape
            )
            .clickable {
                onClickCharacter(id)
            },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(res),
            modifier = Modifier
                .wrapContentSize(),
            contentDescription = null,
        )
    }
}

@Preview
@Composable
fun ChangeTimiTimiImageBottomSheetContentPreview() {
    ChangeTimiTimiImageBottomSheetContent(
        onCompleteButtonClicked = {}
    )
}