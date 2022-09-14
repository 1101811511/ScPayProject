package com.sc.pay.project.ui.fragment

import androidx.databinding.adapters.ImageViewBindingAdapter
import com.bumptech.glide.Glide
import com.sc.pay.project.R
import com.sc.pay.project.base.BaseFragment
import com.sc.pay.project.databinding.FragmentMainBinding
import com.sc.pay.project.viewModel.MainFragmentViewModel
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
        db.mainFramgentClick = MainFragmentViewModel.MainFragmentListener(navigation())
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

}