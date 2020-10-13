package com.vmo.ecom.presentation.util

import com.vmo.ecom.constant.Constant
import com.vmo.ecom.domain.model.WorkingWeekDay
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.text.SimpleDateFormat

internal class WorkingStatusTest {

    private val operatingHours1 = "Mon-Sun 11:30 am - 9:00 pm"
    private val operatingHours2 = "Mon-Thu 11:00 am - 10:30 pm / Fri 11:00 am - 11:00 pm / Sat 11:30 am - 10:30 pm / Sun 4:30 pm - 10:30 pm"
    private val operatingHours3 = "Mon-Sun 11:30 am - 12:00 am"
    private val operatingHours4 = "Mon-Sun 11:30 am - 2:00 am"
    private val workingDays1: MutableList<WorkingWeekDay>
    private val workingDays2: MutableList<WorkingWeekDay>
    private val workingDays3: MutableList<WorkingWeekDay>
    private val workingDays4: MutableList<WorkingWeekDay>
    private val timeStamp0 = 1597449601000L // Saturday, August 15, 2020 00:00:01 AM GMT+00:00
    private val timeStamp1 = 1597491000000L // Saturday, August 15, 2020 11:30:00 AM GMT+00:00
    private val timeStamp2 = 1597525200000L // Saturday, August 15, 2020 9:00:00 PM GMT+00:00
    private val timeStamp3 = 1597525259000L // Saturday, August 15, 2020 9:00:59 PM GMT+00:00
    private val timeStamp4 = 1597525260000L // Saturday, August 15, 2020 9:01:00 PM GMT+00:00
    private val timeStamp5 = 1597535940000L // Saturday, August 15, 2020 11:59:00 PM GMT+00:00
    private val timeStamp6 = 1597530660000L // Saturday, August 15, 2020 10:31:00 PM GMT+00:00
    private val timeStamp7 = 1597530600000L // Saturday, August 15, 2020 10:30:00 PM GMT+00:00
    private val timeStamp8 = 1597491000000L // Saturday, August 15, 2020 11:30:00 AM GMT+00:00
    private val timeStamp9 = 1597490940000L // Saturday, August 15, 2020 11:29:00 AM GMT+00:00
    private val timeStamp10 = 1597449600000L // Saturday, August 15, 2020 00:00:00 AM GMT+00:00
    private val dateFormat: SimpleDateFormat

    init {
        workingDays1 = TimeUtil.parseToWorkingDays(operatingHours1)
        workingDays2 = TimeUtil.parseToWorkingDays(operatingHours2)
        workingDays3 = TimeUtil.parseToWorkingDays(operatingHours3)
        workingDays4 = TimeUtil.parseToWorkingDays(operatingHours4)
        dateFormat = SimpleDateFormat(Constant.DATE_FORMAT_12)
    }


    @Test
    fun `Given timeStamp, Then return Sat`() {
        val day0 = TimeUtil.getDayOfWeek(timeStamp0, 0)
        val day1 = TimeUtil.getDayOfWeek(timeStamp1, 0)
        val day2 = TimeUtil.getDayOfWeek(timeStamp2, 0)
        val day3 = TimeUtil.getDayOfWeek(timeStamp3, 0)
        val day4 = TimeUtil.getDayOfWeek(timeStamp4, 0)
        val day5 = TimeUtil.getDayOfWeek(timeStamp5, 0)
        val day6 = TimeUtil.getDayOfWeek(timeStamp6, 0)
        assertEquals(day0, "Sat")
        assertEquals(day1, "Sat")
        assertEquals(day2, "Sat")
        assertEquals(day3, "Sat")
        assertEquals(day4, "Sat")
        assertEquals(day5, "Sat")
        assertEquals(day6, "Sat")
    }

    @Test
    fun `Given timeStamp1, Then status is open`() {
        val status = TimeUtil.getWorkingStatus(timeStamp1, 0, workingDays1)
        assertTrue(status)
    }

    @Test
    fun `Given timeStamp2, Then status is open`() {
        val status = TimeUtil.getWorkingStatus(timeStamp2,0, workingDays1)
        assertTrue(status)
    }

    @Test
    fun `Given timeStamp3, Then status is open`() {
        val status = TimeUtil.getWorkingStatus(timeStamp3, 0, workingDays1)
        assertTrue(status)
    }

    @Test
    fun `Given timeStamp4, Then status is close`() {
        val status = TimeUtil.getWorkingStatus(timeStamp4, 0, workingDays1)
        assertFalse(status)
    }

    @Test
    fun `Given timeStamp5, Then status is close`() {
        val status = TimeUtil.getWorkingStatus(timeStamp5,0, workingDays1)
        assertFalse(status)
    }

    @Test
    fun `Given timeStamp6, Then status is close`() {
        val status = TimeUtil.getWorkingStatus(timeStamp6, 0, workingDays2)
        assertFalse(status)
    }

    @Test
    fun `Given timeStamp7, Then status is open`() {
        val status = TimeUtil.getWorkingStatus(timeStamp7, 0, workingDays2)
        assertTrue(status)
    }

    @Test
    fun `Given timeStamp8, Then status is open`() {
        val status = TimeUtil.getWorkingStatus(timeStamp8, 0, workingDays2)
        assertTrue(status)
    }

    @Test
    fun `Given timeStamp9, Then status is close`() {
        val status = TimeUtil.getWorkingStatus(timeStamp9, 0, workingDays2)
        assertFalse(status)
    }

    @Test
    fun `Given time is 24 00am, close time is 12 00am, Then status is close`() {
        val status = TimeUtil.getWorkingStatus(timeStamp10, 0, workingDays3)
        assertFalse(status)
    }

    @Test
    fun `Given time is 24 00am, close time is 2 00am, Then status is close`() {
        val status = TimeUtil.getWorkingStatus(timeStamp10, 0, workingDays4)
        assertFalse(status)
    }

}