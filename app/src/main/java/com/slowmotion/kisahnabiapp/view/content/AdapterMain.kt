package com.slowmotion.kisahnabiapp.view.content

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.slowmotion.kisahnabiapp.R
import com.slowmotion.kisahnabiapp.model.ResponseMain
import kotlinx.android.synthetic.main.item_list.view.*

class AdapterMain (
    val data : List<ResponseMain?>,
    val itemClick: OnClickListener
) : RecyclerView.Adapter<AdapterMain.ViewHolder>() {

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: ResponseMain){
            view.tv_name.text = item.name

            Glide.with(view.context)
                .load(item.imageUrl)
                .apply(RequestOptions())
                .override(500,500)
                .placeholder(R.drawable.ic_launcher_background)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .into(view.iv_item)

            view.setOnClickListener{
                itemClick.detail(item)
            }
        }
    }

    interface OnClickListener {
        fun detail(item: ResponseMain)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterMain.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterMain.ViewHolder, position: Int) {
        val item = data?.get(position)

        holder.bind(item!!)
    }

    override fun getItemCount(): Int = data?.size
}