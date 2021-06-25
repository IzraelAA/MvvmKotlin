package app.cekongkir.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import app.cekongkir.base.BaseTabAdapter
import app.cekongkir.ui.cost.CostFragment
import app.cekongkir.ui.waybill.WaybillFragment

class HomeTabAdapter(fragment: FragmentManager, lifecycle: Lifecycle) : BaseTabAdapter(fragment, lifecycle) {
    override val fragments: ArrayList<Fragment>
        get() = arrayListOf(
                CostFragment(),WaybillFragment()

        )

}