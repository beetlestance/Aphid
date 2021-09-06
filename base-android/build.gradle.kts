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
    }
}

dependencies {

    // Local projects
    implementation(projects.base)
    implementation(projects.data)

    // Testing
    testImplementation(libs.test.junit.core)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)

    // AndroidX
    api(libs.androidx.lifecycle.viewmodel.ktx)

    // Material Design
    implementation(libs.google.material.core)
}
