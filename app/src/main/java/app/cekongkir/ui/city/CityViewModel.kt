package app.cekongkir.ui.city

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cekongkir.network.Resource
import app.cekongkir.network.repository.RajaOngkirRepository
import app.cekongkir.network.response.CityResponse
import app.cekongkir.network.response.SubdistrictReponse
import kotlinx.coroutines.launch

class CityViewModel(private val repository: RajaOngkirRepository) : ViewModel() {
    val title: MutableLiveData<String> = MutableLiveData("")
    val cityResponse: MutableLiveData<Resource<CityResponse>> = MutableLiveData()
    val subdistrictReponse: MutableLiveData<Resource<SubdistrictReponse>> = MutableLiveData()

    init {
        fetchCity()
    }

    fun fetchCity() = viewModelScope.launch {
        cityResponse.value = Resource.Loading()
        try {
            val response = repository.fetchCity()

            cityResponse.value = Resource.Success(response.body()!!)
        } catch (ex: Exception) {

            cityResponse.value = Resource.Error(ex.message.toString())
        }
    }

     fun fetchSubDistrict(cityId: String)= viewModelScope.launch {
        subdistrictReponse.value = Resource.Loading()
        try {
            val response = repository.fetchSubdistrict(cityId = cityId)

            subdistrictReponse.value = Resource.Success(response.body()!!)
        } catch (ex: Exception) {

            subdistrictReponse.value = Resource.Error(ex.message.toString())
        }
    }


}