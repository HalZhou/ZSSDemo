package com.zss.common.base

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.zss.common.R
import com.zss.common.databinding.ActivityContainerBinding
import java.lang.ref.WeakReference

class ContainerActivity : BaseActivity<ActivityContainerBinding, BaseViewModel>() {

    protected var mFragment : WeakReference<Fragment>? = null

    override fun initDatas() {
        super.initDatas()
    }

    override fun initViews(savedStateHandle: Bundle?) {
        super.initViews(savedStateHandle)

        val fragment = savedStateHandle?.let { supportFragmentManager.getFragment(it, FRAGMENT_TAG) } ?: initFromIntent(intent)

        supportFragmentManager.commit(true) {
            replace(R.id.fragment_container, fragment)
        }
        mFragment = WeakReference(fragment)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mFragment?.get()?.let { supportFragmentManager.putFragment(outState, FRAGMENT_TAG, it) }
    }

    private fun initFromIntent(data: Intent?): Fragment {
        if (data == null) {
            throw RuntimeException("you must provide a page info to display")
        }
        try {
            val fragmentName = data.getStringExtra(EXTAR_FRAGMENT_NAME)
            if (fragmentName.isNullOrEmpty()) {
                throw IllegalArgumentException("can not find page fragmentName")
            }
            val fragment = Class.forName(fragmentName).newInstance() as Fragment
            data.getBundleExtra(EXTAR_FRAGMENT_BUNDLE)?.let { fragment.arguments = it }
            return fragment
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e : InstantiationException) {
            e.printStackTrace()
        } catch (e : IllegalAccessException) {
            e.printStackTrace()
        }
        throw RuntimeException("fragment initialization failed!")
    }


    override fun getLayoutId(): Int = R.layout.activity_container

    override fun initVariableId(): Int = -1
    override fun initViewModel(): BaseViewModel? = null

    companion object {
        const val FRAGMENT_TAG = "content_fragment_tag"
        const val EXTAR_FRAGMENT_NAME = "extra_fragment_name"
        const val EXTAR_FRAGMENT_BUNDLE = "extra_fragment_bundle"
    }
}