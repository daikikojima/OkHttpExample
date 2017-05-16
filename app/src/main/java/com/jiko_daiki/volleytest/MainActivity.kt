package com.jiko_daiki.volleytest

import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.jiko_daiki.volleytest.R.id.text
import kotlinx.android.synthetic.main.activity_main.*;
import java.io.IOException

import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response

class MainActivity : AppCompatActivity() {

    //var text: TextView = null!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Thread(Runnable {
            val client = OkHttpClient()
            try {
                val body = RequestBody.create(JSON, "{\"name\":\"jiko\"}")
                val request = Request.Builder().url("https://expressjson-jiko.c9users.io/test").get().build()
                val res = client.newCall(request).execute()
                val data = res.body().string()
                runOnUiThread {
                    text.text = data
                }
            }
            catch (e: IOException) {
                runOnUiThread {
                    text.text = e.toString() + "\nError Occured"
                }
            }
        }).start()
    }

    companion object {
        //Define the MediaType called JSON
        val JSON = MediaType.parse("application/json; charset=utf-8")
    }


}
