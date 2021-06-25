package app.cekongkir.ui.city

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.cekongkir.network.repository.RajaOngkirRepository

@Suppress("UNCHECKED_CAST")
class CityViewModelFactory(private val repository: RajaOngkirRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CityViewModel(repository) as T
    }
}