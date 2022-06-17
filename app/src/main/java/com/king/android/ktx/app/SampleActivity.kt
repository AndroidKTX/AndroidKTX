package com.king.android.ktx.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.king.android.ktx.activity.intentExtra
import com.king.android.ktx.activity.lazyIntentExtra

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
class SampleActivity : AppCompatActivity(R.layout.activity_sample) {

    // 懒加载：相当于懒加载的方式获取 getIntent().getExtras().get("key1"); 类型为：Int（有默认值，可保证不为空，并通过默认值的类型可自动推断出变量的类型）
    private val extra1 by lazyIntentExtra("key1", 0)

    // 属性委托：相当于 getIntent().getExtras().get("key2"); 类型为：String（有默认值，可保证不为空）
    private val extra2 by intentExtra("key2", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, SampleFragment.newInstance(extra1 , extra2, true))
            .commit()
    }
}