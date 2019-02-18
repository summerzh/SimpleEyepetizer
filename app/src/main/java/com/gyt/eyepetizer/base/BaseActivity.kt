package com.gyt.eyepetizer.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gyt.eyepetizer.widget.MultiStateView
import com.orhanobut.logger.Logger
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

/**
 * @author gyt
 * @date on 2019/1/18 4:40 PM
 * @describer TODO
 */

abstract class BaseActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {

    private var mMultiStateView: MultiStateView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initView()
        setOnRetryClickListener()
    }

    private fun setOnRetryClickListener() {
        mMultiStateView?.getView(MultiStateView.VIEW_STATE_ERROR)?.setOnClickListener { retryRequest() }
        mMultiStateView?.getView(MultiStateView.VIEW_STATE_NO_NETWORK)?.setOnClickListener { retryRequest() }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        // Some permissions have been granted
        Logger.i("获取成功的权限：$perms")
    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        // Some permissions have been denied
        val sb = StringBuffer()
        for (str in perms) {
            sb.append(str)
            sb.append("\n")
        }
        sb.replace(sb.length - 2, sb.length, "")
        //用户点击拒绝并不在询问时候调用
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            Toast.makeText(this, "已拒绝权限" + sb + "并不再询问", Toast.LENGTH_SHORT).show()
            AppSettingsDialog.Builder(this)
                    .setRationale("此功能需要" + sb + "权限，否则无法正常使用，是否打开设置")
                    .setPositiveButton("好")
                    .setNegativeButton("不行")
                    .build()
                    .show()
        }
    }


    abstract fun getLayoutId(): Int

    abstract fun initView()

    abstract fun retryRequest()
}