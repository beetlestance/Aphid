// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.4.0-alpha08")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.0")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.42")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

plugins {
    // gradlew dependencyUpdates
    id("com.github.ben-manes.versions") version "0.42.0"
}

subprojects {

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

}

tasks.withType<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask> {
    checkForGradleUpdate = true
    outputFormatter = "html,txt"
    outputDir = "./reports/dependency-updates"
    reportfileName = "report"
}

apply(from = "gradle/projectDependencyGraph.gradle")
