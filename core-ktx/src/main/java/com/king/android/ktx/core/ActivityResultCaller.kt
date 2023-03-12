@file:Suppress("unused")

package com.king.android.ktx.core

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.result.*
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityOptionsCompat

/**
 * ActivityResultCaller 中主要是定义一些以 Launcher 结尾的扩展函数；
 * 主要是基于ActivityResultCaller封装大量的样板代码，使用方式更简洁。
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */

//-----------------------------------

/**
 * @param callback The callback to be called on the main thread when activity result is available.
 */
fun ActivityResultCaller.startActivityForResultLauncher(
    callback: ActivityResultCallback<ActivityResult>
) = registerForActivityResult(
    ActivityResultContracts.StartActivityForResult(),
    callback
)

/**
 * @param intent The [Intent] data value.
 * @param callback The callback to be called on the main thread when activity result is available.
 */
fun ActivityResultCaller.startActivityForResultLauncher(
    intent: Intent,
    callback: ActivityResultCallback<ActivityResult>
) = registerForActivityResult(
    ActivityResultContracts.StartActivityForResult(),
    intent,
    callback
)

/**
 * @param callback The callback to be called on the main thread when activity result is available.
 */
fun ActivityResultCaller.startIntentSenderForResultLauncher(
    callback: ActivityResultCallback<ActivityResult>
) = registerForActivityResult(
    ActivityResultContracts.StartIntentSenderForResult(),
    callback
)

/**
 * @param intentSenderRequest The [IntentSenderRequest] data value.
 * @param callback The callback to be called on the main thread when activity result is available.
 */
fun ActivityResultCaller.startIntentSenderForResultLauncher(
    intentSenderRequest: IntentSenderRequest,
    callback: ActivityResultCallback<ActivityResult>
) = registerForActivityResult(
    ActivityResultContracts.StartIntentSenderForResult(),
    intentSenderRequest,
    callback
)

/**
 * @param permission request a permission.
 * @param callback The callback to be called on the main thread when activity result is available.
 */
fun ActivityResultCaller.requestPermissionLauncher(
    permission: String,
    callback: ActivityResultCallback<Boolean>
) = registerForActivityResult(
    ActivityResultContracts.RequestPermission(),
    permission,
    callback
)

/**
 * @param permissions request permissions.
 * @param callback The callback to be called on the main thread when activity result is available.
 */
fun ActivityResultCaller.requestMultiplePermissionsLauncher(
    permissions: Array<String>,
    callback: ActivityResultCallback<Map<String, Boolean>>
) = registerForActivityResult(
    ActivityResultContracts.RequestMultiplePermissions(),
    permissions,
    callback
)

/**
 * @param permissions request permissions.
 * @param callback The callback to be called on the main thread when activity result is available.
 */
fun ActivityResultCaller.requestMultiplePermissionsLauncher(
    permissions: Array<String>,
    callback: (allGranted: Boolean, grantedPermissions: Array<String>, deniedPermissions: Array<String>) -> Unit
) = requestMultiplePermissionsLauncher(permissions) { it ->
    val grantedPermissions = it.filterValues { it }.mapNotNull { it.key }.toTypedArray()
    val deniedPermissions = it.filterValues { !it }.mapNotNull { it.key }.toTypedArray()
    callback(grantedPermissions.size == it.size, grantedPermissions, deniedPermissions)
}

/**
 * @param mimeDataType The MIME type of the data being handled by this intent.
 * @param callback The callback to be called on the main thread when activity result is available.
 */
fun ActivityResultCaller.getContentLauncher(
    mimeDataType: String,
    callback: ActivityResultCallback<Uri>
) = registerForActivityResult(
    ActivityResultContracts.GetContent(),
    mimeDataType,
    callback
)

/**
 * @param mimeDataType The MIME type of the data being handled by this intent.
 * @param callback The callback to be called on the main thread when activity result is available.
 */
fun ActivityResultCaller.getMultipleContentsLauncher(
    mimeDataType: String,
    callback: ActivityResultCallback<List<Uri>>
) = registerForActivityResult(
    ActivityResultContracts.GetMultipleContents(),
    mimeDataType,
    callback
)

/**
 * @param extraTitle Add extra title to the intent.
 * @param callback The callback to be called on the main thread when activity result is available.
 */
fun ActivityResultCaller.createDocumentLauncher(
    extraTitle: String,
    callback: ActivityResultCallback<Uri>
) = registerForActivityResult(
    ActivityResultContracts.CreateDocument(),
    extraTitle,
    callback
)

/**
 * @param mimeDataTypes Used to communicate a set of acceptable MIME types.
 * @param callback The callback to be called on the main thread when activity result is available.
 */
fun ActivityResultCaller.openDocumentLauncher(
    mimeDataTypes: Array<String>,
    callback: ActivityResultCallback<Uri>
) = registerForActivityResult(
    ActivityResultContracts.OpenDocument(),
    mimeDataTypes,
    callback
)

/**
 * @param uri Location should specify a document URI or a tree URI with document ID. If this URI identifies a non-directory, document navigator will attempt to use the parent of the document as the initial location.
 * @param callback The callback to be called on the main thread when activity result is available.
 */
fun ActivityResultCaller.openDocumentTreeLauncher(
    uri: Uri,
    callback: ActivityResultCallback<Uri>
) = registerForActivityResult(
    ActivityResultContracts.OpenDocumentTree(),
    uri,
    callback
)

/**
 * @param mimeDataTypes Used to communicate a set of acceptable MIME types.
 * @param callback The callback to be called on the main thread when activity result is available.
 */
fun ActivityResultCaller.openMultipleDocumentsLauncher(
    mimeDataTypes: Array<String>,
    callback: ActivityResultCallback<List<Uri>>
) = registerForActivityResult(
    ActivityResultContracts.OpenMultipleDocuments(),
    mimeDataTypes,
    callback
)

/**
 * @param callback The callback to be called on the main thread when activity result is available.
 */
fun ActivityResultCaller.pickContactLauncher(
    callback: ActivityResultCallback<Uri>
) = registerForActivityResult(
    ActivityResultContracts.PickContact(),
    null,
    callback
)

/**
 * @param extraOutput The [Uri] data value.
 * @param callback The callback to be called on the main thread when activity result is available.
 */
fun ActivityResultCaller.takePictureLauncher(
    extraOutput: Uri,
    callback: ActivityResultCallback<Boolean>
) = registerForActivityResult(
    ActivityResultContracts.TakePicture(),
    extraOutput,
    callback
)

/**
 * @param callback The callback to be called on the main thread when activity result is available.
 */
fun ActivityResultCaller.takePicturePreviewLauncher(
    callback: ActivityResultCallback<Bitmap>
) = registerForActivityResult(
    ActivityResultContracts.TakePicturePreview(),
    null,
    callback
)

/**
 * @param extraOutput The [Uri] data value.
 * @param callback The callback to be called on the main thread when activity result is available.
 */
fun ActivityResultCaller.takeVideoLauncher(
    extraOutput: Uri,
    callback: ActivityResultCallback<Bitmap>
) = registerForActivityResult(
    ActivityResultContracts.TakeVideo(),
    extraOutput,
    callback
)

/**
 * A version of [ActivityResultCaller.registerForActivityResult]
 * that additionally takes an input right away, producing a launcher that doesn't take any
 * additional input when called.
 *
 * @see ActivityResultCaller.registerForActivityResult
 */
fun <I, O> ActivityResultCaller.registerForActivityResult(
    contract: ActivityResultContract<I, O>,
    input: I,
    registry: ActivityResultRegistry,
    callback: ActivityResultCallback<O>
): ActivityResultLauncher<Unit> {
    val resultLauncher = registerForActivityResult(contract, registry, callback)
    return ActivityResultCallerLauncher(resultLauncher, contract, input)
}

/**
 * A version of [ActivityResultCaller.registerForActivityResult]
 * that additionally takes an input right away, producing a launcher that doesn't take any
 * additional input when called.
 *
 * @see ActivityResultCaller.registerForActivityResult
 */
fun <I, O> ActivityResultCaller.registerForActivityResult(
    contract: ActivityResultContract<I, O>,
    input: I,
    callback: ActivityResultCallback<O>
): ActivityResultLauncher<Unit> {
    val resultLauncher = registerForActivityResult(contract, callback)
    return ActivityResultCallerLauncher(resultLauncher, contract, input)
}

fun ActivityResultLauncher<Unit>.launch(options: ActivityOptionsCompat? = null) {
    launch(Unit, options)
}

internal class ActivityResultCallerLauncher<I, O>(
    private val launcher: ActivityResultLauncher<I>,
    private val callerContract: ActivityResultContract<I, O>,
    private val callerInput: I
) : ActivityResultLauncher<Unit>() {
    private val resultContract: ActivityResultContract<Unit, O> by lazy {
        object : ActivityResultContract<Unit, O>() {
            override fun createIntent(context: Context, input: Unit): Intent {
                return callerContract.createIntent(context, callerInput)
            }

            override fun parseResult(resultCode: Int, intent: Intent?): O {
                return callerContract.parseResult(resultCode, intent)
            }
        }
    }

    override fun launch(input: Unit, options: ActivityOptionsCompat?) {
        launcher.launch(callerInput, options)
    }

    override fun unregister() {
        launcher.unregister()
    }

    override fun getContract(): ActivityResultContract<Unit, O> {
        return resultContract
    }
}
