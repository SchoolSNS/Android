package com.example.hischool.view.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.hischool.R
import com.example.hischool.adapter.ImageSlider
import com.example.hischool.adapter.QuestionAdapter
import com.example.hischool.data.SliderData
import com.example.hischool.data.UserListData
import com.example.hischool.data.feed.FeedRecyclerViewData
import com.example.hischool.data.login.LoginInformation
import com.example.hischool.data.login.Token
import com.example.hischool.network.retrofit.RetrofitClient
import com.example.hischool.network.retrofit.Service
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_other_people_profile.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.imageSlider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class OtherPeopleProfile : AppCompatActivity() {

    lateinit var myAPI: Service
    lateinit var retrofit: Retrofit

    var questionList : ArrayList<FeedRecyclerViewData> = arrayListOf()

    lateinit var slideAdapter : ImageSlider
    var imageList : ArrayList<SliderData> = arrayListOf()
    var identity : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_people_profile)
        retrofit = RetrofitClient.getInstance()
        setData()
        setSlide()
    }

    fun setData()
    {
        myAPI = retrofit.create(Service::class.java)
        myAPI.getOtherUserInfo("Token ${Token.token}", intent.getIntExtra("id", 1).toString()).enqueue(object : Callback<UserListData>{
            override fun onResponse(call: Call<UserListData>, response: Response<UserListData>) {
                other_profile_user_name.text = response.body()?.username
                other_profile_user_school.text = response.body()?.school
                other_profile_user_introduce.text = response.body()?.introduce
                Glide.with(applicationContext)
                    .load(response.body()?.profile)
                    .transform(CenterCrop(), RoundedCorners(10000))
                    .into(other_profile_user_profile)
                identity = response.body()?.identity.toString()
                setQuestionOrAnswer()
            }

            override fun onFailure(call: Call<UserListData>, t: Throwable) {

            }

        })
    }

    fun setQuestionOrAnswer()
    {
        if(identity == "senior")
        {
            question_or_answer.text = "답변"
            myAPI = retrofit.create(Service::class.java)
            Log.d("TAG", LoginInformation.loginInfoData.id.toString())
            Log.d("TAG", "Token ${Token.token}")
            myAPI.getAnswerList("Token ${Token.token}", intent.getIntExtra("id", 1).toString()).enqueue(object : Callback<List<FeedRecyclerViewData>>{
                override fun onResponse(
                    call: Call<List<FeedRecyclerViewData>>,
                    response: Response<List<FeedRecyclerViewData>>
                ) {
                    questionList.clear()
                    questionList = response.body() as ArrayList<FeedRecyclerViewData>
                    Log.d("TAG", "data $questionList")

                    val mAdapter = QuestionAdapter(questionList)
                    other_profile_question_recyclerview.setHasFixedSize(true)
                    other_profile_question_recyclerview.adapter = mAdapter
                }

                override fun onFailure(call: Call<List<FeedRecyclerViewData>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }

        if(identity == "junior")
        {
            question_or_answer.text = "질문"
            myAPI = retrofit.create(Service::class.java)
            Log.d("TAG", LoginInformation.loginInfoData.id.toString())
            Log.d("TAG", "Token ${Token.token}")
            myAPI.getQuestionList("Token ${Token.token}", intent.getIntExtra("id", 1).toString()).enqueue(object : Callback<List<FeedRecyclerViewData>>{
                override fun onResponse(
                    call: Call<List<FeedRecyclerViewData>>,
                    response: Response<List<FeedRecyclerViewData>>
                ) {
                    questionList.clear()
                    questionList = response.body() as ArrayList<FeedRecyclerViewData>
                    Log.d("TAG", "data $questionList")

                    val mAdapter = QuestionAdapter(questionList)
                    other_profile_question_recyclerview.setHasFixedSize(true)
                    other_profile_question_recyclerview.adapter = mAdapter
                }

                override fun onFailure(call: Call<List<FeedRecyclerViewData>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }
    }

    fun setSlide()
    {
        addNewItem()
        slideAdapter = ImageSlider(imageList, applicationContext)
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