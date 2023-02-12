package com.yapp.timitimi.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.yapp.timitimi.designsystem.R
import com.yapp.timitimi.modifier.timiClickable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetLayout(
    isHeaderVisible: Boolean = true,
    modifier: Modifier = Modifier,
    modalBottomSheetState: ModalBottomSheetState,
    sheetContent: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        modifier = modifier,
        sheetState = modalBottomSheetState,
        sheetContent = {
            if (isHeaderVisible) {
                BottomSheetLayoutHeader {
                    coroutineScope.launch {
                        modalBottomSheetState.hide()
                    }
                }
            }
            sheetContent()
        },
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
    ) {
        content()
    }
}

@Composable
fun BottomSheetLayoutHeader(onClickCloseButton: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.End
    ) {
        Image(
            modifier = Modifier
                .size(24.dp)
                .timiClickable(onClick = onClickCloseButton, rippleEnabled = true),
            painter = painterResource(id = R.drawable.icon_close),
            contentDescription = "close button"
        )
    }
}