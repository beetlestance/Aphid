plugins {
    id("kotlin")
    id("com.android.lint")
    id("com.google.devtools.ksp").version("1.7.0-1.0.6")
    kotlin("kapt")
}

val spoonacularKey = project.findProperty("SPOONACULAR_API_KEY")

ksp {
    arg("moshi.generateProguardRules", "false")
}

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
    testImplementation(libs.google.truth)

    // Annotations
    implementation(libs.androidx.annotation)

    // Kotlin
    implementation(libs.kotlin.stdlib.jdk8)

    // Retrofit
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.converter.moshi)

    // Moshi
    ksp(libs.moshi.kotlin.codegen)
    implementation(libs.bundles.moshi)

    // Ok-Http
    implementation(libs.bundles.okhttp)
}
