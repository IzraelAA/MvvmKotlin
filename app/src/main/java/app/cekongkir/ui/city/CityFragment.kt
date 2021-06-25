package app.cekongkir.ui.city

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import app.cekongkir.R
import app.cekongkir.base.BaseFragment
import app.cekongkir.databinding.FragmentCityBinding
import app.cekongkir.network.Resource
import app.cekongkir.network.response.CityResponse
import timber.log.Timber

class CityFragment : BaseFragment<FragmentCityBinding>() {
    private val cityViewModel by lazy { ViewModelProvider(requireActivity()).get(CityViewModel::class.java) }

    private lateinit var cityAdapter: CityAdapter
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCityBinding
        get() = FragmentCityBinding::inflate

    override fun setup() {
        setUpView()
        setUpObserver()
        setupListiner()
        setupRecyclerView()
        cityViewModel.title.postValue("Choose City")
    }

    private fun setUpView() {
        binding.editSearch.doAfterTextChanged {
            cityAdapter.filter.filter(it.toString())
        }

        cityViewModel.fetchCity()
    }


    private fun setupRecyclerView() {

        cityAdapter = CityAdapter(arrayListOf(), object : CityAdapter.OnAdapterListener {
            override fun onClick(result: CityResponse.Rajaongkir.Results) {

                Timber.d("${result.city_name}")

                cityViewModel.fetchSubDistrict(result.city_id)
                findNavController().navigate(R.id.action_cityFragment_to_subdistrictFragment, bundleOf("city_id" to result.city_id, "city_name" to result.city_name))

            }

        })
        binding.listCity.adapter = cityAdapter
    }

    private fun setupListiner() {
        binding.editSearch.doAfterTextChanged {
            cityAdapter.filter
        }
    }


    private fun setUpObserver() {
        cityViewModel.cityResponse.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Loading -> {
                    binding.refreshCity.isRefreshing = true
                    Timber.d("cityResponse : isLoading")
                }
                is Resource.Success -> {
                    Timber.d("cityResponse : $it")
                    binding.refreshCity.isRefreshing = false
                    it.data?.rajaongkir?.let { it1 -> cityAdapter.setData(it1.results) }
                }
                is Resource.Error -> {
                    binding.refreshCity.isRefreshing = false
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}