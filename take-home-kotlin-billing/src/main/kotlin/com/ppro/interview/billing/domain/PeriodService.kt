package com.ppro.interview.billing.domain

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

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

    fun nextPeriodOf(date: LocalDate): LocalDate {
        val nextSaturday = date.with(TemporalAdjusters.next(DayOfWeek.SATURDAY))
        return if (date.monthValue != nextSaturday.monthValue) {
            nextSaturday.withDayOfMonth(1)
        } else {
            nextSaturday
        }
    }

}