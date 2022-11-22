package com.example.instarkilogram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.instarkilogram.databinding.ActivityUpdateBinding
import kotlinx.android.synthetic.main.fragment_two.*

class UpdateActivity : AppCompatActivity() {
    var userTBL: userTBL? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnUpdateSearch.setOnClickListener {
            val dbHelper = DBHelper(applicationContext, "userDB.db",null, 1)
            val id = binding.edtUpdateId.text.toString()
            var flag = true

            if(id.length == 0){
                flag = false
                Toast.makeText(this, "수정할 ID를 입력해주세요.",Toast.LENGTH_SHORT).show()
            }

            if(flag == true){
                userTBL = dbHelper.selectId(id)
                if(userTBL != null){
                    binding.edtUpdateName.setText(userTBL?.name)
                    binding.edtUpdatePassword.setText(userTBL?.password)
                    binding.edtUpdatePhone.setText(userTBL?.phone)
                    binding.edtUpdateEmail.setText(userTBL?.email)
                }else {
                    Toast.makeText(this, "수정할 데이터 정보를 입력해주세요.",Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnUpdate.setOnClickListener {
            val dbHelper = DBHelper(applicationContext, "userDB.db",null, 1)
            val name = binding.edtUpdateName.text.toString()
            val password = binding.edtUpdatePassword.text.toString()
            val phone = binding.edtUpdatePhone.text.toString()
            val email = binding.edtUpdateEmail.text.toString()
            var flag = false

            if(userTBL != null && name.isNotBlank() && password.isNotBlank() && phone.isNotBlank() && email.isNotBlank()) {
                userTBL!!.name = name
                userTBL!!.password = password
                userTBL!!.phone = phone
                userTBL!!.email = email

                flag = dbHelper.update(userTBL)
                if (flag == true) {
                    Toast.makeText(this, "데이터 수정 성공", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "데이터 수정 실패", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "수정할 데이터를 입력해주세요.",Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnUpdateBack.setOnClickListener {
            finish()
        }
    }
}