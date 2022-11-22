package com.example.instarkilogram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.instarkilogram.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun viewOnClick(view: View){
        when(view?.id){
            R.id.btn_login -> {
                val dbHelper = DBHelper(applicationContext, "userDB.db", null, 1)
                val id = binding.edtLoginId.text.toString()
                val password = binding.edtLoginPwd.text.toString()
                var flag = false

                if(id.length == 0 || password.length == 0){
                    Toast.makeText(this, "모든 정보를 입력해주세요.", Toast.LENGTH_SHORT).show()
                    flag = false
                }

                if(dbHelper.selectLogin(id, password) == true){
                    flag = true
                }

                if(flag == true){
                    val intent = Intent(applicationContext, MainActivity2::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.btn_register -> {
                val intent = Intent(applicationContext, RegisterActivity::class.java)
                startActivity(intent)
            }
        }
    }
}