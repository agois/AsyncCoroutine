package com.agois.asynccoroutine

import android.os.Handler
import android.os.Looper
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI

abstract class AsyncCoroutine<Params, Progress, Result> {

    fun execute(params: Array<Params>) : AsyncCoroutine<Params, Progress, Result> {
        launch(UI) {
            val result : Result = doInBackgroundImpl(params)
            onPostExecute(result)
        }

        return this
    }

    suspend fun doInBackgroundImpl(params : Array<Params>): Result = run(CommonPool) {
        doInBackground(params)
    }

    abstract fun doInBackground(params: Array<Params>) : Result

    abstract fun onPostExecute(result: Result)
}