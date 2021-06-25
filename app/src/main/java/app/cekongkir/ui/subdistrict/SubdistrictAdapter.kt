package app.cekongkir.ui.subdistrict

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import app.cekongkir.databinding.AdapterCityBinding
import app.cekongkir.network.response.SubdistrictReponse
import timber.log.Timber


class SubdistrictAdapter(val subdisitrct: ArrayList<SubdistrictReponse.Rajaongkir.Result>, val listener: OnAdapterListener
): RecyclerView.Adapter<SubdistrictAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        AdapterCityBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: SubdistrictAdapter.ViewHolder, position: Int) {
         val cit= subdisitrct[position]
        holder.binding.textName.text = "${cit.subdistrictName}"
        holder.binding.container.setOnClickListener {
            listener.onClick(cit)
        }
    }

    class ViewHolder (val binding: AdapterCityBinding):RecyclerView.ViewHolder(binding.root)

    override fun getItemCount() = subdisitrct.size

    interface  OnAdapterListener{
        fun onClick(result: SubdistrictReponse.Rajaongkir.Result)
    }
    fun setData(data:List<SubdistrictReponse.Rajaongkir.Result>){
        subdisitrct.clear()
        subdisitrct.addAll(data)
        notifyDataSetChanged()
    }

}