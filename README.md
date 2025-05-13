# AndroidKTX

![Logo](app/src/main/ic_launcher-playstore.png)

[![MavenCentral](https://img.shields.io/maven-central/v/io.github.androidktx/android-ktx?logo=sonatype)](https://repo1.maven.org/maven2/io/github/androidktx)
[![JitPack](https://img.shields.io/jitpack/v/github/AndroidKTX/AndroidKTX?logo=jitpack)](https://jitpack.io/#AndroidKTX/AndroidKTX)
[![CircleCI](https:///img.shields.io/circleci/build/github/jenly1314/AndroidKTX?logo=circleci)](https://app.circleci.com/pipelines/github/jenly1314/AndroidKTX)
[![Download](https://img.shields.io/badge/download-APK-brightgreen?logo=github)](https://raw.githubusercontent.com/AndroidKTX/AndroidKTX/master/app/release/app-release.apk)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen?logo=android)](https://developer.android.com/guide/topics/manifest/uses-sdk-element#ApiLevels)
[![License](https://img.shields.io/github/license/AndroidKTX/AndroidKTX?logo=open-source-initiative)](https://opensource.org/licenses/apache-2-0)


AndroidKTX 一个简化 Android 开发的 Kotlin 工具类集合，通过 Kotlin 语法特性封装一些好用的方法和功能，可以使代码更加简洁易读，从而有效的提高开发效率。

## 各个Module说明

- **android-ktx** 内部依赖：**core-ktx**、**activity-ktx**、**fragment-ktx**

- **core-ktx** 主要提供核心和公共的一些工具类集合

- **activity-ktx** 主要提供与 **Activity** 相关的一些工具类集合

- **fragment-ktx** 主要提供与 **Fragment** 相关的一些工具类集合

## 引入

### Gradle:

1. 在Project的 **build.gradle** 或 **setting.gradle** 中添加远程仓库

    ```gradle
    repositories {
        //...
        mavenCentral()
    }
    ```

2. 在Module的 **build.gradle** 中添加依赖项

    方式一：依赖完整库：依赖 **android-ktx** 将直接拥有各个子库模块所有的功能
    ```gradle
    // android-ktx
    implementation 'io.github.androidktx:android-ktx:1.1.0'
    
    ```
   
    方式二：选择性依赖库模块：作为完整库的替代方案，你也可以根据自己的需求，仅依赖你实际需要的库模块；例如：**core-ktx** 、 **activity-ktx** 、 **fragment-ktx**

    ```gradle
    // core-ktx（*必须）
    implementation 'io.github.androidktx:core-ktx:1.1.0'
    // activity-ktx（可选） 
    implementation 'io.github.androidktx:activity-ktx:1.1.0'
    // fragment-ktx（可选）
    implementation 'io.github.androidktx:fragment-ktx:1.1.0'
    
    ```
     
## 使用

### 关于 ActivityResultCaller 相关的 **XXXLauncher**

ActivityResultCaller 中主要是定义一些以 **Launcher** 结尾的扩展函数；
其主要是对原有 ActivityResultCaller 中相关的 **Activity Result API** 进行了封装优化，减少样板代码，使用方式更简洁。

| **Launcher** 结尾的扩展函数          | 函数说明   |
|:-----------------------------------|:-------------------------------------------------|
| startActivityForResultLauncher     | 启动 Activity； 用于替代：startActivityForResult  |
| startIntentSenderForResultLauncher | 启动 IntentSender； 用于替代：startIntentSenderForResult |
| requestPermissionLauncher          | 请求单个权限   |
| requestMultiplePermissionsLauncher | 请求多个权限   |
| getContentLauncher                 | 获取内容（可根据mime类型进行过滤；例如：图片） |
| getMultipleContentsLauncher        | 获取内容（可根据mime类型进行过滤；例如：图片）|
| createDocumentLauncher             | 创建文档  |
| openDocumentLauncher               | 打开单个文档（可根据mime类型进行过滤；例如：图片）|
| openDocumentTreeLauncher           | 打开文档目录  |
| openMultipleDocumentsLauncher      | 打开多个文档（可根据mime类型进行过滤；例如：图片）|
| pickContactLauncher                | 选择联系人  |
| takePictureLauncher                | 拍摄照片 |
| takePicturePreviewLauncher         | 拍照预览 |
| takeVideoLauncher                  | 拍摄视频 |

**XXXLauncher** 的使用示例（常在 **Activity** 或 **Fragment** 中使用）

```kotlin

    // 单个权限申请：只需在需要使用的地方调用：cameraPermissionLauncher.launch() 即可触发回调
    private val cameraPermissionLauncher = requestPermissionLauncher(Manifest.permission.CAMERA) { granted ->
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
        if (allGranted) {
            // 已全部授权，则进行选择文件
            documentLauncher.launch()
        }
    }
    
    // 拍照预览：只需在需要使用的地方调用：picturePreviewLauncher.launch() 即可触发回调
    private val picturePreviewLauncher = takePicturePreviewLauncher{
        binding.iv.setImageBitmap(it)
    }
    
    // 选择文件 (类型：image/*)：只需在需要使用的地方调用：documentLauncher.launch() 即可触发回调
    private val documentLauncher = openDocumentLauncher(arrayOf("image/*")){
        binding.iv.setImageURI(it)
    }
    
    // startActivityForResult：只需在需要使用的地方调用：startActivityLauncher.launch(intent) 即可触发回调
    private val startActivityLauncher = startActivityForResultLauncher{
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
>  关于 ActivityResultCaller 相关的 XXXLauncher 使用方式基本都类似，这里就不再一一列举了。

### 关于 startActivity

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

### 关于 intentExtra

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
### 关于 argument

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

### 关于 Dimension

Dimension中主要定义了一些扩展属性和扩展函数，便于在不同单位之间转换

**Dimension** 中定义的扩展属性主要是将不同单位的值转换为实际像素大小

* **px**：像素；如：10.px
* **dp**：Density-independent pixel；如：10.dp；
* **sp**：Scale-independent pixel；字体大小单位；如：10.sp；
* **pt**：磅；1磅 = 1/72英寸；如：10.pt；
* **in**：英寸；1英寸 = 25.4毫米；如：10.in；
* **mm**：毫米；如：10.mm；
* **vw**：宽度百分比；如：1.vw 表示为宽度的 1%；
* **vh**：高度百分比；如：1.vw 表示为高度的 1%；
* **vmax**：宽高中较大值的百分比；如：1.vmax 表示为宽高中较大值的 1%；
* **vmin**：宽高中较小值的百分比；如：1.vmax 表示为宽高中较小值的 1%；

> 其中的 **vw**、**vh**、**vmax**、**vmin** 这些主要是参考了**CSS**中的一些单位概念

**Dimension** 中定义的扩展函数主要是将实际像素值转换为其他单位值

* **pxToDp()** 将像素值转换为 Density-independent pixel
* **pxToSp()** 将像素值转换为 Scale-independent pixel
* **pxToPt()** 将像素值转换为磅值
* **pxToIn()** 将像素值转换为英寸值
* **pxToMm()** 将像素值转换为毫米值
* **pxToVw()** 将像素值转换为宽度百分比值
* **pxToVh()** 将像素值转换为高度百分比值

更多使用详情，请查看 [app](app) 中的源码使用示例或直接查看 [API帮助文档](https://jitpack.io/com/github/jenly1314/AndroidKTX/latest/javadoc/)

## 常见问题

* 运行时报错：**com.android.builder.merge.DuplicateRelativeFileException: 2 files found with path 'META-INF/XXX.kotlin_module'**

> 解决方法：在 **build.gradle** 中忽略 **kotlin_module** 相关文件即可，只需添加如下配置：

```gradle
    android {
        //...
        packagingOptions {
            exclude 'META-INF/*.kotlin_module'
        }
    }

```

## 其他

需使用JDK8+编译，在你项目中的 **build.gradle** 的 **android{}** 中添加配置：
```
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
```

## 相关推荐

- [KVCache](https://github.com/jenly1314/KVCache) 一个便于统一管理的键值缓存库；支持无缝切换缓存实现。

## 版本日志

#### v1.1.0：2023-03-12
* 新增一些扩展函数与属性
* 优化细节
* 更新Gradle至v7.3.3

#### v1.0.0：2022-06-17
* AndroidKTX初始版本

---

![footer](https://jenly1314.github.io/page/footer.svg)
   
