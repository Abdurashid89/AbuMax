package com.example.abumax.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.abumax.databinding.CategoryItemBinding
import com.example.abumax.databinding.OrderItemBinding
import com.example.abumax.entity.Category
import com.example.abumax.entity.Order
import com.example.abumax.util.bindItem
import com.romainpiel.shimmer.Shimmer

class OrderAdapter(
    private val clickListener: (Order) -> Unit,
) : RecyclerView.Adapter<OrderAdapter.VehicleHolder>() {
    private val list = ArrayList<Order>()
    private var shimmer = Shimmer()

    fun setOnClickMinus() {}

    fun submitList(vehicleList: List<Order>) {
        list.clear()
        list.addAll(vehicleList)
        // do job on the main thread
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VehicleHolder(
        OrderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: VehicleHolder, position: Int) = holder.bind()

    override fun getItemCount() = list.size

    inner class VehicleHolder(private val binding: OrderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                clickListener(list[adapterPosition])
                notifyDataSetChanged()
            }

            binding.tvPlus.setOnClickListener {

            }
        }

        @SuppressLint("SetTextI18n")
        fun bind() = bindItem {
            binding.tvName.text = list[adapterPosition].name
            binding.tvCount.setText("${list[adapterPosition].price}")
            shimmer.setRepeatCount(4)
                .setDuration(1000)
                .setStartDelay(1000).direction = Shimmer.ANIMATION_DIRECTION_LTR
            //  shimmer.start(binding)


        }
    }
}