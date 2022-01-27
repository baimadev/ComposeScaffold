package com.holderzone.library.composescaffold

import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import com.alibaba.android.arouter.launcher.ARouter
import com.holderzone.library.composescaffold.util.XPrefs
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        _context = this
        initLogger()
        initARouter()
        initXPrefs()
    }



    /**初始化Logger*/
    private fun initLogger() {
        //初始化Logger
        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
            //    .showThreadInfo(false) // (Optional) Whether to show thread info or not. Default true
            //    .methodCount(0) // (Optional) How many method line to show. Default 2
            //    .methodOffset(7) // (Optional) Hides internal method calls up to offset. Default 5
            .tag("xia") // (Optional) Global tag for every log. Default PRETTY_LOGGER
            .build()

        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }



    /**初始化路由*/
    private fun initARouter() {
        ARouter.init(this)
    }



    /**初始化xprefs*/
    private fun initXPrefs() {
        XPrefs.Builder()
            .setContext(this)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(packageName)
            .setUseDefaultSharedPreference(true)
            .build()
    }
    
    companion object {
        var _context: Application? = null
        fun getContext(): Context {
            return _context!!
        }

        fun getApplication(): Application {
            return _context!!
        }
    }
}