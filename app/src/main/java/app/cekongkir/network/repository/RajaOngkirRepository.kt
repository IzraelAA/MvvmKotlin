package app.cekongkir.network.repository

import app.cekongkir.database.preferences.CekOngkirPreference
import app.cekongkir.network.Endpoint

class RajaOngkirRepository(
        private val apiService: Endpoint,
        private val pref: CekOngkirPreference) {

    suspend fun fetchCity() = apiService.city()
    suspend fun fetchSubdistrict(cityId: String) = apiService.subdistrict(cityId)
    fun savePreferance(type:String,id:String,name:String){
        when(type){
            "origin"->{
            }
        }
    }
}