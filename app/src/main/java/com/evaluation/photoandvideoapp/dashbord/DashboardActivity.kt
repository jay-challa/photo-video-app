package com.evaluation.photoandvideoapp.dashbord

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.evaluation.photoandvideoapp.R
import com.evaluation.photoandvideoapp.adapter.ViewPagerAdapter
import com.evaluation.photoandvideoapp.utils.Constants
import com.evaluation.photoandvideoapp.viewmodel.DashboardViewModel
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.google.android.material.tabs.TabLayout


class DashboardActivity : AppCompatActivity() {
	
	lateinit var dashboardViewModel : DashboardViewModel
	lateinit var viewPager : ViewPager
	lateinit var toolbar : Toolbar
	lateinit var tabLayout : TabLayout
	lateinit var background : ImageView
	lateinit var avatar : ImageView
	lateinit var avatar_new : ImageView
	var state : String = Constants.EXPANDED
	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_dashboard)
		initview()
		apiCall();
	}
	
	fun initview()
	{
		toolbar = findViewById(R.id.toolbar)
		avatar = findViewById(R.id.avatar)
		avatar_new = findViewById(R.id.avatar_new)
		background = findViewById(R.id.background)
		
		viewPager = findViewById(R.id.viewpager)
		viewPager.adapter = ViewPagerAdapter(supportFragmentManager!!)
		
		tabLayout = findViewById(R.id.tab_layout)
		tabLayout.setupWithViewPager(viewPager)
	}
	
	fun apiCall(){
		dashboardViewModel = DashboardViewModel()
		avatar.setImageDrawable(resources.getDrawable(R.drawable.logo))
		avatar_new.setImageDrawable(resources.getDrawable(R.drawable.logo))
		
		dashboardViewModel.getCurated()
		dashboardViewModel.getCuratedResponses().observe(this, Observer { data->
			Glide.with(this@DashboardActivity).load(data?.photos?.first()?.src?.original).into(background)
		})
	}
	
	override fun onBackPressed() {
		finishAffinity()
	}
}
