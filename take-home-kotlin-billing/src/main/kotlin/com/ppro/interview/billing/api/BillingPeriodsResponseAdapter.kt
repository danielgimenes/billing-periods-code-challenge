package com.ppro.interview.billing.api

import com.ppro.interview.billing.api.data.BillingPeriodView
import com.ppro.interview.billing.domain.Period
import java.time.format.DateTimeFormatter

object BillingPeriodViewAdapter {

    fun fromModel(period: Period): BillingPeriodView {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return BillingPeriodView(period.periodId, formatter.format(period.start), formatter.format(period.end))
    }

}