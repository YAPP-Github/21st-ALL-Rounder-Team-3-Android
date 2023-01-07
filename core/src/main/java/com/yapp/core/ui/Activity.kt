package com.yapp.core.ui

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi

inline fun <reified T : Activity> Activity.changeActivityWithAnimation(
    intentBuilder: Intent.() -> Intent = { this },
) {
    startActivityWithAnimation<T>(
        intentBuilder = intentBuilder,
        withFinish = true,
    )
}

@RequiresApi(Build.VERSION_CODES.ECLAIR)
inline fun <reified T : Activity> Activity.startActivityWithAnimation(
    intentBuilder: Intent.() -> Intent = { this },
    withFinish: Boolean = false,
) {
    startActivity(intentBuilder(Intent(this, T::class.java)))
    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    if (withFinish) finish()
}

@RequiresApi(Build.VERSION_CODES.ECLAIR)
fun Activity.finishWithAnimation() {
    finish()
    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
}