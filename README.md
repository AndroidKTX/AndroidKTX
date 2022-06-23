# AndroidKTX

![Logo](app/src/main/ic_launcher-playstore.png)

[![Download](https://img.shields.io/badge/download-App-blue.svg)](https://raw.githubusercontent.com/AndroidKTX/AndroidKTX/master/app/release/app-release.apk)
[![MavenCentral](https://img.shields.io/maven-central/v/io.github.androidktx/core-ktx)](https://repo1.maven.org/maven2/io/github/androidktx)
[![JitPack](https://jitpack.io/v/AndroidKTX/AndroidKTX.svg)](https://jitpack.io/#AndroidKTX/AndroidKTX)
[![CI](https://travis-ci.com/jenly1314/AndroidKTX.svg?branch=master)](https://app.travis-ci.com/github/jenly1314/AndroidKTX)
[![CircleCI](https://circleci.com/gh/jenly1314/AndroidKTX.svg?style=svg)](https://circleci.com/gh/jenly1314/AndroidKTX)
[![API](https://img.shields.io/badge/API-21%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=21)
[![License](https://img.shields.io/badge/license-Apche%202.0-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0)
[![Blog](https://img.shields.io/badge/blog-Jenly-9933CC.svg)](https://jenly1314.github.io/)
[![QQGroup](https://img.shields.io/badge/QQGroup-20867961-blue.svg)](http://shang.qq.com/wpa/qunwpa?idkey=8fcc6a2f88552ea44b1411582c94fd124f7bb3ec227e2a400dbbfaad3dc2f5ad)

AndroidKTX 一个简化 Android 开发的 Kotlin 工具类集合，通过 Kotlin 语法特性封装一些好用的方法和功能，可以使代码更加简洁易读，从而有效的提高开发效率。

## 引入

### Gradle:

1. 在Project的 **build.gradle** 里面添加远程仓库

```gradle
allprojects {
    repositories {
        //...
        mavenCentral()
    }
}
```

2. 在Module的 **build.gradle** 里面添加引入依赖项
```gradle
// core-ktx（*必须）
implementation 'io.github.androidktx:core-ktx:1.0.0'
// activity-ktx（可选）
implementation 'io.github.androidktx:activity-ktx:1.0.0'
// fragment-ktx（可选）
implementation 'io.github.androidktx:fragment-ktx:1.0.0'

```

## 示例

#### 下面介绍一些部分常用的功能：

ActivityResultCaller 的 **ActivityResultLauncher** 使用示例（常在 **Activity** 或 **Fragment** 中使用）
```kotlin

    // 单个权限申请：只需在需要使用的地方调用：cameraPermissionLauncher.launch() 即可触发回调
    private val cameraPermissionLauncher = requestPermissionLauncher(Manifest.permission.CAMERA) { granted ->
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
    private val picturePreviewLauncher = takePicturePreviewLauncher{
        binding.iv.setImageBitmap(it)
    }
    
    // 选择文件 (类型：image/*)
    private val documentLauncher = openDocumentLauncher(arrayOf("image/*")){
        binding.iv.setImageURI(it)
    }
    
    // startActivityForResult：只需在需要使用的地方调用：startActivityLauncher.launch(intent) 即可触发回调
    private val startActivityLauncher = startActivityForResultLauncher{
        log(msg = "resultCode = ${it.resultCode}")
        if(it.isResultOk()){
            // 成功
            showToast("Result Ok")
        }else{
            showToast("Result Cancel")
        }
    }

```

然后通过申明的 **XXXLauncher** 调用函数 **launch** 即可
```kotlin
    // 如：cameraPermissionLauncher
    cameraPermissionLauncher.launch()

```
```kotlin
    //如：startActivityLauncher
    startActivityLauncher.launch(
            intentOf(
                SampleActivity::class.java,
                "key1" to 2,
                "key2" to "Start Activity For Result"
            )
        )
```

**startActivity** 使用示例（常在 **Activity** 或 **Fragment** 中使用）
```kotlin
    // 相当于 startActivity(Intent(this, MainActivity::class.java))
    startActivity<MainActivity>()
```
或
```kotlin
    // 相当于 startActivity(Intent(this, MainActivity::class.java))
    startActivity(MainActivity::class.java)
```
或（带传递参数）
```kotlin
    // 相当于 startActivity(Intent(this, MainActivity::class.java).putExtra("key1", 1).putExtra("key2", "Start Activity")
    startActivity(SampleActivity::class.java, "key1" to 1, "key2" to "Start Activity")
```

然后在跳转后的界面接收数据
```kotlin
class SampleActivity : AppCompatActivity(R.layout.activity_sample) {

    // 懒加载：相当于懒加载的方式获取 getIntent().getExtras().get("key1"); 类型为：Int（有默认值，可保证不为空，并通过默认值的类型可自动推断出变量的类型）
    private val extra1 by lazyIntentExtra("key1", 0)

    // 属性委托：相当于 getIntent().getExtras().get("key2"); 类型为：String（有默认值，可保证不为空）
    private val extra2 by intentExtra("key2", "")


}
```


Intent 的 **intentExtra** 使用示例（常在 **Activity** 中使用）
```kotlin

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

```

Fragment 的 **argument** 使用示例（常在 **Fragment** 中使用）
```kotlin


    // 属性委托：相当于 fragment.getArguments().get("arg1"); 类型为：Int?
    private val arg1: Int? by argument<Int>("arg1")

    // 属性委托：相当于 fragment.getArguments().get("arg2"); 类型为：Int?（有申明类型时，可以省略泛型）
    private val arg2: Int? by argument("arg2")

    // 属性委托：相当于 fragment.getArguments().get("arg3"); 类型为：Int?（有声明泛型时，可以省略类型）
    private val arg3 by argument<Int>("arg3")

    // 属性委托：相当于 fragment.getArguments().get("arg4"); 类型为：Int（有默认值，可保证不为空）
    private val arg4: Int by argument("arg4", 1)

    // 属性委托：相当于 fragment.getArguments().get("arg5"); 类型为：Int（有默认值，通过默认值的类型可自动推断出变量的类型）
    private val arg5 by argument("arg5", 1)

    // 属性委托：相当于 fragment.getArguments().get("arg6"); 类型为：Int（key的默认值如果忽略或为空时，则默认值为变量的名称）
    private val arg6 by argument(defaultValue = 1)

    // 属性委托：相当于懒加载的方式获取 fragment.getArguments().get("arg7"); 类型为：Int?
    private val arg7: Int? by argument()

    // 懒加载：相当于懒加载的方式获取 fragment.getArguments().get("arg8"); 类型为：Int?
    private val arg8: Int? by lazyArgument("arg8")

    // 懒加载：相当于懒加载的方式获取 fragment.getArguments().get("arg9"); 类型为：Int（有默认值，可保证不为空，并通过默认值的类型可自动推断出变量的类型）
    private val arg9 by lazyArgument("arg9", 1)


```

**argument** 主要使用场景示例
```kotlin
    class SampleFragment : Fragment() {
        
        // 属性委托的方式传递参数
        private var argInt by argument(defaultValue = 0)
        private var argString: String? by argument()
        private var argBool by argument(defaultValue = false)
        
        companion object {
    
            fun newInstance(argInt: Int, argString: String, isBool: Boolean): SampleFragment {
                return SampleFragment().apply {
                    // 示例：属性委托的方式传递参数
                    this.argInt = argInt
                    this.argString = argString
                    this.argBool = isBool
                }
            }
        }
        
        //...
        
    }
```

更多使用详情，请查看 [Demo](app) 中的源码使用示例或直接查看 [API帮助文档](https://jitpack.io/com/github/jenly1314/AndroidKTX/latest/javadoc/)

## 其他

需使用JDK8+编译，在你项目中的 **build.gradle** 的 **android{}** 中添加配置：
```
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
```

## 版本记录

#### v1.0.0：2022-06-17
*  AndroidKTX初始版本

## 赞赏
如果你喜欢AndroidKTX，或感觉AndroidKTX帮助到了你，可以点右上角“Star”支持一下，你的支持就是我的动力，谢谢 :smiley:<p>
你也可以扫描下面的二维码，请作者喝杯咖啡 :coffee:
<div>
<img src="https://jenly1314.github.io/image/pay/wxpay.png" width="280" heght="350">
<img src="https://jenly1314.github.io/image/pay/alipay.png" width="280" heght="350">
<img src="https://jenly1314.github.io/image/pay/qqpay.png" width="280" heght="350">
<img src="https://jenly1314.github.io/image/alipay_red_envelopes.jpg" width="233" heght="350">
</div>

## 关于我
Name: <a title="关于作者" href="https://about.me/jenly1314" target="_blank">Jenly</a>

Email: <a title="欢迎邮件与我交流" href="mailto:jenly1314@gmail.com" target="_blank">jenly1314#gmail.com</a> / <a title="给我发邮件" href="mailto:jenly1314@vip.qq.com" target="_blank">jenly1314#vip.qq.com</a>

CSDN: <a title="CSDN博客" href="http://blog.csdn.net/jenly121" target="_blank">jenly121</a>

CNBlogs: <a title="博客园" href="https://www.cnblogs.com/jenly" target="_blank">jenly</a>

GitHub: <a title="GitHub开源项目" href="https://github.com/jenly1314" target="_blank">jenly1314</a>

Gitee: <a title="Gitee开源项目" href="https://gitee.com/jenly1314" target="_blank">jenly1314</a>

加入QQ群: <a title="点击加入QQ群" href="http://shang.qq.com/wpa/qunwpa?idkey=8fcc6a2f88552ea44b1411582c94fd124f7bb3ec227e2a400dbbfaad3dc2f5ad" target="_blank">20867961</a>
   <div>
       <img src="https://jenly1314.github.io/image/jenly666.png">
       <img src="https://jenly1314.github.io/image/qqgourp.png">
   </div>


   
