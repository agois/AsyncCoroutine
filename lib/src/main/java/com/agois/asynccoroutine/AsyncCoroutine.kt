package com.agois.asynccoroutine

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.run

abstract class AsyncCoroutine<Params, Progress, Result> {

    fun execute(vararg params: Params) : AsyncCoroutine<Params, Progress, Result> {
        launch(UI) {
            val result : Result = doInBackgroundImpl(*params)
            onPostExecute(result)
        }

        return this
    }

    suspend fun doInBackgroundImpl(vararg params : Params): Result = run(CommonPool) {
        doInBackground(*params)
    }

    abstract fun doInBackground(vararg params: Params) : Result

    abstract fun onPostExecute(result: Result)

    protected open fun onProgressUpdate(vararg values: Progress) {}

    protected fun publishProgress(vararg values: Progress) {
        onProgressUpdate(*values)
    }
}