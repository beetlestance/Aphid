plugins {
    id("kotlin")
    kotlin("kapt")
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
}

dependencies {
    // Testing
    testImplementation(libs.test.junit.core)

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
