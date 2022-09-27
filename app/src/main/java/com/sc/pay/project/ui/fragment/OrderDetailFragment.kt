package com.sc.pay.project.ui.fragment

import android.app.Dialog
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import com.sc.pay.project.R
import com.sc.pay.project.ScApplicaiton
import com.sc.pay.project.base.BaseFragment
import com.sc.pay.project.databinding.FragmentOrderDetailBinding
import com.sc.pay.project.viewModel.OderDetailFragmentModel
import com.sc.pay.project.widget.activity_name
import com.sc.pay.project.widget.package_name
import com.sc.pay.project.widget.showMsg

/**
 *    author : 桶哥二号
 *    motto : Anything is possible
 *    date   : 2022/9/22  5:34 下午
 *    desc   : 我好难呀，我太难了呀
 *    version: 1.0
 */
class OrderDetailFragment :
    BaseFragment<FragmentOrderDetailBinding, OderDetailFragmentModel>(OderDetailFragmentModel::class.java) {
    override fun initView() {
        db.orderDetailClickListener = OnDetailClickListener()
        db.tabTitle.mainTitleText.text = resources.getString(R.string.order_info)
        db.tabTitle.mainTitleBack.visibility = View.INVISIBLE
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
        val  bundle = Bundle()
        when (payWay) {
            "刷卡" -> {
                bundle.putString()
                "刷卡".showMsg()
            }
            "主扫" -> {
                "主扫".showMsg()
            }
            "被扫" -> {
                "被扫".showMsg()
            }
        }
        val intent = Intent()
        intent.component = ComponentName(package_name, activity_name)
        intent.putExtras(bundle!!)
//        if (getV().getActivity().getPackageManager().resolveActivity(
//                intent,
//                PackageManager.MATCH_DEFAULT_ONLY
//            ) != null
//        ) {
//            try {
//              startActivityForResult(intent, request_pay)
//            } catch (e: ActivityNotFoundException) {
//                Toast.makeText(getV().getContext(), "请先安装收单应用！", Toast.LENGTH_SHORT).show()
//            }
//        } else {
//            Toast.makeText(getV().getContext(), "请先安装收单应用！", Toast.LENGTH_SHORT).show()
//        }


    }
}