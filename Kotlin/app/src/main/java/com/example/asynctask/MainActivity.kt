package com.example.asynctask

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity() : AppCompatActivity(){
    private var mTextView: TextView? = null
    private var TEXT_STATE = "currentText"
    private var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mTextView = findViewById(R.id.textView1)
        progressBar = findViewById(R.id.progressBar)

        with(mTextView) { this?.setText(savedInstanceState?.getString(TEXT_STATE)) }
    }

    fun startTask(view: View?) { // Put a message in the text view
        mTextView?.setText(R.string.napping)
        progressBar?.setMax(10)
        progressBar?.setProgress(0)

        // Start the AsyncTask.
        val task = SimpleAsyncTask()
        task.execute(10)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Save the state of the TextView
        outState.putString(TEXT_STATE, mTextView!!.text.toString())
    }
}
