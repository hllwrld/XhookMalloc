package com.hllwrld.xhookmalloc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Example of a call to a native method
        setContentView(R.layout.activity_main)

        //load xhook

        //load xhook
        XHook.getInstance().init(this.applicationContext)
        if (!com.qiyi.xhook.XHook.getInstance().isInited()) {
            return
        }
        //com.qiyi.xhook.XHook.getInstance().enableDebug(true); //default is false
        //com.qiyi.xhook.XHook.getInstance().enableSigSegvProtection(false); //default is true

        //load and run your biz lib (for register hook points)
        //com.qiyi.xhook.XHook.getInstance().enableDebug(true); //default is false
        //com.qiyi.xhook.XHook.getInstance().enableSigSegvProtection(false); //default is true

        //load and run your biz lib (for register hook points)
        com.qiyi.biz.Biz.getInstance().init()
        com.qiyi.biz.Biz.getInstance().start()

        //xhook do refresh

        //xhook do refresh
        com.qiyi.xhook.XHook.getInstance().refresh(false)

        //load and run the target lib

        //load and run the target lib
        com.qiyi.test.Test.getInstance().init()
        com.qiyi.test.Test.getInstance().start()
        try {
            Thread.sleep(200)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        //xhook do refresh again

        //xhook do refresh again
        com.qiyi.xhook.XHook.getInstance().refresh(false)

        //xhook do refresh again for some reason,
        //maybe called after some System.loadLibrary() and System.load()
        //*

        //xhook do refresh again for some reason,
        //maybe called after some System.loadLibrary() and System.load()
        //*
        Thread {
            while (true) {
                com.qiyi.xhook.XHook.getInstance().refresh(true)
                try {
                    Thread.sleep(5000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }.start()
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI()

    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}