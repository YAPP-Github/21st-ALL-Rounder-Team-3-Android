package com.yapp.presentation

import com.yapp.presentation.redux.BaseIntent

sealed interface MainIntent : BaseIntent {
    object ShowToast: MainIntent
    object ChangeTopButtonText: MainIntent
    object ChangeBottomButtonText: MainIntent
    object CompleteLoading: MainIntent
}