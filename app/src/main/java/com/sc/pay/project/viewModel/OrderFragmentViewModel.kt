package com.sc.pay.project.viewModel

import android.os.RemoteException
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.centerm.smartpos.aidl.qrscan.AidlScanCallback
import com.centerm.smartpos.aidl.qrscan.CameraBeanZbar
import com.sc.pay.project.data.bean.User
import com.sc.pay.project.repository.OrderNumRepository
import com.sc.pay.project.scanCode.ScanCodeHelper
import com.sc.pay.project.widget.apiCall
import com.sc.pay.project.widget.showMsg
import kotlinx.coroutines.launch

/**
 * author : 桶哥二号
 * motto  : 又是一个坑
 * date   : 2022/9/10.
 */

class OrderFragmentViewModel : ViewModel() {
     val orderNum: MutableLiveData<String> = MutableLiveData()
     val user: MutableLiveData<User> = MutableLiveData()
     val orderNumRes: OrderNumRepository = OrderNumRepository()

    fun getOrderInfo(userName: String, pwd: String) {
        try {
            viewModelScope.launch {
                val  result =  apiCall { orderNumRes.getOrderInfo(userName, pwd) }
                if (result.errorCode == 0&&result.data!=null){
                        user.postValue(result.data)
                }else{
                    result.errorMsg!!.showMsg()
                }

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    fun startScan() {
        ScanCodeHelper.getScanServices()
        try {
            val cameraBean = CameraBeanZbar(
                0, 640, 480, 1,
                Int.MAX_VALUE.toLong(), 90, 1
            )
            cameraBean.cameraId = 0
            ScanCodeHelper.mScanDev!!.scanQRCode(cameraBean, object : AidlScanCallback.Stub() {
                @Throws(RemoteException::class)
                override fun onCaptured(result: String, i: Int) {
                    Log.i("扫码", "扫码结果：$result")
                    orderNum.postValue(result)
                }

                @Throws(RemoteException::class)
                override fun onFailed(i: Int) {
                    Log.i("扫码", "扫码失败，错误码：$i")
                }
            })
        } catch (e: Exception) {
            Log.i("扫码异常：", e.message.toString())
        }
    }
}


