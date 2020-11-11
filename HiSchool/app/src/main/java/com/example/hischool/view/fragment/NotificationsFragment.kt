package com.example.hischool.view.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hischool.R
import com.example.hischool.adapter.FeedAdapter
import com.example.hischool.adapter.NotificationAdapt
import com.example.hischool.data.NotificationRecyclerViewData
import com.example.hischool.data.feed.FeedRecyclerViewData
import com.example.hischool.data.login.Token
import com.example.hischool.network.retrofit.RetrofitClient
import com.example.hischool.network.retrofit.Service
import kotlinx.android.synthetic.main.fragment_feed.*
import kotlinx.android.synthetic.main.fragment_notifications.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class NotificationsFragment : Fragment() {

    lateinit var myAPI: Service
    lateinit var retrofit: Retrofit
    var notificationList : ArrayList<NotificationRecyclerViewData> = arrayListOf()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }

    override fun onResume() {
        retrofit = RetrofitClient.getInstance()
        getNotification()
        super.onResume()
    }

    private fun getNotification()
    {
        myAPI = retrofit.create(Service::class.java)
        Log.d("TAG", "token : ${Token.token}")
        myAPI.getNotification("Token ${Token.token}").enqueue(object :Callback<List<NotificationRecyclerViewData>>{
            override fun onResponse(
                call: Call<List<NotificationRecyclerViewData>>,
                response: Response<List<NotificationRecyclerViewData>>
            ) {
                Log.d("TAG", "responseCode : ${response.code()}")
                Log.d("TAG", "error : ${response.message()}")
                notificationList.clear()
                notificationList = response.body() as ArrayList<NotificationRecyclerViewData>
                Log.d("TAG", "data $notificationList")
                val mAdapter = NotificationAdapt(notificationList)
                notification_recyclerview.setHasFixedSize(true)
                notification_recyclerview.adapter = mAdapter
            }

            override fun onFailure(call: Call<List<NotificationRecyclerViewData>>, t: Throwable) {
                Log.d("TAG", "fail ${t.message}")
            }

        })
    }
}