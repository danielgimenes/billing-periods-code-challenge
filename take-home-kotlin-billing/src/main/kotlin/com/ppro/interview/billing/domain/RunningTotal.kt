package com.ppro.interview.billing.domain

import java.math.BigDecimal

data class RunningTotal(val accountId: Int, val billingPeriodId: String, val totalValue: BigDecimal, val count: Int) {
}