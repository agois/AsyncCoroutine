package com.agois.asynccoroutine.sample

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.agois.asynccoroutine.AsyncCoroutine

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            val async = object : AsyncCoroutine<String, String, String>() {
                override fun doInBackground(vararg params: String): String {
                    for (i in 1..5) {
                        Log.w("agois", "here " + i)
                        publishProgress(""+i)
                        Thread.sleep(100)
                    }
                    return "res"
                }

                override fun onProgressUpdate(vararg progress: String) {
                    for (i in progress)
                        Snackbar.make(view, "RUN " + i, Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show()
                }

                override fun onPostExecute(result: String) {
                    Log.w("agois", "result")
                    Snackbar.make(view, "RUN " + result, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                }
            }
            async.execute(*arrayOf("1"))
        }
    }

}
