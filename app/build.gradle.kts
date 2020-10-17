import com.beetlestance.buildsrc.Aphid
import com.beetlestance.buildsrc.Libs

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
}

extra {
    var ci: Boolean by extra
    @Suppress("UnstableApiUsage")
    // CI Always set to true for github actions so check only if flag is present
    ci = providers.environmentVariable("CI").forUseAtConfigurationTime().isPresent
}

android {
    compileSdkVersion(Aphid.compileSdkVersion)

    defaultConfig {
        applicationId = Aphid.applicationId
        minSdkVersion(Aphid.minSdkVersion)
        targetSdkVersion(Aphid.targetSdkVersion)
        versionCode = Aphid.versionCode
        versionName = Aphid.versionName

        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    dexOptions {
        // Don"t pre-dex on CI
        preDexLibraries != extra.get("ci")
    }

    lintOptions {
        // Disable lintVital. Not needed since lint is run on CI
        isCheckReleaseBuilds = false
        // Allow lint to check dependencies
        isCheckDependencies = true
        // Ignore any tests
        isIgnoreTestSources = true

        // Lint doesn"t seem to handle Kotlin int types + string format very well
        disable("StringFormatMatches")

        // The crash seems to involve the detector
        // androidx.activity.lint.ActivityResultFragmentVersionDetector.
        // Added a new InvalidFragmentVersionForActivityResult lint check that verifies that you are
        // using Fragment 1.3.0-alpha07 when using the Activity Result API, avoiding runtime crashes due
        // to “invalid request code” issues and non-functioning permission requests caused by using
        // older versions of Fragments.
        disable("InvalidFragmentVersionForActivityResult")
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
    implementation(project(":data"))
    implementation(project(":base-android"))
    implementation(project(":data-android"))
    implementation(project(":domain"))
    implementation(project(":spoonacular"))
    implementation(project(":spoonacular-kotlin"))

    // Lint checks
    lintChecks(project(":mdc-theme-lint"))

    // Testing
    testImplementation(Libs.Test.junit)
    androidTestImplementation(Libs.AndroidX.Test.junit)
    androidTestImplementation(Libs.AndroidX.Test.espressoCore)

    // AndroidX
    implementation(Libs.AndroidX.appcompat)
    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.AndroidX.Fragment.fragmentKtx)

    // Material Design
    implementation(Libs.Google.Mdc.material)
    implementation(Libs.Google.Mdc.composeThemeAdapter)

    // Compose
    implementation(Libs.AndroidX.Compose.runtime)
    implementation(Libs.AndroidX.Compose.foundation)
    implementation(Libs.AndroidX.Compose.ui)
    implementation(Libs.AndroidX.Compose.layout)
    implementation(Libs.AndroidX.Compose.material)
    implementation(Libs.AndroidX.Compose.animation)
    implementation(Libs.AndroidX.Compose.tooling)
    implementation(Libs.AndroidX.Compose.livedata)

    // Coil
    implementation(Libs.Coil.coil)

    // Kotlin
    implementation(Libs.Kotlin.stdlib)
    implementation(Libs.AndroidX.Lifecycle.viewmodelKtx)

    // Hilt
    implementation(Libs.Hilt.library)
    implementation(Libs.AndroidX.Hilt.viewmodel)
    kapt(Libs.AndroidX.Hilt.compiler)
    kapt(Libs.Hilt.compiler)

    // Ok-Http
    implementation(Libs.OkHttp.okhttp)
    implementation(Libs.OkHttp.loggingInterceptor)
    implementation(Libs.OkHttp.urlConnection)

}
