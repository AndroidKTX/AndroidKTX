package com.king.android.ktx.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.king.android.ktx.app.databinding.FragmentSampleBinding
import com.king.android.ktx.fragment.argument
import com.king.android.ktx.fragment.finish
import com.king.android.ktx.fragment.finishWithResult
import com.king.android.ktx.fragment.lazyArgument

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
class SampleFragment : Fragment() {

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


    // 属性委托的方式传递参数
    private var argInt by argument(defaultValue = 0)
    private var argString: String? by argument()
    private var argBool by argument(defaultValue = false)

    private lateinit var rootView: View
    private val binding by lazy { FragmentSampleBinding.bind(rootView) }

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


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_sample, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvArguments.text = "arguments: {\n\targInt = $argInt, \n\targString = $argString, \n\targBool = $argBool\n}"

        binding.btnResultOk.setOnClickListener {
            finishWithResult()
        }

        binding.btnResultCancel.setOnClickListener {
            finish()
        }
    }

}