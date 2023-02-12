package com.yapp.timitimi.presentation.helper

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.google.firebase.dynamiclinks.DynamicLink
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import com.google.firebase.dynamiclinks.PendingDynamicLinkData
import com.yapp.timitimi.presentation.BuildConfig
import dagger.hilt.android.qualifiers.ActivityContext
import timber.log.Timber
import javax.inject.Inject

class FirebaseDynamicLinkHelper @Inject constructor(
    @ActivityContext private val context: Context
) {
    private val activity: Activity
        get() = context as Activity

    fun createDynamicLink(code: String) {
        FirebaseDynamicLinks.getInstance().createDynamicLink()
            .setLink(getProjectDeepLink(code))
            .setDomainUriPrefix(BuildConfig.TIMITIMI_DYNAMIC_LINK)
            .setAndroidParameters(
                DynamicLink.AndroidParameters.Builder(activity.packageName)
                    .setMinimumVersion(1)
                    .build()
            )
            .buildShortDynamicLink()
            .addOnCompleteListener(
                activity
            ) { task ->
                if (task.isSuccessful) {
                    val shortLink = task.result.shortLink
                    try {
                        val sendIntent = Intent()
                        sendIntent.action = Intent.ACTION_SEND
                        sendIntent.putExtra(Intent.EXTRA_TEXT, shortLink.toString())
                        sendIntent.type = "text/plain"
                        activity.startActivity(Intent.createChooser(sendIntent, "Share"))
                    } catch (ignored: ActivityNotFoundException) {
                    }
                } else {
                    Timber.e(task.exception?.localizedMessage)
                }
            }
    }

    private fun getProjectDeepLink(projectCode: String) : Uri {
        return Uri.parse("timitimi://invitedCode=$projectCode");
    }

    fun handleDynamicLinks() {
        FirebaseDynamicLinks.getInstance()
            .getDynamicLink(activity.intent)
            .addOnSuccessListener { pendingDynamicLinkData: PendingDynamicLinkData? ->
                if (pendingDynamicLinkData == null) return@addOnSuccessListener

                val deepLink = pendingDynamicLinkData.link
                Timber.d("deepLink is: $deepLink")
            }
            .addOnFailureListener {
                Timber.e(it.localizedMessage)
            }
    }
}