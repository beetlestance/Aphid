package com.beetlestance.base_android

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.reflect.KProperty1


abstract class AphidViewModel<S>(
    initialState: S
) : ViewModel() {
    private val state = MutableStateFlow(initialState)
    private val stateMutex = Mutex()

    /**
     * Returns a snapshot of the current state.
     */
    fun currentState(): S = state.value

    /**
     * Returns state as livedata
     */
    val liveData: LiveData<S>
        get() = state.asLiveData()

    /**
     * Extension function to collect and set state
     */
    protected suspend fun <T> Flow<T>.collectAndSetState(reducer: S.(T) -> S) {
        return collect { item -> setState { reducer(item) } }
    }

    /**
     * Return livedata for a specific property in state for changes
     */
    fun <A> selectObserve(prop1: KProperty1<S, A>): LiveData<A> {
        return selectSubscribe(prop1).asLiveData()
    }

    /**
     * Subscribe to state and perform [block]
     */
    protected fun subscribe(block: (S) -> Unit) {
        viewModelScope.launch {
            state.collect { block(it) }
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
        return state.map { prop1.get(it) }.distinctUntilChanged()
    }

    /**
     * Updates the state of view model
     */
    protected suspend fun setState(reducer: S.() -> S) {
        stateMutex.withLock {
            state.value = reducer(state.value)
        }
    }

    /**
     * Provide a launch block with state update
     */
    protected fun CoroutineScope.launchSetState(reducer: S.() -> S) {
        launch { this@AphidViewModel.setState(reducer) }
    }

    /**
     * Perform [block] with current state as parameter
     */
    protected suspend fun withState(block: (S) -> Unit) {
        stateMutex.withLock {
            block(state.value)
        }
    }

    /**
     * Scope extension to perform [block] on current state
     */
    protected fun CoroutineScope.withState(block: (S) -> Unit) {
        launch { this@AphidViewModel.withState(block) }
    }
}