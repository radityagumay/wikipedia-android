object Dependency {
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib:1.4.10"
    const val ANDROID_CORE_KTX = "androidx.core:core-ktx:1.3.2"
    const val ANDROID_APP_COMPAT = "androidx.appcompat:appcompat:1.2.0"
    const val ANDROID_MATERIAL = "com.google.android.material:material:1.2.1"
    const val ANDROID_ANNOTATION = "androidx.annotation:annotation:1.1.0"
    const val ANDROID_CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:2.0.2"
    const val ANDROID_LIFECYCLE_EXT = "androidx.lifecycle:lifecycle-extensions:2.2.0"
    const val ANDROID_FRAGMENT_KTX = "androidx.fragment:fragment-ktx:1.3.0-beta01"
    const val ANDROID_ACTIVITY_KTX = "androidx.activity:activity-ktx:1.2.0-beta01"
    const val ANDROID_NAV_UI = "androidx.navigation:navigation-ui-ktx:2.3.1"
    const val ANDROID_NAV_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:2.3.1"

    const val HILT_ANDROID = "com.google.dagger:hilt-android:2.29.1-alpha"
    const val HILT_COMMON = "androidx.hilt:hilt-common:1.0.0-alpha02"
    const val HILT_VIEWMODEL = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha02"

    const val FLOW_BINDING = "io.github.reactivecircus.flowbinding:flowbinding-android:1.0.0-alpha04"

    const val COIL = "io.coil-kt:coil:1.0.0-rc3"

    object Network {
        const val RETROFIT = "com.squareup.retrofit2:retrofit:2.9.0"
        const val RETROFIT_CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:2.9.0"
        const val OKHTTP = "com.squareup.okhttp3:okhttp:4.8.1"
    }

    object Kapt {
        const val HILT_ANDROID_COMPILER = "com.google.dagger:hilt-android-compiler:2.29.1-alpha"
        const val HILT_COMPILER = "androidx.hilt:hilt-compiler:1.0.0-alpha02"
    }


    object UnitTest {
        const val JUNIT = "junit:junit:4.13"
    }

    object AndroidTest {
        const val ANDROID_JUNIT = "androidx.test.ext:junit:1.1.2"
        const val ANDROID_ESPRESSO_CORE = "androidx.test.espresso:espresso-core:3.3.0"

        const val HILT_ANDROID_TESTING = "com.google.dagger:hilt-android-testing:2.29.1-alpha"
    }
}
