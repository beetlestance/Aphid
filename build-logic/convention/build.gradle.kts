plugins {
    `kotlin-dsl`
}

group = "com.beetlestance.aphid.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    implementation(libs.android.tools.build.gradle)
    implementation(libs.kotlin.gradle)
    implementation(libs.spotless.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "aphid.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidApplicationCompose") {
            id = "aphid.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }

        register("androidLibrary") {
            id = "aphid.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidLibraryCompose") {
            id = "aphid.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }

        register("androidFeature") {
            id = "aphid.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }

        register("spotless") {
            id = "aphid.spotless"
            implementationClass = "SpotlessConventionPlugin"
        }
    }
}