package com.ppro.interview.billing.domain

import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalQueries

class PeriodService {

    fun periodIdOfDate(rawDateStr: String): String {
        val dateStr = removeOrdinalIndicator(rawDateStr)
        val date = DateTimeFormatter.ofPattern("d MMM yyyy").parse(dateStr, TemporalQueries.localDate())
        return "periodId = ${Period.ofDate(date).periodId}"
    }

    private fun removeOrdinalIndicator(dateStr: String) =
        dateStr.replace(Regex("([0-9]+)[a-z]{2}"), "$1") // removes "st", "nd", "rd" from day

}