import com.beetlestance.buildsrc.Aphid
import com.beetlestance.buildsrc.Libs

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
}

extra {
    var ci: Boolean by extra
    ci = System.getenv("CI") == "true"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
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
        dataBinding = true
    }
}

dependencies {

    // Local projects
    implementation(project(":base"))
    implementation(project(":data"))
    implementation(project(":base-android"))
    implementation(project(":data-android"))
    implementation(project(":domain"))
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
    implementation(Libs.AndroidX.constraintlayout)

    // Material Design
    implementation(Libs.Google.material)

    // Kotlin
    implementation(Libs.Kotlin.stdlib)
}
