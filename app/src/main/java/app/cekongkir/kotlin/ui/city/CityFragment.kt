package app.cekongkir.kotlin.ui.city

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import app.cekongkir.R
import app.cekongkir.kotlin.remote.Resource
import app.cekongkir.kotlin.remote.responses.CityResponse
import kotlinx.android.synthetic.main.fragment_city.view.*
import timber.log.Timber

class CityFragment : Fragment() {

    private lateinit var fragmentView: View
    private lateinit var cityAdapter: CityAdapter
    private val viewModel by lazy {
        ViewModelProvider(requireActivity()).get(CityViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        fragmentView = inflater.inflate(R.layout.fragment_city, container, false)
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupRecyclerView()
        setupObserver()
    }

    override fun onStart() {
        super.onStart()
        viewModel.fetchCity()
    }

    private fun setupView(){
        (requireActivity() as CityActivity)
                .supportActionBar!!.title = "Pilih Kota"
    }

    private fun setupRecyclerView(){
        cityAdapter = CityAdapter(arrayListOf(), object : CityAdapter.OnAdapterListener {
            override fun onClick(result: CityResponse.Result) {
                val bundle = bundleOf("city" to result.city_id)
                findNavController().navigate(
                        R.id.action_cityFragment_to_subdistrictFragment,
                        bundle
                )
            }
        })
        fragmentView.list_city.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cityAdapter
        }
    }

    private fun setupObserver(){
        viewModel.cityResponse.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    cityAdapter.setData( it.data!!.rajaongkir.results )
                }
                is Resource.Error -> {

                }
            }
        })
    }
}