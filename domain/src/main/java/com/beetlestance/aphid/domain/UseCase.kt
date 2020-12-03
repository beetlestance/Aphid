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
package com.beetlestance.aphid.domain

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import timber.log.Timber

/**
 * To be used when we just want to observe state of the work not the result
 *
 * as [flow] is cold flow that means every new subscriber will execute the code again.
 */
abstract class UseCase<in P> {
    operator fun invoke(params: P): Flow<Status> {
        return flow {
            emit(Status.Started)
            doWork(params)
            emit(Status.Success)
        }.catch { throwable ->
            emit(Status.Error(throwable))
            Timber.e(throwable)
        }
    }

    // Will only be used when we know the result will be returned without errors
    suspend fun executeSync(params: P): Unit = doWork(params)

    protected abstract suspend fun doWork(params: P)
}

/**
 * To be used when we want the result for a work
 *
 * @sample
 * resultUseCase(param).first() this will also auto cancels the flow
 */
abstract class ResultUseCase<in P, out R> {
    operator fun invoke(params: P): Flow<R> {
        return flow {
            emit(doWork(params))
        }.catch { throwable ->
            Timber.e(throwable)
        }
    }

    protected abstract suspend fun doWork(params: P): R
}

/**
 * To be used for observing values from database or any source
 *
 * ```
 * subscribe to the flow
 * observeUseCase.observe().collect{
 *      # do work
 * }
 *
 * set the parameters for creating observable
 * we can call this multiple times without subscribing to flow again
 * observeUseCase.invoke(input)
 * ```
 *
 * @Warning Always start observing before invoke
 */
abstract class ObserveUseCase<P : Any, T> {
    private val paramState = MutableSharedFlow<P>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    operator fun invoke(params: P) {
        paramState.tryEmit(params)
    }

    protected abstract fun createObservable(params: P): Flow<T>

    fun observe(): Flow<T> = paramState.flatMapLatest {
        createObservable(it).catch { throwable ->
            Timber.e(throwable)
        }
    }
}

/**
 * To be used for performing suspending work
 *
 * Use when a work need to be executed with different params
 */
abstract class SuspendableWorkUseCase<P : Any, T> : ObserveUseCase<P, T>() {
    override fun createObservable(params: P): Flow<T> = flow {
        emit(doWork(params))
    }.catch { throwable ->
        Timber.e(throwable)
    }

    abstract suspend fun doWork(params: P): T
}


operator fun UseCase<Unit>.invoke(): Flow<Status> = invoke(Unit)
operator fun <T> ResultUseCase<Unit, T>.invoke(): Flow<T> = invoke(Unit)
operator fun <T> ObserveUseCase<Unit, T>.invoke(): Unit = invoke(Unit)
