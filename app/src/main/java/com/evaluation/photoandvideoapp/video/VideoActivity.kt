package com.evaluation.photoandvideoapp.video

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.evaluation.photoandvideoapp.R
import com.evaluation.photoandvideoapp.utils.library.AndExoPlayerView
import com.evaluation.photoandvideoapp.viewmodel.PhotoViewModel
import com.evaluation.photoandvideoapp.viewmodel.VideoViewModel

class VideoActivity : AppCompatActivity(), View.OnClickListener {
	
	lateinit var backIcon : ImageView
	lateinit var videoViewModel : VideoViewModel
	lateinit var profile : ImageView
	lateinit var like : ImageView
	lateinit var title : TextView
	lateinit var name : TextView
	lateinit var  andExoPlayerView : AndExoPlayerView
	var  id : Int = 0
	
	
	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_video)
		initView()
		callAPI()
	}
	
	
	fun initView(){
		id=intent.getIntExtra("id",0)
		backIcon = findViewById(R.id.back)
		profile = findViewById<ImageView>(R.id.profile)
		like = findViewById<ImageView>(R.id.like)
		title = findViewById<TextView>(R.id.title)
		name = findViewById<TextView>(R.id.name)
		andExoPlayerView = findViewById(R.id.andExoPlayerView)
		backIcon.setOnClickListener(this)
		videoViewModel = VideoViewModel()
	}
	
	fun callAPI(){
		videoViewModel.getVideoObject(id)
		videoViewModel.getVideoObjectLives().observe(this, Observer { data ->
			title.text = data.user.name
			name.text = data.user.name
			Glide.with(this).load(data.image).into(profile)
			
			if(data.liked!!)
				Glide.with(this).load(resources.getDrawable(R.drawable.ic_details_favorite_slect)).into(like)
			else
				Glide.with(this).load(resources.getDrawable(R.drawable.ic_details_favorite_deslect)).into(like)
			
			andExoPlayerView.setSource(data.video_files[0].link)
		})
	}
	
	override fun onClick(v : View?) {
		when (v!!.id){
			R.id.back -> { onBackPressed() }
		}
	}
	
}
