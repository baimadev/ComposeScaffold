package com.holderzone.library.composescaffold.util.ext

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

/**
 * @author terry
 * @date 2018/08/25 下午17:00
 */

/**
 * Extension method to provide show keyboard for View.
 */
fun View.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    this.requestFocus()
    imm.showSoftInput(this, 0)
}

/**
 * Extension method to provide hide keyboard for [View].
 */
fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}


fun Activity.hideKeyboard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(window.decorView.windowToken, 0)
}

/**
 * Extension method to provide hide keyboard for [Activity].
 */
fun Activity.hideSoftKeyboard() {
    currentFocus?.run {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }
}

/**
 * Extension method to provide hide keyboard for [Fragment].
 */
fun Fragment.hideSoftKeyboard() {
    activity?.hideSoftKeyboard()
}

/**
 * Return whether Keyboard is currently visible on screen or not.
 *
 * @return true if keyboard is visible.
 */
fun Activity.isKeyboardVisible(): Boolean {
    val r = Rect()

    //r will be populated with the coordinates of your view that area still visible.
    window.decorView.getWindowVisibleDisplayFrame(r)

    //get screen height and calculate the difference with the usable area from the r
    val height = getDisplaySize().y
    val diff = height - r.bottom

    // If the difference is not 0 we assume that the keyboard is currently visible.
    return diff != 0
}