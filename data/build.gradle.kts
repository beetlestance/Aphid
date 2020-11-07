import com.beetlestance.aphid.buildsrc.Libs

plugins {
    id("kotlin")
}

dependencies {

    // Local projects
    api(project(":base"))
    implementation(project(":spoonacular-kotlin"))

    // Room
    api(Libs.AndroidX.Room.common)

    // Kotlin
    implementation(Libs.Kotlin.stdlib)
}
