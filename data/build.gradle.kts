plugins {
    id("kotlin")
    kotlin("kapt")
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
}

dependencies {

    // Local projects
    implementation(projects.base)
    implementation(projects.spoonacularKotlin)

    // Room
    api(libs.androidx.room.common)
}
