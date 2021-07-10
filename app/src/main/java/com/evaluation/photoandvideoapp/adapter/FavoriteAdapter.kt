package com.evaluation.photoandvideoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.evaluation.photoandvideoapp.R
import com.evaluation.photoandvideoapp.interfaces.ItemClick
import com.evaluation.photoandvideoapp.response.Photos
import com.evaluation.photoandvideoapp.utils.CircleImageView

class FavoriteAdapter (var itemClick : ItemClick) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
	
	var type : Int = 0
	lateinit var list : List<Photos?>
	override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : FavoriteViewHolder {
			val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
			return FavoriteViewHolder(v)
	}
	
	override fun getItemCount() : Int {
		return list.size
	}
	
	override fun onBindViewHolder(holder : FavoriteViewHolder, position : Int) {
		
			holder.videoButton.visibility = View.INVISIBLE
			Glide.with(holder.itemView.context).load(list[position]?.src?.original).into(holder.backgorund)
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
	
	
	
	class FavoriteViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
		var backgorund : ImageView = itemView.findViewById<ImageView>(R.id.background)
		var like : ImageView = itemView.findViewById<ImageView>(R.id.like)
		var profile : CircleImageView = itemView.findViewById<CircleImageView>(R.id.profile)
		var videoButton : ImageView = itemView.findViewById<ImageView>(R.id.play)
		var title : TextView = itemView.findViewById<TextView>(R.id.title)
		
	}
}

