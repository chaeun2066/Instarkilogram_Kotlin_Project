package com.example.instarkilogram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.widget.Toast
import com.example.instarkilogram.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.edtRegisterPhone.addTextChangedListener(PhoneNumberFormattingTextWatcher())

        binding.btnSignup.setOnClickListener {
            val dbHelper = DBHelper(applicationContext, "userDB.db",null, 1)
            val id = binding.edtRegisterId.text.toString()
            val name = binding.edtRegisterName.text.toString()
            val password = binding.edtRegisterPwd.text.toString()
            val password2 = binding.edtRegisterPwd2.text.toString()
            val phone = binding.edtRegisterPhone.text.toString()
            val email = binding.edtRegisterEmail.text.toString()
            var flag:Boolean = true

            if(id.length == 0 || name.length == 0 || password.length == 0 || phone.length == 0 || email.length == 0){
                Toast.makeText(this, "모든 정보를 입력해주세요.", Toast.LENGTH_SHORT).show()
                flag = false
            }

            if(!password2.equals(password)){
                Toast.makeText(this, "입력하신 패스워드와 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                binding.edtRegisterPwd2.requestFocus()
                binding.edtRegisterPwd2.setBackgroundResource(R.drawable.edit_wrong_background)
                flag = false
            }

            if(dbHelper.selectCheckId(id)){
                Toast.makeText(this, "아이디가 중복입니다.", Toast.LENGTH_SHORT).show()
                binding.edtRegisterId.requestFocus()
                binding.edtRegisterId.setBackgroundResource(R.drawable.edit_wrong_background)
                flag = false
            }

            if(!dbHelper.insert(id, name, password, phone, email)){
                Toast.makeText(this, "회원가입 오류 발생", Toast.LENGTH_SHORT).show()
                flag = false
            }

            if(flag != false) {
                finish()
            }
        }

        binding.btnReturn.setOnClickListener {
            finish()
        }
    }
}