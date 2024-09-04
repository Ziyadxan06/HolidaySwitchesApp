package com.ziyad.switchproject

import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


class EventHandler(val view : View, val bottomNav : BottomNavigationView) : LifecycleEventObserver {

    var activeSwitches : MutableList<Int> = mutableListOf()

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when(event){
            Lifecycle.Event.ON_START -> addCheckedSwitch()
            Lifecycle.Event.ON_RESUME -> makeEgoChecked()
            else -> {}
        }
    }

    var SwWork: SwitchCompat = view.findViewById(R.id.workSwitch)
    var SwSacrifice: SwitchCompat = view.findViewById(R.id.eidaladhaSwitchCompat)
    var SwChristmas: SwitchCompat = view.findViewById(R.id.christmasSwitchCompat)
    var SwNovruz: SwitchCompat = view.findViewById(R.id.novruzSwitchCompat)
    var SwRamadan: SwitchCompat = view.findViewById(R.id.ramadanSwitchCompat)
    var SwHaloween: SwitchCompat = view.findViewById(R.id.haloweenSwitchCompat)
    var switches: List<SwitchCompat> = listOf( SwSacrifice, SwChristmas, SwNovruz, SwRamadan, SwHaloween)


    fun makeEgoChecked(){
        if(activeSwitches.isEmpty()){
            SwWork.isChecked = true
            disableAndCloseOtherSwitches()
            bottomNav.visibility = View.GONE
        }
    }

    fun addCheckedSwitch(){
        switches.forEach { switchCompat ->
            if(switchCompat.isChecked && activeSwitches.size < 4){
                activeSwitches.add(switchCompat.id)
            }
        }
    }

    fun OnClickEgo() {
        if (SwWork.isChecked) {
            disableAndCloseOtherSwitches()
            bottomNav.visibility = View.GONE
        } else {
            enableAndCloseOtherSwitches()
            bottomNav.visibility = View.VISIBLE
        }

        switches.forEach { switch ->
            switch.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked && SwWork.isChecked) {
                    switch.isChecked = false
                }
            }
        }
    }

    fun disableAndCloseOtherSwitches() {
        switches.forEachIndexed { index, switch ->
            switch.isEnabled = false
            if (switch.isChecked) {
                switch.isChecked = false
                activeSwitches.clear()
                removeAllDestinations(index)
            }
        }
    }

    fun enableAndCloseOtherSwitches() {
        switches.forEach { switch ->
            switch.isEnabled = true
        }
    }

    fun onClickSwitch(view: View) {
        switches.forEach { switch ->
            if (switch.isChecked && view.id == switch.id && !activeSwitches.contains(view.id) && activeSwitches.size < 4) {
                activeSwitches.add(switch.id)
                addDestinationToBottomNav(activeSwitches)
            } else if (!switch.isChecked && view.id == switch.id && activeSwitches.contains(view.id)) {
                activeSwitches.remove(switch.id)
                removeDestinationFromBottomNav(view)
                autoReplace()
            }
        }
    }

    fun addDestinationToBottomNav(activeSwitches: MutableList<Int>) {
        when (activeSwitches.last()) {
            R.id.eidaladhaSwitchCompat -> bottomNav.menu.add(
                Menu.NONE,
                R.id.givingFragment,
                Menu.NONE,
                "Eid al-Adha"
            )
                .setIcon(R.drawable.ic_sacrifice)

            R.id.christmasSwitchCompat -> bottomNav.menu.add(
                Menu.NONE,
                R.id.respectFragment,
                Menu.NONE,
                "Christmas"
            )
                .setIcon(R.drawable.ic_xmas)

            R.id.novruzSwitchCompat -> bottomNav.menu.add(
                Menu.NONE,
                R.id.happinessFragment,
                Menu.NONE,
                "Novruz"
            )
                .setIcon(R.drawable.ic_novruz)

            R.id.ramadanSwitchCompat -> bottomNav.menu.add(
                Menu.NONE,
                R.id.kindnessFragment,
                Menu.NONE,
                "Ramadan"
            )
                .setIcon(R.drawable.ic_ramadan)

            R.id.haloweenSwitchCompat -> bottomNav.menu.add(
                Menu.NONE,
                R.id.optimismFragment,
                Menu.NONE,
                "Haloween"
            )
                .setIcon(R.drawable.ic_haloween)
        }
    }

    fun removeDestinationFromBottomNav(view: View) {
        when (view.id) {
            R.id.eidaladhaSwitchCompat -> bottomNav.menu.removeItem(R.id.givingFragment)
            R.id.christmasSwitchCompat -> bottomNav.menu.removeItem(R.id.respectFragment)
            R.id.novruzSwitchCompat -> bottomNav.menu.removeItem(R.id.happinessFragment)
            R.id.ramadanSwitchCompat -> bottomNav.menu.removeItem(R.id.kindnessFragment)
            R.id.haloweenSwitchCompat -> bottomNav.menu.removeItem(R.id.optimismFragment)
        }
    }

    fun removeAllDestinations(index: Int) {
        when (index) {
            0 -> bottomNav.menu.removeItem(R.id.givingFragment)
            1 -> bottomNav.menu.removeItem(R.id.respectFragment)
            2 -> bottomNav.menu.removeItem(R.id.happinessFragment)
            3 -> bottomNav.menu.removeItem(R.id.kindnessFragment)
            4 -> bottomNav.menu.removeItem(R.id.optimismFragment)
        }
    }

    fun autoReplace(){
        switches.forEach{ switch ->
            if(switch.isChecked && !activeSwitches.contains(switch.id) && activeSwitches.size < 4){
                activeSwitches.add(switch.id)
                addDestinationToBottomNav(activeSwitches)
                Toast.makeText(view.context, "${switch.text} switch item added to bottom nav", Toast.LENGTH_SHORT).show()
            }
        }
    }
}