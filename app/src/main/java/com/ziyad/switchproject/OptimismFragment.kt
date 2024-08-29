package com.ziyad.switchproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ziyad.switchproject.databinding.FragmentOptimismBinding

class OptimismFragment : Fragment() {

    private lateinit var binding: FragmentOptimismBinding
    private lateinit var destinationMessages: DestinationMessages

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOptimismBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        destinationMessages = DestinationMessages()
        binding.message = destinationMessages
    }
}