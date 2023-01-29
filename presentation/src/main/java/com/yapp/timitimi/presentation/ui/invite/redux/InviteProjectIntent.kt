package com.yapp.timitimi.presentation.ui.invite.redux

import com.yapp.timitimi.redux.BaseIntent

sealed interface InviteProjectIntent : BaseIntent {
    data class InputProjectLink(val text: String): InviteProjectIntent
    data class ChangeProjectLinkTextFieldFocused(val hasFocused: Boolean) : InviteProjectIntent
    object ClearProjectLink : InviteProjectIntent
    object ClickBackButton : InviteProjectIntent
    object ClickCompleteButton : InviteProjectIntent
}