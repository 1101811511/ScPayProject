package com.sc.pay.project.ui.fragment

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import androidx.navigation.fragment.FragmentNavigator
import com.sc.pay.project.R
import com.sc.pay.project.base.BaseFragment
import com.sc.pay.project.databinding.FragmentOrderInputBinding
import com.sc.pay.project.viewModel.OrderFragmentViewModel
import java.io.Serializable

/**
 * author : 桶哥二号
 * motto  : 又是一个坑
 * date   : 2022/9/13.
 */
class InputOrderFragment :
    BaseFragment<FragmentOrderInputBinding, OrderFragmentViewModel>(OrderFragmentViewModel::class.java) {
    override fun getLayoutId() = R.layout.fragment_order_input
    override fun initView() {
        refreshUiState(viewModel.uiState)
        db.orderFragmentOnclickLis = OrderOnClickListern()
        db.tabTitle.mainTitleText.text = resources.getString(R.string.input_order)
    }

    override fun initData() {
        viewModel.orderNum .observe(this) { orderNum ->
            db.ddzfOrderNo.setText(orderNum)
            db.ddzfOrderNo.setSelection(orderNum.length)
        }
        viewModel.user.observe(this){
            val bundle = Bundle()
            bundle.putParcelable("result",it as Parcelable)
            navigation().navigate(R.id.action_orderFragment_to_order_detail_fragment, bundle)
        }
        viewModel.startScan()
    }

    //没想到好的办法，后边会优化
    inner class OrderOnClickListern() {
        fun orderClickListener(view: View) {
            when (view.id) {
                R.id.check_commit -> viewModel.registerUser("xijianyu231","123123","123123")
                R.id.main_title_back -> naback()
                R.id.ddzf_scan -> viewModel.startScan()
            }
        }

    }


}