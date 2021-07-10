package com.evaluation.photoandvideoapp.adapter

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.evaluation.photoandvideoapp.R
import com.evaluation.photoandvideoapp.interfaces.ItemClick
import com.evaluation.photoandvideoapp.response.Photos
import com.evaluation.photoandvideoapp.utils.CircleImageView

class PhotoAdapter (var itemClick : ItemClick) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {
	
	var type : Int = 0
	lateinit var list : List<Photos?>
	override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : PhotoAdapter.PhotoViewHolder {
		val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
			return PhotoViewHolder(v)
			
	}
	
	override fun getItemCount() : Int {
		return list.size
	}
	
	override fun onBindViewHolder(holder : PhotoAdapter.PhotoViewHolder, position : Int) {
		
			holder.videoButton.visibility = View.INVISIBLE
			Glide.with(holder.itemView.context)
					.asBitmap()
					.load(list[position]?.src?.original)
					.into( object : BitmapImageViewTarget(holder.backgorund){
						override fun onResourceReady(resource : Bitmap, transition : Transition<in Bitmap>?) {
							super.onResourceReady(resource, transition)
							holder.progressBar.visibility = View.GONE
						}
					})
					//. into(holder.backgorund)
			Glide.with(holder.itemView.context).load(list[position]?.src?.original).into(holder.profile)
			if(list[position]?.liked!!)
				holder.like.setImageDrawable(holder.itemView.resources.getDrawable(R.drawable.ic_details_favorite_slect))
			else
				holder.like.setImageDrawable(holder.itemView.resources.getDrawable(R.drawable.ic_details_favorite_deslect))
			holder.title.text = list[position]?.photographer
			
			holder.itemView.setOnClickListener {
				itemClick.ItemClick(list[position]!!)
			}
		
	}
	
	fun setItems(list : List<Photos?>){
		this.list = list
	}
	
	class PhotoViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
		var progressBar : ProgressBar = itemView.findViewById(R.id.progressBar)
		var backgorund : ImageView = itemView.findViewById<ImageView>(R.id.background)
		var like : ImageView = itemView.findViewById<ImageView>(R.id.like)
		var profile : CircleImageView = itemView.findViewById<CircleImageView>(R.id.profile)
		var videoButton : ImageView = itemView.findViewById<ImageView>(R.id.play)
		var title : TextView = itemView.findViewById<TextView>(R.id.title)
		
	}
	
	
}

