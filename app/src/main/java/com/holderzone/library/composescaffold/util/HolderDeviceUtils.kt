package com.holderzone.library.composescaffold.util

import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import com.blankj.utilcode.util.EncryptUtils
import com.blankj.utilcode.util.LogUtils
import com.holderzone.library.composescaffold.App


/**
 * Author: lcl <br/>
 * Date: 2018/9/11<br/>
 * Description <br/>
 * 设备相关的操作工具类，例如获取设备id,是否支持打印,刷卡等
 */
@RequiresApi(Build.VERSION_CODES.M)
object HolderDeviceUtils {


    /**
     * 获取设备ID(设备硬件编号)
     */
    val deviceID: String by lazy {
        if (brand == "SUNMI"){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                 Build.getSerial();
            } else {
                 Build.SERIAL;
            }
        }else{
            val c = Class.forName("android.os.SystemProperties")
            val get = c.getMethod("get", String::class.java)
            val deviceId = get.invoke(c, "ro.serialno") as String?
            LogUtils.d(deviceId)
            return@lazy if (deviceId == null || deviceId.isBlank()) uniqueID else deviceId
        }

    }

    /**
     * adnroidId
     */
    val uniqueID: String by lazy {
        val context = App.getContext()
        val androidId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        LogUtils.d(EncryptUtils.encryptMD5ToString(androidId.toString()))
        androidId
    }


    /**
     * 型号
     */
    val model= android.os.Build.MODEL;


    /**
     * 厂商
     */
    val carrier= android.os.Build.MANUFACTURER;


    /**
     * 品牌  商米的品牌名统一为 SUNMI
     */
    val brand: String = android.os.Build.BRAND
//    /**
//     * 仅当brand为SUNMI 下生效
//     * 设备的系统型号 model( V、M、P、L开头为手持设备)
//     */
//    val model: String? by lazy {
//        return@lazy SystemProperties.get("ro.product.model")
//    }
//    /**
//     * 获取ROM版本号
//     */
//    val versionname: String? by lazy {
//        return@lazy SystemProperties.get("ro.version.sunmi_versionname")
//    }
//    /**
//     *获取ROM顺序号
//     */
//    val versioncode: String? by lazy {
//        return@lazy SystemProperties.get("ro.version.sunmi_versioncode")
//    }
//    val sunmi: Boolean by lazy {
//        BRAND_SUNMI.equals(brand, true)
//    }
//    /**
//     *是否支持打印
//     */
//    val supportPrint: Boolean by lazy {
//        if (BRAND_SUNMI.equals(brand, true) &&
//                (BRAND_SUNMI_MODEL_P1.equals(model, true)
//                        || BRAND_SUNMI_MODEL_P1N.equals(model, true)
//                        || BRAND_SUNMI_MODEL_P2.equals(model, true)
//                        || BRAND_SUNMI_MODEL_V1.equals(model, true)
//                        || BRAND_SUNMI_MODEL_V1S.equals(model, true)
//                        || BRAND_SUNMI_MODEL_V2.equals(model, true)
//                        || BRAND_SUNMI_MODEL_V2_MF.equals(model, true)
//                        || BRAND_SUNMI_MODEL_V2PRO.equals(model, true))) {
//            return@lazy true
//        }
//        false
//    }
//    /**
//     * 是否支持银行卡功能 目前商米P系列支持（芯片  nfc   磁条）
//     */
//    val supportBankCard: Boolean by lazy {
//        if (BRAND_SUNMI.equals(brand, true) && (
//                        BRAND_SUNMI_MODEL_P1.equals(model, true)
//                                || BRAND_SUNMI_MODEL_P1N.equals(model, true)
//                                || BRAND_SUNMI_MODEL_P2.equals(model, true))) {
//            return@lazy true
//        }
//        false
//    }
//    /**
//     * 是否支持红外扫描头功能 （条形码和二维码）
//     * L2：L201不带红外扫描头   L203带红外扫描头
//     */
//    val supportInfraredScanning: Boolean by lazy {
//        if (BRAND_SUNMI.equals(brand, true)
//                && (BRAND_SUNMI_MODEL_L2.equals(model, true) && deviceID.startsWith(BRAND_SUNMI_L2_SUPPORT_INFRARED_SCANNING, true))) {
//            return@lazy true
//        }
//        false
//    }
}