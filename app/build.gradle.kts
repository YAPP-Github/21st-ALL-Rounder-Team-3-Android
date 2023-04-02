@file:Suppress("DSL_SCOPE_VIOLATION", "UnstableApiUsage")

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.yapp.timitimi.app"
    compileSdk = 33

    signingConfigs {
        create("release") {
            val configFile = project.rootProject.file("signingconfig.properties")
            val properties = Properties()
            properties.load(FileInputStream(configFile))

            storeFile = project.rootProject.file(properties["storeFile"] as String)
            storePassword = properties["storePassword"] as String
            keyAlias = properties["keyAlias"] as String
            keyPassword = properties["keyPassword"] as String
        }
    }

    defaultConfig {
        with(libs.versions) {
            applicationId = app.id.get()
            minSdk = min.sdk.get().toInt()
            targetSdk = target.sdk.get().toInt()
            versionCode = app.version.code.get().toInt()
            versionName = app.version.name.get()
        }
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
        dataBinding = true
    }

    buildTypes {
        debug {
            buildConfigField("String", "KAKAO_KEY", "\"4a81c848b641ec8de75cbf5f529d13cc\"")
        }

        release {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(project(":presentation"))
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(libs.timber)
    implementation(libs.hilt.android)
    kapt(libs.hilt.kapt)
    implementation("com.jakewharton.threetenabp:threetenabp:1.3.0")
    implementation("com.kakao.sdk:v2-user:2.13.0")
}