import com.beetlestance.aphid.buildsrc.Libs

plugins {
    id("kotlin")
    kotlin("kapt")
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
}

dependencies {
    // Testing
    testImplementation(Libs.Test.junit)

    //Annotations
    implementation(Libs.AndroidX.annotation)

    // Kotlin
    implementation(Libs.Kotlin.stdlib)

    // Retrofit
    implementation(Libs.Retrofit.retrofit)
    implementation(Libs.Retrofit.moshiConverter)

    //Moshi
    implementation(Libs.Moshi.moshi)
    implementation(Libs.Moshi.moshiKotlin)
    implementation(Libs.Moshi.moshiAdapters)

    // Ok-Http
    implementation(Libs.OkHttp.okhttp)
    implementation(Libs.OkHttp.loggingInterceptor)
    implementation(Libs.OkHttp.urlConnection)
}
