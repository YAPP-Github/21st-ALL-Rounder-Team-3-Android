package com.yapp.timitimi.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.yapp.timitimi.theme.AllRounder3Theme
import com.yapp.timitimi.theme.Black
import com.yapp.timitimi.theme.H3_SemiBold
import com.yapp.timitimi.theme.Purple500


@Composable
fun TimiTwoButtonDialog(
    titlePairs: List<Pair<String, Color>>,
    description: String,
    positiveText: String,
    negativeText: String,
    onPositiveButtonClicked: () -> Unit,
    onNegativeButtonClicked: () -> Unit,
    onDismissRequest: () -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                TimiAnnotatedText(
                    textPairs = titlePairs,
                    textStyle = H3_SemiBold,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                TimiBody2Medium(
                    text = description,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                            .border(
                                width = 1.dp,
                                color = Purple500,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .clip(RoundedCornerShape(16.dp)),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.White
                        ),
                        onClick = onNegativeButtonClicked,
                    ) {
                        TimiButton1SemiBold(
                            text = negativeText,
                            textAlign = TextAlign.Center,
                            color = Purple500
                        )
                    }

                    Spacer(modifier = Modifier.padding(5.dp))

                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Purple500
                        ),
                        onClick = onPositiveButtonClicked,
                    ) {
                        TimiButton1SemiBold(
                            text = positiveText,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun TimiTwoButtonDialogPreview() {
    AllRounder3Theme {
        Box(modifier = Modifier.background(Color.White)) {
            TimiTwoButtonDialog(
                titlePairs = listOf(
                    Pair("정말 ", Black),
                    Pair("가연", Purple500),
                    Pair("님께\n팀장 권한을 넘기시겠어요?", Black)
                ),
                description = "더 이상 프로젝트 관리를 할 수 없어요.",
                positiveText = "팀장 넘기기",
                negativeText = "취소하기",
                onNegativeButtonClicked = {},
                onPositiveButtonClicked = {},
                onDismissRequest = {}
            )
        }
    }
}