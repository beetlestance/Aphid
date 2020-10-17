import com.beetlestance.buildsrc.Libs

plugins {
    id("kotlin")
    kotlin("kapt")
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
}

dependencies {

    // Local projects
    implementation(project(":base"))
    implementation(project(":spoonacular-kotlin"))

    // Kotlin
    implementation(Libs.Kotlin.stdlib)

    // Dagger
    kapt(Libs.Dagger.compiler)

    // Ok-Http
    implementation(Libs.OkHttp.okhttp)
    implementation(Libs.OkHttp.loggingInterceptor)
    implementation(Libs.OkHttp.urlConnection)
}
