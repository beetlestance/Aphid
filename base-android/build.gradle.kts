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
    namespace = "com.beetlestance.aphid.base.android"
    compileSdk = Aphid.compileSdkVersion

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

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
    testImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.test.ext.junit)

    // AndroidX
    api(libs.androidx.lifecycle.viewmodel.ktx)

    // Material Design
    implementation(libs.google.material.core)

    // Timber
    api(libs.timber)
}
