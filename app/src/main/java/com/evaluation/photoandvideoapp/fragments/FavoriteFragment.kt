package com.evaluation.photoandvideoapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.evaluation.photoandvideoapp.R
import com.evaluation.photoandvideoapp.adapter.FavoriteAdapter
import com.evaluation.photoandvideoapp.interfaces.ItemClick
import com.evaluation.photoandvideoapp.photo.PhotoActivity
import com.evaluation.photoandvideoapp.response.Photos
import com.evaluation.photoandvideoapp.response.Video
import com.evaluation.photoandvideoapp.viewmodel.DashboardViewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FavoriteFragment : Fragment(), ItemClick {
	
	private var param1 : String? = null
	private var param2 : String? = null
	
	
	var favoriteAdapter = FavoriteAdapter(this)
	var dashboardViewModel : DashboardViewModel = DashboardViewModel()
	
	lateinit var recyclerView : RecyclerView
	
	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		arguments?.let {
			param1 = it.getString(ARG_PARAM1)
			param2 = it.getString(ARG_PARAM2)
		}
	}
	
	override fun onCreateView(
			inflater : LayoutInflater, container : ViewGroup?,
			savedInstanceState : Bundle?
	) : View? {
		var view = inflater.inflate(R.layout.fragment_favorite, container, false)
		recyclerView = view.findViewById(R.id.recyclerview)
		
		favoriteAdapter.setItems(dashboardViewModel.getPhotoResponse(context!!)!!.photos!!)
		recyclerView.adapter = favoriteAdapter
		favoriteAdapter.notifyDataSetChanged()
		return view
	
	}
	
	companion object {
		@JvmStatic
		fun newInstance(param1 : String, param2 : String) =
				FavoriteFragment().apply {
					arguments = Bundle().apply {
						putString(ARG_PARAM1, param1)
						putString(ARG_PARAM2, param2)
					}
				}
	}
	
	override fun ItemClick(any : Any) {
		if(any is Photos){
			var intent = Intent(context, PhotoActivity::class.java)
			intent.putExtra("id",any.id)
			startActivity(intent)
		}
	}
	
}
