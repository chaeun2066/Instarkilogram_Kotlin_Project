package com.example.instarkilogram

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.instarkilogram.databinding.FragmentTwoBinding

class TwoFragment : Fragment() {
    lateinit var binding: FragmentTwoBinding
    var dataList2 = mutableListOf<PlaceVO>()
    lateinit var placeAdapter: PlaceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTwoBinding.inflate(inflater, container,false)

        dataList2.add(PlaceVO("이탈리아","베니스",R.drawable.italy,R.drawable.bheart, (Math.random()*9999).toInt().toString()))
        dataList2.add(PlaceVO("그리스","산토리니",R.drawable.santorini,R.drawable.bheart, (Math.random()*9999).toInt().toString()))
        dataList2.add(PlaceVO("한국","제주도",R.drawable.jeju,R.drawable.bheart, (Math.random()*9999).toInt().toString()))
        dataList2.add(PlaceVO("로마","콜로세움",R.drawable.colosseum,R.drawable.bheart, (Math.random()*9999).toInt().toString()))
        dataList2.add(PlaceVO("미국","뉴욕",R.drawable.newyork,R.drawable.bheart, (Math.random()*9999).toInt().toString()))
        dataList2.add(PlaceVO("이집트","피라미드",R.drawable.egypt,R.drawable.bheart, (Math.random()*9999).toInt().toString()))
        dataList2.add(PlaceVO("그리스","산토리니",R.drawable.santorini,R.drawable.bheart, (Math.random()*9999).toInt().toString()))
        dataList2.add(PlaceVO("그리스","산토리니",R.drawable.santorini,R.drawable.bheart, (Math.random()*9999).toInt().toString()))
        dataList2.add(PlaceVO("그리스","산토리니",R.drawable.santorini,R.drawable.bheart, (Math.random()*9999).toInt().toString()))
        dataList2.add(PlaceVO("그리스","산토리니",R.drawable.santorini,R.drawable.bheart, (Math.random()*9999).toInt().toString()))
        dataList2.add(PlaceVO("그리스","산토리니",R.drawable.santorini,R.drawable.bheart,(Math.random()*9999).toInt().toString()))
        dataList2.add(PlaceVO("그리스","산토리니",R.drawable.santorini,R.drawable.bheart,(Math.random()*9999).toInt().toString()))

        val gridLayoutManager = GridLayoutManager(container?.context,2)

        placeAdapter = PlaceAdapter(dataList2)

        binding.fr2Recyclerview.layoutManager = gridLayoutManager

        binding.fr2Recyclerview.setHasFixedSize(true)

        binding.fr2Recyclerview.adapter = placeAdapter

        return binding.root
    }
}