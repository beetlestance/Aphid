package com.beetlestance.aphid.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

sealed class Status {
    object Started : Status()
    object Success : Status()
    data class Error(val t: Throwable) : Status()
}

inline fun Status.onStarted(
    action: Status.Started.() -> Unit = {}
) {
    if (this is Status.Started) action(this)
}

inline fun Status.onError(
    action: Status.Error.() -> Unit = {}
) {
    if (this is Status.Error) action()
}

inline fun Status.onSuccess(
    action: Status.Success.() -> Unit = {}
) {
    if (this is Status.Success) action()
}

suspend fun Flow<Status>.watchStatus(
    action: Status.() -> Unit
): Unit = collect { action(it) }
