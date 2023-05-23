package com.ppro.interview.billing.api

import com.ppro.interview.billing.api.data.BillingPeriodView
import com.ppro.interview.billing.domain.Period
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate

class BillingPeriodViewAdapterTests {

    @Test
    fun fromModelWhenSample1() {
        val inputPeriod = Period(
            year = 2019,
            number = 1,
            start = LocalDate.of(2019, 1, 1),
            end = LocalDate.of(2019, 1, 4)
        )

        val expected = BillingPeriodView("2019-1", "2019-01-01", "2019-01-04")

        assertEquals(expected, BillingPeriodViewAdapter.fromModel(inputPeriod))
    }

    @Test
    fun fromModelWhenSample2() {
        val inputPeriod = Period(
            year = 2019,
            number = 2,
            start = LocalDate.of(2019, 1, 5),
            end = LocalDate.of(2019, 1, 11)
        )

        val expected = BillingPeriodView("2019-2", "2019-01-05", "2019-01-11")

        assertEquals(expected, BillingPeriodViewAdapter.fromModel(inputPeriod))
    }

}