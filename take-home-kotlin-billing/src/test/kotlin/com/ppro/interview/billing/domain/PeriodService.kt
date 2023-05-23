package com.ppro.interview.billing.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate

class PeriodServiceTests {

    @Test
    fun ofDateWhenFirstDayOfYear() {
        val inputDate = LocalDate.of(2023, 1,1)

        val expectedPeriod = "2023-1"

        assertEquals(expectedPeriod, PeriodService().ofDate(inputDate))
    }

    @Test
    fun ofDateWhenLastDayOfYear() {
        val inputDate = LocalDate.of(2023, 12,31)

        val expectedPeriod = "2023-62"

        assertEquals(expectedPeriod, PeriodService().ofDate(inputDate))
    }

    @Test
    fun ofDateWhenDateIsSaturday() {
        val inputDate = LocalDate.of(2023, 1,7)

        val expectedPeriod = "2023-2"

        assertEquals(expectedPeriod, PeriodService().ofDate(inputDate))
    }

    @Test
    fun ofDateWhenDateIsOnShorterPeriod() {
        val inputDate = LocalDate.of(2023, 6,2)

        val expectedPeriod = "2023-26"

        assertEquals(expectedPeriod, PeriodService().ofDate(inputDate))
    }

    @Test
    fun ofDateWhenDateIsOnFirstPeriodOfMonthAndSaturday() {
        val inputDate = LocalDate.of(2023, 4,1)

        val expectedPeriod = "2023-16"

        assertEquals(expectedPeriod, PeriodService().ofDate(inputDate))
    }

    @Test
    fun ofDateWhenSampleInput1() {
        val inputDate = LocalDate.of(2019, 1,24)

        val expectedPeriod = "2019-4"

        assertEquals(expectedPeriod, PeriodService().ofDate(inputDate))
    }

    @Test
    fun ofDateWhenSampleInput2() {
        val inputDate = LocalDate.of(2019, 2,2)

        val expectedPeriod = "2019-7"

        assertEquals(expectedPeriod, PeriodService().ofDate(inputDate))
    }

    @Test
    fun nextPeriodOfWhenSaturday() {
        val inputDate = LocalDate.of(2023, 4,1)

        val expectedPeriod = LocalDate.of(2023, 4, 8)

        assertEquals(expectedPeriod, PeriodService().nextPeriodOf(inputDate))
    }

    @Test
    fun nextPeriodOfWhenNotSaturday() {
        val inputDate = LocalDate.of(2023, 1,1)

        val expectedPeriod = LocalDate.of(2023, 1, 7)

        assertEquals(expectedPeriod, PeriodService().nextPeriodOf(inputDate))
    }

    @Test
    fun nextPeriodOfWhenEndOfYear() {
        val inputDate = LocalDate.of(2023, 12,31)

        val expectedPeriod = LocalDate.of(2024, 1, 1)

        assertEquals(expectedPeriod, PeriodService().nextPeriodOf(inputDate))
    }

}