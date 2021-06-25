package app.cekongkir.ui.subdistrict

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import app.cekongkir.base.BaseFragment
import app.cekongkir.databinding.FragmentSubdistrictBinding
import app.cekongkir.network.Resource
import app.cekongkir.network.response.SubdistrictReponse
import app.cekongkir.ui.city.CityViewModel
import timber.log.Timber

class SubdistrictFragment : BaseFragment<FragmentSubdistrictBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSubdistrictBinding
        get() = FragmentSubdistrictBinding::inflate
    private val cityViewModel by lazy { ViewModelProvider(requireActivity()).get(CityViewModel::class.java) }

    private lateinit var subdistrictAdapter: SubdistrictAdapter
    private val cityId by lazy { requireArguments().getString("city_id") }
    private val cityName by lazy { requireArguments().getString("city_name") }
    override fun setup() {

        cityViewModel.title.postValue("Choose Districts")
        setUpListiner()
        setRecyclerView()
        setUpObserver()

    }

    private fun setRecyclerView() {
        subdistrictAdapter = SubdistrictAdapter(arrayListOf(), object : SubdistrictAdapter.OnAdapterListener {
            override fun onClick(result: SubdistrictReponse.Rajaongkir.Result) {
            }

        })
        binding.listSubdistrict.adapter = subdistrictAdapter
    }

    private fun setUpListiner() {
        binding.refreshSubdistrict.setOnRefreshListener {
            cityViewModel.fetchSubDistrict(cityId = cityId!!)
        }
    }

    private fun setUpObserver() {
        cityViewModel.subdistrictReponse.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Loading -> {
                    Timber.e("cityResponse : isLoading")
                    binding.refreshSubdistrict.isRefreshing = true
                }
                is Resource.Success -> {
                    binding.refreshSubdistrict.isRefreshing = false
                    it.data?.rajaongkir?.let { it1 -> subdistrictAdapter.setData(it1.results) }
                    Timber.e("subDistrict : $it")
                }
                is Resource.Error -> {

                    Timber.e("gagal : $it")
                    binding.refreshSubdistrict.isRefreshing = true
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

}