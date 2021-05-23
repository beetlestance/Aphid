plugins {
    id("kotlin")
    kotlin("kapt")
}

val spoonacularKey = project.findProperty("SPOONACULAR_API_KEY")

kapt {
    correctErrorTypes = true
    useBuildCache = true
}

tasks.test {
    if (spoonacularKey != null) environment("TEST_API_KEY", spoonacularKey)
}

dependencies {
    // Testing
    testImplementation(libs.test.junit.core)
    testImplementation(Libs.Google.truth)

    //Annotations
    implementation(libs.androidx.annotation)

    // Kotlin
    implementation(libs.kotlin.stdlib.jdk8)

    // Retrofit
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.converter.moshi)

    //Moshi
    implementation(libs.bundles.moshi)

    // Ok-Http
    implementation(libs.bundles.okhttp)
}
