import com.beetlestance.aphid.buildsrc.DependencyUpdates
import com.beetlestance.aphid.buildsrc.ReleaseType

// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        @Suppress("UnstableApiUsage")
        // https://github.com/gradle/gradle/issues/16958
        val libs = project.extensions.getByType<VersionCatalogsExtension>()
            .named("libs") as org.gradle.accessors.dm.LibrariesForLibs

        classpath(libs.android.gradle.plugin)
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.google.hilt.gradle.plugin)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

plugins {
    id("com.diffplug.spotless") version "5.14.1"
    // gradlew dependencyUpdates
    id("com.github.ben-manes.versions") version "0.39.0"
}

subprojects {

    plugins.apply("com.diffplug.spotless")

    configure<com.diffplug.gradle.spotless.SpotlessExtension> {
        kotlin {
            target("**/*.kt")
            targetExclude("$buildDir/**/*.kt")
            targetExclude("bin/**/*.kt")

            ktlint(libs.versions.ktlint.get())
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
                // allWarningsAsErrors = hasTask(":app:assembleDebug").not()
                allWarningsAsErrors = false
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
                "-Xjvm-default=all"
            )

            // Set JVM target to 11
            // Set to 15 once intellij upgrade asm 7.0 to 9.0,
            // now org.objectweb.asm.ClassReader has major opt code 12
            jvmTarget = "11"
        }
    }
}

/**
 * Update dependencyUpdates task to reject versions which are more 'unstable' than our
 * current version.
 */
tasks.withType<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask> {
    rejectVersionIf {
        val current = DependencyUpdates.versionToRelease(currentVersion)
        // If we're using a SNAPSHOT, ignore since we must be doing so for a reason.
        if (current == ReleaseType.SNAPSHOT) return@rejectVersionIf true

        // Otherwise we reject if the candidate is more 'unstable' than our version
        val candidate = DependencyUpdates.versionToRelease(candidate.version)

        return@rejectVersionIf candidate.isLessStableThan(current)
    }
}

apply(from = "gradle/projectDependencyGraph.gradle")
