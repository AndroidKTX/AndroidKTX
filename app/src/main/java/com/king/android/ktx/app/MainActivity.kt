package com.king.android.ktx.app

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.king.android.ktx.activity.*
import com.king.android.ktx.app.databinding.ActivityMainBinding
import com.king.android.ktx.core.*


class MainActivity : AppCompatActivity() {

    // 属性委托：相当于 getIntent().getExtras().get("extra1"); 类型为：Int?
    private val extra1: Int? by intentExtra<Int>("extra1")

    // 属性委托：相当于 getIntent().getExtras().get("extra2"); 类型为：Int?（有申明类型时，可以省略泛型）
    private val extra2: Int? by intentExtra("extra2")

    // 属性委托：相当于 getIntent().getExtras().get("extra3"); 类型为：Int?（有声明泛型时，可以省略类型）
    private val extra3 by intentExtra<Int>("extra3")

    // 属性委托：相当于 getIntent().getExtras().get("extra4"); 类型为：Int（有默认值，可保证不为空）
    private val extra4: Int by intentExtra("extra4", 1)

    // 属性委托：相当于 getIntent().getExtras().get("extra5"); 类型为：Int（有默认值，通过默认值的类型可自动推断出变量的类型）
    private val extra5 by intentExtra("extra5", 1)

    // 属性委托：相当于 getIntent().getExtras().get("extra6"); 类型为：Int（key的默认值如果忽略或为空时，则默认值为变量的名称）
    private val extra6 by intentExtra(defaultValue = 1)

    // 属性委托：相当于懒加载的方式获取 getIntent().getExtras().get("extra7"); 类型为：Int?
    private val extra7: Int? by intentExtra()

    // 懒加载：相当于懒加载的方式获取 getIntent().getExtras().get("extra8"); 类型为：Int?
    private val extra8: Int? by lazyIntentExtra("extra8")

    // 懒加载：相当于懒加载的方式获取 getIntent().getExtras().get("extra9"); 类型为：Int（有默认值，可保证不为空，并通过默认值的类型可自动推断出变量的类型）
    private val extra9 by lazyIntentExtra("extra9", 1)

    // 单个权限申请：只需在需要使用的地方调用：cameraPermissionLauncher.launch() 即可触发回调
    private val cameraPermissionLauncher =
        requestPermissionLauncher(Manifest.permission.CAMERA) { granted ->

            showToast("granted = $granted")
            log(msg = "granted = $granted")
            if (granted) {
                // 已授权，则进行拍照预览
                picturePreviewLauncher.launch()
            }
        }

    // 多个权限申请：只需在需要使用的地方调用：readWritePermissionLauncher.launch() 即可触发回调
    private val readWritePermissionLauncher = requestMultiplePermissionsLauncher(
        arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    ) { allGranted, grantedPermissions, deniedPermissions ->
        showToast("allGranted = $allGranted")
        log(msg = "allGranted = $allGranted, grantedPermissions = $grantedPermissions, deniedPermissions = $deniedPermissions")
        if (allGranted) {
            // 已全部授权，则进行选择文件
            documentLauncher.launch()
        }
    }

    // 拍照预览
    private val picturePreviewLauncher = takePicturePreviewLauncher {
        binding.iv.setImageBitmap(it)
    }

    // 选择文件 (类型：image/*)
    private val documentLauncher = openDocumentLauncher(arrayOf("image/*")) {
        binding.iv.setImageURI(it)
    }

    // startActivityForResult：只需在需要使用的地方调用：startActivityLauncher.launch(intent) 即可触发回调
    private val startActivityLauncher = startActivityForResultLauncher {
        log(msg = "resultCode = ${it.resultCode}")
        if (it.isResultOk()) {
            // 成功
            showToast("Result Ok")
        } else {
            showToast("Result Cancel")
        }
    }

    private val binding by lazy {
        ActivityMainBinding.inflate(
            layoutInflater,
            window.decorView as ViewGroup,
            false
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        printlnIntentExtras()

    }

    private fun printlnIntentExtras() {

        log(msg = "private val extra1: Int? by intentExtra<Int>(\"extra1\") \t i1 = $extra1")
        log(msg = "private val extra2: Int? by intentExtra(\"extra2\") \t i2 = $extra2")
        log(msg = "private val extra3 by intentExtra<Int>(\"extra3\") \t i3 = $extra3")
        log(msg = "private val extra4: Int by intentExtra(\"extra4\", 1) \t i4 = $extra4")
        log(msg = "private val extra5 by intentExtra(\"extra5\", 1) \t i6 = $extra5")
        log(msg = "private val extra6 by intentExtra(defaultValue = 1) \t i7 = $extra6")
        log(msg = "private val extra7: Int? by intentExtra() \t i8 = $extra7")
        log(msg = "private val extra8: Int? by lazyIntentExtra(\"extra8\") \t extra8 = $extra8")
        log(msg = "private val extra9 by lazyIntentExtra(\"extra9\", 1) \t extra9 = $extra9")

    }

    private fun testRequestPermission() {
        cameraPermissionLauncher.launch()
    }

    private fun testRequestMultiplePermissions() {
        readWritePermissionLauncher.launch()
    }

    private fun testStartActivity() {
//        // 相当于 startActivity(Intent(this, MainActivity::class.java))
//        startActivity<MainActivity>()
//        // 相当于 startActivity(Intent(this, MainActivity::class.java))
//        startActivity(MainActivity::class.java)
        // 相当于 startActivity(Intent(this, MainActivity::class.java).putExtra("key1", 1).putExtra("key2", "Start Activity")
        startActivity(SampleActivity::class.java, "key1" to 1, "key2" to "Start Activity")
    }

    private fun testStartActivityForResult() {

        startActivityLauncher.launch(
            intentOf(
                SampleActivity::class.java,
                "key1" to 2,
                "key2" to "Start Activity For Result"
            )
        )
    }


    fun onClick(v: View) {
        when (v.id) {
            R.id.btnRequestPermission -> testRequestPermission()
            R.id.btnRequestMultiplePermissions -> testRequestMultiplePermissions()
            R.id.btnStartActivity -> testStartActivity()
            R.id.btnStartActivityForResult -> testStartActivityForResult()
        }
    }

}