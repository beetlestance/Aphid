import com.beetlestance.aphid.buildsrc.Aphid
import com.beetlestance.aphid.buildsrc.Libs

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
}

android {
    compileSdkVersion(Aphid.compileSdkVersion)

    defaultConfig {
        minSdkVersion(Aphid.minSdkVersion)

        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerVersion = Libs.Kotlin.version
        kotlinCompilerExtensionVersion = Libs.AndroidX.Compose.version
    }
}

dependencies {

    // Local projects
    implementation(project(":base"))
    implementation(project(":base-android"))

    implementation(Libs.AndroidX.palette)

    // Testing
    testImplementation(Libs.Test.junit)
    androidTestImplementation(Libs.AndroidX.Test.junit)
    androidTestImplementation(Libs.AndroidX.Test.espressoCore)

    // AndroidX
    implementation(Libs.AndroidX.appcompat)
    implementation(Libs.AndroidX.coreKtx)

    // Compose
    api(Libs.AndroidX.Compose.runtime)
    api(Libs.AndroidX.Compose.foundation)
    api(Libs.AndroidX.Compose.ui)
    api(Libs.AndroidX.Compose.layout)
    api(Libs.AndroidX.Compose.material)
    api(Libs.AndroidX.Compose.animation)
    api(Libs.AndroidX.Compose.tooling)
    api(Libs.AndroidX.Compose.livedata)

    // Accompanist
    api(Libs.Accompanist.coil)
    implementation(Libs.Accompanist.insets)

}
