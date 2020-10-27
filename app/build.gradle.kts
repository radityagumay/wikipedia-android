import Dependency.ANDROID_ACTIVITY_KTX
import Dependency.ANDROID_ANNOTATION
import Dependency.ANDROID_APP_COMPAT
import Dependency.ANDROID_CONSTRAINT_LAYOUT
import Dependency.ANDROID_CORE_KTX
import Dependency.ANDROID_FRAGMENT_KTX
import Dependency.ANDROID_LIFECYCLE_EXT
import Dependency.ANDROID_MATERIAL
import Dependency.ANDROID_NAV_FRAGMENT
import Dependency.ANDROID_NAV_UI
import Dependency.AndroidTest
import Dependency.COIL
import Dependency.FLOW_BINDING
import Dependency.HILT_ANDROID
import Dependency.HILT_COMMON
import Dependency.HILT_VIEWMODEL
import Dependency.KOTLIN
import Dependency.Kapt
import Dependency.Network
import Dependency.UnitTest

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.2")

    defaultConfig {
        applicationId = "com.raditya.wikipedia"
        minSdkVersion(19)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
        getByName("debug") {
            versionNameSuffix = "-debug"
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        val options = this as org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
        options.jvmTarget = "1.8"
    }
}

dependencies {
    // android foundation
    implementation(KOTLIN)
    implementation(ANDROID_CORE_KTX)
    implementation(ANDROID_APP_COMPAT)
    implementation(ANDROID_MATERIAL)
    implementation(ANDROID_ANNOTATION)
    implementation(ANDROID_CONSTRAINT_LAYOUT)
    implementation(ANDROID_LIFECYCLE_EXT)
    implementation(ANDROID_FRAGMENT_KTX)
    implementation(ANDROID_ACTIVITY_KTX)

    // fragment nav
    implementation(ANDROID_NAV_FRAGMENT)
    implementation(ANDROID_NAV_UI)

    // image
    implementation(COIL)

    // hilt
    implementation(HILT_ANDROID)
    implementation(HILT_COMMON)
    implementation(HILT_VIEWMODEL)
    kapt(Kapt.HILT_COMPILER)
    kapt(Kapt.HILT_ANDROID_COMPILER)
    kaptAndroidTest(Kapt.HILT_ANDROID_COMPILER)
    androidTestImplementation(AndroidTest.HILT_ANDROID_TESTING)

    // flow binding
    implementation(FLOW_BINDING)

    // network
    implementation(Network.RETROFIT)
    implementation(Network.RETROFIT_CONVERTER_GSON)
    implementation(Network.OKHTTP)

    testImplementation(UnitTest.JUNIT)
    androidTestImplementation(AndroidTest.ANDROID_JUNIT)
    androidTestImplementation(AndroidTest.ANDROID_ESPRESSO_CORE)
}