plugins {
    id("kotlin")
    kotlin("kapt")
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
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

    // Timber
    api(libs.timber)
}
