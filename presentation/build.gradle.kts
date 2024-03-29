@file:Suppress("DSL_SCOPE_VIOLATION", "UnstableApiUsage")

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.kotlin.kapt)
    id(libs.plugins.firebase.services.get().pluginId)
}

android {
    namespace = "com.yapp.timitimi.presentation"
    compileSdk = libs.versions.compile.sdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.min.sdk.get().toInt()
        targetSdk = libs.versions.target.sdk.get().toInt()
    }

    buildTypes {
        debug {
            buildConfigField("String", "TIMITIMI_DYNAMIC_LINK", "\"https://timitimi.page.link\"")
            buildConfigField("String", "KAKAO_LOGIN_AUTH_URL", "\"http://timitimi.site/oauth2/authorization/kakao\"")
            buildConfigField("String", "WEB_URL", "\"http://timitimi-front.s3-website.ap-northeast-2.amazonaws.com\"")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            buildConfigField("String", "TIMITIMI_DYNAMIC_LINK", "\"https://timitimi.page.link\"")
            buildConfigField("String", "KAKAO_LOGIN_AUTH_URL", "\"http://timitimi.site/oauth2/authorization/kakao\"")
            buildConfigField("String", "WEB_URL", "\"http://timitimi-front.s3-website.ap-northeast-2.amazonaws.com\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.majorVersion
    }
    buildFeatures {
        compose = true
        viewBinding = true
        dataBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":core"))
    implementation(project(":designsystem"))
    implementation(libs.kotlin.coroutines)
    implementation(libs.kotlin.collections.immutable)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.ktx)
    implementation(libs.bundles.accompanist)
    implementation(libs.hilt.android)
    implementation(libs.androidx.appcompat)
    implementation("com.google.android.material:material:1.7.0")
    implementation("com.jakewharton.threetenabp:threetenabp:1.3.0")
    implementation("androidx.navigation:navigation-compose:2.5.3")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.databinding:databinding-common:7.4.1")
    implementation("androidx.databinding:viewbinding:7.4.1")
    implementation("com.github.bumptech.glide:glide:4.14.2")
    annotationProcessor("com.github.bumptech.glide:compiler:4.14.2")
    kapt(libs.hilt.kapt)
    implementation(libs.timber)

    implementation(platform(libs.firebase.bom))
    implementation(libs.bundles.firebase)
    implementation("com.kakao.sdk:v2-user:2.13.0")
}