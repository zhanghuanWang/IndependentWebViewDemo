package com.acorn.independentwebview.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.acorn.independentwebview.Book
import com.acorn.independentwebview.IMyAidlInterface

class MyService : Service() {
    private val bookList = mutableListOf<Book>()

    override fun onCreate() {
        super.onCreate()
        initData()
    }

    private fun initData() {
        bookList.add(Book("书a"))
        bookList.add(Book("书b"))
        bookList.add(Book("书c"))
        bookList.add(Book("书d"))
        bookList.add(Book("书3"))
    }

    override fun onBind(intent: Intent?): IBinder? {
        return MyBinder()
    }

    inner class MyBinder : IMyAidlInterface.Stub() {
        override fun requestBookList(): MutableList<Book> {
            return bookList
        }

        override fun addBookInOut(book: Book?) {
            book?.let {
                it.name = "服务器改了名字 ${it.name}"
                bookList.add(it)
            }
        }

        /**
         * In 类型的表现形式是：数据只能由客户端传向服务端，服务端对数据的修改不会影响到客户端
         */
        override fun addBookIn(book: Book?) {
            book?.let {
                it.name = "我服务器改不了?!"
                bookList.add(it)
            }
        }

        /**
         * Out类型的表现形式是：数据只能由服务端传向客户端，即使客户端向方法接口传入了一个对象，
         * 该对象中的属性值也是为空的，即不包含任何数据，服务端获取到该对象后，对该对象的任何操作，就会同步到客户端这边
         */
        override fun addBookOut(book: Book?) {
            book?.let {
                it.name = "你客户端改不了 ${it.name}"
                bookList.add(it)
            }
        }

        override fun myAction(msg: String?): String {
            return "I see,$msg right?"
        }
    }
}