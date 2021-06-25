package app.cekongkir.ui.cost

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import app.cekongkir.base.BaseFragment
import app.cekongkir.databinding.FragmentCostBinding
import app.cekongkir.ui.city.CityActivity

class CostFragment : BaseFragment<FragmentCostBinding>() {


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCostBinding
        get() = FragmentCostBinding::inflate

    override fun setup() {

        binding.editOrigin.setOnClickListener {

            startActivity(Intent(context, CityActivity::class.java))
        }
    }
}