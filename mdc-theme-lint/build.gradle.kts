plugins {
    id("kotlin")
    id("com.android.lint")
    kotlin("kapt")
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
}

lintOptions {
    htmlReport = true
    htmlOutput = file("lint-report.html")
    textReport = true
    isAbsolutePaths = false
    isIgnoreTestSources = true
}

dependencies {

    // Kotlin
    compileOnly(libs.kotlin.stdlib.jdk8)

    // Lint
    compileOnly(libs.bundles.android.tools.lint)
}
