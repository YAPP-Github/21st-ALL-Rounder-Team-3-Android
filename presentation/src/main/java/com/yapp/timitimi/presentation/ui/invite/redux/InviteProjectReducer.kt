package com.yapp.timitimi.presentation.ui.invite.redux

import com.yapp.timitimi.redux.BaseIntent
import com.yapp.timitimi.redux.Reducer
import javax.inject.Inject

class InviteProjectReducer @Inject constructor() : Reducer<InviteProjectState> {
    override fun invoke(action: BaseIntent, state: InviteProjectState): InviteProjectState {
        var newState = state
        when (action) {
            is InviteProjectIntent.InputProjectLink -> {
                newState = newState.copy(
                    isButtonEnabled = action.text.isNotEmpty(),
                    projectLinkUrl = action.text
                )
            }

            is InviteProjectIntent.ClearProjectLink -> {
                newState = newState.copy(
                    projectLinkUrl = ""
                )
            }

            is InviteProjectIntent.ChangeProjectLinkTextFieldFocused -> {
                newState = newState.copy(
                    hasProjectLinkUrlFieldFocused = action.hasFocused
                )
            }
        }

        return newState
    }
}
