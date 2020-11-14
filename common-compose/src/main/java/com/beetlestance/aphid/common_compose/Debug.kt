package com.beetlestance.aphid.common_compose

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.onCommit
import androidx.compose.runtime.remember

class Ref(var value: Int)

const val EnableDebugCompositionLogs = true

/**
 * An effect which longs the number compositions at the invoked point of the slot table.
 * Thanks to [objcode](https://github.com/objcode) for this code.
 *
 * This is an inline function to act as like a C-style #include to the host composable function.
 * That way we track it's compositions, not this function's compositions.
 *
 * @param tag Log tag used for [Log.d]
 */
@Composable
inline fun LogCompositions(tag: String) {
    if (EnableDebugCompositionLogs && BuildConfig.DEBUG) {
        val ref = remember { Ref(0) }
        onCommit { ref.value++ }
        Log.d(tag, "Compositions: ${ref.value}")
    }
}