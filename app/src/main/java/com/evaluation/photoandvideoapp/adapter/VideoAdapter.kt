package com.evaluation.photoandvideoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.evaluation.photoandvideoapp.R
import com.evaluation.photoandvideoapp.interfaces.ItemClick
import com.evaluation.photoandvideoapp.response.Photos
import com.evaluation.photoandvideoapp.response.Video
import com.evaluation.photoandvideoapp.utils.CircleImageView

class VideoAdapter (var itemClick : ItemClick) : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {
	
	var type : Int = 0
	lateinit var list : List<Video?>
	override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : VideoViewHolder {
		val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
		return VideoViewHolder(v)
	
	}
	
	override fun getItemCount() : Int {
		return list.size
	}
	
	override fun onBindViewHolder(holder : VideoViewHolder, position : Int) {
			holder.progressBar.visibility = View.GONE
			holder.videoButton.visibility = View.VISIBLE
			Glide.with(holder.itemView.context).load(list[position]?.image).into(holder.backgorund)
			Glide.with(holder.itemView.context).load(list[position]?.image).into(holder.profile)
			if(list[position]?.liked!!)
				holder.like.setImageDrawable(holder.itemView.resources.getDrawable(R.drawable.ic_details_favorite_slect))
			else
				holder.like.setImageDrawable(holder.itemView.resources.getDrawable(R.drawable.ic_details_favorite_deslect))
			holder.title.text = list[position]?.user?.name
			
			holder.itemView.setOnClickListener {
				itemClick.ItemClick(list[position]!!)
			}
		
	}
	
	fun setItems(list : List<Video?>){
		this.list = list
	}
	
	class VideoViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
		var progressBar : ProgressBar = itemView.findViewById(R.id.progressBar)
		var backgorund : ImageView = itemView.findViewById<ImageView>(R.id.background)
		var like : ImageView = itemView.findViewById<ImageView>(R.id.like)
		var profile : CircleImageView = itemView.findViewById<CircleImageView>(R.id.profile)
		var videoButton : ImageView = itemView.findViewById<ImageView>(R.id.play)
		var title : TextView = itemView.findViewById<TextView>(R.id.title)
		
	}
	
}

