package com.evaluation.photoandvideoapp.photo

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.evaluation.photoandvideoapp.R
import com.evaluation.photoandvideoapp.utils.TouchImageView
import com.evaluation.photoandvideoapp.viewmodel.PhotoViewModel
import kotlin.math.max
import kotlin.math.min

class PhotoActivity : AppCompatActivity(), View.OnClickListener {
	
	lateinit var backIcon : ImageView
	lateinit var photoViewModel : PhotoViewModel
	lateinit var profile : ImageView
	lateinit var like : ImageView
	//lateinit var background : ImageView
	lateinit var title : TextView
	lateinit var name : TextView
	lateinit var zoom_in_rl : RelativeLayout
	lateinit var zoom_out_rl : RelativeLayout
	lateinit var progressBar : ProgressBar
	var  id : Int = 0
	lateinit var touchImageView : TouchImageView
	
	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_photo)
		initView()
		callAPI()
	}
	
	fun initView(){
		id=intent.getIntExtra("id",0)
		backIcon = findViewById(R.id.back)
		progressBar = findViewById(R.id.progressBar)
		profile = findViewById<ImageView>(R.id.profile)
		like = findViewById<ImageView>(R.id.like)
		touchImageView = findViewById(R.id.touchImageView)
		touchImageView.setMaxZoom(4f)
		//background = findViewById<ImageView>(R.id.background)
		title = findViewById<TextView>(R.id.title)
		name = findViewById<TextView>(R.id.name)
		zoom_in_rl = findViewById<RelativeLayout>(R.id.zoom_in_rl)
		zoom_out_rl = findViewById<RelativeLayout>(R.id.zoom_out_rl)
		zoom_in_rl.setOnClickListener(this)
		zoom_out_rl.setOnClickListener(this)
		backIcon.setOnClickListener(this)
		photoViewModel = PhotoViewModel()
	}
	
	fun callAPI(){
		photoViewModel.getPhotoObect(id)
		photoViewModel.getPhotoResponseLiveDatas().observe(this, Observer { data ->
			title.text = data.photographer
			name.text = data.photographer
			
			Glide.with(this)
					.asBitmap()
					.load(data.src?.original)
					.into( object : CustomTarget<Bitmap>(){
						override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
							touchImageView.setImageBitmap(resource)
							progressBar.visibility = View.GONE
						}
						override fun onLoadCleared(placeholder: Drawable?) {
							// this is called when imageView is cleared on lifecycle call or for
							// some other reason.
							// if you are referencing the bitmap somewhere else too other than this imageView
							// clear it here as you can no longer have the bitmap
						}
					})
			
			Glide.with(this)
					.asBitmap()
					.load(data.src?.original)
					.into( object : BitmapImageViewTarget(profile){
						override fun onResourceReady(resource : Bitmap, transition : Transition<in Bitmap>?) {
							super.onResourceReady(resource, transition)
							progressBar.visibility = View.GONE
						}
					})
			
			if(data.liked!!)
				Glide.with(this).load(resources.getDrawable(R.drawable.ic_details_favorite_slect)).into(like)
			else
				Glide.with(this).load(resources.getDrawable(R.drawable.ic_details_favorite_deslect)).into(like)
		})
	}
	
	private fun zoomInPhoto(){
		touchImageView.zoomIn()
	}
	
	private fun zoomOutPhoto(){
		touchImageView.zoomOut()
	}
	
	override fun onClick(v : View?) {
		when (v!!.id){
			R.id.back -> { onBackPressed() }
			R.id.zoom_in_rl -> { zoomInPhoto() }
			R.id.zoom_out_rl->{ zoomOutPhoto() }
		}
	}
}
