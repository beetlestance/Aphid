plugins {
    id("aphid.android.library")
    kotlin("kapt")
    id("aphid.spotless")
}

android {
    namespace = "com.beetlestance.aphid.base.android"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {

    // Local projects
    implementation(projects.base)
    implementation(projects.data)

    // Testing
    testImplementation(libs.test.junit.core)
    testImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.test.ext.junit)

    // AndroidX
    api(libs.androidx.lifecycle.viewmodel.ktx)

    // Material Design
    implementation(libs.google.material.core)

    // Timber
    api(libs.timber)
}
