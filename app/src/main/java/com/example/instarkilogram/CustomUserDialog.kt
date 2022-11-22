package com.example.instarkilogram

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.DatePicker
import android.widget.Toast
import com.example.instarkilogram.databinding.DialogUserBinding
import com.example.instarkilogram.databinding.FragmentOneBinding
import com.example.instarkilogram.databinding.ItemMainBinding

class CustomUserDialog(val context:Context, val oneFragmentOneBinding: FragmentOneBinding){
    val dialog = Dialog(context)
    lateinit var binding: DialogUserBinding
    lateinit var binding2: ItemMainBinding

    var year : Int = 0
    var month : Int = 0
    var date : Int = 0

    fun showDialog(){
        binding = DialogUserBinding.inflate(LayoutInflater.from(context))
        lateinit var dataVO: DataVO
        dialog.setContentView(binding.root)
        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(false)
        dialog.show()

        binding.btnCancel.setOnClickListener {
            val eventHandler = object : DialogInterface.OnClickListener{
                override fun onClick(userDialog: DialogInterface?, p1: Int) {
                    if(p1 == DialogInterface.BUTTON_POSITIVE){
                        Toast.makeText(context, "작성이 취소되었습니다.", Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    }else if (p1 == DialogInterface.BUTTON_NEGATIVE){
                        Toast.makeText(context, "이어서 작성합니다.", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context,"정상적인 작성을 해주시기 바랍니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            AlertDialog.Builder(context).run{
                setTitle("경고")
                setIcon(R.drawable.emergency)
                setMessage("작성하신 내용이 삭제됩니다. 작성 취소하시겠습니까?")
                setPositiveButton("OK", eventHandler)
                setNegativeButton("CANCEL",eventHandler)
                show()
            }.setCanceledOnTouchOutside(true)
        }

        binding.tvDate.setOnClickListener {
            DatePickerDialog(context, object: DatePickerDialog.OnDateSetListener{
                override fun onDateSet(datePicker: DatePicker?, y: Int, m: Int, d: Int) {
                    year = y
                    month = m
                    date = d
                    Log.d("CompanyMeeting", "${year}.${month+1}.${date}")
                    binding.tvDate.setText("${year}.${month+1}.${date}")
                }
            }, 2022, 9,27).show()
        }

        binding.ivPhotoadd.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            (context as MainActivity2).requestGalleryLauncher.launch(intent)
            (context as MainActivity2).binding2 = binding
        }

    binding.btnUpload.setOnClickListener {
        val profile = R.drawable.profile
        val id = "chaeun2066"
        val more = R.drawable.more
        var photo = R.drawable.photo_add
        val heart = R.drawable.heart_empty
        val hashtag = binding.edtHashtag.text.toString()
        val article = binding.edtArticle.text.toString()
        val date = binding.tvDate.text.toString()

        if(article.length == 0){
            Toast.makeText(context,"게시글 내용을 작성해주세요.",Toast.LENGTH_SHORT).show()
        }else {
            dataVO = DataVO(profile, id, more, photo, heart, hashtag, article, date)
            (context as MainActivity2).oneFragment.refreshRecyclerViewAdd(dataVO)
            dialog.dismiss()
        }
      }
    }
}