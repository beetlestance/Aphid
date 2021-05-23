import com.beetlestance.aphid.buildsrc.Aphid

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
}

android {
    compileSdk = Aphid.compileSdkVersion

    defaultConfig {
        minSdk = Aphid.minSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        javaCompileOptions {
            annotationProcessorOptions {
                arguments(
                    mapOf(
                        "room.schemaLocation" to "$projectDir/schemas",
                        "room.incremental" to "true"
                    )
                )
            }
        }
    }
}

dependencies {
    // Local projects
    implementation(projects.base)
    implementation(projects.data)
    implementation(projects.baseAndroid)

    // Testing
    testImplementation(libs.test.junit.core)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)

    // Room
    api(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    kapt(libs.androidx.room.compiler)

    // Room Migration
    implementation(libs.roomigrant.core)
    kapt(libs.roomigrant.compiler)

    // Hilt
    implementation(libs.google.hilt.android)
    kapt(libs.androidx.hilt.compiler)
    kapt(libs.google.hilt.compiler)

}
