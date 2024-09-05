package com.ziyad.switchproject

import android.view.Menu
import android.view.View
import android.widget.Switch
import androidx.appcompat.widget.SwitchCompat
import androidx.lifecycle.LifecycleOwner
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}

class EventHandlerTest {

    private lateinit var mockView: View
    private lateinit var mockBottomNav: BottomNavigationView
    private lateinit var mockSwWork: SwitchCompat
    private lateinit var mockSwSacrifice: SwitchCompat
    private lateinit var mockSwChristmas: SwitchCompat
    private lateinit var mockSwNovruz: SwitchCompat
    private lateinit var mockSwRamadan: SwitchCompat
    private lateinit var mockSwHaloween: SwitchCompat
    private lateinit var eventHandler: EventHandler

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        // Mock views
        mockView = mock(View::class.java)
        mockBottomNav = mock(BottomNavigationView::class.java)
        mockSwWork = mock(SwitchCompat::class.java)
        mockSwSacrifice = mock(SwitchCompat::class.java)
        mockSwChristmas = mock(SwitchCompat::class.java)
        mockSwNovruz = mock(SwitchCompat::class.java)
        mockSwRamadan = mock(SwitchCompat::class.java)
        mockSwHaloween = mock(SwitchCompat::class.java)

        // Mock findViewById to return mocked SwitchCompat objects
        `when`(mockView.findViewById<SwitchCompat>(R.id.workSwitch)).thenReturn(mockSwWork)
        `when`(mockView.findViewById<SwitchCompat>(R.id.eidaladhaSwitchCompat)).thenReturn(mockSwSacrifice)
        `when`(mockView.findViewById<SwitchCompat>(R.id.christmasSwitchCompat)).thenReturn(mockSwChristmas)
        `when`(mockView.findViewById<SwitchCompat>(R.id.novruzSwitchCompat)).thenReturn(mockSwNovruz)
        `when`(mockView.findViewById<SwitchCompat>(R.id.ramadanSwitchCompat)).thenReturn(mockSwRamadan)
        `when`(mockView.findViewById<SwitchCompat>(R.id.haloweenSwitchCompat)).thenReturn(mockSwHaloween)

        // Initialize the EventHandler with mocked view and bottom navigation
        eventHandler = EventHandler(mockView, mockBottomNav)
    }

    @Test
    fun `test makeEgoChecked hides bottom nav when activeSwitches is empty`() {
        // Given
        `when`(mockSwWork.isChecked).thenReturn(false)

        // When
        eventHandler.makeEgoChecked()

        // Then
        verify(mockSwWork).isChecked = true
        verify(mockBottomNav).visibility = View.GONE
    }
}