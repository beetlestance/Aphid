import com.beetlestance.buildsrc.Libs

plugins {
    id("java-library")
    id("kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {

    // Kotlin
    implementation(Libs.Kotlin.stdlib)

    // Co-Routines
    api(Libs.Coroutines.core)

}
