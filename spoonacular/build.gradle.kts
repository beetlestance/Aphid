import com.beetlestance.buildsrc.Libs

plugins {
    id("kotlin")
}

dependencies {

    // Local projects
    implementation(project(":spoonacular-kotlin"))

    // Kotlin
    implementation(Libs.Kotlin.stdlib)

    // Dagger
    implementation(Libs.Dagger.compiler)

    // Ok-Http
    implementation(Libs.OkHttp.okhttp)
    implementation(Libs.OkHttp.loggingInterceptor)
    implementation(Libs.OkHttp.urlConnection)
}
