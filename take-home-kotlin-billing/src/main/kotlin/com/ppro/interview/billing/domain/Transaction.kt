package com.ppro.interview.billing.domain

import java.math.BigDecimal
import java.time.LocalDate

data class Transaction(val accountId: Int, val value: BigDecimal, val date: LocalDate) {
}