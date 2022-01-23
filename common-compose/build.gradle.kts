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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
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

    // Coil
    api(libs.coil.compose)
}
