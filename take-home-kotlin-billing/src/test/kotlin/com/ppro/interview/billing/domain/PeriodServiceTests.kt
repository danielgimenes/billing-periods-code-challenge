package com.ppro.interview.billing.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.Year

class PeriodServiceTests {

    @Test
    fun periodIdOfDateWhenDateSample1() {
        val inputDate = "24th Jan 2019"

        val expected = "periodId = 2019-4"

        assertEquals(expected, PeriodService().periodIdOfDate(inputDate))
    }

    @Test
    fun periodIdOfDateWhenDateSample2() {
        val inputDate = "2nd Feb 2019"

        val expected = "periodId = 2019-7"

        assertEquals(expected, PeriodService().periodIdOfDate(inputDate))
    }

    @Test
    fun periodIdOfDateWhenFirstDay() {
        val inputDate = "1st Mar 2023"

        val expected = "periodId = 2023-11"

        assertEquals(expected, PeriodService().periodIdOfDate(inputDate))
    }

    @Test
    fun periodIdOfDateWhenSecondDay() {
        val inputDate = "2nd Mar 2023"

        val expected = "periodId = 2023-11"

        assertEquals(expected, PeriodService().periodIdOfDate(inputDate))
    }

    @Test
    fun periodIdOfDateWhenThirdDay() {
        val inputDate = "3rd Mar 2023"

        val expected = "periodId = 2023-11"

        assertEquals(expected, PeriodService().periodIdOfDate(inputDate))
    }

    @Test
    fun periodIdOfDateWhenOtherDay() {
        val inputDate = "24th Mar 2023"

        val expected = "periodId = 2023-14"

        assertEquals(expected, PeriodService().periodIdOfDate(inputDate))
    }

    @Test
    fun periodsOfYearWhenSample1() {
        val inputYear = Year.of(2019)

        val expected = PeriodFixtures.periodsOf2019

        assertEquals(expected, PeriodService().periodsOfYear(inputYear))
    }

}