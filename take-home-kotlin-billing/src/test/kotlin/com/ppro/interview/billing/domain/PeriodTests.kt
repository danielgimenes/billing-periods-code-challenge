package com.ppro.interview.billing.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate

class PeriodTests {

    @Test
    fun ofDateWhenFirstDayOfYear() {
        val inputDate = LocalDate.of(2023, 1,1)

        val expectedPeriod = Period(2023, 1, LocalDate.of(2023, 1,1))

        assertEquals(expectedPeriod, Period.ofDate(inputDate))
    }

    @Test
    fun ofDateWhenLastDayOfYear() {
        val inputDate = LocalDate.of(2023, 12,31)

        val expectedPeriod = Period(2023, 62, LocalDate.of(2023, 12,30))

        assertEquals(expectedPeriod, Period.ofDate(inputDate))
    }

    @Test
    fun ofDateWhenDateIsSaturday() {
        val inputDate = LocalDate.of(2023, 1,7)

        val expectedPeriod = Period(2023, 2, LocalDate.of(2023, 1,7))

        assertEquals(expectedPeriod, Period.ofDate(inputDate))
    }

    @Test
    fun ofDateWhenDateIsOnShorterPeriod() {
        val inputDate = LocalDate.of(2023, 6,2)

        val expectedPeriod = Period(2023, 26, LocalDate.of(2023, 6,1))

        assertEquals(expectedPeriod, Period.ofDate(inputDate))
    }

    @Test
    fun ofDateWhenDateIsOnFirstPeriodOfMonthAndSaturday() {
        val inputDate = LocalDate.of(2023, 4,1)

        val expectedPeriod = Period(2023, 16, LocalDate.of(2023, 4,1))

        assertEquals(expectedPeriod, Period.ofDate(inputDate))
    }

    @Test
    fun ofDateWhenSampleInput1() {
        val inputDate = LocalDate.of(2019, 1,24)

        val expectedPeriod = Period(2019, 4, LocalDate.of(2019, 1,19))

        assertEquals(expectedPeriod, Period.ofDate(inputDate))
    }

    @Test
    fun ofDateWhenSampleInput2() {
        val inputDate = LocalDate.of(2019, 2,2)

        val expectedPeriod = Period(2019, 7, LocalDate.of(2019, 2,2))

        assertEquals(expectedPeriod, Period.ofDate(inputDate))
    }

    @Test
    fun nextPeriodWhenSaturday() {
        val period = Period(2023, 16, LocalDate.of(2023, 4,1))

        val expectedPeriod = Period(2023, 17, LocalDate.of(2023, 4, 8))

        assertEquals(expectedPeriod, period.nextPeriod())
    }

    @Test
    fun nextPeriodWhenNotSaturday() {
        val period = Period(2023, 1, LocalDate.of(2023, 1,1))

        val expectedPeriod = Period(2023, 2, LocalDate.of(2023, 1, 7))

        assertEquals(expectedPeriod, period.nextPeriod())
    }

    @Test
    fun nextPeriodWhenEndOfYear() {
        val period = Period(2023, 62, LocalDate.of(2023, 12,31))

        val expectedPeriod = Period(2024, 1, LocalDate.of(2024, 1, 1))

        assertEquals(expectedPeriod, period.nextPeriod())
    }

}