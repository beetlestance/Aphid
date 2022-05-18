plugins {
    id("kotlin")
    id("com.android.lint")
    id("com.google.devtools.ksp").version("1.6.21-1.0.5")
    kotlin("kapt")
}

ksp {
    arg("moshi.generateProguardRules", "false")
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
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
