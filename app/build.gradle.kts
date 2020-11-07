import com.beetlestance.aphid.buildsrc.Aphid
import com.beetlestance.aphid.buildsrc.Libs

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

@Suppress("UnstableApiUsage")
// CI Always set to true for github actions so check only if flag is present
val ci: Boolean by extra(providers.environmentVariable("CI").forUseAtConfigurationTime().isPresent)

android {
    compileSdkVersion(Aphid.compileSdkVersion)

    defaultConfig {
        applicationId = Aphid.applicationId
        minSdkVersion(Aphid.minSdkVersion)
        targetSdkVersion(Aphid.targetSdkVersion)
        versionCode = Aphid.versionCode
        versionName = Aphid.versionName

        buildConfigField(
            type = "String",
            name = "SpoonacularApiKey",
            value = "\"" + (project.findProperty("SPOONACULAR_API_KEY") ?: "") + "\""
        )

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
        preDexLibraries != ci
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
    implementation(project(":common-compose"))
    implementation(project(":data"))
    implementation(project(":data-android"))
    implementation(project(":domain"))
    implementation(project(":feature-explore"))
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
