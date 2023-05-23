package com.ppro.interview.billing.domain

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

data class Period(val year: Int, val number: Int, val start: LocalDate) {

    val periodId: String = "${year}-${number}"

    fun nextPeriod(): Period {
        val nextSaturday = start.with(TemporalAdjusters.next(DayOfWeek.SATURDAY))
        return Period(
            year = nextSaturday.year,
            number = if (nextSaturday.year > year) 1 else number + 1,
            start =  if (start.monthValue != nextSaturday.monthValue) {
                nextSaturday.withDayOfMonth(1)
            } else {
                nextSaturday
            })
    }

    companion object {
        fun ofDate(date: LocalDate): Period {
            var periodsToDate = 1
            var period = Period(date.year, 1, date.withDayOfMonth(1).withMonth(1))
            var next = period.nextPeriod()
            while (next.start.isBefore(date) || date.isEqual(next.start)) {
                periodsToDate++
                period = next
                next = period.nextPeriod()
            }
            return period
        }
    }

}