package com.yapp.timitimi.presentation.ui.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yapp.timitimi.border.TimiBorder
import com.yapp.timitimi.component.TimiBody2Medium
import com.yapp.timitimi.component.TimiBody3Regular
import com.yapp.timitimi.component.TimiButton1SemiBold
import com.yapp.timitimi.component.TimiH3SemiBold
import com.yapp.timitimi.designsystem.R
import com.yapp.timitimi.modifier.timiClipBorder
import com.yapp.timitimi.presentation.R.drawable
import com.yapp.timitimi.presentation.ui.createproject.screen.Spacing
import com.yapp.timitimi.theme.Gray100
import com.yapp.timitimi.theme.Gray600
import com.yapp.timitimi.theme.Gray700
import com.yapp.timitimi.theme.Purple500

@Composable
fun MyPageScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        item { ProfileCard() }
        item { Spacing(4.dp) }
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(40.dp)
                    .background(shape = RoundedCornerShape(8.dp), color = Gray100),
                contentAlignment = Alignment.Center
            ) {
                TimiBody3Regular(
                    modifier = Modifier.fillMaxWidth(),
                    text = "ISTJ가 되고 싶은 ENFP 리더형인가 팔로워형인가",
                    textAlign = TextAlign.Center,
                    color = Gray600
                )
            }
        }
        item {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(46.dp)
                    .timiClipBorder(
                        border = TimiBorder(color = Purple500),
                        shape = RoundedCornerShape(16.dp)
                    ),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White
                ),
                onClick = {},
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(4.dp),
                        painter = painterResource(id = R.drawable.icon_edit),
                        contentDescription = "edit icon",
                        tint = Purple500,
                    )

                    TimiButton1SemiBold(
                        text = "프로필 수정하기",
                        color = Purple500
                    )
                }
            }
        }
        item { MyPageBanner() }
        item {
            TimiButton1SemiBold(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(vertical = 12.dp, horizontal = 16.dp),
                text = "기타",
                color = Gray700,
            )
        }
        item {
            TimiBody2Medium(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(vertical = 12.dp, horizontal = 16.dp), text = "의견 보내기", color = Gray700
            )
        }
        item {
            TimiBody2Medium(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(vertical = 12.dp, horizontal = 16.dp),
                text = "서비스 이용 약관",
                color = Gray700
            )
        }
        item {
            TimiBody2Medium(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(vertical = 12.dp, horizontal = 16.dp), text = "만든 사람들", color = Gray700
            )
        }
        item {
            TimiBody2Medium(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(vertical = 12.dp, horizontal = 16.dp), text = "버전 정보", color = Gray700
            )
        }
        item {
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .padding(horizontal = 16.dp)
                    .background(Gray100)
            )
        }
        item {
            TimiButton1SemiBold(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(vertical = 12.dp, horizontal = 16.dp), text = "계정 관리", color = Gray700
            )
        }
        item {
            TimiBody2Medium(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(vertical = 12.dp, horizontal = 16.dp), text = "로그아웃", color = Gray700
            )
        }
        item {
            TimiBody2Medium(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(vertical = 12.dp, horizontal = 16.dp), text = "회원 탈퇴", color = Gray700
            )
        }

    }
}

@Composable
fun MyPageBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Gray100)
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = drawable.mypage_banner),
            contentDescription = "mypage banner"
        )
    }
}

@Composable
fun ProfileCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Image(
            modifier = Modifier
                .size(80.dp),
            painter = painterResource(id = drawable.default_profile_image),
            contentDescription = "profile image"
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            TimiH3SemiBold(text = "가연")
            Spacing(6.dp)
            TimiBody3Regular(text = "sefe@vaera.com")
        }
    }
}

@Preview
@Composable
fun MyPageScreenPreview() {
    MyPageScreen()
}