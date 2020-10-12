import com.beetlestance.buildsrc.Libs

plugins {
    id("kotlin")
}

dependencies {

    // Local Projects
    // Have to check if this dependency can go here or not, base is supposed to be independent
    implementation(project(":spoonacular-kotlin"))

    // Kotlin
    implementation(Libs.Kotlin.stdlib)

    // Co-Routines
    api(Libs.Coroutines.core)

    // Retrofit
    api(Libs.Retrofit.retrofit)

    // Timber
    api(Libs.timber)
}
