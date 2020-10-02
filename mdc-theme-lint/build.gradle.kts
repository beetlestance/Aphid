import com.beetlestance.buildsrc.Libs

plugins {
    id("java-library")
    id("kotlin")
    id("com.android.lint")
}

lintOptions {
    htmlReport = true
    htmlOutput = file("lint-report.html")
    textReport = true
    isAbsolutePaths = false
    isIgnoreTestSources = true
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {

    // Kotlin
    compileOnlyApi(Libs.Kotlin.jdk7)

    compileOnlyApi(Libs.Lint.api)
    compileOnlyApi(Libs.Lint.checks)
}
