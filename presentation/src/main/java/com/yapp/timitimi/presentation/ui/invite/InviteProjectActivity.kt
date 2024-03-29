package com.yapp.timitimi.presentation.ui.invite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.yapp.timitimi.presentation.helper.FirebaseDynamicLinkHelper
import com.yapp.timitimi.theme.AllRounder3Theme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class InviteProjectActivity: ComponentActivity() {
    @Inject
    lateinit var firebaseDynamicLinkHelper: FirebaseDynamicLinkHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AllRounder3Theme {
                InviteProjectScreen(firebaseDynamicLinkHelper = firebaseDynamicLinkHelper)
            }
        }
    }
}