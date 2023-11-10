package com.zss.common.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zss.common.constants.Constants
import com.zss.common.utils.ToastUtils
import com.zss.common.widget.LoadingView
import dagger.hilt.android.AndroidEntryPoint

abstract class BaseActivity<VB : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity(), IView {

    private var _binding: VB? = null
    val binding: VB get() = _binding!!

    protected lateinit var viewModel: VM

    private val loadingView by lazy {
        LoadingView.Builder(this)
            .build()
    }

    private val cm by lazy { getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = DataBindingUtil.setContentView(this, getLayoutId())
        initViewDataBinding(savedInstanceState)

        //初始化数据
        initDatas()
        //初始化页面
        initViews(savedInstanceState)
        //初始化页面事件监控
        initViewObservables()

        //注册网络变化通知
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()
        cm.registerNetworkCallback(networkRequest, networkCallback)
    }

    private fun initViewDataBinding(savedInstanceState: Bundle?) {
        viewModel = initViewModel() ?: createBaseViewModel() as VM
        //关联ViewModel
        binding.setVariable(initVariableId(), viewModel)
        //支持LiveData绑定Xml，数据改变，UI自动更新
        binding.lifecycleOwner = this
        //让ViewModel拥有View的生命周期感应
        lifecycle.addObserver(viewModel)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        cm.unregisterNetworkCallback(networkCallback)
    }

    override fun initDatas() {
    }

    override fun initViews(savedStateHandle: Bundle?) {
    }

    override fun initViewObservables() {
        viewModel.uiState.toastText.observe(this) {
            showShort(it)
        }
        viewModel.uiState.showLoading.observe(this) {
            showLoading(it)
        }
        viewModel.uiState.hideLoading.observe(this) {
            hideLoading()
        }
        viewModel.uiState.finishPage.observe(this) { bundle ->
            bundle?.let { setResult(Activity.RESULT_OK, Intent().apply { this.putExtras(it) }) }
            finish()
        }
        viewModel.uiState.startPage.observe(this) {map->
            val clazz = map[Constants.CLASS_NAME] as Class<*>?
            val bundle = map[Constants.CLASS_BUNDLE] as Bundle?
            startActivity(clazz,bundle)
        }
    }

    fun startActivity(
        clazz: Class<*>?,
        bundle: Bundle? = null,
        launcher: ActivityResultLauncher<Intent>? = null
    ) {
        if (clazz == null) return
        val mIntent = Intent(this, clazz)
        bundle?.let { mIntent.putExtras(it) }
        if (launcher == null) {
            startActivity(mIntent)
        } else {
            launcher.launch(mIntent)
        }
    }

    fun showLoading(text: String?) {
        loadingView.show()
    }

    fun hideLoading() {
        loadingView.dismiss()
    }

    override fun showShort(toast: String?) {
        ToastUtils.showShort(toast)
    }

    fun showShort(@StringRes resId: Int) {
        showShort(getString(resId))
    }

    abstract fun getLayoutId(): Int

    //有可能部分页面 不需要ViewModel
    abstract fun initViewModel(): VM?
    abstract fun initVariableId(): Int

    private fun createBaseViewModel(): BaseViewModel {
        return ViewModelProvider(this)[BaseViewModel::class.java]
    }

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            val caps = cm.getNetworkCapabilities(network)
            Toast.makeText(this@BaseActivity, "Network available", Toast.LENGTH_SHORT).show()
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            Toast.makeText(this@BaseActivity, "Network Lost", Toast.LENGTH_SHORT).show()
        }

        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            super.onCapabilitiesChanged(network, networkCapabilities)
        }
    }
}