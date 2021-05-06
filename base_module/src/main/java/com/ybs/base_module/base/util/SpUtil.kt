package com.ybs.base_module.base.util

import android.content.Context.MODE_PRIVATE
import com.ybs.distributenettool.base.MyApp


/**
 *
 * @ProjectName:    AW
 * @Package:        com.aylson.aw.view.other
 * @ClassName:      SpUtil
 * @Description:
 * @Author:         TaxiDriverSantos
 * @CreateDate:     2020/6/16   10:25
 * @UpdateUser:     TaxiDriverSantos
 * @UpdateDate:     2020/6/16   10:25
 * @UpdateRemark:
 * @Version:        1.0
 */


const val KEY_IS_H_WIND = "keyIsHighWind"
const val KEY_IS_WARDRONE = "keyIsWardrobe"
const val KEY_DELTA_TIME = "keyTime"
const val KEY_TIME_STAMP = "keyTimeStamp"
const val KEY_4_DAILY = "key4Daily"

const val KEY_UDP_URL = "keyUdpUrl"

val app =MyApp.app

val sp = app.getSharedPreferences("sp", MODE_PRIVATE)

fun saveInt(key: String, value: Int) {
    sp.edit().putInt(key, value).apply()
}


fun getInt(key: String, default: Int = -1) = sp.getInt(key, default)


fun saveString(key: String, value: String) {
    sp.edit().putString(key, value).apply()
}

fun getStr(key: String, default: String = "") = sp.getString(key, default)?:""

fun saveBoolean(key: String, value: Boolean) {
    sp.edit().putBoolean(key, value).apply()
}


fun getBoolean(key: String, default: Boolean = false) = sp.getBoolean(key, default)

fun saveLong(key: String, value: Long) {
    sp.edit().putLong(key, value).apply()
}

fun getLong(key: String, default: Long = -1L) = sp.getLong(key, default)

