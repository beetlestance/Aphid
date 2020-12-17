// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        google()
        jcenter()
        mavenCentral()
    }

    dependencies {
        classpath(com.beetlestance.aphid.buildsrc.Libs.androidGradlePlugin)
        classpath(com.beetlestance.aphid.buildsrc.Libs.Kotlin.gradlePlugin)
        classpath(com.beetlestance.aphid.buildsrc.Libs.Hilt.gradlePlugin)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

plugins {
    id("com.diffplug.spotless") version "5.8.2"
    id("com.github.ben-manes.versions") version "0.36.0"
}

subprojects {

    plugins.apply("com.diffplug.spotless")

    configure<com.diffplug.gradle.spotless.SpotlessExtension> {
        kotlin {
            target("**/*.kt")
            targetExclude("$buildDir/**/*.kt")
            targetExclude("bin/**/*.kt")

            ktlint(com.beetlestance.aphid.buildsrc.Versions.ktlint)
                // Disable paren-spacing rule for NonParenthesizedAnnotationsOnFunctionalTypes
                .userData(mapOf("disabled_rules" to "paren-spacing"))
            /* ./gradlew spotlessApply */
            licenseHeaderFile(rootProject.file("spotless/copyright.kt"))
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
                allWarningsAsErrors = hasTask(":app:assembleDebug").not()
            }

            // Enable experimental coroutines APIs, including Flow
            if (project.name != "spoonacular-kotlin" && project.name != "dicebar-kotlin") {
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

            // Set JVM target to 11, on 1.8 library sources are not getting attached automatically
            // Set to 15 once intellij upgrade asm 7.0 to 9.0,
            // now org.objectweb.asm.ClassReader has major opt code 12
            jvmTarget = "11"
        }
    }
}
