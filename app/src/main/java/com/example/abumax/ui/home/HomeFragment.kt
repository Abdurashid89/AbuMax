package com.example.abumax.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.abumax.R
import com.example.abumax.databinding.FragmentHomeBinding
import com.example.abumax.entity.Category
import com.example.abumax.entity.Order
import com.example.abumax.ui.home.adapter.CategoryAdapter
import com.example.abumax.ui.home.adapter.OrderAdapter
import com.example.abumax.util.fadeIn

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel.text.observe(viewLifecycleOwner, {

        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()

    }

    private fun setUpUI() {
        setUpAdapter()
    }

    private fun setUpAdapter() {

        val adapter by lazy {
            CategoryAdapter { category ->
                val bundle = Bundle()
                bundle.putInt("count", category.count!!)
                findNavController().navigate(R.id.to_order)
            }
        }
        adapter.submitList(generateCategoryList())
        binding.rv.adapter = adapter
        binding.rv.fadeIn(1000)
    }

    private fun generateCategoryList(): ArrayList<Category> {
        val list = ArrayList<Category>()

        for (i in 1..20) {
            list.add(Category(name = "Продукт", count = i))
        }
        return list
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}