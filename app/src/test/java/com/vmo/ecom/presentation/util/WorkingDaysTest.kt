package com.vmo.ecom.presentation.util

import com.vmo.ecom.domain.model.WorkingWeekDay
import org.junit.Assert.assertEquals
import org.junit.Test

internal class WorkingDaysTest {

    private val operatingHours1 = "Mon-Sun 11:30 am - 9:00 pm"
    private var workingDays1: MutableList<WorkingWeekDay>
    private val operatingHours2 = "Fri-Mon 11:30 am - 9:00 pm"
    private var workingDays2: MutableList<WorkingWeekDay>
    private val operatingHours3 = "Mon-Wed, Sun 11:30 am - 10:00 pm / Fri-Sat 11:30 am - 11:00 pm"
    private var workingDays3: MutableList<WorkingWeekDay>
    private val operatingHours4 = "Mon-Wed, Sun 11:30 am - 10:00 pm / Thu 8:30 am - 10:00 pm / Fri-Sat 11:30 am - 11:00 pm"
    private var workingDays4: MutableList<WorkingWeekDay>
    private val operatingHours5 = "Mon-Sun 11:30 am - 12:00 pm"
    private var workingDays5: MutableList<WorkingWeekDay>

    init {
        workingDays1 = TimeUtil.parseToWorkingDays(operatingHours1)
        workingDays2 = TimeUtil.parseToWorkingDays(operatingHours2)
        workingDays3 = TimeUtil.parseToWorkingDays(operatingHours3)
        workingDays4 = TimeUtil.parseToWorkingDays(operatingHours4)
        workingDays5 = TimeUtil.parseToWorkingDays(operatingHours5)
    }

    @Test
    fun `Given operatingHours1, Then workingDays1 having 7 items`() {
        assertEquals(workingDays1.size, 7)
    }

    @Test
    fun `Given operatingHours2, Then workingDays2 having 4 items`() {
        assertEquals(workingDays2.size, 4)
    }

    @Test
    fun `Given operatingHour2, Then last item of workingDays2 is Sun`() {
        assertEquals(workingDays2.last().weekDay, "Sun")
    }

    @Test
    fun `Given operatingHour2, Then first day of workingDays2 is Mon`() {
        assertEquals(workingDays2.first().weekDay, "Mon")
    }

    @Test
    fun `Given operatingHours3, Then workingDays3 having 6 items`() {
        assertEquals(workingDays3.size, 6)
    }

    @Test
    fun `Given operatingHours3, Then Thursday not in workingDays3`() {
        val result = workingDays3.find { it.weekDay == "Thu" }
        assertEquals(result, null)
    }

    @Test
    fun `Given operatingHours4, Then workingDays4 having 7 items`() {
        assertEquals(workingDays4.size, 7)
    }

    @Test
    fun `Give operatingHours4, Then open hour on Thu is 8 30 am`() {
        assertEquals(workingDays4[3].openHour, "8:30 am")
    }

    @Test
    fun `Given operatingHour5, Then close hour on Sunday is 12 00 pm`() {
        assertEquals(workingDays5[6].closeHour, "12:00 pm")
    }
}