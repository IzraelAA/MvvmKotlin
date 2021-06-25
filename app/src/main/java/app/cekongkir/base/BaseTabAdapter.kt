package app.cekongkir.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

abstract class BaseTabAdapter(fragment: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragment, lifecycle) {

    abstract val fragments: ArrayList<Fragment>

    override fun getItemCount(): Int {
        return  fragments!!.size
    }

    override fun createFragment(position: Int): Fragment {
        return  fragments!![position]
    }
}
