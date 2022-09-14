package com.sc.pay.project.viewModel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.sc.pay.project.R

/**
 * author : 桶哥二号
 * motto  : 又是一个坑
 * date   : 2022/9/10.
 */
class MainFragmentViewModel:ViewModel() {



    class MainFragmentListener(private val navController: NavController){
       fun onItemClickListener(view: View) {
           when (view.id) {
               R.id.order_pay -> navController.navigate(R.id.action_mainFragment_to_order_pay)
               R.id.check_error -> "异常订单"
               R.id.check_money -> "余额查询"
           }
       }
   }
}