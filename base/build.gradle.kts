import com.beetlestance.buildsrc.Libs

plugins {
    id("kotlin")
}

dependencies {

    // Kotlin
    implementation(Libs.Kotlin.stdlib)

    // Co-Routines
    api(Libs.Coroutines.core)

    // Dagger
    api(Libs.Dagger.dagger)

    // Retrofit
    api(Libs.Retrofit.retrofit)

    // Timber
    api(Libs.timber)
}
