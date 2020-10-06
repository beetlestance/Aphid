import com.beetlestance.buildsrc.Libs

plugins {
    id("kotlin")
}

dependencies {

    // Local projects
    implementation(project(":base"))
    implementation(project(":spoonacular-kotlin"))

    // Kotlin
    implementation(Libs.Kotlin.stdlib)
}
