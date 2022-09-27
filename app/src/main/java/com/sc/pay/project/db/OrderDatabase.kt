package com.sc.pay.project.db

import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sc.pay.project.ScApplicaiton

/**
 *    author : 桶哥二号
 *    motto : Anything is possible
 *    date   : 2022/9/26  3:51 下午
 *    desc   : 我好难呀，我太难了呀
 *    version: 1.0
 */
@Database(entities = [OrderEntity::class], version = 1, exportSchema = false)
abstract class OrderDatabase :RoomDatabase() {
    companion object {
        var dataBase: OrderDatabase =
            Room.databaseBuilder(ScApplicaiton.applicationContent, OrderDatabase::class.java, "db_record")
                //是否允许在主线程进行查询
                .allowMainThreadQueries()
                //数据库创建和打开后的回调，可以重写其中的方法
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Log.d("数据库", "onCreate: db_record")
                    }
                })
                //数据库升级异常之后的回滚
                .fallbackToDestructiveMigration()
                .build()

    }

    abstract fun getUserDao(): OrderDao

}