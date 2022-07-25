plugins {
    id("aphid.android.library")
    id("aphid.android.library.compose")
    kotlin("kapt")
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
}

android {
    namespace = "com.beetlestance.aphid.common.compose"

    defaultConfig {

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {

    // Local projects
    implementation(projects.base)
    implementation(projects.baseAndroid)

    implementation(libs.androidx.palette)

    // Testing
    testImplementation(libs.test.junit.core)
    testImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.test.ext.junit)

    // Remove when animated vector drawable support
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)

    // Material Design
    implementation(libs.google.material.core)
    implementation(libs.google.material.compose.theme.adapter)

    // Compose
    api(libs.bundles.androidx.compose)

    // Accompanist
    api(libs.bundles.google.accompanist)

    // Hilt Nav ViewModel
    api(libs.androidx.hilt.navigation.compose)

    // Compose ViewModel Extensions
    api(libs.androidx.lifecycle.viewmodel.compose)
    api(libs.androidx.lifecycle.runtime.compose)

    // Coil
    api(libs.coil.compose)
}
