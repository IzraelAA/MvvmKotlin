package app.cekongkir.ui.city

import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import app.cekongkir.base.BaseActivity
import app.cekongkir.databinding.ActivityCityBinding
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class CityActivity : BaseActivity<ActivityCityBinding>(), KodeinAware {

    override val kodein by kodein()

    private val viewModelFactory: CityViewModelFactory by instance()

    private lateinit var cityViewModel: CityViewModel

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }


    override val bindingInflater: (LayoutInflater) -> ActivityCityBinding
        get() = ActivityCityBinding::inflate

    override fun setup() {

        setUpViewModel()
        setUpObserver()
    }

    private fun setUpObserver() {

        cityViewModel.title.observe(this, {
            supportActionBar!!.title = it
        })

    }

    private fun setUpViewModel() {
        cityViewModel = ViewModelProvider(this, viewModelFactory).get(cityViewModel::class.java)
    }
}