package com.example.hischool.view.fragment

import android.content.Intent
import android.graphics.Color
import android.media.Image
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.hischool.R
import com.example.hischool.adapter.FeedAdapter
import com.example.hischool.adapter.ImageSlider
import com.example.hischool.adapter.QuestionAdapter
import com.example.hischool.data.SliderData
import com.example.hischool.data.feed.FeedRecyclerViewData
import com.example.hischool.data.login.LoginInformation
import com.example.hischool.data.login.Token
import com.example.hischool.network.retrofit.RetrofitClient
import com.example.hischool.network.retrofit.Service
import com.example.hischool.view.activity.EditProfileActivity
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.fragment_feed.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_sign_up_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class ProfileFragment : Fragment(){

    lateinit var myAPI: Service
    lateinit var retrofit: Retrofit
    var questionList : ArrayList<FeedRecyclerViewData> = arrayListOf()
    lateinit var slideAdapter : ImageSlider
    var imageList : ArrayList<SliderData> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retrofit = RetrofitClient.getInstance()
        setData()
        setDate()
        getQuestion()
        setSlide()
        edit_profile_btn.setOnClickListener {
            val intent = Intent(activity, EditProfileActivity::class.java)
            intent.putExtra("userName", LoginInformation.loginInfoData.username)
            intent.putExtra("userSchool", LoginInformation.loginInfoData.school)
            intent.putExtra("userIntroduce", LoginInformation.loginInfoData.introduce)
            intent.putExtra("userProfile", LoginInformation.loginInfoData.profile)
            startActivity(intent)
        }

    }

    fun setData()
    {
        Glide.with(requireActivity())
            .load(LoginInformation.loginInfoData.profile)
            .transform(CenterCrop(), RoundedCorners(10000))
            .into(profile_user_profile)

        profile_user_name.text = LoginInformation.loginInfoData.username
        profile_user_school.text = LoginInformation.loginInfoData.school
        profile_user_introduce.text = LoginInformation.loginInfoData.introduce
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setDate()
    {
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR).toShort()
        val month = (cal.get(Calendar.MONTH) + 1).toShort()
        val day = cal.get(Calendar.DATE).toShort()
        profile_now_date.text = ("${year}년 ${month}월 ${day}일")
    }

    fun getQuestion()
    {
        myAPI = retrofit.create(Service::class.java)
        Log.d("TAG", LoginInformation.loginInfoData.id.toString())
        Log.d("TAG", "Token ${Token.token}")
        myAPI.getQuestionList("Token ${Token.token}", LoginInformation.loginInfoData.id.toString()).enqueue(object : Callback<List<FeedRecyclerViewData>>{
            override fun onResponse(
                call: Call<List<FeedRecyclerViewData>>,
                response: Response<List<FeedRecyclerViewData>>
            ) {
                questionList.clear()
                questionList = response.body() as ArrayList<FeedRecyclerViewData>
                Log.d("TAG", "data $questionList")

                val mAdapter = QuestionAdapter(questionList)
                profile_question_recyclerview?.setHasFixedSize(true)
                profile_question_recyclerview.adapter = mAdapter
            }

            override fun onFailure(call: Call<List<FeedRecyclerViewData>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun setSlide()
    {
        addNewItem()
        slideAdapter = ImageSlider(imageList, requireContext())
        imageSlider.setSliderAdapter(slideAdapter)
        imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM)
        imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        imageSlider.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH
        imageSlider.indicatorSelectedColor = Color.WHITE
        imageSlider.indicatorUnselectedColor = Color.GRAY
        imageSlider.scrollTimeInSec = 3
        imageSlider.isAutoCycle = true
        imageSlider.startAutoCycle()
    }

    private fun addNewItem() {
        val sliderItem1 = SliderData(R.drawable.calender)
        val sliderItem2 = SliderData(R.drawable.chat)
        val sliderItem3 = SliderData(R.drawable.camera)
        val sliderItem4 = SliderData(R.drawable.heart)

        imageList.add(sliderItem1)
        imageList.add(sliderItem2)
        imageList.add(sliderItem3)
        imageList.add(sliderItem4)
    }

}