package com.ppro.interview.billing.domain

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

data class Period(val year: Int, val number: Int, val start: LocalDate, val end: LocalDate) {

    val periodId: String = "${year}-${number}"

    constructor(year: Int, number: Int, start: LocalDate)
            : this(year, number, start, nextPeriodStartDate(start).minusDays(1))

    fun nextPeriod(): Period {
        val nextStart = nextPeriodStartDate(start)
        val nextEnd = nextPeriodStartDate(nextStart).minusDays(1)
        return Period(
            year = nextStart.year,
            number = if (nextStart.year > year) 1 else number + 1,
            start = nextStart,
            end = nextEnd
        )
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

        private fun nextPeriodStartDate(date: LocalDate): LocalDate {
            val nextSaturday = date.with(TemporalAdjusters.next(DayOfWeek.SATURDAY))
            val isChangeOfMonth = date.monthValue != nextSaturday.monthValue
            return if (isChangeOfMonth) {
                nextSaturday.withDayOfMonth(1)
            } else {
                nextSaturday
            }
        }
    }

}