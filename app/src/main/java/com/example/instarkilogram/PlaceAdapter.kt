package com.example.instarkilogram

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.instarkilogram.databinding.ItemRecommendBinding

class PlaceAdapter(val dataList2: MutableList<PlaceVO>):RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>() {
    override fun getItemCount(): Int {
        return dataList2.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val binding = ItemRecommendBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val placeViewHoler = PlaceViewHolder(binding)

        placeViewHoler.itemView.setOnClickListener{
            val position: Int = placeViewHoler.bindingAdapterPosition
            val placeVO = dataList2.get(position)

            Toast.makeText(parent.context,"${placeVO.country}의 ${placeVO.place}입니다.",Toast.LENGTH_SHORT).show()
        }
        return placeViewHoler
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val binding = (holder as PlaceViewHolder).binding
        val placeVO = dataList2.get(position)

        binding.fr2IvPicture.setImageResource(placeVO.picture)
        binding.fr2TvCountry.text = placeVO.country
        binding.fr2TvPlace.text = placeVO.place
        binding.fr2IvHeart.setImageResource(placeVO.heart)
        binding.fr2TvLike.text = placeVO.like

    }

    class PlaceViewHolder(val binding:ItemRecommendBinding):RecyclerView.ViewHolder(binding.root)
}