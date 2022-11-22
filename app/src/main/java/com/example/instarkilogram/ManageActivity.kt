package com.example.instarkilogram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.instarkilogram.databinding.ActivityManageBinding

class ManageActivity : AppCompatActivity() {
    lateinit var binding: ActivityManageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun viewOnClick(view: View){
        when(view?.id){
            R.id.lay_addAccount -> {
                val intent = Intent(applicationContext, RegisterActivity::class.java)
                startActivity(intent)
            }
            R.id.lay_deleteAccount -> {
                val intent = Intent(applicationContext, DeleteActivity::class.java)
                startActivity(intent)
            }
            R.id.lay_editAccount -> {
                val intent = Intent(applicationContext, UpdateActivity::class.java)
                startActivity(intent)
            }
            R.id.lay_listAccount -> {
                val intent = Intent(applicationContext, ListActivity::class.java)
                startActivity(intent)
            }
//            R.id.btn_table_delete -> {
//
//            }
            R.id.btn_back -> {
                finish()
            }
        }
    }
}