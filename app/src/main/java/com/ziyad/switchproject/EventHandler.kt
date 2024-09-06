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

    var activeSwitches: MutableList<Int> = mutableListOf()

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_START -> addCheckedSwitch()
            Lifecycle.Event.ON_RESUME -> makeWorkChecked()
            else -> {}
        }
    }



    var SwWork: SwitchCompat = view.findViewById(R.id.workSwitch)
    var SwSacrifice: SwitchCompat = view.findViewById(R.id.eidaladhaSwitchCompat)
    var SwChristmas: SwitchCompat = view.findViewById(R.id.christmasSwitchCompat)
    var SwHaloween: SwitchCompat = view.findViewById(R.id.haloweenSwitchCompat)
    var SwRamadan: SwitchCompat = view.findViewById(R.id.ramadanSwitchCompat)
    var SwNovruz: SwitchCompat = view.findViewById(R.id.novruzSwitchCompat)
    var switches: List<SwitchCompat> =
        listOf(SwSacrifice, SwChristmas, SwHaloween, SwRamadan, SwNovruz)

    fun makeWorkChecked() {
        if (activeSwitches.isEmpty()) {
            SwWork.isChecked = true
            disableAndCloseOtherSwitches()
            bottomNav.visibility = View.GONE
        }
    }

    fun addCheckedSwitch() {
        switches.forEach { switchCompat ->
            if (switchCompat.isChecked && activeSwitches.size < 4) {
                activeSwitches.add(switchCompat.id)
            }
        }
    }

    fun OnClickWork() {
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
            }else if(bottomNav.menu.size() == 5 && view.id == switch.id && !activeSwitches.contains(switch.id) && switch.isChecked ){
                switch.isChecked = false
                Toast.makeText(view.context, "You can't add 6 items to bottom navbar", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun addDestinationToBottomNav(activeSwitches: MutableList<Int>) {
        when (activeSwitches.last()) {
            R.id.eidaladhaSwitchCompat -> bottomNav.menu.add(
                Menu.NONE,
                R.id.sacrificeFragment,
                Menu.NONE,
                "Eid al-Adha"
            )
                .setIcon(R.drawable.ic_sacrifice)

            R.id.christmasSwitchCompat -> bottomNav.menu.add(
                Menu.NONE,
                R.id.christmasFragment,
                Menu.NONE,
                "Christmas"
            )
                .setIcon(R.drawable.ic_xmas)

            R.id.haloweenSwitchCompat -> bottomNav.menu.add(
                Menu.NONE,
                R.id.haloweenFragment,
                Menu.NONE,
                "Haloween"
            )
                .setIcon(R.drawable.ic_haloween)

            R.id.ramadanSwitchCompat -> bottomNav.menu.add(
                Menu.NONE,
                R.id.ramadanFragment,
                Menu.NONE,
                "Ramadan"
            )
                .setIcon(R.drawable.ic_ramadan)

            R.id.novruzSwitchCompat -> bottomNav.menu.add(
                Menu.NONE,
                R.id.novruzFragment,
                Menu.NONE,
                "Novruz"
            )
                .setIcon(R.drawable.ic_novruz)
        }
    }

    fun removeDestinationFromBottomNav(view: View) {
        when (view.id) {
            R.id.eidaladhaSwitchCompat -> bottomNav.menu.removeItem(R.id.sacrificeFragment)
            R.id.christmasSwitchCompat -> bottomNav.menu.removeItem(R.id.christmasFragment)
            R.id.novruzSwitchCompat -> bottomNav.menu.removeItem(R.id.novruzFragment)
            R.id.ramadanSwitchCompat -> bottomNav.menu.removeItem(R.id.ramadanFragment)
            R.id.haloweenSwitchCompat -> bottomNav.menu.removeItem(R.id.haloweenFragment)
        }
    }

    fun removeAllDestinations(index: Int) {
        when (index) {
            0 -> bottomNav.menu.removeItem(R.id.sacrificeFragment)
            1 -> bottomNav.menu.removeItem(R.id.christmasFragment)
            2 -> bottomNav.menu.removeItem(R.id.haloweenFragment)
            3 -> bottomNav.menu.removeItem(R.id.ramadanFragment)
            4 -> bottomNav.menu.removeItem(R.id.novruzFragment)

        }
    }
}