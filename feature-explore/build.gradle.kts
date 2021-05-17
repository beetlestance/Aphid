import com.beetlestance.aphid.buildsrc.Aphid
import com.beetlestance.aphid.buildsrc.Libs

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
        kotlinCompilerExtensionVersion = Libs.AndroidX.Compose.version
    }
}

dependencies {

    // Local projects
    implementation(project(":base"))
    implementation(project(":base-android"))
    implementation(project(":common-compose"))
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":spoonacular-kotlin"))

    // AndroidX
    implementation(Libs.AndroidX.coreKtx)

    // Compose
    implementation(Libs.AndroidX.constraintLayout)

    // Testing
    testImplementation(Libs.Test.junit)
    androidTestImplementation(Libs.AndroidX.Test.Ext.junit)
    androidTestImplementation(Libs.AndroidX.Test.espressoCore)

    // AndroidX
    implementation(Libs.AndroidX.coreKtx)

    // Lifecycle
    implementation(Libs.AndroidX.Lifecycle.viewmodelKtx)

    // Hilt
    implementation(Libs.Hilt.library)
    kapt(Libs.AndroidX.Hilt.compiler)
    kapt(Libs.Hilt.compiler)
}
