package com.holderzone.library.composescaffold.util.ext

import android.Manifest
import android.accounts.AccountManager
import android.app.*
import android.app.admin.DevicePolicyManager
import android.app.job.JobScheduler
import android.app.usage.NetworkStatsManager
import android.app.usage.UsageStatsManager
import android.appwidget.AppWidgetManager
import android.bluetooth.BluetoothManager
import android.content.BroadcastReceiver
import android.content.ClipboardManager
import android.content.Context
import android.content.RestrictionsManager
import android.content.pm.LauncherApps
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Point
import android.graphics.drawable.Drawable
import android.hardware.ConsumerIrManager
import android.hardware.SensorManager
import android.hardware.camera2.CameraManager
import android.hardware.display.DisplayManager
import android.hardware.fingerprint.FingerprintManager
import android.hardware.input.InputManager
import android.hardware.usb.UsbManager
import android.location.LocationManager
import android.media.AudioManager
import android.media.MediaRouter
import android.media.midi.MidiManager
import android.media.projection.MediaProjectionManager
import android.media.session.MediaSessionManager
import android.media.tv.TvInputManager
import android.net.ConnectivityManager
import android.net.nsd.NsdManager
import android.net.wifi.WifiManager
import android.net.wifi.p2p.WifiP2pManager
import android.nfc.NfcManager
import android.os.*
import android.os.storage.StorageManager
import android.print.PrintManager
import android.telecom.TelecomManager
import android.telephony.CarrierConfigManager
import android.telephony.SubscriptionManager
import android.telephony.TelephonyManager
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.WindowManager
import android.view.accessibility.AccessibilityManager
import android.view.accessibility.CaptioningManager
import android.view.inputmethod.InputMethodManager
import android.view.textservice.TextServicesManager
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import androidx.core.content.ContextCompat
import java.io.File
import java.io.IOException
import java.nio.charset.Charset

/**
 * @author terry
 * @date 2018/08/17 上午10:58
 */

/**
 * 数据流量格式化
 */
fun Context.dataFormat(total: Long): String {
    val result: String
    val speedReal: Int = (total / (1024)).toInt()
    result = if (speedReal < 512) {
        speedReal.toString() + " KB"
    } else {
        val mSpeed = speedReal / 1024.0
        (Math.round(mSpeed * 100) / 100.0).toString() + " MB"
    }
    return result
}

/**
 * 获取DisplaySize，以Point对象表示
 */
fun Context.getDisplaySize() = Point().apply { getWindowManager().defaultDisplay.getSize(this) }

/**
 * 屏幕宽度
 */
fun Context.displayWidth(): Int = getDisplaySize().x

/**
 * 屏幕高度
 */
fun Context.displayHeight(): Int = getDisplaySize().y

/**
 * 根据资源返回解码后的Bitmap，如果图片无法被解码，返回null
 */
fun Context.decodeBitmap(resId: Int): Bitmap? = BitmapFactory.decodeResource(resources, resId)

/**
 * 从ContextCompat获取颜色Color
 */
fun Context.getColorCompat(color: Int) = ContextCompat.getColor(this, color)

/**
 * 从ContextCompat中获取Drawable
 */
fun Context.getDrawableCompat(drawableResId: Int): Drawable? =
    ContextCompat.getDrawable(this, drawableResId)

/**
 * Checks network connectivity
 */
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
fun Context.isNetworkStatusAvailable(): Boolean {
    val connectivityManager = this
        .getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    connectivityManager?.let {
        val netInfo = it.activeNetworkInfo
        netInfo?.let {
            if (netInfo.isConnected) return true
        }
    }
    return false
}

/**
 * Execute block of code if network is available
 */
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
inline fun Context.withNetwork(block: () -> Unit) {
    if (isNetworkStatusAvailable()) {
        block()
    }
}

/**
 * Loads content of file from assets as String using UTF-8 charset
 */
fun Context.loadFromAsset(jsonName: String): String? {
    var stream: String? = null
    try {
        val inputStream = this.assets.open(jsonName)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        stream = String(buffer, Charset.forName("UTF-8"))
    } catch (e: IOException) {
    }
    return stream
}

/**
 * Computes status bar height
 */
fun Context.getStatusBarHeight(): Int {
    var result = 0
    val resourceId = this.resources.getIdentifier(
        "status_bar_height", "dimen",
        "android"
    )
    if (resourceId > 0) {
        result = this.resources.getDimensionPixelSize(resourceId)
    }
    return result
}


/**
 * Computes screen height
 */
fun Context.getScreenHeight(): Int {
    var screenHeight = 0
    val wm = this.getSystemService(Context.WINDOW_SERVICE) as? WindowManager
    wm?.let {
        val metrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(metrics)
        screenHeight = metrics.heightPixels
    }
    return screenHeight
}

fun Context.checkAllSelfPermissionsGranted(vararg permissions: String): Boolean {
    return permissions.none {
        ContextCompat.checkSelfPermission(
            this,
            it
        ) != PackageManager.PERMISSION_GRANTED
    }
}

fun Context.checkAnySelfPermissionsGranted(vararg permissions: String): Boolean {
    return permissions.any {
        ContextCompat.checkSelfPermission(
            this,
            it
        ) == PackageManager.PERMISSION_GRANTED
    }
}

fun Context.unregisterReceiverSafe(receiver: BroadcastReceiver?): Boolean {
    if (receiver == null) return false
    return try {
        unregisterReceiver(receiver)
        true
    } catch (e: IllegalArgumentException) {
        false
    }
}

val Context.preferExternalCacheDir: File
    get() = externalCacheDir ?: cacheDir

fun Context.getActivityManager() = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
fun Context.getAlarmManager() = getSystemService(Context.ALARM_SERVICE) as AlarmManager
fun Context.getAudioManager() = getSystemService(Context.AUDIO_SERVICE) as AudioManager
fun Context.getClipboardManager() = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
fun Context.getConnectivityManager() =
    getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

fun Context.getKeyguardManager() = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
fun Context.getLayoutInflater() =
    getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

fun Context.getLocationManager() = getSystemService(Context.LOCATION_SERVICE) as LocationManager
fun Context.getNotificationManager() =
    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

fun Context.getPowerManager() = getSystemService(Context.POWER_SERVICE) as PowerManager
fun Context.getSearchManager() = getSystemService(Context.SEARCH_SERVICE) as SearchManager
fun Context.getSensorManager() = getSystemService(Context.SENSOR_SERVICE) as SensorManager
fun Context.getTelephonyManager() = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
fun Context.getVibrator() = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
fun Context.getWallpaperService() = getSystemService(Context.WALLPAPER_SERVICE) as WallpaperManager
fun Context.getWifiManager() =
    applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

fun Context.getWindowManager() = getSystemService(Context.WINDOW_SERVICE) as WindowManager
fun Context.getInputMethodManager() =
    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

fun Context.getAccessibilityManager() =
    getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager

fun Context.getAccountManager() = getSystemService(Context.ACCOUNT_SERVICE) as AccountManager
fun Context.getDevicePolicyManager() =
    getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager

fun Context.getDropBoxManager() = getSystemService(Context.DROPBOX_SERVICE) as DropBoxManager
fun Context.getUiModeManager() = getSystemService(Context.UI_MODE_SERVICE) as UiModeManager
fun Context.getDownloadManager() = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
fun Context.getStorageManager() = getSystemService(Context.STORAGE_SERVICE) as StorageManager
fun Context.getNfcManager() = getSystemService(Context.NFC_SERVICE) as NfcManager
fun Context.getUsbManager() = getSystemService(Context.USB_SERVICE) as UsbManager
fun Context.getTextServicesManager() =
    getSystemService(Context.TEXT_SERVICES_MANAGER_SERVICE) as TextServicesManager

fun Context.getWifiP2pManager() = getSystemService(Context.WIFI_P2P_SERVICE) as WifiP2pManager
fun Context.getInputManager() = getSystemService(Context.INPUT_SERVICE) as InputManager
fun Context.getMediaRouter() = getSystemService(Context.MEDIA_ROUTER_SERVICE) as MediaRouter
fun Context.getNsdManager() = getSystemService(Context.NSD_SERVICE) as NsdManager
fun Context.getDisplayManager() = getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
fun Context.getUserManager() = getSystemService(Context.USER_SERVICE) as UserManager
fun Context.getBluetoothManager() = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
fun Context.getAppOpsManager() = getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
fun Context.getCaptioningManager() =
    getSystemService(Context.CAPTIONING_SERVICE) as CaptioningManager

fun Context.getConsumerIrManager() =
    getSystemService(Context.CONSUMER_IR_SERVICE) as ConsumerIrManager

fun Context.getPrintManager() = getSystemService(Context.PRINT_SERVICE) as PrintManager
fun Context.getAppWidgetManager() = getSystemService(Context.APPWIDGET_SERVICE) as AppWidgetManager
fun Context.getBatteryManager() = getSystemService(Context.BATTERY_SERVICE) as BatteryManager
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Context.getCameraManager() = getSystemService(Context.CAMERA_SERVICE) as CameraManager
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Context.getJobScheduler() = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Context.getLauncherApps() = getSystemService(Context.LAUNCHER_APPS_SERVICE) as LauncherApps
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Context.getMediaProjectionManager() =
    getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Context.getMediaSessionManager() =
    getSystemService(Context.MEDIA_SESSION_SERVICE) as MediaSessionManager

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Context.getRestrictionsManager() =
    getSystemService(Context.RESTRICTIONS_SERVICE) as RestrictionsManager

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Context.getTelecomManager() = getSystemService(Context.TELECOM_SERVICE) as TelecomManager
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Context.getTvInputManager() = getSystemService(Context.TV_INPUT_SERVICE) as TvInputManager
@RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
fun Context.getSubscriptionManager() =
    getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE) as SubscriptionManager

@RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
fun Context.getUsageStatsManager() =
    getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager

@RequiresApi(Build.VERSION_CODES.M)
fun Context.getCarrierConfigManager() =
    getSystemService(Context.CARRIER_CONFIG_SERVICE) as CarrierConfigManager

@RequiresApi(Build.VERSION_CODES.M)
fun Context.getFingerprintManager() =
    getSystemService(Context.FINGERPRINT_SERVICE) as FingerprintManager

@RequiresApi(Build.VERSION_CODES.M)
fun Context.getMidiManager() = getSystemService(Context.MIDI_SERVICE) as MidiManager

@RequiresApi(Build.VERSION_CODES.M)
fun Context.getNetworkStatsManager() =
    getSystemService(Context.NETWORK_STATS_SERVICE) as NetworkStatsManager