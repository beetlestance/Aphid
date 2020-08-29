package com.beetlestance.buildsrc

object Libs {

    const val androidGradlePlugin = "com.android.tools.build:gradle:4.2.0-alpha08"

    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:2.2"

    const val timber = "com.jakewharton.timber:timber:4.7.1"

    object Test {
        const val junit = "junit:junit:4.13"
        const val robolectric = "org.robolectric:robolectric:4.3.1"
        const val mockK = "io.mockk:mockk:1.9.3"
    }

    object AndroidX {
        const val androidAnnotation = "androidx.annotation:annotation:1.1.0"
        const val appcompat = "androidx.appcompat:appcompat:1.3.0-alpha02"
        const val coreKtx = "androidx.core:core-ktx:1.5.0-alpha02"
        const val adsIdentifier = "androidx.ads:ads-identifier:1.0.0-alpha04"
        const val multidex = "androidx.multidex:multidex:2.0.1"

        const val collection = "androidx.collection:collection-ktx:1.1.0"
        const val leagcy = "androidx.legacy:legacy-support-v4:1.0.0"

        const val exifInfo = "androidx.exifinterface:exifinterface:1.2.0"

        const val swipeToRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
        const val constraintlayout = "androidx.constraintlayout:constraintlayout:2.0.0-beta8"
        const val recyclerview = "androidx.recyclerview:recyclerview:1.2.0-alpha04"
        const val cardView = "androidx.cardview:cardview:1.0.0"
        const val webkit = "androidx.webkit:webkit:1.2.0"

        const val guava = "com.google.guava:guava:29.0-android"

        object Navigation {
            private const val version = "2.3.0"
            const val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
            const val ui = "androidx.navigation:navigation-ui-ktx:$version"
            const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
        }

        object Fragment {
            private const val version = "1.2.5"
            const val fragment = "androidx.fragment:fragment:$version"
            const val fragmentKtx = "androidx.fragment:fragment-ktx:$version"
        }

        object Test {
            private const val version = "1.3.0"
            const val core = "androidx.test:core:$version"
            const val runner = "androidx.test:runner:$version"
            const val rules = "androidx.test:rules:$version"
            const val junit = "androidx.test.ext:junit:1.1.2"
            const val espressoCore = "androidx.test.espresso:espresso-core:3.3.0"
        }

        object Paging {
            private const val version = "2.1.2"
            const val common = "androidx.paging:paging-common-ktx:$version"
            const val runtime = "androidx.paging:paging-runtime-ktx:$version"
        }

        object Lifecycle {
            private const val version = "2.2.0"
            const val extensions = "androidx.lifecycle:lifecycle-extensions:$version"
            const val viewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val livedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val compiler = "androidx.lifecycle:lifecycle-compiler:$version"
            const val common = "androidx.lifecycle:lifecycle-common-java8:$version"
        }

        object Room {
            private const val version = "2.2.4" // 2.2.3
            const val common = "androidx.room:room-common:$version"
            const val runtime = "androidx.room:room-runtime:$version"
            const val compiler = "androidx.room:room-compiler:$version"
            const val ktx = "androidx.room:room-ktx:$version"
            const val testing = "androidx.room:room-testing:$version"
            const val rxJava = "androidx.room:room-rxjava2:$version"
        }

        object Work {
            private const val version = "2.4.0"
            const val runtime = "androidx.work:work-runtime:$version"
            const val runtimeKtx = "androidx.work:work-runtime-ktx:$version"
        }

        object Animation {
            private const val version = "1.0.0-alpha03"
            const val dynamicAnimations = "androidx.dynamicanimation:dynamicanimation:$version"
            const val dynamicKtx = "androidx.dynamicanimation:dynamicanimation-ktx:$version"
        }
    }

    object Google {
        const val material = "com.google.android.material:material:1.3.0-alpha01"
        const val flexBox = "com.google.android:flexbox:2.0.1"
        const val playCore = "com.google.android.play:core:1.8.0"
        const val playCoreKtx = "com.google.android.play:core-ktx:1.8.1"
        const val gson = "com.google.code.gson:gson:2.8.6"
        const val places = "com.google.android.libraries.places:places:2.3.0"

        object PlayServices {
            const val gmsGoogleServices = "com.google.gms:google-services:4.3.3"

            const val phone = "com.google.android.gms:play-services-auth-api-phone:17.4.0"
            const val gps = "com.google.android.gms:play-services-location:17.0.0"
            const val auth = "com.google.android.gms:play-services-auth:18.1.0"
            const val maps = "com.google.android.gms:play-services-maps:17.0.0"
        }

        object Firebase {
            const val firebasePrefPlugin = "com.google.firebase:perf-plugin:1.3.1"
            const val crashlyticsGradle = "com.google.firebase:firebase-crashlytics-gradle:2.2.0"

            const val analytics = "com.google.firebase:firebase-analytics:17.5.0"
            const val analyticsKtx = "com.google.firebase:firebase-analytics-ktx:17.5.0"
            const val auth = "com.google.firebase:firebase-auth:19.3.2"
            const val config = "com.google.firebase:firebase-config:19.2.0"
            const val configKtx = "com.google.firebase:firebase-config-ktx:19.2.0"
            const val core = "com.google.firebase:firebase-core:17.5.0"
            const val crashlytics = "com.google.firebase:firebase-crashlytics:17.2.1"
            const val dynamicLink = "com.google.firebase:firebase-dynamic-links:19.1.0"
            const val firestore = "com.google.firebase:firebase-firestore-ktx:21.5.0"
            const val inAppMessaging =
                "com.google.firebase:firebase-inappmessaging-display-ktx:19.1.0"
            const val messaging = "com.google.firebase:firebase-messaging:20.2.4"
            const val performance = "com.google.firebase:firebase-perf:19.0.8"
            const val uiStorage = "com.firebaseui:firebase-ui-storage:6.2.0"
        }
    }

    object Kotlin {
        private const val version = "1.4.0"
        const val jdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$version"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect:$version"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
    }

    object Retrofit {
        private const val version = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:$version"
        const val coroutineAdapter =
            "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"
    }

    object OkHttp {
        private const val version = "4.7.2"
        const val okhttp = "com.squareup.okhttp3:okhttp:$version"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
        const val urlConnection = "com.squareup.okhttp3:okhttp-urlconnection:4.7.2"
    }

    object Coroutines {
        private const val version = "1.3.9"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object Insetter {
        private const val version = "0.3.0"

        // If you're using data-binding use this
        const val dbInsetter = "dev.chrisbanes:insetter-dbx:$version"

        // If you're using Kotlin use this too
        const val ktxInsetter = "dev.chrisbanes:insetter-ktx:$version"
    }

    object BeetleStance {
        const val navigationExtensions =
            "com.github.beetlestance.android-extensions:navigation:0.1.1"
    }

    object Lint {
        private const val version = "27.2.0-alpha08"
        const val api = "com.android.tools.lint:lint-api:$version"
        const val checks = "com.android.tools.lint:lint-checks:$version"
    }

    object ThirdParty {
        const val youtubePlayer = "com.pierfrancescosoffritti.androidyoutubeplayer:core:10.0.5"
        const val viewPagerDotsIndicator = "com.tbuonomo.andrui:viewpagerdotsindicator:4.1.2"
        const val coil = "io.coil-kt:coil:0.11.0"
        const val spotDialog = "com.github.d-max:spots-dialog:0.7@aar"
    }
}
