package com.example.myapplicationapi.ui.DetailFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplicationapi.R
import com.example.myapplicationapi.databinding.ViewPagerItemBinding

class ViewPagerAdapter(private val onItemClicked: (String)-> Unit): RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    private val myList: MutableList<String> = mutableListOf()

    //Сеттер для новых данных
    fun set(newList: List<String>){
        this.myList.clear()
        this.myList.addAll(newList)
        notifyDataSetChanged()
    }
    // ВьюХолдер
    class ViewPagerViewHolder(view: View):RecyclerView.ViewHolder(view) {
        private val binding = ViewPagerItemBinding.bind(view)
        fun bind(item: String, onItemClicked: (String) -> Unit){
            with(binding) {
                Glide.with(imDetailImage.context)
                .load(item)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(imDetailImage)
                root.setOnClickListener{
                    onItemClicked(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_pager_item, parent, false)
        return ViewPagerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.bind(myList[position], onItemClicked)

    }

    override fun getItemCount(): Int = myList.size

}