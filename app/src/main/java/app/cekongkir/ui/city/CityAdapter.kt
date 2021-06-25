package app.cekongkir.ui.city

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import app.cekongkir.databinding.AdapterCityBinding
import app.cekongkir.network.response.CityResponse
import timber.log.Timber


class CityAdapter(var cities: ArrayList<CityResponse.Rajaongkir.Results>, val listener: OnAdapterListener
): RecyclerView.Adapter<CityAdapter.ViewHolder>(),Filterable {

    private  var citiesFilter = ArrayList<CityResponse.Rajaongkir.Results>()
   init {
       citiesFilter = cities
   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        AdapterCityBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CityAdapter.ViewHolder, position: Int) {
         val cit= citiesFilter[position]
        holder.binding.textName.text = "${cit.city_name} - ${cit.postal_code}"
        holder.binding.container.setOnClickListener {
            listener.onClick(cit)
        }
    }

    class ViewHolder (val binding: AdapterCityBinding):RecyclerView.ViewHolder(binding.root)

    override fun getItemCount() = citiesFilter.size

    interface  OnAdapterListener{
        fun onClick(result: CityResponse.Rajaongkir.Results)
    }
    fun setData(data:List<CityResponse.Rajaongkir.Results>){
        cities.clear()
        cities.addAll(data)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return  object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                Timber.d("Char Search : $charSearch")
                if(charSearch.isEmpty()){
                    citiesFilter = cities
                }else{
                    val  citiesFiltered = ArrayList<CityResponse.Rajaongkir.Results>()
                    for(city in cities){
                        if(city.city_name.toLowerCase().contains(charSearch.toLowerCase())){
                            citiesFiltered.add(city)
                        }
                    }
                    citiesFilter = citiesFiltered
                }
                val  citiesFiltered = FilterResults()
                citiesFiltered.values = citiesFilter
                return  citiesFiltered
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

                citiesFilter = results?.values as ArrayList<CityResponse.Rajaongkir.Results>
                notifyDataSetChanged()
                }

        }
    }
}