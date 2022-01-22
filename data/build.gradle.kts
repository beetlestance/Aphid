plugins {
    id("kotlin")
    id("com.android.lint")
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
