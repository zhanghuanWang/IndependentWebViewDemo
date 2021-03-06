package com.acorn.independentwebview

import android.content.Context
import android.webkit.JavascriptInterface
import android.widget.Toast

/**
 * java的话需要继承Object,Kotlin不用
 */
class AndroidToJs(private val context: Context) {

    // 定义JS需要调用的方法
    // 被JS调用的方法必须加入@JavascriptInterface注解
    @JavascriptInterface
    fun hello(msg: String) {
        println("JS调用了Android的hello方法")
        Toast.makeText(context,"点击了",Toast.LENGTH_LONG).show()
    }
}