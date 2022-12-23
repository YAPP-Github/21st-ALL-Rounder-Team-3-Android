package com.yapp.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun TimiProjectCard(
    backgroundImage: Painter,
    isLeader: Boolean,
) {
    Box(
        modifier = Modifier.paint(painter = backgroundImage)
    )
    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        if (isLeader) {
            TimiCaption2SemiBold(text = "팀장")
        }
        TimiH3SemiBold(text = "고전문학사 팀플 3조")
        Row{
            TimiMediumRoundedBadge(text = "완벽하게")
            TimiCaption2Regular(text = "학기 성적 A+ 도전")
        }
        Row{
            TimiMediumRoundedBadge(text = "D-10")
            TimiCaption2Regular(text = "11/16 ~ 12/7")
            TimiCaption2Regular(text = "45%")
            TimiLinearProgressBar(strokeWidth = 6.dp, progress = 0.45f)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ){
            //Image 두개 component 구현
        }
    }
}

