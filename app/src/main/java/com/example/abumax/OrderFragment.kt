package com.example.abumax

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.abumax.databinding.FragmentOrderBinding
import com.example.abumax.entity.Order
import com.example.abumax.ui.home.adapter.OrderAdapter
import com.example.abumax.util.showToastMessage

class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null
    private var count = 0

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            count = requireArguments().getInt("count", 0)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
        setUpAdapter()
    }

    private fun setUpUI() {
        binding.btnSave.setOnClickListener {
            findNavController().popBackStack()
            showToastMessage("Save!!!")
        }
    }

    private fun setUpAdapter() {
        val adapter by lazy {
            OrderAdapter {

            }
        }

        when (count) {
            in 0..4 -> adapter.submitList(generateOrderList())
            else -> adapter.submitList(generateOrderList2())
        }
        binding.rv.adapter = adapter
    }

    private fun generateOrderList(): ArrayList<Order> {
        val list = ArrayList<Order>()

        for (i in 1..15) {
            list.add(Order(name = "Продукт", "${i * 30}"))
        }
        return list
    }

    private fun generateOrderList2(): ArrayList<Order> {
        val list = ArrayList<Order>()

        for (i in 1..16) {
            list.add(Order(name = "Продукт 2", "${i * 20}"))
        }
        return list
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}