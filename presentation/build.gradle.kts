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
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            buildConfigField("String", "TIMITIMI_DYNAMIC_LINK", "\"https://timitimi.page.link\"")
            buildConfigField("String", "KAKAO_LOGIN_AUTH_URL", "\"http://timitimi.site/oauth2/authorization/kakao\"")
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
    kapt(libs.hilt.kapt)
    implementation(libs.timber)

    implementation(platform(libs.firebase.bom))
    implementation(libs.bundles.firebase)
}