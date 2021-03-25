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

    // Remove when animated vector drawable support
    implementation(Libs.AndroidX.appcompat)
    implementation(Libs.AndroidX.coreKtx)

    // Material Design
    implementation(Libs.Google.Mdc.material)
    implementation(Libs.Google.Mdc.composeThemeAdapter)

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
    api(Libs.Google.Accompanist.coil)
    api(Libs.Google.Accompanist.insets)
    api(Libs.Google.Accompanist.flowlayout)

    // Remove once hilt support saved state registry owner for compose
    implementation(Libs.AndroidX.Navigation.navigation)
    implementation(Libs.AndroidX.Hilt.navigation)

}
