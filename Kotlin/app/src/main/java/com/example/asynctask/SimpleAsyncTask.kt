package com.example.asynctask

import android.os.AsyncTask
import android.widget.ProgressBar
import android.widget.TextView
import java.lang.ref.WeakReference
import java.security.SecureRandom

class SimpleAsyncTask : AsyncTask<Int, Int, String>() {
    private var mTextView: WeakReference<TextView>? = null
    private var progressBar: WeakReference<ProgressBar>? = null

    fun SimpleAsyncTask(tv: TextView, pb: ProgressBar) {
        mTextView = WeakReference(tv)
        progressBar = WeakReference(pb)
    }

    override fun doInBackground(vararg integers: Int?): String? {
        // Generate a random number between 0 and 10
        var r = SecureRandom()
        var n = r.nextInt(11)

        // Make the task take long enough that we have
        // time to rotate the phone while it is running
        var s = n * 200

        // Sleep for the random amount of time
        for (count in 0..integers[0]!!) {
            try {
                Thread.sleep(s.toLong())
                publishProgress(count)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }

        // Return a String result
        return "Awake at last after sleeping for ${s* integers[0]!!} milliseconds!"
    }

    protected fun onProgressUpdate(vararg values: Int) {
        progressBar!!.get()!!.setProgress(values[0]);
    }

    override fun onPostExecute(result: String?) {
        mTextView!!.get()!!.text = result
    }
}