import com.beetlestance.aphid.buildsrc.Aphid

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
            value = project.findProperty("SPOONACULAR_API_KEY").asString()
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
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    lint {
        // Disable lintVital. Not needed since lint is run on CI
        checkReleaseBuilds = false
        // Allow lint to check dependencies
        checkDependencies = true
        // Ignore any tests
        ignoreTestSources = true
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidx.compose.get()
    }

    packagingOptions {
        // A failure occurred while executing com.android.build.gradle.internal.tasks.MergeJavaResWorkAction
        // https://github.com/Kotlin/kotlinx.coroutines/issues/2023
        resources.excludes.addAll(listOf("META-INF/AL2.0", "META-INF/LGPL2.1"))
    }
}

dependencies {

    // Local projects
    implementation(projects.base)
    implementation(projects.baseAndroid)
    implementation(projects.commonCompose)
    implementation(projects.data)
    implementation(projects.dataAndroid)
    implementation(projects.domain)
    implementation(projects.featureExplore)
    implementation(projects.featureChat)
    implementation(projects.featureProfile)
    implementation(projects.spoonacular)
    implementation(projects.spoonacularKotlin)

    // Testing
    testImplementation(libs.test.junit.core)
    testImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.test.ext.junit)

    // AndroidX
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.navigation.compose)

    // Material Design
    implementation(libs.google.material.core)
    implementation(libs.google.material.compose.theme.adapter)

    // Hilt
    implementation(libs.google.hilt.android)
    kapt(libs.androidx.hilt.compiler)
    kapt(libs.google.hilt.compiler)

    // Ok-Http
    implementation(libs.okhttp.core)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.okhttp.url.connection)
}

fun Any?.asString(): String = "\"${this ?: ""}\""
