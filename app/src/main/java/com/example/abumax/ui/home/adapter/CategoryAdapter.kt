package com.example.abumax.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.abumax.databinding.CategoryItemBinding
import com.example.abumax.entity.Category
import com.example.abumax.util.bindItem
import com.romainpiel.shimmer.Shimmer
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class CategoryAdapter(
    private val clickListener: (Category) -> Unit,
) : RecyclerView.Adapter<CategoryAdapter.VehicleHolder>() {
    private val list = ArrayList<Category>()
    private var shimmer = Shimmer()

    fun submitList(vehicleList: List<Category>) {
        list.clear()
        list.addAll(vehicleList)
       MainScope().launch {
           notifyDataSetChanged()
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VehicleHolder(
        CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: VehicleHolder, position: Int) = holder.bind()

    override fun getItemCount() = list.size

    inner class VehicleHolder(private val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                clickListener(list[adapterPosition])
                notifyDataSetChanged()
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind() = bindItem {
            binding.tvName.text = list[adapterPosition].name
            binding.tvCount.text = "${list[adapterPosition].count}"
            shimmer.setRepeatCount(4)
                .setDuration(1000)
                .setStartDelay(1000).direction = Shimmer.ANIMATION_DIRECTION_LTR
           //  shimmer.start(binding)


        }
    }
}