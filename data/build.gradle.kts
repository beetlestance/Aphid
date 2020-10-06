import com.beetlestance.buildsrc.Libs

plugins {
    id("kotlin")
}

dependencies {

    // Local projects
    implementation(project(":base"))

    // Kotlin
    implementation(Libs.Kotlin.stdlib)
}
