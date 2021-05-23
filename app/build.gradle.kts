import com.beetlestance.aphid.buildsrc.Aphid
import com.beetlestance.aphid.buildsrc.Libs

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
}

val useReleaseKeystore = rootProject.file("release/app-release.jks").exists()

android {
    compileSdk = Aphid.compileSdkVersion

    defaultConfig {
        applicationId = Aphid.applicationId
        minSdk = Aphid.minSdkVersion
        targetSdk = Aphid.targetSdkVersion
        versionCode = Aphid.versionCode
        versionName = Aphid.versionName

        buildConfigField(
            type = "String",
            name = "SpoonacularApiKey",
            value = "\"" + (project.findProperty("SPOONACULAR_API_KEY") ?: "") + "\""
        )

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        getByName("debug") {
            storeFile = rootProject.file("release/aphid-debug.jks")
            storePassword = "stance@debug"
            keyAlias = "Aphid-Debug"
            keyPassword = "stance@debug"
        }

        create("release") {
            if (useReleaseKeystore) {
                storeFile = rootProject.file("release/aphid-release.jks")
                storePassword = project.findProperty("RELEASE_KEY_ALIAS") as String
                keyAlias = project.findProperty("RELEASE_STORE_PASSWORD") as String
                keyPassword = project.findProperty("RELEASE_KEY_PASSWORD") as String
            }
        }
    }

    buildTypes {
        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
            versionNameSuffix = "-dev"
            applicationIdSuffix = ".debug"
        }

        getByName("release") {
            signingConfig = signingConfigs.getByName(if (useReleaseKeystore) "release" else "debug")
            isMinifyEnabled = true
            // Re-Enable once agp 7 beta 01 released
            // https://issuetracker.google.com/issues/186806256
            // isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    lint {
        // Disable lintVital. Not needed since lint is run on CI
        isCheckReleaseBuilds = false
        // Allow lint to check dependencies
        isCheckDependencies = true
        // Ignore any tests
        isIgnoreTestSources = true
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
    implementation(project(":data-android"))
    implementation(project(":domain"))
    implementation(project(":feature-explore"))
    implementation(project(":feature-chat"))
    implementation(project(":feature-profile"))
    implementation(project(":spoonacular"))
    implementation(project(":spoonacular-kotlin"))

    // Testing
    testImplementation(Libs.Test.junit)
    androidTestImplementation(Libs.AndroidX.Test.Ext.junit)
    androidTestImplementation(Libs.AndroidX.Test.espressoCore)

    // AndroidX
    implementation(Libs.AndroidX.appcompat)
    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.AndroidX.Navigation.navigation)

    // Material Design
    implementation(Libs.Google.Mdc.material)
    implementation(Libs.Google.Mdc.composeThemeAdapter)

    // Lifecycle
    implementation(Libs.AndroidX.Lifecycle.viewmodelKtx)

    // Hilt
    implementation(Libs.Hilt.library)
    kapt(Libs.AndroidX.Hilt.compiler)
    kapt(Libs.Hilt.compiler)

    // Ok-Http
    implementation(Libs.OkHttp.okhttp)
    implementation(Libs.OkHttp.loggingInterceptor)
    implementation(Libs.OkHttp.urlConnection)

}
