import com.beetlestance.aphid.buildsrc.Libs

plugins {
    id("kotlin")
}

dependencies {
    // Testing
    testImplementation(Libs.Test.junit)

    //Annotations
    implementation(Libs.AndroidX.androidAnnotation)

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
