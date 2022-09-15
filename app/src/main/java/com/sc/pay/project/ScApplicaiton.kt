package com.sc.pay.project

import android.app.Application
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import com.centerm.smartpos.aidl.qrscan.AidlQuickScanZbar
import com.centerm.smartpos.aidl.sys.AidlDeviceManager
import com.centerm.smartpos.constant.Constant
import kotlin.properties.Delegates

/**
 * author : 桶哥二号
 * motto  : 又是一个坑
 * date   : 2022/9/14.
 */
 class ScApplicaiton : Application() {
    private val TAG: String = ScApplicaiton::class.java.simpleName


    companion object{
        var  applicationContent:Context by Delegates.notNull()
         var mDeviceManager: AidlDeviceManager? = null
         fun getDeviceManager(): AidlDeviceManager? {
            return mDeviceManager
        }
    }

    override fun onCreate() {
        super.onCreate()
        applicationContent = applicationContext
        bindDeviceService()
    }
    /**
     * 初始化设备服务
     */
     fun bindDeviceService(): Boolean {
        val intent = Intent()
        intent.setPackage("com.centerm.smartposservice")
        intent.action = "com.centerm.smartpos.service.MANAGER_SERVICE"
        val result = bindService(intent, mConnectStateListener, BIND_AUTO_CREATE)
        Log.d(TAG, "bind CPaySDK service result => $result")
        return result
    }

    private val mConnectStateListener: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName) {
            mDeviceManager = null
        }

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            mDeviceManager = AidlDeviceManager.Stub.asInterface(service)
        }
    }
}