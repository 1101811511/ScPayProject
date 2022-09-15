package com.sc.pay.project.ui.fragment

import android.view.View
import com.sc.pay.project.R
import com.sc.pay.project.base.BaseFragment
import com.sc.pay.project.databinding.FragmentOrderInputBinding
import com.sc.pay.project.viewModel.OrderFragmentViewModel
import com.sc.pay.project.widget.checkConten

/**
 * author : 桶哥二号
 * motto  : 又是一个坑
 * date   : 2022/9/13.
 */
class InputOrderFragment :
    BaseFragment<FragmentOrderInputBinding, OrderFragmentViewModel>(OrderFragmentViewModel::class.java) {
    override fun getLayoutId() = R.layout.fragment_order_input
    override fun initView() {
        db.orderFragmentOnclickLis = OrderOnClickListern()
        db.tabTitle.mainTitleText.text = resources.getString(R.string.input_order)
    }

    override fun initData() {
        viewModel.getOrderNum().observe(this) { orderNum ->
            db.ddzfOrderNo.setText(orderNum)
            db.ddzfOrderNo.setSelection(orderNum.length)
        }
        viewModel.startScan()
    }

    //没想到好的办法，后边会优化
    inner class OrderOnClickListern() {
        fun orderClickListener(view: View) {
            when (view.id) {
//                db.ddzfOrderNo.checkConten(resources.getString(R.string.order_empty))
                R.id.check_commit -> viewModel.getOrderInfo("xijianyu6","123123")
                R.id.main_title_back -> naback()
                R.id.ddzf_scan -> viewModel.startScan()
            }
        }

    }


}