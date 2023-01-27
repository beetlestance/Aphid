// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:8.1.0-alpha02")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.0")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.42")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

plugins {
    // gradlew spotlessApply
    id("com.diffplug.spotless") version "6.8.0"
    // gradlew dependencyUpdates
    id("com.github.ben-manes.versions") version "0.42.0"
}

subprojects {

    plugins.apply("com.diffplug.spotless")

    configure<com.diffplug.gradle.spotless.SpotlessExtension> {
        kotlin {
            target("**/*.kt")
            targetExclude("$buildDir/**/*.kt")
            targetExclude("bin/**/*.kt")

            ktlint(libs.versions.ktlint.get())
                .setUseExperimental(true)
                // Allow extensions file with single function by disabling rule "filename" where
                // files containing only one top level domain should be named according to function.
                .editorConfigOverride(mapOf("disabled_rules" to "filename"))
            licenseHeaderFile(rootProject.file("spotless/copyright.kt"))
        }

        kotlinGradle {
            target("*.gradle.kts") // default target for kotlinGradle
            ktlint(libs.versions.ktlint.get())
        }
    }

    tasks.withType<JavaCompile> {
        // Set to 14 once intellij upgrade asm 7.0 to 9.0,
        // now org.objectweb.asm.ClassReader has major opt code 12
        sourceCompatibility = JavaVersion.VERSION_11.toString()
        targetCompatibility = JavaVersion.VERSION_11.toString()
        //enable compilation in a separate daemon process
        options.isFork = true
    }

    tasks.withType<Test> {
        maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).takeIf { it > 0 } ?: 1
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            // Treat all Kotlin warnings as errors
            gradle.taskGraph.whenReady {
                // allWarningsAsErrors = hasTask(":app:assembleDebug").not()
                allWarningsAsErrors = false
            }

            // Enable experimental coroutines APIs, including Flow
            if (project.name != "spoonacular-kotlin" && project.name != "dicebar-kotlin") {
                freeCompilerArgs = freeCompilerArgs + listOf(
                    "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                    "-opt-in=kotlinx.coroutines.FlowPreview"
                )
            }

            freeCompilerArgs = freeCompilerArgs + listOf(
                "-opt-in=kotlin.Experimental",
                "-opt-in=kotlin.RequiresOptIn"
            )

            // Set JVM target to 11
            // Set to 15 once intellij upgrade asm 7.0 to 9.0,
            // now org.objectweb.asm.ClassReader has major opt code 12
            jvmTarget = "11"
        }
    }
}

tasks.withType<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask> {
    checkForGradleUpdate = true
    outputFormatter = "html,txt"
    outputDir = "./reports/dependency-updates"
    reportfileName = "report"
}

apply(from = "gradle/projectDependencyGraph.gradle")
