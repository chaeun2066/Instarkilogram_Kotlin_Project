package com.example.instarkilogram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.instarkilogram.databinding.ActivityListBinding

class ListActivity : AppCompatActivity() {
    lateinit var binding: ActivityListBinding
    lateinit var listUserAdapter: ListUserAdapter
    var userList = mutableListOf<userTBL>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dbHelper = DBHelper(applicationContext, "userDB.db",null, 1)
        userList = dbHelper.selectAll()

        val gridLayoutManager = GridLayoutManager(applicationContext,2)
        listUserAdapter = ListUserAdapter(userList)

        binding.recyclerviewList.layoutManager = gridLayoutManager
        binding.recyclerviewList.setHasFixedSize(true)
        binding.recyclerviewList.adapter = listUserAdapter

        binding.btnListBack.setOnClickListener {
            finish()
        }
    }

    fun refreshRecyclerViewDrop(userTBL: userTBL, position: Int){
        val dbHelper = DBHelper(applicationContext, "userDB.db",null, 1)
        Toast.makeText(this,"${userTBL.id} 계정이 삭제되었습니다.",Toast.LENGTH_SHORT).show()
        dbHelper.delete(userTBL.id)
        userList.remove(userTBL)
        listUserAdapter.notifyItemRemoved(position)
    }
}