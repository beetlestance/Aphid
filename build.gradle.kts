import java.net.URI

// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        google()
        jcenter()
        mavenCentral()
    }
    dependencies {
        classpath(com.beetlestance.buildsrc.Libs.androidGradlePlugin)
        classpath(com.beetlestance.buildsrc.Libs.Kotlin.gradlePlugin)
        classpath(com.beetlestance.buildsrc.Libs.Kotlin.extensions)
        classpath(com.beetlestance.buildsrc.Libs.Hilt.gradlePlugin)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

plugins {
    id("com.diffplug.spotless") version "5.6.1"
    id("com.github.ben-manes.versions") version "0.33.0"
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()

        // Used for Roomigrant
        maven { url = URI("https://jitpack.io") }
    }
}

subprojects {

    plugins.apply("com.diffplug.spotless")

    configure<com.diffplug.gradle.spotless.SpotlessExtension> {
        kotlin {
            target("**/*.kt")
            targetExclude("$buildDir/**/*.kt")
            targetExclude("bin/**/*.kt")

            ktlint(com.beetlestance.buildsrc.Versions.ktlint)
            /* ./gradlew spotlessApply */
            licenseHeaderFile(rootProject.file("spotless/copyright.kt"))
        }
    }

    tasks.withType<JavaCompile> {
        // Remove once kotlin supports jvm 15
        sourceCompatibility = JavaVersion.VERSION_1_8.toString()
        targetCompatibility = JavaVersion.VERSION_1_8.toString()
        options.isFork = true
    }

    tasks.withType<Test> {
        maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).takeIf { it > 0 } ?: 1
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            // Treat all Kotlin warnings as errors
            allWarningsAsErrors = true

            // Enable experimental coroutines APIs, including Flow
            if (project.name != "mdc-theme-lint" && project.name != "spoonacular-kotlin") {
                freeCompilerArgs = freeCompilerArgs + listOf(
                    "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                    "-Xopt-in=kotlinx.coroutines.FlowPreview"
                )
            }

            freeCompilerArgs = freeCompilerArgs + listOf(
                "-Xopt-in=kotlin.Experimental",
                "-Xallow-jvm-ir-dependencies",
                "-Xjvm-default=all"
            )

            // Set JVM target to 1.8
            jvmTarget = "1.8"
        }
    }
}
