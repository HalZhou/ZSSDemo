package com.zss.demo.ui

import android.Manifest
import androidx.activity.viewModels
import com.permissionx.guolindev.PermissionX
import com.permissionx.guolindev.callback.ExplainReasonCallback
import com.permissionx.guolindev.callback.RequestCallback
import com.permissionx.guolindev.request.ExplainScope
import com.zss.common.base.BaseActivity
import com.zss.demo.R
import com.zss.demo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import com.zss.demo.BR

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding,MainViewModel>() {

    override fun initViewObservables() {
        super.initViewObservables()
        binding.btnCheck.setOnClickListener {
            /*PermissionX.init(this)
                .permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.BLUETOOTH)
                .explainReasonBeforeRequest()
                .onExplainRequestReason { scope, deniedList ->
                    scope.showRequestReasonDialog(deniedList,"请允许这些权限","确定","取消")
                }
                .onForwardToSettings{scope,deniedList->
                    scope.showForwardToSettingsDialog(deniedList,"you need to allow necessary permission in settings manually","OK","CANCEL")
                }
                .request { allGranted, grantedList, deniedList ->
                    if (allGranted) {
                        showShort("All permission are granted")
                    } else {
                        showShort("These permissions are denied")
                    }
                }*/
            viewModel.getUser()
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initViewModel(): MainViewModel = viewModels<MainViewModel>().value

    override fun initVariableId(): Int = BR.mainViewModel
}