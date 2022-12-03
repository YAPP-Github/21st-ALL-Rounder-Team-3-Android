package com.yapp.presentation.ui.main

import com.yapp.core.redux.BaseIntent

sealed interface MainIntent : BaseIntent {
    object ShowToast: MainIntent
    object ChangeTopButtonText: MainIntent
    object ChangeBottomButtonText: MainIntent
    object CompleteLoading: MainIntent
}