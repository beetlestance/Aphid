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

        javaCompileOptions {
            annotationProcessorOptions {
                arguments(
                    mapOf(
                        "room.schemaLocation" to "$projectDir/schemas",
                        "room.incremental" to "true"
                    )
                )
            }
        }
    }
}

dependencies {
    // Local projects
    implementation(project(":base"))
    implementation(project(":data"))
    implementation(project(":base-android"))

    // Testing
    testImplementation(Libs.Test.junit)
    androidTestImplementation(Libs.AndroidX.Test.Ext.junit)
    androidTestImplementation(Libs.AndroidX.Test.espressoCore)

    // Room
    api(Libs.AndroidX.Room.runtime)
    implementation(Libs.AndroidX.Room.ktx)
    kapt(Libs.AndroidX.Room.compiler)

    // Room Migration
    implementation(Libs.Roomigrant.library)
    kapt(Libs.Roomigrant.compiler)

    // Hilt
    implementation(Libs.Hilt.library)
    kapt(Libs.AndroidX.Hilt.compiler)
    kapt(Libs.Hilt.compiler)

}
