plugins {
    id("com.gradle.enterprise").version("3.5")
}

rootProject.name = "Aphid"

include(
    ":app",
    ":base",
    ":base-android",
    ":common-compose",
    ":data",
    ":dicebar-kotlin",
    ":data-android",
    ":domain",
    ":feature-explore",
    ":feature-chat",
    ":feature-profile",
    ":spoonacular",
    ":spoonacular-kotlin"
)

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()

        // org.jetbrains.kotlinx:kotlinx-collections-immutable-jvm:0.3.3
        //  Required by: androidx.compose.runtime:runtime:1.0.0-beta01
        maven {
            url = java.net.URI("https://kotlin.bintray.com/kotlinx")
        }

        // Used for Roomigrant
        maven { url = java.net.URI("https://jitpack.io") }
    }
}