import com.beetlestance.aphid.buildsrc.Libs

plugins {
    id("kotlin")
}

dependencies {

    // Local projects
    implementation(project(":base"))
    implementation(project(":spoonacular-kotlin"))

    // Room
    api(Libs.AndroidX.Room.common)
}
