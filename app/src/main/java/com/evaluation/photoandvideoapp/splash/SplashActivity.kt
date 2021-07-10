package com.evaluation.photoandvideoapp.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.evaluation.photoandvideoapp.R
import com.evaluation.photoandvideoapp.dashbord.DashboardActivity
import java.util.*

class SplashActivity : AppCompatActivity() {
	
	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_splash)
		
		Timer().schedule(object : TimerTask() {
			override fun run() {
				startActivity(Intent(applicationContext, DashboardActivity::class.java))
			}
		}, 2000)
	}
}
