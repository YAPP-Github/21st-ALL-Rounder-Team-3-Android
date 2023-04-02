package com.yapp.timitimi.app

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        KakaoSdk.init(this, BuildConfig.KAKAO_KEY)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
