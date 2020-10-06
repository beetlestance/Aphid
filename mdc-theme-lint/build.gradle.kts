import com.beetlestance.buildsrc.Libs

plugins {
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

dependencies {

    // Kotlin
    compileOnly(Libs.Kotlin.jdk7)

    compileOnly(Libs.Lint.api)
    compileOnly(Libs.Lint.checks)
}
