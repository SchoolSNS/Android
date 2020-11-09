package com.example.hischool.adapter

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.hischool.R
import com.example.hischool.bottomSheet.FeedBottomSheet
import com.example.hischool.data.SearchFeedRecyclerViewData
import com.example.hischool.data.feed.CheckLike
import com.example.hischool.data.feed.FeedRecyclerViewData
import com.example.hischool.data.login.LoginInformation
import com.example.hischool.data.login.Token
import com.example.hischool.module.FeedTime
import com.example.hischool.network.retrofit.RetrofitClient
import com.example.hischool.network.retrofit.Service
import com.example.hischool.view.activity.CommentActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class SearchFeedAdapter(private val searchFeedList: ArrayList<SearchFeedRecyclerViewData>, val context: Context?) : RecyclerView.Adapter<SearchFeedAdapter.ViewHolder>() {



    fun updateList(list : ArrayList<SearchFeedRecyclerViewData>) {
        searchFeedList.clear()
        searchFeedList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchFeedAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        return ViewHolder(view, context)
    }

    override fun getItemCount(): Int {
        return searchFeedList.size
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: SearchFeedAdapter.ViewHolder, position: Int) {
        holder.bind(searchFeedList[position])
    }


    inner class ViewHolder(itemView: View, context: Context?) : RecyclerView.ViewHolder(itemView){
        lateinit var myAPI: Service
        lateinit var retrofit: Retrofit
        val feedTime = FeedTime()

        val profile: ImageView = itemView.findViewById(R.id.search_profile_image)
        val nickName: TextView = itemView.findViewById(R.id.search_name_text)
        val time: TextView = itemView.findViewById(R.id.search_time_text)
        val title: TextView = itemView.findViewById(R.id.search_title_text)
        val question: TextView = itemView.findViewById(R.id.search_question_text)
        val liked: ImageView = itemView.findViewById(R.id.search_heart_btn)
        val countHeart: TextView = itemView.findViewById(R.id.search_count_heart_text)
        val countMessage: TextView = itemView.findViewById(R.id.search_count_message_text)
        val mContext = context
        val feedImage1: ImageView = itemView.findViewById(R.id.feed_image1)
        val feedImage2: ImageView = itemView.findViewById(R.id.feed_image2)

        @RequiresApi(Build.VERSION_CODES.N)
        fun bind(item: SearchFeedRecyclerViewData) {
            Log.d("TAG", item.is_liked.toString())
            retrofit = RetrofitClient.getInstance()

            nickName.text = item.owner.username
            time.text = feedTime.calFeedTime(item.created_at)
            title.text = item.title
            question.text = item.text
            countHeart.text = item.like_count.toString()
            Log.d("TAG", item.like_count.toString())
            countMessage.text = item.comment_count.toString()

            feedImage1.visibility = View.GONE
            feedImage2.visibility = View.GONE

            if (item.image_urls.size == 1) {
                feedImage1.visibility = View.VISIBLE
                setOneFeedImage(item)
            } else if (item.image_urls.size > 1) {
                feedImage1.visibility = View.VISIBLE
                feedImage2.visibility = View.VISIBLE
                setAllFeedImage(item)
                Log.d("TAG", "2개이상")
            }

            if (mContext != null) {
                Glide.with(mContext)
                    .load(item.owner.profile)
                    .transform(CenterCrop(), RoundedCorners(10000))
                    .into(profile)
            }


            when (item.is_liked) {
                true -> {
                    if (mContext != null) {
                        Glide.with(mContext)
                            .load(R.drawable.heart_true)
                            .into(liked)
                    }
                }

                false -> {
                    if (mContext != null) {
                        Glide.with(mContext)
                            .load(R.drawable.heart)
                            .into(liked)
                    }
                }
            }

            liked.setOnClickListener {
                when (item.is_liked) {
                    true -> {
                        cancelLike(item)
                    }

                    false -> {
                        setLike(item)
                    }
                }
            }

            itemView.setOnClickListener {
                val intent = (Intent(itemView.context, CommentActivity::class.java))
                intent.putExtra("id", item.id)
                intent.putExtra("ownerName", item.owner.username)
                intent.putExtra("time", feedTime.calFeedTime(item.created_at))
                intent.putExtra("profile", item.owner.profile)
                intent.putExtra("title", item.title)
                intent.putExtra("content", item.text)
                intent.putExtra("heartCount", item.like_count)
                intent.putExtra("commentCount", item.comment_count)
                intent.putExtra("isLike", item.is_liked)
                intent.putExtra("imageUrls", item.image_urls)
                Log.d("TAG", "image url : ${item.image_urls}")
                itemView.context.startActivity(intent)
            }
        }

        private fun setOneFeedImage(item: SearchFeedRecyclerViewData) {
            if (mContext != null) {
                Glide.with(mContext)
                    .load(item.image_urls[0])
                    .transform(CenterCrop(), RoundedCorners(25))
                    .into(feedImage1)
            }
        }

        private fun setAllFeedImage(item: SearchFeedRecyclerViewData) {
            setOneFeedImage(item)
            if (mContext != null) {
                Glide.with(mContext)
                    .load(item.image_urls[1])
                    .transform(CenterCrop(), RoundedCorners(25))
                    .into(feedImage2)
            }
        }



        private fun setLike(item: SearchFeedRecyclerViewData) {
            myAPI = retrofit.create(Service::class.java)
            myAPI.setLike(
                token = "Token ${Token.token}",
                postId = item.id
            ).enqueue(object : Callback<CheckLike> {
                override fun onResponse(call: Call<CheckLike>, response: Response<CheckLike>) {
                    if (response.code() == 200) {
                        if (mContext != null) {
                            Glide.with(mContext)
                                .load(R.drawable.heart_true)
                                .into(liked)
                        }
                        item.like_count = item.like_count + 1
                        countHeart.text = (item.like_count).toString()
                        item.is_liked = true
                    } else {
                        Toast.makeText(mContext, "좋아요 실패", Toast.LENGTH_SHORT).show()
                        Log.d("TAG", response.code().toString())
                    }
                }

                override fun onFailure(call: Call<CheckLike>, t: Throwable) {
                    Log.d("TAG", t.message.toString())
                    Toast.makeText(mContext, "좋아요 실패", Toast.LENGTH_SHORT).show()
                }
            })
        }

        private fun cancelLike(item: SearchFeedRecyclerViewData) {
            myAPI = retrofit.create(Service::class.java)
            myAPI.cancelLike(
                token = "Token ${Token.token}",
                postId = item.id
            ).enqueue(
                object : Callback<CheckLike> {
                    override fun onResponse(
                        call: Call<CheckLike>,
                        response: Response<CheckLike>
                    ) {
                        if (response.code() == 200) {
                            if (mContext != null) {
                                Glide.with(mContext)
                                    .load(R.drawable.heart)
                                    .into(liked)
                            }

                            item.is_liked = false
                            item.like_count = item.like_count - 1
                            countHeart.text = (item.like_count).toString()
                            Toast.makeText(mContext, "좋아요 취소", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(mContext, "좋아요 취소 실패", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<CheckLike>, t: Throwable) {
                        Toast.makeText(mContext, "좋아요 취소 실패", Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }
}


