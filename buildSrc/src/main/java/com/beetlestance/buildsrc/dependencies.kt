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

    const val androidGradlePlugin: String = "com.android.tools.build:gradle:4.2.0-alpha10"

    const val leakCanary: String = "com.squareup.leakcanary:leakcanary-android:2.2"

    const val timber: String = "com.jakewharton.timber:timber:4.7.1"

    object Test {
        const val junit: String = "junit:junit:4.13"
        const val robolectric: String = "org.robolectric:robolectric:4.3.1"
        const val mockK: String = "io.mockk:mockk:1.9.3"
    }

    object AndroidX {
        const val androidAnnotation: String = "androidx.annotation:annotation:1.1.0"
        const val appcompat: String = "androidx.appcompat:appcompat:1.3.0-alpha02"
        const val coreKtx: String = "androidx.core:core-ktx:1.5.0-alpha02"
        const val adsIdentifier: String = "androidx.ads:ads-identifier:1.0.0-alpha04"
        const val multidex: String = "androidx.multidex:multidex:2.0.1"

        const val collection: String = "androidx.collection:collection-ktx:1.1.0"
        const val leagcy: String = "androidx.legacy:legacy-support-v4:1.0.0"

        const val exifInfo: String = "androidx.exifinterface:exifinterface:1.2.0"

        const val swipeToRefreshLayout: String =
            "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
        const val constraintlayout: String =
            "androidx.constraintlayout:constraintlayout:2.0.1"
        const val recyclerview: String = "androidx.recyclerview:recyclerview:1.2.0-alpha04"
        const val cardView: String = "androidx.cardview:cardview:1.0.0"
        const val webkit: String = "androidx.webkit:webkit:1.2.0"

        const val guava: String = "com.google.guava:guava:29.0-android"

        object Navigation {
            private const val version = "2.3.0"
            const val fragment: String = "androidx.navigation:navigation-fragment-ktx:$version"
            const val ui: String = "androidx.navigation:navigation-ui-ktx:$version"
            const val safeArgs: String =
                "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
        }

        object Fragment {
            private const val version = "1.2.5"
            const val fragment: String = "androidx.fragment:fragment:$version"
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

        object Paging {
            private const val version = "2.1.2"
            const val common: String = "androidx.paging:paging-common-ktx:$version"
            const val runtime: String = "androidx.paging:paging-runtime-ktx:$version"
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
            private const val version = "2.2.4" // 2.2.3
            const val common: String = "androidx.room:room-common:$version"
            const val runtime: String = "androidx.room:room-runtime:$version"
            const val compiler: String = "androidx.room:room-compiler:$version"
            const val ktx: String = "androidx.room:room-ktx:$version"
            const val testing: String = "androidx.room:room-testing:$version"
            const val rxJava: String = "androidx.room:room-rxjava2:$version"
        }

        object Work {
            private const val version = "2.4.0"
            const val runtime: String = "androidx.work:work-runtime:$version"
            const val runtimeKtx: String = "androidx.work:work-runtime-ktx:$version"
        }

        object Animation {
            private const val version = "1.0.0-alpha03"
            const val dynamicAnimations: String =
                "androidx.dynamicanimation:dynamicanimation:$version"
            const val dynamicKtx: String = "androidx.dynamicanimation:dynamicanimation-ktx:$version"
        }
    }

    object Google {
        const val material: String = "com.google.android.material:material:1.3.0-alpha02"
        const val flexBox: String = "com.google.android:flexbox:2.0.1"
        const val playCore: String = "com.google.android.play:core:1.8.0"
        const val playCoreKtx: String = "com.google.android.play:core-ktx:1.8.1"
        const val gson: String = "com.google.code.gson:gson:2.8.6"
        const val places: String = "com.google.android.libraries.places:places:2.3.0"

        object PlayServices {
            const val gmsGoogleServices: String = "com.google.gms:google-services:4.3.3"

            const val phone: String = "com.google.android.gms:play-services-auth-api-phone:17.4.0"
            const val gps: String = "com.google.android.gms:play-services-location:17.0.0"
            const val auth: String = "com.google.android.gms:play-services-auth:18.1.0"
            const val maps: String = "com.google.android.gms:play-services-maps:17.0.0"
        }

        object Firebase {
            const val firebasePrefPlugin: String = "com.google.firebase:perf-plugin:1.3.1"
            const val crashlyticsGradle: String =
                "com.google.firebase:firebase-crashlytics-gradle:2.2.0"

            const val analytics: String = "com.google.firebase:firebase-analytics:17.5.0"
            const val analyticsKtx: String = "com.google.firebase:firebase-analytics-ktx:17.5.0"
            const val auth: String = "com.google.firebase:firebase-auth:19.3.2"
            const val config: String = "com.google.firebase:firebase-config:19.2.0"
            const val configKtx: String = "com.google.firebase:firebase-config-ktx:19.2.0"
            const val core: String = "com.google.firebase:firebase-core:17.5.0"
            const val crashlytics: String = "com.google.firebase:firebase-crashlytics:17.2.1"
            const val dynamicLink: String = "com.google.firebase:firebase-dynamic-links:19.1.0"
            const val firestore: String = "com.google.firebase:firebase-firestore-ktx:21.5.0"
            const val inAppMessaging: String =
                "com.google.firebase:firebase-inappmessaging-display-ktx:19.1.0"
            const val messaging: String = "com.google.firebase:firebase-messaging:20.2.4"
            const val performance: String = "com.google.firebase:firebase-perf:19.0.8"
            const val uiStorage: String = "com.firebaseui:firebase-ui-storage:6.2.0"
        }
    }

    object Kotlin {
        private const val version = "1.4.10"
        const val jdk7: String = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$version"
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
        private const val version = "1.10.0"
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

    object Insetter {
        private const val version = "0.3.0"

        // If you're using data-binding use this
        const val dbInsetter: String = "dev.chrisbanes:insetter-dbx:$version"

        // If you're using Kotlin use this too
        const val ktxInsetter: String = "dev.chrisbanes:insetter-ktx:$version"
    }

    object BeetleStance {
        const val navigationExtensions: String =
            "com.github.beetlestance.android-extensions:navigation:0.1.1"
    }

    object Lint {
        private const val version = "27.2.0-alpha10"
        const val api: String = "com.android.tools.lint:lint-api:$version"
        const val checks: String = "com.android.tools.lint:lint-checks:$version"
    }

    object ThirdParty {
        const val youtubePlayer: String =
            "com.pierfrancescosoffritti.androidyoutubeplayer:core:10.0.5"
        const val viewPagerDotsIndicator: String =
            "com.tbuonomo.andrui:viewpagerdotsindicator:4.1.2"
        const val coil: String = "io.coil-kt:coil:0.11.0"
        const val spotDialog: String = "com.github.d-max:spots-dialog:0.7@aar"
    }
}
