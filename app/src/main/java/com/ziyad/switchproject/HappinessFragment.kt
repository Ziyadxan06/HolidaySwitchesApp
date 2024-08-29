package com.ziyad.switchproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ziyad.switchproject.databinding.FragmentHappinessBinding

class HappinessFragment : Fragment() {

    lateinit var binding: FragmentHappinessBinding
    lateinit var destinationMessages: DestinationMessages

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHappinessBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        destinationMessages = DestinationMessages()
        binding.message = destinationMessages
    }
}