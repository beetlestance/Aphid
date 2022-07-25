plugins {
    id("kotlin")
    id("com.android.lint")
    kotlin("kapt")
}

dependencies {

    // Kotlin
    api(libs.kotlin.stdlib.jdk8)

    // Co-Routines
    api(libs.kotlinx.coroutines.core)

    // Dagger
    api(libs.google.dagger.core)

    // Retrofit
    api(libs.retrofit.core)
}
