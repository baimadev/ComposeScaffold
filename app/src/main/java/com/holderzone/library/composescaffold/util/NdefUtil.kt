package com.holderzone.library.composescaffold.util

import android.nfc.FormatException
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.nfc.tech.NdefFormatable
import android.widget.Toast
import com.blankj.utilcode.util.LogUtils
import com.orhanobut.logger.Logger
import java.io.IOException
import java.nio.charset.Charset
import java.util.*
import kotlin.experimental.and

object NdefUtil {


    /**
     * 创建 TNF_WELL_KNOWN with RTD_TEXT数据
     */
    private fun createTextRecord(payload: String, locale: Locale, encodeInUtf8: Boolean): NdefRecord {
        val langBytes = locale.language.toByteArray(Charset.forName("US-ASCII"))
        val utfEncoding = if (encodeInUtf8) Charset.forName("UTF-8") else Charset.forName("UTF-16")
        val textBytes = payload.toByteArray(utfEncoding)
        val utfBit: Int = if (encodeInUtf8) 0 else 1 shl 7
        val status = (utfBit + langBytes.size).toChar()
        val data = ByteArray(1 + langBytes.size + textBytes.size)
        data[0] = status.toByte()
        System.arraycopy(langBytes, 0, data, 1, langBytes.size)
        System.arraycopy(textBytes, 0, data, 1 + langBytes.size, textBytes.size)
        return NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT, ByteArray(0), data)
    }

    //写入NdefData
    fun writeNdefData(tag: Tag?, data:String?) {
        if (data == null || tag == null) return
        val msg = NdefMessage(arrayOf(createTextRecord(data, Locale.getDefault(), true)))
        val size = msg.toByteArray().size

        try {
            //2.判断NFC标签的数据类型（通过Ndef.get方法）
            val ndef = Ndef.get(tag)
            //判断是否为NDEF标签
            if (ndef != null) {
                ndef.connect()
                if (!ndef.isWritable) return
                //判断标签的容量是否够用
                if (ndef.maxSize < size) return
                //3.写入数据
                ndef.writeNdefMessage(msg)
                Logger.d("写入成功")
                ndef.close()
            } else {
                //若标签为格式化按此步骤进行
                val format = NdefFormatable.get(tag)//Ndef格式类
                //判断是否获得了NdefFormatable对象，有一些标签是只读的或者不允许格式化的
                if (format != null) {
                    //连接
                    format.connect()
                    //格式化并将信息写入标签
                    format.format(msg)
                    Logger.d("写入成功")
                    format.close()
                } else {
                    Logger.d("写入失败")
                }
            }
        } catch (e: Exception) {
            Logger.d("写入失败$e")
        }

    }

    /**
     * 解析NDEF文本数据，TNF_WELL_KNOWN with RTD_TEXT
     * @param ndefRecord
     * @return
     */
    fun parseTextRecord(ndefRecord: NdefRecord): String? {
        /**
         * 判断数据是否为NDEF格式
         */
        //判断TNF
        if (ndefRecord.tnf != NdefRecord.TNF_WELL_KNOWN) {
            return null
        }
        //判断可变的长度的类型
        if (!Arrays.equals(ndefRecord.type, NdefRecord.RTD_TEXT)) {
            return null
        }
        try {
            //获得字节数组，然后进行分析
            val payload = ndefRecord.payload
            //下面开始NDEF文本数据第一个字节，状态字节
            //判断文本是基于UTF-8还是UTF-16的，取第一个字节"位与"上16进制的80，16进制的80也就是最高位是1，
            //其他位都是0，所以进行"位与"运算后就会保留最高位
            val tem = (payload[0] and 0x80.toByte()).toInt()
            val textEncoding = if (tem == 0) "UTF-8" else "UTF-16";
            //3f最高两位是0，第六位是1，所以进行"位与"运算后获得第六位
            val languageCodeLength = payload[0] and 0x3f
            //下面开始NDEF文本数据第二个字节，语言编码
            //获得语言编码
            val languageCode = String(payload, 1, languageCodeLength.toInt(), Charset.forName("US-ASCII"));
            //下面开始NDEF文本数据后面的字节，解析出文本
            val textRecord = String(payload, languageCodeLength + 1,
                    payload.size - languageCodeLength - 1, Charset.forName(textEncoding));
            return textRecord;
        } catch (e: Exception) {
            Logger.e(e.toString())
            return null
        }
    }
    /**
     * 写数据
     *
     * @param ndefMessage 创建好的NDEF文本数据
     * @param tag         标签
     * @return
     */
    fun writeTag(ndefMessage: NdefMessage?, tag: Tag?): Boolean {
        LogUtils.d("write_tag", ndefMessage.toString())
        val ndef = Ndef.get(tag)
        val size = ndefMessage?.toByteArray()?.size
        LogUtils.d("write_tag", ndefMessage?.records)
        val ndefFormatable = NdefFormatable.get(tag)
        try {
            if (ndef != null) {
                ndef.connect()

                if (!ndef.isWritable) {
                    throw Exception("NFC Tag是仅仅读的。")
                    return false;
                }
                if (ndef.maxSize < size!!) {
                    throw Exception("NFC Tag的空间不足！")
                    return false;
                }
                ndef.writeNdefMessage(ndefMessage)
                return true
            } else if (ndefFormatable != null) {
                ndefFormatable.connect()
                ndefFormatable.format(ndefMessage)
                return true
            }
        } catch (e: IOException) {
            e.printStackTrace()
            LogUtils.d("Write_Exception", e)
//            LogUtils.d("cache_data",ndef.cachedNdefMessage)
        } catch (e: FormatException) {
            e.printStackTrace()
            LogUtils.d("Write_Exception", e)
        }
        return false
    }


    /**
     * 创建NDEF文本数据
     *
     * @param text
     * @return
     */
     fun createTextRecord(text: String): NdefRecord? {
        val langBytes = Locale.CHINA.language.toByteArray(Charset.forName("US-ASCII"))
        val utfEncoding = Charset.forName("UTF-8")
        //将文本转换为UTF-8格式
        val textBytes = text.toByteArray(utfEncoding)
        //设置状态字节编码最高位数为0
        val utfBit = 0
        //定义状态字节
        val status = (utfBit + langBytes.size).toChar()
        val data = ByteArray(1 + langBytes.size + textBytes.size)
        //设置第一个状态字节，先将状态码转换成字节
        data[0] = status.toByte()
        //设置语言编码，使用数组拷贝方法，从0开始拷贝到data中，拷贝到data的1到langBytes.length的位置
        System.arraycopy(langBytes, 0, data, 1, langBytes.size)
        //设置文本字节，使用数组拷贝方法，从0开始拷贝到data中，拷贝到data的1 + langBytes.length
        //到textBytes.length的位置
        System.arraycopy(textBytes, 0, data, 1 + langBytes.size, textBytes.size)
        //通过字节传入NdefRecord对象
        //NdefRecord.RTD_TEXT：传入类型 读写
        return NdefRecord(
            NdefRecord.TNF_WELL_KNOWN,
            NdefRecord.RTD_TEXT, ByteArray(0), data
        )
    }


    fun byteToHexString(intArray: ByteArray): String {
        return flipHexStr(byteArrayToHexString(intArray))
    }

    private fun flipHexStr(s: String): String {
        val result = StringBuilder()
        var i = 0
        while (i <= s.length - 2) {
            result.append(StringBuilder(s.substring(i, i + 2)).reverse())
            i += 2
        }
        return result.reverse().toString()
    }

    // 16转10进制
    private fun byteArrayToHexString(inarray: ByteArray): String {
        var i: Int
        var `in`: Int
        val hex =
                arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F")
        val out = StringBuilder()
        var j: Int = 0
        while (j < inarray.size) {
            `in` = inarray[j].toInt() and 0xff
            i = `in` shr 4 and 0x0f
            out.append(hex[i])
            i = `in` and 0x0f
            out.append(hex[i])
            ++j
        }
        return out.toString()
    }

}