/*
 * Copyright 2020 BeetleStance
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
package com.beetlestance.aphid.base_android

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.reflect.KProperty1

abstract class ReduxStateViewModel<S> constructor(initialState: S) : ViewModel() {
    private val _state = MutableStateFlow(initialState)
    private val stateMutex = Mutex()

    val state: StateFlow<S>
        get() = _state.asStateFlow()

    fun <A> KProperty1<S, A>.asFlow(): Flow<A> {
        return _state.map { get(it) }.distinctUntilChanged()
    }

    // Todo - In co-routine 1.6.0 protected api is in-accessible, fix once patch is released
    suspend fun setState(reducer: suspend S.() -> S) {
        stateMutex.withLock {
            _state.value = reducer(_state.value)
        }
    }

    suspend inline fun <reified T> Flow<T>.collectAndSetState(
        crossinline reducer: suspend S.(T) -> S
    ) = collect { item -> setState { reducer(item) } }
}