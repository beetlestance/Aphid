plugins {
    id("kotlin")
    id("com.android.lint")
    kotlin("kapt")
    id("aphid.spotless")
}

dependencies {

    // Local projects
    implementation(projects.base)
    implementation(projects.spoonacularKotlin)

    // Room
    api(libs.androidx.room.common)
}
