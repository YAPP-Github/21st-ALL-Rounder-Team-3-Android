@file:Suppress("DSL_SCOPE_VIOLATION", "UnstableApiUsage")

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.kotlin.kapt)
}

android {
    compileSdk = libs.versions.compile.sdk.get().toInt()
    namespace = "com.yapp.data"
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
}