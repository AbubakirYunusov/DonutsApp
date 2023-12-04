package com.example.donutsapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.animeapp.R
import com.example.animeapp.databinding.FoodItemBinding
import com.example.donutsapp.data.model.FoodModel

class FoodAdapter(
    private val listener: FoodsitemClickListener
) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    private var foodList = mutableListOf<FoodModel>()

    fun updateFoodList(movieList: List<FoodModel>) {
        foodList.clear()
        foodList.addAll(movieList)
        notifyDataSetChanged()
    }

    inner class FoodViewHolder(private val binding: FoodItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(foodModel: FoodModel) {

            binding.textView.text = foodModel.foodName
            binding.textView2.text = foodModel.foodDescription
            binding.textView3.text = foodModel.foodPrice.toString()

            binding.cardView.setOnClickListener {
                listener.onFoodItemClick(foodModel)
            }
            binding.deleteItem.setOnClickListener {
                listener.onFoodItemBackClick(foodList.indexOf(foodModel))

            }

            Glide.with(binding.root)
                .load(foodModel.foodImg)
                .into(binding.imageView)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int,
    ): FoodViewHolder {
        val binding = FoodItemBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.food_item, parent, false)
        )
        return FoodViewHolder(binding)
    }

    override fun getItemCount(): Int = foodList.size

    override fun onBindViewHolder(
        holder: FoodAdapter.FoodViewHolder,
        position: Int,
    ) {
        holder.bind(foodList[position])
    }
}