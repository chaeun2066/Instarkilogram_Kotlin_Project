package com.example.instarkilogram

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.instarkilogram.databinding.ItemListBinding

class ListUserAdapter(val userList:MutableList<userTBL>):RecyclerView.Adapter<ListUserAdapter.ListViewHolder>() {
    override fun getItemCount() = userList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val listViewHolder = ListViewHolder(binding)

        listViewHolder.itemView.setOnClickListener{
            val position: Int = listViewHolder.bindingAdapterPosition
            val userTBL = userList.get(position)

            Toast.makeText(parent.context, "${userTBL.id}님의 계정입니다.", Toast.LENGTH_SHORT).show()
        }

        listViewHolder.itemView.setOnLongClickListener {
            val eventHandler = object : DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, answer: Int) {
                    if(answer == DialogInterface.BUTTON_POSITIVE){
                        val position: Int = listViewHolder.bindingAdapterPosition
                        val userTBL = userList.get(position)
                        (parent.context as ListActivity).refreshRecyclerViewDrop(userTBL, position)
                    }
                }
            }

            AlertDialog.Builder(parent.context).run{
                setTitle("경고")
                setIcon(R.drawable.emergency)
                setMessage("해당 계정을 삭제하시겠습니까?")
                setPositiveButton("DELETE", eventHandler)
                setNegativeButton("CANCEL",null)
                show()
            }.setCanceledOnTouchOutside(true)
            true
        }
        return listViewHolder
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val binding = (holder as ListViewHolder).binding
        val user = userList.get(position)

        binding.tvListId.text = user.id
        binding.tvListEmail.text = user.email
    }

    class ListViewHolder(val binding:ItemListBinding):RecyclerView.ViewHolder(binding.root)
}