package com.ziyad.switchproject

import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import javax.inject.Inject

class DestinationTextAnimations @Inject constructor(){
    private lateinit var christrmasTextView: TextView
    private lateinit var sacrificeTextView: TextView
    private lateinit var novruzTextView: TextView
    private lateinit var haloweenTextView: TextView
    private lateinit var ramadanTextView: TextView
    private val handler = Handler(Looper.getMainLooper())
    private var index = 0

    fun animateSacrificeText(view: View, textToAnimate: String) {
        sacrificeTextView = view.findViewById(R.id.sacrificeFragmentText)
        handler.postDelayed({
            sacrificeTextView.text = textToAnimate.substring(0, index++)
            if (index <= textToAnimate.length) {
                animateSacrificeText(view, textToAnimate)
            }
        }, 20)
    }

    fun animateChristmasText(view: View, textToAnimate: String) {
        christrmasTextView = view.findViewById(R.id.christmasFragmentText)
        handler.postDelayed({
            christrmasTextView.text = textToAnimate.substring(0, index++)
            if (index <= textToAnimate.length) {
                animateChristmasText(view, textToAnimate)
            }
        }, 20)
    }

    fun animateNovruzText(view: View, textToAnimate: String) {
        novruzTextView = view.findViewById(R.id.novruzFragmentText)
        handler.postDelayed({
            novruzTextView.text = textToAnimate.substring(0, index++)
            if (index <= textToAnimate.length) {
                animateNovruzText(view, textToAnimate)
            }
        }, 20)
    }

    fun animateRamadanText(view: View, textToAnimate: String) {
        ramadanTextView = view.findViewById(R.id.ramadanFragmentText)
        handler.postDelayed({
            ramadanTextView.text = textToAnimate.substring(0, index++)
            if (index <= textToAnimate.length) {
                animateRamadanText(view, textToAnimate)
            }
        }, 20)
    }

    fun animateHaloweenText(view: View, textToAnimate: String) {
        haloweenTextView = view.findViewById(R.id.haloweenFragmentText)
        handler.postDelayed({
            haloweenTextView.text = textToAnimate.substring(0, index++)
            if (index <= textToAnimate.length) {
                animateHaloweenText(view, textToAnimate)
            }
        }, 20)
    }
}