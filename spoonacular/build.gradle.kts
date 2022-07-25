plugins {
    id("kotlin")
    id("com.android.lint")
    kotlin("kapt")
    id("aphid.spotless")
}

dependencies {

    // Local projects
    implementation(projects.base)
    implementation(projects.spoonacularKotlin)

    // Dagger
    kapt(libs.google.dagger.compiler)

    // Ok-Http
    implementation(libs.okhttp.core)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.okhttp.url.connection)
}
