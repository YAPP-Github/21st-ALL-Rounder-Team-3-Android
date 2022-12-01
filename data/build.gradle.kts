@file:Suppress("DSL_SCOPE_VIOLATION", "UnstableApiUsage")

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.kotlin.kapt)
}

android {
    compileSdk = 33
    namespace = "com.yapp.data"
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(libs.kotlin.coroutines)
    implementation(libs.bundles.network)
    implementation(libs.hilt.android)
    implementation(libs.timber)
    kapt(libs.hilt.kapt)
}