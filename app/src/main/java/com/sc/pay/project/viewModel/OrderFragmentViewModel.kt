package com.sc.pay.project.viewModel

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.sc.pay.project.R

/**
 * author : 桶哥二号
 * motto  : 又是一个坑
 * date   : 2022/9/10.
 */

class OrderFragmentViewModel:ViewModel() {



    fun onClick(view:View){
        when(view.id){
            R.id.check_commit ->Log.i("123123","提交")
            R.id.main_title_back ->Log.i("123123","返回")
        }

    }
}