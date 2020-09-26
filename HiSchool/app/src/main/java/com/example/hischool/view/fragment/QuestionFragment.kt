package com.example.hischool.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hischool.R
import com.example.hischool.view.activity.MainActivity
import com.example.hischool.view.activity.QuestionActivity
import com.example.hischool.widget.noFinishStartActivity
import com.example.hischool.widget.startActivity

class QuestionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startActivity(QuestionActivity::class.java)
    }
}