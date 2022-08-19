package com.example.myapplicationapi.ui.MainFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.data.models.DataBaseModel
import com.example.myapplicationapi.R
import com.example.myapplicationapi.databinding.ItemViewBinding

// Адаптер для главного экрана
class MyAdapter(private val onItemClicked: (DataBaseModel)-> Unit):RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    // Приватный и неизменяемый, для большего контроля деействий в адаптере
    private val myList: MutableList<DataBaseModel> = mutableListOf()

    // Сначала очищаем а потом сетим новый список
    fun set(newList: List<DataBaseModel>){
        this.myList.clear()
        this.myList.addAll(newList)
        notifyDataSetChanged()
    }
    // Все действия происходят в ViewHolder, чтобы он был самостоятельный
    class MyViewHolder(view: View):RecyclerView.ViewHolder(view) {
        private val binding = ItemViewBinding.bind(view)
        fun bind(item: DataBaseModel, onItemClicked: (DataBaseModel) -> Unit)
         = with(binding) {
            tvNameMain.text = item.name
            tvDescription.text = item.description
            Glide.with(ivPerson.context)
                .load(item.imageAvatar)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(ivPerson)
            root.setOnClickListener{
                onItemClicked(item)
            }
         }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(myList[position], onItemClicked)
    }

    override fun getItemCount(): Int {
        return myList.size
    }
}