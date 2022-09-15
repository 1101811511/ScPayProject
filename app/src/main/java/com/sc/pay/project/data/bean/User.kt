package com.sc.pay.project.data.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * author : 桶哥二号
 * motto  : 又是一个坑
 * date   : 2022/9/15.
 */
@Parcelize
data class User(
    val admin: Boolean = false,
    val chapterTops: List<String> = listOf(),
    val collectIds: MutableList<String> = mutableListOf(),
    val email: String = "",
    val icon: String = "",
    val id: String = "",
    val nickname: String = "",
    val password: String = "",
    val token: String = "",
    val type: Int = 0,
    val username: String = ""
):Parcelable
