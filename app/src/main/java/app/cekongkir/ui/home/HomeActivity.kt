package app.cekongkir.ui.home

import android.view.LayoutInflater
import app.cekongkir.base.BaseActivity
import app.cekongkir.databinding.ActivityHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    private fun setUpTabLayout() {
        val tabTitle = arrayOf("Check Payment", "Check Receipt")
        val tabAdapter = HomeTabAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = tabAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabTitle[position]
        }.attach()
    }

    override val bindingInflater: (LayoutInflater) -> ActivityHomeBinding
        get() = ActivityHomeBinding::inflate

    override fun setup() {
        setUpTabLayout()
    }
}