import Dependency.ANDROID_ACTIVITY_KTX
import Dependency.ANDROID_ANNOTATION
import Dependency.ANDROID_APP_COMPAT
import Dependency.ANDROID_CONSTRAINT_LAYOUT
import Dependency.ANDROID_CORE_KTX
import Dependency.ANDROID_FRAGMENT_KTX
import Dependency.ANDROID_LIFECYCLE_EXT
import Dependency.ANDROID_MATERIAL
import Dependency.AndroidTest
import Dependency.HILT_ANDROID
import Dependency.HILT_COMMON
import Dependency.HILT_VIEWMODEL
import Dependency.KOTLIN
import Dependency.Kapt
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
/*
    implementation(ANDROID_FRAGMENT_KTX)
    implementation(ANDROID_ACTIVITY_KTX)
*/

    implementation("androidx.activity:activity-ktx:1.2.0-alpha04")
    implementation("androidx.fragment:fragment-ktx:1.3.0-alpha04")

    // fragment nav
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.0")

    // image
    implementation("io.coil-kt:coil:1.0.0-rc3")

    // hilt
    implementation(HILT_ANDROID)
    implementation(HILT_COMMON)
    implementation(HILT_VIEWMODEL)
    add("kapt", Kapt.HILT_ANDROID_COMPILER)
    add("kapt", Kapt.HILT_COMPILER)
    add("kaptAndroidTest", Kapt.HILT_ANDROID_COMPILER)
    androidTestImplementation(AndroidTest.HILT_ANDROID_TESTING)

    // flowbinding
    implementation("io.github.reactivecircus.flowbinding:flowbinding-android:1.0.0-alpha04")

    // network
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.8.1")

    testImplementation(UnitTest.JUNIT)
    androidTestImplementation(AndroidTest.ANDROID_JUNIT)
    androidTestImplementation(AndroidTest.ANDROID_ESPRESSO_CORE)
}