package com.ppro.interview.billing.domain

import java.math.BigDecimal
import java.util.concurrent.ConcurrentHashMap

object AccountService {

    private val runningTotals: ConcurrentHashMap<Pair<Int, Period>, Pair<BigDecimal, Int>> = ConcurrentHashMap()

    fun submitTransaction(transaction: Transaction): RunningTotal {
        val billingPeriodId = Period.ofDate(transaction.date)
        val id = Pair(transaction.accountId, billingPeriodId)

        val value = runningTotals.compute(id) { _, value ->
            if (value == null) {
                Pair(transaction.value, 1)
            } else {
                Pair(value.first.add(transaction.value), value.second + 1)
            }
        }!!

        return RunningTotal(transaction.accountId, billingPeriodId.periodId, value.first, value.second)
    }


}