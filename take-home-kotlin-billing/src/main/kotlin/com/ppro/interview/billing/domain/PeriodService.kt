package com.ppro.interview.billing.domain

import java.time.LocalDate

class PeriodService {

    fun ofDate(date: LocalDate): String {
        var periodsToDate = 1
        var period = date.withDayOfMonth(1).withMonth(1)
        var nextPeriod = nextPeriodOf(period)
        while (nextPeriod.isBefore(date) || date.isEqual(nextPeriod)) {
            periodsToDate++
            period = nextPeriod
            nextPeriod = nextPeriodOf(period)
        }
        return "${date.year}-${periodsToDate}"
    }

}