/*
 * Copyright 2021 BeetleStance
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.beetlestance.aphid.domain

import kotlinx.coroutines.flow.Flow

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
