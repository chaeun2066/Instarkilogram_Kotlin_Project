package com.example.instarkilogram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.instarkilogram.databinding.ActivityDeleteBinding

class DeleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDeleteDelete.setOnClickListener {
            val dbHelper = DBHelper(applicationContext, "userDB.db",null, 1)
            var flag = false
            val id = binding.edtDeleteId.text.toString()

            if(id.length == 0){
                Toast.makeText(this, "ID를 입력해주시기 바랍니다.", Toast.LENGTH_SHORT).show()
                flag = false
            }

            if(dbHelper.delete(id) == true){
                flag = true
                Toast.makeText(applicationContext, "${id} 계정을 삭제 완료하였습니다.", Toast.LENGTH_SHORT).show()
                Log.d("com.example.instarkilogram","${id} 삭제 성공")
                dbHelper.selectAll()
            }

            if(flag == true) {
                finish()
            }else{
                Log.d("com.example.instarkilogram", "${id} 삭제 실패")
            }
        }

        binding.btnDeleteBack.setOnClickListener {
            finish()
        }
    }
}