package com.evaluation.photoandvideoapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.evaluation.photoandvideoapp.fragments.FavoriteFragment
import com.evaluation.photoandvideoapp.fragments.PhotoFragment
import com.evaluation.photoandvideoapp.fragments.VideoFragment


class ViewPagerAdapter(fm : FragmentManager?) : FragmentPagerAdapter(fm!!) {
	override fun getItem(position : Int) : Fragment {
		return if(position == 0) PhotoFragment() else if(position==1) VideoFragment() else FavoriteFragment()
	}
	
	override fun getCount() : Int {
		return 3
	}
	
	override fun getPageTitle(position : Int) : CharSequence? {
		return  if (position == 0)   "Photos" else if (position == 1) "Videos" else "Favorites"
	}
}