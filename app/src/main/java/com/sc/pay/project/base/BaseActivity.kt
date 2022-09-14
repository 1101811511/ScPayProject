package com.sc.pay.project.base

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

/**
 * author : 桶哥二号
 * motto  : 又是一个坑
 * date   : 2022/9/10.
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayOutId())
    }

    abstract fun getLayOutId(): Int
    fun <T> startActivity(clazz: Class<T>, bundle: Bundle?) {
        val intent = Intent(this, clazz).apply {
            bundle?.let {
                putExtras(it)
            }
        }
        startActivity(intent)
    }
}