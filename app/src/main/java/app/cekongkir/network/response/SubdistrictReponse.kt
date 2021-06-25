package app.cekongkir.network.response


import com.google.gson.annotations.SerializedName

data class SubdistrictReponse(
    val rajaongkir: Rajaongkir
) {
    data class Rajaongkir(
        val query: Query,
        val results: List<Result>,
        val status: Status
    ) {
        data class Query(
            val city: String
        )

        data class Result(
            val city: String,
            @SerializedName("city_id")
            val cityId: String,
            val province: String,
            @SerializedName("province_id")
            val provinceId: String,
            @SerializedName("subdistrict_id")
            val subdistrictId: String,
            @SerializedName("subdistrict_name")
            val subdistrictName: String,
            val type: String
        )

        data class Status(
            val code: Int,
            val description: String
        )
    }
}