package com.beetlestance.aphid.common_compose.extensions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.toBitmap
import coil.Coil
import coil.request.ImageRequest
import coil.request.SuccessResult

suspend fun getBitmap(
    context: Context,
    imageSrc: Any
): Drawable? {
    val request = ImageRequest.Builder(context)
        .data(imageSrc)
        .crossfade(true)
        .allowHardware(false)
        .build()

    val imageResult = Coil.execute(request)

    return if (imageResult is SuccessResult) {
        imageResult.drawable
    } else
        null
}