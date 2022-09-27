package com.sc.pay.project.ui.fragment

import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.provider.FontsContractCompat.Columns.RESULT_CODE
import com.sc.pay.project.R
import com.sc.pay.project.base.BaseFragment
import com.sc.pay.project.databinding.FragmentOrderDetailBinding
import com.sc.pay.project.db.OrderDatabase
import com.sc.pay.project.db.OrderEntity
import com.sc.pay.project.viewModel.OderDetailFragmentModel
import com.sc.pay.project.widget.*

/**
 *    author : 桶哥二号
 *    motto : Anything is possible
 *    date   : 2022/9/22  5:34 下午
 *    desc   : 我好难呀，我太难了呀
 *    version: 1.0
 */
class OrderDetailFragment :
    BaseFragment<FragmentOrderDetailBinding, OderDetailFragmentModel>(OderDetailFragmentModel::class.java) {
    val database: OrderDatabase = OrderDatabase.dataBase
    override fun initView() {
        db.orderDetailClickListener = OnDetailClickListener()
        db.tabTitle.mainTitleText.text = resources.getString(R.string.order_info)
        db.tabTitle.mainTitleBack.visibility = View.INVISIBLE
    }


    private val callPay =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.data?.extras != null) {
                if ("00" == result!!.data!!.getStringExtra("resp_code")) {
                    result.data?.let {
                        var orderEntity = OrderEntity(
                            getSysDate("yyyyMMdd"),
                            getSysDate("yyyy-MM-dd-HH:mm:ss"),
                            "1",//0代表成功 1代表失败
                            it.getStringExtra("trans_amt"),
                            System.currentTimeMillis().toString(),
                            "123123"
                        );
                        database.getUserDao().insertOrder(orderEntity)
                    }

                } else {
                    result.data!!.getStringExtra("resp_msg")?.showMsg()
                }
            } else {
                naback()
            }

        }

    override fun getLayoutId() = R.layout.fragment_order_detail

    inner class OnDetailClickListener {
        fun orderDetailClick(view: View) {
            when (view.id) {
                R.id.commit_button -> payWay()
            }
        }
    }

    /**
     * viewmodel中传入context，要优化下
     */
    fun payWay() {
        val payDialog = Dialog(requireContext(), R.style.ChooseDialogTheme)
        val view = View.inflate(requireContext(), R.layout.layout_trans_pop, null)
        payDialog.setContentView(view)
        val window: Window? = payDialog.window
        window?.setGravity(Gravity.BOTTOM)
        window?.setWindowAnimations(R.style.main_menu_animStyle)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        payDialog.show()

        payDialog.findViewById<LinearLayout>(R.id.popup_item_one).setOnClickListener {
            callPay("刷卡")
            payDialog.dismiss()
        }
        payDialog.findViewById<LinearLayout>(R.id.popup_item_two).setOnClickListener {
            callPay("主扫")
            payDialog.dismiss()
        }
        payDialog.findViewById<LinearLayout>(R.id.popup_item_three).setOnClickListener {
            payDialog.dismiss()
            callPay("被扫")
        }

    }


    private fun callPay(payWay: String) {
        val bundle = Bundle()
        when (payWay) {
            "刷卡" -> bundle.putString(trans_code, pay_card)
            "主扫" -> bundle.putString(trans_code, code_scan)
            "被扫" -> bundle.putString(trans_code, scan_code)
        }
        bundle.putString(trans_amt, "0.01") //测试  mJktzsje.getText().toString().trim()
        bundle.putString(caller_id, package_name)
        bundle.putString(control_info, "11000000")
        bundle.putString(order_no, System.currentTimeMillis().toString())
        val intent = Intent()
        intent.component = ComponentName(package_name, activity_name)
        intent.putExtras(bundle!!)
        if (activity?.packageManager?.resolveActivity(
                intent,
                PackageManager.MATCH_DEFAULT_ONLY
            ) != null
        ) {
            try {
                callPay.launch(intent)
            } catch (e: ActivityNotFoundException) {
                "请先安装收单应用!".showMsg()
            }
        } else {
            "请先安装收单应用!".showMsg()
        }


    }

}