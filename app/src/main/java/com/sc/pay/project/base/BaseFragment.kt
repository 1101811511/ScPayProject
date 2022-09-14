package com.sc.pay.project.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment

/**
 * author : 桶哥二号
 * motto  : 又是一个坑
 * date   : 2022/9/10.
 */
abstract class BaseFragment <DB : ViewDataBinding,VM : ViewModel>(val mClass:Class<VM>) : Fragment() {

    lateinit var db: DB
    val viewModel by lazy {
        ViewModelProvider(this).get(mClass)
    }
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        db = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        initView()
        initData()
        return db.root
    }

    /**
     * navigation的处理
     */
    fun navigation(): NavController = NavHostFragment.findNavController(this)
    fun naback() = Navigation.findNavController(requireView()).navigateUp()

    /**
     * 初始化
     */
    abstract fun initView()
    abstract fun getLayoutId(): Int
    open fun initData(){

    }


}