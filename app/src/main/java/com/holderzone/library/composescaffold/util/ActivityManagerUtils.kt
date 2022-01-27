package com.holderzone.library.composescaffold.util

import android.app.Activity
import android.util.Log
import java.util.*

object ActivityManagerUtils {



    private val TAG = ActivityManagerUtils::class.java.simpleName
    private var activityStack: Stack<Activity>? = null


    /**
     * 从栈中移除最后一个
     */
    fun popActivity() {
        var activity = currentActivity()
        if (activity != null) {
            if (!activity.isFinishing) {
                activity.finish()
            }
            activityStack!!.removeElement(activity)
            activity = null
        }
        Log.i(TAG, "当前栈内信息${activityStack.toString()}")
    }

    /**
     * 从栈中移除指定的元素
     * @param activity
     */
    fun popActivity(activity: Activity?) {
        var activity = activity
        if (activity != null) {
            Log.i(TAG, "要删除" + activity.javaClass.simpleName)
            if (!activity.isFinishing) {
                activity.finish()
                Log.i(TAG, activity.javaClass.simpleName + "正在关闭")
            } else {
                Log.i(TAG, activity.javaClass.simpleName + "已经关闭")
            }

            activityStack!!.removeElement(activity)
            Log.i(TAG, activity.javaClass.simpleName + "从栈中移除")
            activity = null
        }

        val size = activityStack!!.size
        Log.i(TAG, "删除后个数$size")
        Log.i(TAG, "当前栈内信息${activityStack.toString()}")
    }

    /**
     * 将新元素添加到栈中
     * @param activity
     */
    fun addActivity(activity: Activity) {
        if (activityStack == null) {
            activityStack = Stack()
        }
        activityStack?.add(activity)

        Log.i(TAG, "加入" + activity.javaClass.simpleName)
        val size = activityStack!!.size
        Log.i(TAG, "添加后个数$size")
        Log.i(TAG, "当前栈内信息${activityStack.toString()}")
    }


    /**
     * 将 actvitiy 移除堆栈
     */
    fun removeActivity(activity: Activity){
        if(activityStack != null){
            if(activityStack?.contains(activity)!!){
                Log.i(TAG, "移除" + activity.javaClass.simpleName+"-----移除前个数："+ activityStack?.size)
                activityStack?.remove(activity)
                Log.i(TAG, "移除后个数${activityStack?.size}")
            }
        }
        Log.i(TAG, "当前栈内信息${activityStack.toString()}")
    }



    /**
     * 获取栈顶元素
     * @return
     */
    fun currentActivity(): Activity? {
        var activity: Activity? = null
        try {
            activity = activityStack!!.lastElement()
        } catch (e: Exception) {
        }
        Log.i(TAG, "当前栈内信息${activityStack.toString()}")
        return activity
    }

    /**
     * 移除全部元素，除了指定类型的以外
     * @param cls
     */
    fun popAllActivityExceptOne(cls: Class<*>) {
        var size = activityStack!!.size
        var i = 0
        while (i < size) {
            val activity = activityStack!![i]
            if (activity != null && activity.javaClass == cls) {
            } else {
                popActivity(activity)
                size--
                i--
            }
            i++
        }
        Log.i(TAG, "当前栈内信息${activityStack.toString()}")
    }


    /**
     * 按照给定的Class，结束一个Activity
     * @param cls
     */
    fun finishActivityByClass(cls: Class<*>) {
        var size = activityStack!!.size
        var i = 0
        while (i < size) {
            val activity = activityStack!![i]
            if (activity != null && activity.javaClass == cls) {
                popActivity(activity)
                size--
                i--
            }
            i++
        }
        Log.i(TAG, "当前栈内信息${activityStack.toString()}")
    }

    /**
     * 清除当前栈内实例
     * */
    fun clearActivityStack(){
        var size = activityStack!!.size
        var i = 0
        while (i < size) {
            activityStack!![i]?.let {
                popActivity(it)
                size--
                i--
            }
            i++
        }
        Log.i(TAG, "当前栈内信息${activityStack.toString()}")
    }

}
