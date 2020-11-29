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
        kotlinCompilerVersion = Libs.AndroidX.Compose.ktVersion
        kotlinCompilerExtensionVersion = Libs.AndroidX.Compose.version
    }
}

dependencies {

    // Local projects
    implementation(project(":base"))
    implementation(project(":base-android"))
    implementation(project(":common-compose"))
    implementation(project(":data"))
    implementation(project(":spoonacular-kotlin"))

    // AndroidX
    implementation(Libs.AndroidX.coreKtx)
    // Testing
    testImplementation(Libs.Test.junit)
    androidTestImplementation(Libs.AndroidX.Test.junit)
    androidTestImplementation(Libs.AndroidX.Test.espressoCore)

}
