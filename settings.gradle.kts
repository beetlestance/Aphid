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
    ":mdc-theme-lint",
    ":spoonacular",
    ":spoonacular-kotlin"
)

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositories {
        google()
        jcenter()
        mavenCentral()

        // Used for Roomigrant
        maven { url = java.net.URI("https://jitpack.io") }
    }
}