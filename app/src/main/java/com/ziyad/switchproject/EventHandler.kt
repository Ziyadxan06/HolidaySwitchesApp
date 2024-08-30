package com.ziyad.switchproject

import android.view.Menu
import android.view.View
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

    var SwEgo: SwitchCompat = view.findViewById(R.id.egoSwitch)
    var SwGiving: SwitchCompat = view.findViewById(R.id.givingSwitchCompat)
    var SwRespect: SwitchCompat = view.findViewById(R.id.respectSwitchCompat)
    var SwHappiness: SwitchCompat = view.findViewById(R.id.happinessSwitchCompat)
    var SwKindness: SwitchCompat = view.findViewById(R.id.kindessSwitchCompat)
    var SwOptimism: SwitchCompat = view.findViewById(R.id.optimismSwitchCompat)
    var switches: List<SwitchCompat> = listOf(SwGiving, SwRespect, SwHappiness, SwKindness,SwOptimism)


    fun makeEgoChecked(){
        if(activeSwitches.isEmpty()){
            SwEgo.isChecked = true
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
        if (SwEgo.isChecked) {
            disableAndCloseOtherSwitches()
            bottomNav.visibility = View.GONE
        } else {
            enableAndCloseOtherSwitches()
            bottomNav.visibility = View.VISIBLE
        }

        switches.forEach { switch ->
            switch.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked && SwEgo.isChecked) {
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
            R.id.givingSwitchCompat -> bottomNav.menu.add(
                Menu.NONE,
                R.id.givingFragment,
                Menu.NONE,
                "Giving"
            )
                .setIcon(R.drawable.ic_giving)

            R.id.respectSwitchCompat -> bottomNav.menu.add(
                Menu.NONE,
                R.id.respectFragment,
                Menu.NONE,
                "Respect"
            )
                .setIcon(R.drawable.ic_respect)

            R.id.happinessSwitchCompat -> bottomNav.menu.add(
                Menu.NONE,
                R.id.happinessFragment,
                Menu.NONE,
                "Hapiness"
            )
                .setIcon(R.drawable.ic_happiness)

            R.id.kindessSwitchCompat -> bottomNav.menu.add(
                Menu.NONE,
                R.id.kindnessFragment,
                Menu.NONE,
                "Kindness"
            )
                .setIcon(R.drawable.ic_kindness)

            R.id.optimismSwitchCompat -> bottomNav.menu.add(
                Menu.NONE,
                R.id.optimismFragment,
                Menu.NONE,
                "Optimism"
            )
                .setIcon(R.drawable.ic_optimism)
        }
    }

    fun removeDestinationFromBottomNav(view: View) {
        when (view.id) {
            R.id.givingSwitchCompat -> bottomNav.menu.removeItem(R.id.givingFragment)
            R.id.respectSwitchCompat -> bottomNav.menu.removeItem(R.id.respectFragment)
            R.id.happinessSwitchCompat -> bottomNav.menu.removeItem(R.id.happinessFragment)
            R.id.kindessSwitchCompat -> bottomNav.menu.removeItem(R.id.kindnessFragment)
            R.id.optimismSwitchCompat -> bottomNav.menu.removeItem(R.id.optimismFragment)
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
            }
        }
    }
}