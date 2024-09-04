package com.ziyad.switchproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ziyad.switchproject.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var bottomNav : BottomNavigationView
    lateinit var eventHandler: EventHandler

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottomNav = requireActivity().findViewById(R.id.bottomNavigation)

        eventHandler = EventHandler(view, bottomNav)
        binding.handler = eventHandler
        lifecycle.addObserver(eventHandler)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        lifecycle.removeObserver(eventHandler)
    }
}