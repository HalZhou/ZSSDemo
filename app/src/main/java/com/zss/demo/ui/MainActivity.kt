package com.zss.demo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.zss.common.base.BaseActivity
import com.zss.common.utils.NetworkUtils
import com.zss.demo.R

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnCheck)?.setOnClickListener {
            Toast.makeText(this,if (NetworkUtils.isAvailable()) "Available" else "Unavailable",Toast.LENGTH_SHORT).show()
        }
    }
}