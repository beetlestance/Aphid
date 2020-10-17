package com.beetlestance.buildsrc

object Versions {
    const val ktlint: String = "0.37.2"
}

object Aphid {
    const val buildToolsVersion = "30.0.2"
    const val minSdkVersion: Int = 21
    const val targetSdkVersion: Int = 29
    const val compileSdkVersion: Int = 29
    const val applicationId: String = "com.beetlestance.aphid"
    const val versionCode: Int = 1
    const val versionName: String = "0.1"
}

object Libs {

    const val androidGradlePlugin: String = "com.android.tools.build:gradle:4.2.0-alpha13"

    const val leakCanary: String = "com.squareup.leakcanary:leakcanary-android:2.2"

    const val timber: String = "com.jakewharton.timber:timber:4.7.1"

    object Test {
        const val junit: String = "junit:junit:4.13.1"
        const val robolectric: String = "org.robolectric:robolectric:4.3.1"
        const val mockK: String = "io.mockk:mockk:1.9.3"
    }

    object AndroidX {
        const val androidAnnotation: String = "androidx.annotation:annotation:1.2.0-alpha01"
        const val appcompat: String = "androidx.appcompat:appcompat:1.3.0-alpha02"
        const val coreKtx: String = "androidx.core:core-ktx:1.5.0-alpha04"

        object Fragment {
            private const val version = "1.3.0-beta01"
            const val fragmentKtx: String = "androidx.fragment:fragment-ktx:$version"
        }

        object Test {
            private const val version = "1.3.0"
            const val core: String = "androidx.test:core:$version"
            const val runner: String = "androidx.test:runner:$version"
            const val rules: String = "androidx.test:rules:$version"
            const val junit: String = "androidx.test.ext:junit:1.1.2"
            const val espressoCore: String = "androidx.test.espresso:espresso-core:3.3.0"
        }

        object Lifecycle {
            private const val version = "2.2.0"
            const val extensions: String = "androidx.lifecycle:lifecycle-extensions:$version"
            const val viewmodelKtx: String = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val livedataKtx: String = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val compiler: String = "androidx.lifecycle:lifecycle-compiler:$version"
            const val common: String = "androidx.lifecycle:lifecycle-common-java8:$version"
        }

        object Room {
            private const val version = "2.2.5"
            const val common: String = "androidx.room:room-common:$version"
            const val runtime: String = "androidx.room:room-runtime:$version"
            const val compiler: String = "androidx.room:room-compiler:$version"

            // optional - Kotlin Extensions and Coroutines support for Room
            const val ktx: String = "androidx.room:room-ktx:$version"

            // optional - Test helpers
            const val testing: String = "androidx.room:room-testing:$version"
        }

        object Work {
            private const val version = "2.4.0"
            const val runtimeKtx: String = "androidx.work:work-runtime-ktx:$version"
        }

        object Compose {
            const val version = "1.0.0-alpha05"

            const val compiler = "androidx.compose.compiler:compiler:$version"

            const val runtime = "androidx.compose.runtime:runtime:$version"
            const val livedata = "androidx.compose.runtime:runtime-livedata:$version"

            const val foundation = "androidx.compose.foundation:foundation:$version"
            const val layout = "androidx.compose.foundation:foundation-layout:$version"

            const val ui = "androidx.compose.ui:ui:$version"
            const val material = "androidx.compose.material:material:$version"

            const val animation = "androidx.compose.animation:animation:$version"

            const val tooling = "androidx.ui:ui-tooling:$version"
            const val test = "androidx.ui:ui-test:$version"
        }

        object Hilt {
            private const val version = "1.0.0-alpha02"
            const val work = "androidx.hilt:hilt-work:$version"
            const val viewmodel = "androidx.hilt:hilt-lifecycle-viewmodel:$version"
            const val compiler = "androidx.hilt:hilt-compiler:$version"
        }
    }

    object Google {

        const val openSourceLicensesPlugin = "com.google.android.gms:oss-licenses-plugin:0.10.2"
        const val openSourceLicensesLibrary =
            "com.google.android.gms:play-services-oss-licenses:17.0.0"

        object Mdc {
            const val material: String = "com.google.android.material:material:1.3.0-alpha03"
            const val composeThemeAdapter =
                "com.google.android.material:compose-theme-adapter:1.0.0-alpha04"
        }

        object PlayServices {
            const val gmsGoogleServices: String = "com.google.gms:google-services:4.3.3"
        }

        object Firebase {
            const val performance: String = "com.google.firebase:firebase-perf:19.0.8"
            const val performancePlugin: String = "com.google.firebase:perf-plugin:1.3.1"

            const val crashlytics: String = "com.google.firebase:firebase-crashlytics:17.2.1"
            const val crashlyticsGradle: String =
                "com.google.firebase:firebase-crashlytics-gradle:2.2.0"

            const val auth: String = "com.google.firebase:firebase-auth:19.3.2"

            const val messaging: String = "com.google.firebase:firebase-messaging:20.2.4"
        }
    }

    object Coil {
        private const val version = "1.0.0-rc3"
        const val coil = "io.coil-kt:coil:$version"
    }

    object Accompanist {
        private const val version = "0.3.1"
        const val coil = "dev.chrisbanes.accompanist:accompanist-coil:$version"
    }

    object Dagger {
        private const val version = "2.29.1"
        const val dagger = "com.google.dagger:dagger:$version"
        const val compiler = "com.google.dagger:dagger-compiler:$version"
    }

    object Hilt {
        private const val version = "2.29.1-alpha"
        const val library = "com.google.dagger:hilt-android:$version"
        const val compiler = "com.google.dagger:hilt-android-compiler:$version"
        const val testing = "com.google.dagger:hilt-android-testing:$version"
        const val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
    }

    object Kotlin {
        const val version = "1.4.10"
        const val stdlib: String = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val reflect: String = "org.jetbrains.kotlin:kotlin-reflect:$version"
        const val extensions: String = "org.jetbrains.kotlin:kotlin-android-extensions:$version"
        const val gradlePlugin: String = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
    }

    object Retrofit {
        private const val version = "2.9.0"
        const val retrofit: String = "com.squareup.retrofit2:retrofit:$version"
        const val moshiConverter = "com.squareup.retrofit2:converter-moshi:$version"
    }

    object Moshi {
        private const val version = "1.11.0"
        const val moshi = "com.squareup.moshi:moshi:$version"
        const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:$version"
        const val moshiAdapters = "com.squareup.moshi:moshi-adapters:$version"

    }

    object OkHttp {
        private const val version = "4.9.0"
        const val okhttp: String = "com.squareup.okhttp3:okhttp:$version"
        const val loggingInterceptor: String = "com.squareup.okhttp3:logging-interceptor:$version"
        const val urlConnection: String = "com.squareup.okhttp3:okhttp-urlconnection:$version"
    }

    object Coroutines {
        private const val version = "1.3.9"
        const val core: String = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android: String = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test: String = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object Lint {
        private const val version = "27.2.0-alpha13"
        const val api: String = "com.android.tools.lint:lint-api:$version"
        const val checks: String = "com.android.tools.lint:lint-checks:$version"
    }

    object Roomigrant {
        /**
         * We use a fork which has been migrated to AndroidX Room
         */
        private const val version = "master-SNAPSHOT"
        const val library = "com.github.chrisbanes.Roomigrant:RoomigrantLib:$version"
        const val compiler = "com.github.chrisbanes.Roomigrant:RoomigrantCompiler:$version"
    }
}
