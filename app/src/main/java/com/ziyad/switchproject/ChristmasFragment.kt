package com.ziyad.switchproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChristmasFragment : Fragment() {

    @Inject
    lateinit var destinationTextProvider: DestinationTextProvider

    @Inject
    lateinit var destinationTextAnimations: DestinationTextAnimations

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_christmas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        destinationTextAnimations.animateChristmasText(view, destinationTextProvider.christrmasText)
    }


}