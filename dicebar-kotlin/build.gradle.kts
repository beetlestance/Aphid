plugins {
    id("kotlin")
    id("com.android.lint")
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

    //Moshi
    implementation(libs.bundles.moshi)

    // Ok-Http
    implementation(libs.okhttp.core)
    implementation(libs.okhttp.url.connection)
}
