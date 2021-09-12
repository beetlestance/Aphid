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
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.reflect.KProperty1

abstract class ReduxStateViewModel<S>(
    initialState: S
) : ViewModel() {
    private val _state = MutableStateFlow(initialState)
    private val stateMutex = Mutex()

    /**
     * Returns a snapshot of the current state.
     */
    val state: StateFlow<S>
        get() = _state.asStateFlow()

    /**
     * Extension function to collect and set state
     */
    protected suspend inline fun <reified T> Flow<T>.collectAndSetState(
        crossinline reducer: suspend S.(T) -> S
    ) = collect { item -> setState { reducer(item) } }

    fun <A> selectObserve(prop1: KProperty1<S, A>): Flow<A> {
        return selectSubscribe(prop1)
    }

    /**
     * Subscribe to state and perform [block]
     */
    protected fun subscribe(block: (S) -> Unit) {
        viewModelScope.launch {
            _state.collect { block(it) }
        }
    }

    /**
     * Subscribe to a property in state for changes and performs [block]
     */
    protected fun <A> selectSubscribe(prop1: KProperty1<S, A>, block: (A) -> Unit) {
        viewModelScope.launch {
            selectSubscribe(prop1).collect { block(it) }
        }
    }

    private fun <A> selectSubscribe(prop1: KProperty1<S, A>): Flow<A> {
        return _state.map { prop1.get(it) }.distinctUntilChanged()
    }

    /**
     * Updates the state of view model
     */
    protected suspend fun setState(reducer: suspend S.() -> S) {
        stateMutex.withLock {
            _state.value = reducer(_state.value)
        }
    }

    /**
     * Provide a launch block with state update
     */
    protected fun CoroutineScope.launchSetState(reducer: suspend S.() -> S) {
        launch { this@ReduxStateViewModel.setState(reducer) }
    }

    /**
     * Perform [block] with current state as parameter
     */
    protected suspend fun withState(block: (S) -> Unit) {
        stateMutex.withLock {
            block(_state.value)
        }
    }

    /**
     * Scope extension to perform [block] on current state
     */
    protected fun CoroutineScope.withState(block: (S) -> Unit) {
        launch { this@ReduxStateViewModel.withState(block) }
    }
}
