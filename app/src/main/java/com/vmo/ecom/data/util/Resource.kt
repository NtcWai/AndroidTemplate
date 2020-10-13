/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vmo.ecom.data.util

import android.content.Context
import com.vmo.ecom.R

// https://github.com/android/architecture-components-samples/blob/master/GithubBrowserSample/app/src/main/java/com/android/example/github/vo/Resource.kt

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
data class Resource<out T>(val status: Status, val data: T?, val exception: Throwable?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(exception: Throwable, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, exception)
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null, null)
        }

        fun <T> initalize(data: T? = null): Resource<T> {
            return Resource(Status.INITIALIZE, null, null)
        }
    }

    fun isLoading(): Boolean {
        return this.status == Status.LOADING
    }
}

/**
 * Status of a resource that is provided to the UI.
 *
 *
 * These are usually created by the Repository classes where they return
 * `LiveData<Resource<T>>` to pass back the latest data to the UI with its fetch status.
 */
enum class Status {
    SUCCESS,
    ERROR,
    LOADING,
    INITIALIZE
}

inline fun <T, E> createResourceWithNetworkCheck(
    context: Context,
    request: () -> T,
    parseResult: (T) -> E
): Resource<E> {
    if (!context.isNetworkConnected()) {
        return Resource.error(
            NoNetworkException(
                context.getString(R.string.error),
                context.getString(R.string.error_no_network_message)
            )
        )
    }
    return try {
        val response = request()
        Resource.success(parseResult(response))
    } catch (e: Throwable) {
        return Resource.error(e) // you can parse some common error like 401
    }
}