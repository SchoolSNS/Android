package com.example.hischool.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.hischool.R
import com.example.hischool.data.feed.CheckLike
import com.example.hischool.data.feed.FeedRecyclerViewData
import com.example.hischool.network.retrofit.Service
import com.example.hischool.view.activity.CommentActivity
import com.example.hischool.widget.FeedBottomSheet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class FeedAdapter(private val feedList : ArrayList<FeedRecyclerViewData>, private val context: Context) : RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.feed_item, parent, false)
        return ViewHolder(view, context)
    }

    override fun getItemCount(): Int {
        return feedList.size
    }

    override fun onBindViewHolder(holder: FeedAdapter.ViewHolder, position: Int) {
        holder.bind(feedList[position])
    }

    class ViewHolder (itemView: View, context: Context) : RecyclerView.ViewHolder(itemView){

        lateinit var myAPI: Service
        lateinit var retrofit: Retrofit

        val proFile = itemView.findViewById<ImageView>(R.id.feed_profile_image)
        val nickName = itemView.findViewById<TextView>(R.id.feed_name_text)
        val time = itemView.findViewById<TextView>(R.id.feed_time_text)
        val title = itemView.findViewById<TextView>(R.id.feed_title_text)
        val question = itemView.findViewById<TextView>(R.id.feed_question_text)
        val countHeart = itemView.findViewById<TextView>(R.id.feed_count_heart_text)
        val countMessage = itemView.findViewById<TextView>(R.id.feed_count_message_text)
        val comment1 : ConstraintLayout = itemView.findViewById(R.id.feed_comment1)
        val comment2 : ConstraintLayout = itemView.findViewById(R.id.feed_comment2)
        val comment1Image : ImageView = itemView.findViewById(R.id.feed_comment1_profile_image)
        val comment1Name : TextView = itemView.findViewById(R.id.feed_comment1_name)
        val comment1Text : TextView = itemView.findViewById(R.id.feed_comment1_message)
        val comment2Image : ImageView = itemView.findViewById(R.id.feed_comment2_profile_image)
        val comment2Name : TextView = itemView.findViewById(R.id.feed_comment2_name)
        val comment2Text : TextView = itemView.findViewById(R.id.feed_comment2_message)
        val comment1ImageList : ImageView = itemView.findViewById(R.id.feed_comment1_image_list)
        val comment1ImageList2 : ImageView = itemView.findViewById(R.id.feed_comment1_image_list2)
        val comment2ImageList : ImageView = itemView.findViewById(R.id.feed_comment2_image_list)
        val comment2ImageList2 : ImageView = itemView.findViewById(R.id.feed_comment2_image_list2)
        val mContext = context
        val heartBtn : ImageButton = itemView.findViewById(R.id.feed_heart_btn)
        val moreBtn : Button = itemView.findViewById(R.id.feed_more_btn)

        fun bind(item : FeedRecyclerViewData)
        {
            comment1.visibility = View.GONE
            comment2.visibility = View.GONE

            when
            {
                item.comment_count <= 0 -> {
                    comment1.visibility = View.GONE
                    comment2.visibility = View.GONE
                }
                item.comment_count == 1 -> {
                    if(item.comment_preview[0].image_urls.size == 1)
                    {
                        comment1ImageList.visibility = View.VISIBLE
                        setOneImageCommentOne(item)
                    }
                    else if(item.comment_preview[0].image_urls.size == 2)
                    {
                        comment1ImageList.visibility = View.VISIBLE
                        comment1ImageList2.visibility = View.VISIBLE
                        setAllImageCommentOne(item)
                    }
                    comment1.visibility = View.VISIBLE
                    comment2.visibility = View.GONE
                    setOneCommentData(item)
                }
                item.comment_count > 1 -> {
                    if(item.comment_preview[0].image_urls.size == 1)
                    {
                        comment1ImageList.visibility = View.VISIBLE
                        setOneImageCommentOne(item)
                    }
                    else if(item.comment_preview[0].image_urls.size == 2)
                    {
                        comment1ImageList.visibility = View.VISIBLE
                        comment1ImageList2.visibility = View.VISIBLE
                        setAllImageCommentOne(item)
                    }
                    if(item.comment_preview[1].image_urls.size == 1)
                    {
                        comment2ImageList.visibility = View.VISIBLE
                        setOneImageCommentTwo(item)
                    }
                    else if(item.comment_preview[1].image_urls.size == 2)
                    {
                        comment2ImageList.visibility = View.VISIBLE
                        comment2ImageList2.visibility = View.VISIBLE
                        setAllImageCommentTwo(item)
                    }

                    comment1.visibility = View.VISIBLE
                    comment2.visibility = View.VISIBLE
                    setAllCommentData(item)
                }
            }

            when(item.is_liked)
            {
                true -> {
                    Glide.with(mContext)
                        .load(R.drawable.heart_true)
                        .transform(CenterCrop(), RoundedCorners(25))
                        .into(heartBtn)
                }

                false -> {
                    Glide.with(mContext)
                        .load(R.drawable.heart)
                        .transform(CenterCrop(), RoundedCorners(25))
                        .into(heartBtn)
                }
            }

            nickName.text = item.owner.username
            time.text = item.created_at
            title.text = item.title
            question.text = item.text
            countHeart.text = item.like_count.toString()
            countMessage.text = item.comment_count.toString()

            heartBtn.setOnClickListener {
                when(item.is_liked)
                {
                    true -> {
                        cancelLike(item)
                    }

                    false -> {
                        setLike(item)
                    }
                }
            }

            moreBtn.setOnClickListener {
                val bottomSheet = FeedBottomSheet(item.id)
                bottomSheet.show((mContext as AppCompatActivity).supportFragmentManager, bottomSheet.tag)
            }
            itemView.setOnClickListener {
                val intent = (Intent(itemView.context, CommentActivity::class.java))
                intent.putExtra("id", item.id)
                itemView.context.startActivity(intent)
            }
        }

        private fun setOneCommentData(item : FeedRecyclerViewData)
        {
            comment1Name.text = item.comment_preview[0].owner.username
            comment1Text.text = item.comment_preview[0].content
        }

        private fun setAllCommentData(item : FeedRecyclerViewData)
        {
            setOneCommentData(item)
            comment2Name.text = item.comment_preview[1].owner.username
            comment2Text.text = item.comment_preview[1].content
        }

        private fun setOneImageCommentOne(item: FeedRecyclerViewData)
        {
            Glide.with(mContext)
                .load(item.comment_preview[0].image_urls[0])
                .transform(CenterCrop(), RoundedCorners(25))
                .into(comment1ImageList)
        }

        private fun setAllImageCommentOne(item: FeedRecyclerViewData)
        {
            setOneImageCommentOne(item)
            Glide.with(mContext)
                .load(item.comment_preview[0].image_urls[1])
                .transform(CenterCrop(), RoundedCorners(25))
                .into(comment1ImageList2)
        }

        private fun setOneImageCommentTwo(item: FeedRecyclerViewData)
        {
            Glide.with(mContext)
                .load(item.comment_preview[1].image_urls[0])
                .transform(CenterCrop(), RoundedCorners(25))
                .into(comment2ImageList)
        }

        private fun setAllImageCommentTwo(item: FeedRecyclerViewData)
        {
            setOneImageCommentTwo(item)
            Glide.with(mContext)
                .load(item.comment_preview[1].image_urls[1])
                .transform(CenterCrop(), RoundedCorners(25))
                .into(comment2ImageList2)
        }

        private fun cancelLike(item: FeedRecyclerViewData)
        {
            myAPI = retrofit.create(Service::class.java)
            myAPI.cancelLike(token = "Token 719e203a89eaf9bd377a5e345da7da653d15492e", postId = item.id).enqueue(
                object : Callback<CheckLike> {
                    override fun onResponse(
                        call: Call<CheckLike>,
                        response: Response<CheckLike>
                    ) {
                        if (response.code() == 200) {
                            Glide.with(mContext)
                                .load(R.drawable.heart)
                                .transform(CenterCrop(), RoundedCorners(25))
                                .into(heartBtn)

                            item.is_liked = false

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

        private fun setLike(item : FeedRecyclerViewData)
        {
            myAPI = retrofit.create(Service::class.java)
            myAPI.setLike(token = "Token 719e203a89eaf9bd377a5e345da7da653d15492e", postId = item.id).enqueue(object : Callback<CheckLike>{
                override fun onResponse(call: Call<CheckLike>, response: Response<CheckLike>) {
                    if(response.code() == 200)
                    {
                        Glide.with(mContext)
                            .load(R.drawable.heart_true)
                            .transform(CenterCrop(), RoundedCorners(25))
                            .into(heartBtn)
                        item.is_liked = true
                    }
                    else{
                        Toast.makeText(mContext, "좋아요 실패", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<CheckLike>, t: Throwable) {
                    Toast.makeText(mContext, "좋아요 실패", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}