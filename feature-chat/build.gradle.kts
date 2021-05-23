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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidx.compose.get()
    }
}

dependencies {

    // Local projects
    implementation(projects.base)
    implementation(projects.baseAndroid)
    implementation(projects.commonCompose)
    implementation(projects.data)
    implementation(projects.domain)
    implementation(projects.spoonacularKotlin)

    // Testing
    testImplementation(libs.test.junit.core)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)

    // Hilt
    implementation(libs.google.hilt.android)
    kapt(libs.androidx.hilt.compiler)
    kapt(libs.google.hilt.compiler)
}
