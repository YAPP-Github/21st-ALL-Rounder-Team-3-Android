import org.gradle.internal.impldep.com.google.gson.internal.bind.TypeAdapters.URL

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://jitpack.io") }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://jitpack.io") }
    }
}
rootProject.name = "all-rounder-3"
include(":app")
include(":presentation")
include(":domain")
include(":data")
include(":core")
