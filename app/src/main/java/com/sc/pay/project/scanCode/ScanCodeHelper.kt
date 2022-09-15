package com.sc.pay.project.scanCode

import android.os.RemoteException
import android.util.Log
import com.centerm.smartpos.aidl.qrscan.AidlQuickScanZbar
import com.centerm.smartpos.aidl.qrscan.AidlScanCallback
import com.centerm.smartpos.aidl.qrscan.CameraBeanZbar
import com.centerm.smartpos.constant.Constant
import com.sc.pay.project.ScApplicaiton

/**
 * author : 桶哥二号
 * motto  : 又是一个坑
 * date   : 2022/9/14.
 */
 object ScanCodeHelper {
    var mScanDev:AidlQuickScanZbar? =null
     fun getScanServices(){
         mScanDev = AidlQuickScanZbar.Stub.asInterface(
             ScApplicaiton.getDeviceManager()?.getDevice(
                 Constant.DEVICE_TYPE.DEVICE_TYPE_QUICKSCAN
             )
         )

     }

    fun openCamera(){
        try {
            val cameraBean = CameraBeanZbar(
                0, 640, 480, 1,
                Int.MAX_VALUE.toLong(), 90, 1
            )
            cameraBean.cameraId = 0
            mScanDev!!.scanQRCode(cameraBean, object : AidlScanCallback.Stub() {
                @Throws(RemoteException::class)
                override fun onCaptured(s: String, i: Int) {
                    Log.i("111111", "扫码结果：$s")
                }

                @Throws(RemoteException::class)
                override fun onFailed(i: Int) {
                    Log.i("11111", "扫码失败，错误码：$i")
                }
            })
        } catch (e: Exception) {
            Log.i("扫码异常：", e.message.toString())
        }
    }
}