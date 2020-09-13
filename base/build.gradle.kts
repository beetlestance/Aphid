import com.beetlestance.buildsrc.Libs

plugins {
    id("java-library")
    id("kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {

    // Kotlin
    implementation(Libs.Kotlin.stdlib)

    // Co-Routines
    api(Libs.Coroutines.core)

}