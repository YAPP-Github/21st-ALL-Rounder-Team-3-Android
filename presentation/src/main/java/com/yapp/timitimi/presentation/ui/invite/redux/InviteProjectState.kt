package com.yapp.timitimi.presentation.ui.invite.redux

import com.yapp.timitimi.redux.BaseState

data class InviteProjectState(
    val projectLinkUrl: String = "",
    val hasProjectLinkUrlFieldFocused: Boolean = false,
    val isButtonEnabled: Boolean = false,
): BaseState