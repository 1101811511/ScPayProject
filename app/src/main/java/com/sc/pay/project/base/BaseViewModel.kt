package com.sc.pay.project.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sc.pay.project.common.UiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

/**
 *    author : 桶哥二号
 *    motto : Anything is possible
 *    date   : 2022/9/23  2:29 下午
 *    desc   : 我好难呀，我太难了呀
 *    version: 1.0
 */
open class BaseViewModel : ViewModel(), LifecycleObserver {

    val uiState = MutableStateFlow(UiState.LoadDefault)

}