import com.beetlestance.buildsrc.Aphid
import com.beetlestance.buildsrc.Libs

plugins {
    id("com.android.application")
    kotlin("android")
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
        sourceCompatibility = JavaVersion.VERSION_12
        targetCompatibility = JavaVersion.VERSION_12
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
    }

    buildFeatures {
        // We need to keep this enabled because submodules use it
        dataBinding = true

        viewBinding = true
    }
}

dependencies {

    // Local projects
    implementation(project(":base"))
    implementation(project(":data"))
    implementation((":base-android"))
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
    implementation(Libs.AndroidX.constraintlayout)

    // Material Design
    implementation(Libs.Google.material)

    // Kotlin
    implementation(Libs.Kotlin.stdlib)
}
