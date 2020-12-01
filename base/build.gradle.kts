import com.beetlestance.aphid.buildsrc.Libs

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
    api(Libs.Kotlin.stdlib)

    // Co-Routines
    api(Libs.Coroutines.core)

    // Dagger
    api(Libs.Dagger.dagger)

    // Retrofit
    api(Libs.Retrofit.retrofit)

    // Timber
    api(Libs.timber)
}
