package com.example.instarkilogram

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.instarkilogram.databinding.FragmentOneBinding

class OneFragment : Fragment() {
    lateinit var binding: FragmentOneBinding
    var dataList = mutableListOf<DataVO>()
    lateinit var customAdapter: CustomAdapter
    var mainContainer: ViewGroup? = null
    lateinit var dialog: CustomUserDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOneBinding.inflate(inflater, container, false)
        mainContainer = container

        dataList.add(DataVO(R.drawable.profile,"chaeun2066",R.drawable.more,R.drawable.night,R.drawable.heart_empty,
            "#우리동네 #저녁하늘 #색감"," 오늘 회사에서 퇴근하는 길에 걸어오면서 내가 다니던 고등학교 근처에서 하늘을 바라보니 무척이나 아름다운 노을이 지고 " +
                    "있었다. 분홍색, 보라색, 주황색의 조화로운 노을 배경이 나의 사진 작품이 되었다. 나는 가던 발걸을을 멈추어 그자리에서 계속 쳐다보기만 했던 것 같다. " +
                    "이러한 풍경을 언제 다시 한번 또 볼 수 있을까.","2022.10.5"))
        dataList.add(DataVO(R.drawable.profile,"chaeun2066",R.drawable.more,R.drawable.ocean,R.drawable.heart_empty,
            "#강릉 #오션뷰 #안목해변","처음으로 가보았던 강릉 안목해변 바다. 가슴까지 확 트이게 해주는 바람과 파도소리 갈매기소리 그야말로 완벽했다." +
                    "가만히 눈을 감고 파도소리를 들어보니 심신이 편안해짐과 동시에 근심 또한 잠시 내려놓는 순간이 될 수 있었다. 감상했던 것도 잠시 직접 파도에" +
                    "뛰어 들어가보며 발목까지 적셔 직접 바다를 맞이하는 시간도 가졌었다. 다음에도 찾아와 파도가 반겨주는 날을 기다리겠다.","2022.10.13"))
        dataList.add(DataVO(R.drawable.profile,"chaeun2066",R.drawable.more,R.drawable.sunset,R.drawable.heart_empty,
            "#화전 #봉사활동 #유기동물","반려동물을 키우는 보호자로서 유기동물의 아픔을 알고있는 상황에서, 한 번쯤은 유기동물에게도 도움이 되어주고" +
                    "싶다는 생각에 지인과 함께 첫 유기견보호소에 다녀와봤지만 내가 예상했던 곳보다 보호소의 환경은 쾌적하여, 이 곳보다 더 도움을 필요로 하는 보호소가" +
                    "분명 있을 것이라는 생각에 화전에 있는 유기견 보호소에 다녀왔으며 그 곳에서 소중한 인연도 만나게 되는 순간이었다.","2022.10.24"))
        dataList.add(DataVO(R.drawable.profile,"chaeun2066",R.drawable.more,R.drawable.school,R.drawable.heart_empty,
            "#모교 #집앞 #저녁하늘","오늘도 집으로 돌아가는 길에 문득 고개를 들어 하늘을 바라보았더니 저녁 하늘이 아름답게 주황색으로 물든 장면을 볼 수 있었다." +
                    "주변 나무들이 학교의 모습을 살짝 가리는 가운데 오히려 이를 액자 틀로 생각하여 사진에 담아보니 꽤나 만족스러운 작품이 나온 것 같다. 저녁 공기와 함께 바라보던" +
                    "이 하늘은 지난 번과 같이 다시 보고 싶었지만, 겨울이 된 이후로는 해가 무척이나 짧아져 이 하늘을 만났던 시간에는 더 이상 볼 수 없게 되었다.","2022.11.3"))
        dataList.add(DataVO(R.drawable.profile,"chaeun2066",R.drawable.more,R.drawable.wakling,R.drawable.heart_empty,
            "#산책길 #운동 #10000보","지하철과 버스로 집으로 편히 돌아갈 수 있었지만 최근 운동과 활동력이 너무 부족한 것 같아, 집으로 돌아가는 길은 앞으로 걸어다녀보기로 " +
                    "했다. 걷기에 그리 멀지도 않은 거리기에 운동 겸 하루 10000보 채우기가 될 것만 같았다. 최근에 구매한 갤럭시 핏2를 착용하면 현재 내가 걷고 있는 걸음 수를 정확하게 측정 " +
                    "할 수 있기 때문에 가벼운 워치를 통해서 걸음 수 확인 및 유지를 잘 할 수 있을 것 같다. 하지만 컨디션을 잘 체크를 하면서 도전해보려고 한다.","2022.11.20"))
        dataList.add(DataVO(R.drawable.profile,"chaeun2066",R.drawable.more,R.drawable.rose,R.drawable.heart_empty,
            "#장미 #집앞화단 #화려한","장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미" +
                    "장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장" +
                    "장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미장미","2022.11.22"))

        val LinearLayoutManager = LinearLayoutManager(container?.context)

        customAdapter = CustomAdapter(dataList)

        binding.fr1Recylerview.layoutManager = LinearLayoutManager

        binding.fr1Recylerview.setHasFixedSize(true)

        val dividerItemDecoration = DividerItemDecoration(binding.fr1Recylerview.context, LinearLayoutManager(context).orientation)
        binding.fr1Recylerview.addItemDecoration(dividerItemDecoration)

        binding.fr1Recylerview.adapter = customAdapter

        binding.ivScrollup2.setOnClickListener {
            binding.fr1Recylerview?.smoothScrollToPosition(0)
        }

        binding.ivScrolldown2.setOnClickListener {
            binding.fr1Recylerview?.smoothScrollToPosition(dataList.size)
        }

        binding.fr1Fab.setOnClickListener{
            dialog = CustomUserDialog(binding.root.context, binding)
            dialog.showDialog()
        }

        return binding.root
    }

    fun refreshRecyclerViewAdd(dataVO: DataVO){
        dataList.add(dataVO)
        customAdapter.notifyDataSetChanged()
        Toast.makeText(binding.root.context,"게시글 작성이 완료되었습니다.", Toast.LENGTH_SHORT).show()
    }

    fun refreshRecylerViewDrop(dataVO: DataVO, position: Int) {
        val id = dataVO.id
        dataList.remove(dataVO)
        customAdapter.notifyItemRemoved(position)
        Toast.makeText(binding.root.context, "${id}님의 게시글이 삭제되었습니다.", Toast.LENGTH_SHORT).show()
    }
}