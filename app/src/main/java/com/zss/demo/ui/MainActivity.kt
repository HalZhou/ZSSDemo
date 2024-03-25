package com.zss.demo.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zss.common.base.BaseActivity
import com.zss.demo.BR
import com.zss.demo.R
import com.zss.demo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding,MainViewModel>() {

    val mDatas = arrayListOf<String>()
    private val mAdapter by lazy { MyAdapter(mDatas) }
    override fun initViewObservables() {
        super.initViewObservables()
        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mAdapter
        }
        generateData()
        mAdapter.notifyDataSetChanged()
    }

    private fun generateData() {
        for (index in 0..100) {
            mDatas.add("index = $index")
        }
    }

    private fun log(message : String) {
        Log.d(TAG, "thread = ${Thread.currentThread().name} $message ")
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initViewModel(): MainViewModel = viewModels<MainViewModel>().value

    override fun initVariableId(): Int = BR.mainViewModel
    
    companion object {
        private const val TAG = "MainActivity"
    }

    class MyAdapter(val datas : ArrayList<String>) : RecyclerView.Adapter<MyViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            return MyViewHolder(LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1,parent,false))
        }

        override fun getItemCount(): Int {
            return datas.size
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val item = datas[position]
            holder.bind(item)
        }
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private val textView = itemView.findViewById<TextView>(android.R.id.text1)
        fun bind(item : String) {
            textView.setText(item)
        }
    }
}