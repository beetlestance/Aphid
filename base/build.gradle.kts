import com.beetlestance.buildsrc.Libs

plugins {
    id("kotlin")
}

dependencies {

    // Local Projects
    implementation(project(":spoonacular-kotlin"))

    // Kotlin
    implementation(Libs.Kotlin.stdlib)

    // Co-Routines
    api(Libs.Coroutines.core)

    // Retrofit
    api(Libs.Retrofit.retrofit)

}
