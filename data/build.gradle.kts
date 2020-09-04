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

    // Local projects
    implementation(project(":base"))

    // Kotlin
    implementation(Libs.Kotlin.stdlib)
}
