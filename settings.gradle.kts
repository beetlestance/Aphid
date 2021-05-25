plugins {
    id("com.gradle.enterprise").version("3.5")
}

/*
* Enable Version catalogs for centralized dependency
*  https://docs.gradle.org/7.0.2/userguide/platforms.html#sub:central-declaration-of-dependencies
*/
enableFeaturePreview("VERSION_CATALOGS")
/*
* Enable type-safe project access
*
    dependencies {
     type-safe alternative to project(":client")
       implementation projects.client
    }
*/
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

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

    defaultLibrariesExtensionName.set("libs")

    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    repositories {
        google()
        mavenCentral()

        // org.jetbrains.kotlinx:kotlinx-collections-immutable-jvm:0.3.3
        //  Required by: androidx.compose.runtime:runtime:1.0.0-beta01
        maven {
            url = java.net.URI("https://kotlin.bintray.com/kotlinx")
        }
    }
}