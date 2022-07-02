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
    namespace = "com.beetlestance.aphid.feature.chat"
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
        kotlinCompilerExtensionVersion = libs.versions.androidx.compose.kotlin.compiler.get()
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
    testImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.test.ext.junit)

    // Hilt
    implementation(libs.google.hilt.android)
    kapt(libs.androidx.hilt.compiler)
    kapt(libs.google.hilt.compiler)
}
