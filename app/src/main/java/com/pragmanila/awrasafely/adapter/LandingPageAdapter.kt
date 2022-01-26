package com.pragmanila.awrasafely.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pragmanila.awrasafely.R
import com.pragmanila.awrasafely.api.model.landingpage.LandingPage
import java.util.*


class LandingPageAdapter (var context: Context) : RecyclerView.Adapter<LandingPageAdapter.ViewHolder>() {

    var dataList = emptyList<LandingPage>()

    internal fun setDataList(dataList: List<LandingPage>) {
        this.dataList = dataList
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var name: TextView

        init {
            image = itemView.findViewById(R.id.image)
            name = itemView.findViewById(R.id.title)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LandingPageAdapter.ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.landingpage_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: LandingPageAdapter.ViewHolder, position: Int) {

        var data = dataList[position]
        holder.name.text = data.name
        holder.image.setImageResource(data.image)
    }

    override fun getItemCount() = dataList.size
}