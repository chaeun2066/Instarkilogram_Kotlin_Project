package com.example.instarkilogram

import android.app.AlertDialog
import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.instarkilogram.databinding.ItemMainBinding

class CustomAdapter(val dataList:MutableList<DataVO>):RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {
    override fun getItemCount() = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = ItemMainBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val customViewHolder = CustomViewHolder(binding)

        customViewHolder.itemView.setOnClickListener {
            val position: Int = customViewHolder.bindingAdapterPosition
            val dataVO = dataList.get(position)

            Toast.makeText(parent.context,"${dataVO.id}님의 게시글입니다.",Toast.LENGTH_SHORT).show()
        }

        binding.ivMore.setOnClickListener {
            val items = arrayOf<String>("공유하기", "삭제하기", "신고하기")

            AlertDialog.Builder(parent.context).run{
                setItems(items, object: DialogInterface.OnClickListener{
                    override fun onClick(userdialog: DialogInterface?, index: Int) {
                        if(index == 0){
                            Toast.makeText(context,"해당 게시글의 주소가 복사되었습니다.",Toast.LENGTH_SHORT).show()
                        } else if(index == 1){
                                val position: Int = customViewHolder.bindingAdapterPosition
                                Log.d("com.example.instarkilogram", "작동1")
                                val dataVO = dataList.get(position)
                                (parent.context as MainActivity2).oneFragment.refreshRecylerViewDrop(dataVO,position)
                        } else {
                            Toast.makeText(context,"해당 게시글이 신고되었습니다.",Toast.LENGTH_SHORT).show()
                        }
                    }
                })
                show()
            }.setCanceledOnTouchOutside(true)
        }
        return customViewHolder
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val binding = (holder as CustomViewHolder).binding
        val dataVO = dataList.get(position)
        var i = 0

        binding.ivProfile.setImageResource(dataVO.profile)
        binding.tvId.text = dataVO.id
        binding.ivMore.setImageResource(dataVO.more)
        binding.ivPhoto.setImageResource(dataVO.photo)
        binding.ivHeartempty.setImageResource(dataVO.heart)
        binding.tvHashtag.text = dataVO.hashtag
        binding.tvArticle.text = dataVO.article
        binding.tvDate.text = dataVO.date

        binding.ivHeartempty.setOnClickListener {
            i = 1 - i

            if(i == 0){
                binding.ivHeartempty.setImageResource(R.drawable.heart)
            }else {
                binding.ivHeartempty.setImageResource(R.drawable.heart_empty)
            }
        }
    }
    class CustomViewHolder(val binding:ItemMainBinding):RecyclerView.ViewHolder(binding.root)
}