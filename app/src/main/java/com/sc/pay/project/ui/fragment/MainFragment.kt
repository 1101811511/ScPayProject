package com.sc.pay.project.ui.fragment

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.adapters.ImageViewBindingAdapter
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import com.sc.pay.project.R
import com.sc.pay.project.base.BaseFragment
import com.sc.pay.project.databinding.FragmentMainBinding
import com.sc.pay.project.db.OrderEntity
import com.sc.pay.project.viewModel.MainFragmentViewModel
import com.sc.pay.project.widget.*
import com.youth.banner.adapter.BannerAdapter
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder

/**
 * author : 桶哥二号
 * motto  : 又是一个坑
 * date   : 2022/9/10.
 */
class MainFragment :
    BaseFragment<FragmentMainBinding, MainFragmentViewModel>(MainFragmentViewModel::class.java) {
    override fun getLayoutId() = R.layout.fragment_main
    override fun initView() {
        db.mainFramgentClick = MainFragmentListener()
       val  mutableList =  mutableListOf(R.mipmap.guanggao,R.mipmap.guanggao1)
        db.banner.setAdapter(object:BannerImageAdapter<Int>(mutableList){
            override fun onBindView(
                holder: BannerImageHolder?,
                data: Int?,
                position: Int,
                size: Int
            ) {
                Glide.with(context!!).load(data).into(holder!!.imageView)
            }

        })
    }

  inner class MainFragmentListener(){
        fun onItemClickListener(view: View) {
            when (view.id) {
                R.id.order_pay -> navigation().navigate(R.id.action_mainFragment_to_order_pay)
                R.id.check_record -> navigation().navigate(R.id.action_mainFragment_to_order_list)
                R.id.check_money -> {
                    val bundle = Bundle()
                    val intent = Intent()
                    bundle.putString(trans_code, check_money)
                    bundle.putString(caller_id, package_name)
                    intent.component = ComponentName(package_name, activity_name)
                    intent.putExtras(bundle!!)
                    checkMoney.launch(intent)
                }
                }
            }
        }

    private val checkMoney =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.data?.extras != null) {
                if ("00" != result!!.data!!.getStringExtra("resp_code")) {
                    result.data!!.getStringExtra("resp_msg")?.showMsg()
                }
            } else {
                naback()
            }

        }
}

