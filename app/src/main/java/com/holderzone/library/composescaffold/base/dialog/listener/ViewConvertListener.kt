package com.holderzone.library.composescaffold.base.dialog.listener

import android.os.Parcel
import android.os.Parcelable
import com.holderzone.android.store.view.dialog.other.ViewHolder
import com.holderzone.library.composescaffold.base.dialog.BaseDialogFragment


/**
 * @ClassName : ViewConvertListener
 * @Description:
 * @Author: XiaRuPeng
 * @Date: 2021/7/23 10:55
 */

abstract class ViewConvertListener : Parcelable {

    abstract fun convertView(holder: ViewHolder, dialogFragment: BaseDialogFragment)

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {}

    constructor() {}

    protected constructor(source: Parcel) {}

    companion object {
        val CREATOR: Parcelable.Creator<ViewConvertListener> = object : Parcelable.Creator<ViewConvertListener> {
            override fun createFromParcel(source: Parcel): ViewConvertListener {
                return object : ViewConvertListener(source) {
                    override fun convertView(holder: ViewHolder, dialogFragment: BaseDialogFragment) {

                    }
                }
            }

            override fun newArray(size: Int): Array<ViewConvertListener?> {
                return arrayOfNulls(size)
            }
        }
    }
}