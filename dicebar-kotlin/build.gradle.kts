plugins {
    id("kotlin")
    id("com.android.lint")
    id("com.google.devtools.ksp").version("1.7.0-1.0.6")
    kotlin("kapt")
    id("aphid.spotless")
}

ksp {
    arg("moshi.generateProguardRules", "false")
}

dependencies {

    // Testing
    testImplementation(libs.test.junit.core)

    // Annotations
    implementation(libs.androidx.annotation)

    // Kotlin
    implementation(libs.kotlin.stdlib.jdk8)

    // Moshi
    ksp(libs.moshi.kotlin.codegen)
    implementation(libs.bundles.moshi)

    // Ok-Http
    implementation(libs.okhttp.core)
    implementation(libs.okhttp.url.connection)
}
