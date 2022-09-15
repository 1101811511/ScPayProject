package com.sc.pay.project.viewModel

import android.os.RemoteException
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.centerm.smartpos.aidl.qrscan.AidlScanCallback
import com.centerm.smartpos.aidl.qrscan.CameraBeanZbar
import com.sc.pay.project.R
import com.sc.pay.project.data.bean.User
import com.sc.pay.project.repository.OrderNumRepository
import com.sc.pay.project.scanCode.ScanCodeHelper
import com.sc.pay.project.ui.fragment.InputOrderFragment
import kotlinx.coroutines.launch

/**
 * author : 桶哥二号
 * motto  : 又是一个坑
 * date   : 2022/9/10.
 */

class OrderFragmentViewModel : ViewModel() {
   private val orderNum:MutableLiveData<String> = MutableLiveData()
    //测试
    private val user:MutableLiveData<User> = MutableLiveData()

    fun getOrderNum():MutableLiveData<String> =orderNum

    val orderNumRes:OrderNumRepository = OrderNumRepository()


    fun getOrderInfo(userName:String,pwd:String){
        viewModelScope.launch {
            user.value =orderNumRes.getOrderInfo(userName,pwd)
        }
    }


    fun startScan(){
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
                    Log.i("111111", "扫码结果：$result")
                    orderNum.postValue(result)
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


