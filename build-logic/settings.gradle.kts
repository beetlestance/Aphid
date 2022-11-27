/*
* Enable type-safe project access
*
    dependencies {
     type-safe alternative to project(":client")
       implementation projects.client
    }
*/

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

include(":convention")