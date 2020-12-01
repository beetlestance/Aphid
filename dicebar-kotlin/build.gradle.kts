import com.beetlestance.aphid.buildsrc.Libs

plugins {
    id("kotlin")
    kotlin("kapt")
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
}

dependencies {

    // Testing
    testImplementation(Libs.Test.junit)

    //Annotations
    implementation(Libs.AndroidX.androidAnnotation)

    // Kotlin
    implementation(Libs.Kotlin.stdlib)
}
