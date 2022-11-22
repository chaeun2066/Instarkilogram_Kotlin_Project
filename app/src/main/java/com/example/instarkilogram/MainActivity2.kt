package com.example.instarkilogram

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.instarkilogram.databinding.ActivityMain2Binding
import com.example.instarkilogram.databinding.DialogUserBinding
import com.example.instarkilogram.databinding.UsertabButtonBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.dialog_user.view.*

class MainActivity2 : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var binding: ActivityMain2Binding
    lateinit var binding2: DialogUserBinding
    lateinit var loadingUri: Uri

    lateinit var oneFragment: OneFragment
    lateinit var twoFragment: TwoFragment
//    lateinit var threeFragment: ThreeFragment

    val requestGalleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode === android.app.Activity.RESULT_OK) {
            Glide.with(getApplicationContext())
                .load(it.data?.data)
                .apply(RequestOptions().override(250, 250))
                .centerCrop()
                .into(binding2.ivPhotoadd)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        binding2 = DialogUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        toggle = ActionBarDrawerToggle(this, binding.drawerlayout, R.string.drawer_open,R.string.drawer_close)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_24)
        toggle.syncState()

        val pagerAdapter = PagerAdapter(this)
        val title = mutableListOf<String>("Feed","Recommend")
//        val title = mutableListOf<String>("Feed","Recommend","Manage")
        oneFragment = OneFragment()
        twoFragment = TwoFragment()
//        threeFragment = ThreeFragment()

        pagerAdapter.addFragment(oneFragment, title[0])
        pagerAdapter.addFragment(twoFragment, title[1])
//        pagerAdapter.addFragment(threeFragment, title[2])

        binding.viewpager.adapter = pagerAdapter

        TabLayoutMediator(binding.tablayout, binding.viewpager){ tab, position ->
            tab.setCustomView(createTabView(title[position]))
        }.attach()

        binding.navigationview.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_like -> {
                    Toast.makeText(applicationContext,"좋아요 목록으로 이동합니다.",Toast.LENGTH_SHORT).show()
                    // Intent 기능
                }
                R.id.menu_setting -> {
                    Toast.makeText(applicationContext,"설정으로 이동합니다.",Toast.LENGTH_SHORT).show()
                    // Intent 기능
                }
                R.id.menu_account -> {
                    Toast.makeText(applicationContext,"계정 정보로 이동합니다.",Toast.LENGTH_SHORT).show()
                    // Intent 기능
                }
                R.id.menu_manage -> {
                    Toast.makeText(applicationContext,"관리자 정보로 이동합니다.",Toast.LENGTH_SHORT).show()
                    // Intent ManageActivity
                    val intent = Intent(applicationContext, ManageActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)

        val menuItem = menu?.findItem(R.id.app_bar_message)

        menuItem?.setOnMenuItemClickListener {
            Toast.makeText(applicationContext,"Direction Message로 이동합니다.",Toast.LENGTH_SHORT).show()
            true
        }
        return super.onCreateOptionsMenu(menu)
    }

    private fun createTabView(title:String): View {
        val useTabBinding = UsertabButtonBinding.inflate(layoutInflater)
        useTabBinding.tvTabName.text = title

        when(title){
            "Feed" -> useTabBinding.ivTabLogo.setImageResource(R.drawable.ic_feed_24)
            "Recommend" -> useTabBinding.ivTabLogo.setImageResource(R.drawable.ic_place_24)
//            "Manage" -> useTabBinding.ivTabLogo.setImageResource(R.drawable.ic_update_24)
        }
        return useTabBinding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}