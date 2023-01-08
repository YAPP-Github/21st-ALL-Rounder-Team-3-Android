@file:Suppress("DSL_SCOPE_VIOLATION", "UnstableApiUsage")

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    compileSdk = libs.versions.compile.sdk.get().toInt()
    namespace = "com.yapp.timitimi.data"

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"https://timitimi.site\"")
        }
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "BASE_URL", "\"https://timitimi.site\"")
        }
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.majorVersion
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(libs.kotlin.coroutines)
    implementation(libs.bundles.network)
    implementation(libs.hilt.android)
    kapt(libs.hilt.kapt)
    implementation(libs.timber)

    implementation(libs.bundles.network)
    debugImplementation(libs.chuncker.library)
    releaseImplementation(libs.chuncker.library.no.op)
}