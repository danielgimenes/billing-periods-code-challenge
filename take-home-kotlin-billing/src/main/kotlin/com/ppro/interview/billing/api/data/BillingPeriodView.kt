package com.ppro.interview.billing.api.data

data class BillingPeriodView(val periodId: String, val from: String, val to: String) {

    override fun toString() = "periodId=${periodId}, from=${from}, to=${to}"

}