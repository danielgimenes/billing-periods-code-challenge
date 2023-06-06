package com.ppro.interview.billing.domain

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDate

class AccountServiceTests {

    @Test
    fun submitTransactionSample1() {
        val transaction = Transaction(
            accountId = 1,
            value = BigDecimal.valueOf(123.45),
            date = LocalDate.of(2019, 1, 1)
        )
        val expected = RunningTotal(
            accountId = 1,
            billingPeriodId = "2019-1",
            totalValue = BigDecimal.valueOf(123.45),
            count = 1
        )

        Assertions.assertEquals(expected, AccountService.submitTransaction(transaction))
    }

    @Test
    fun submitTransactionMultipleTransactions() {
        val transaction1 = Transaction(
            accountId = 1,
            value = BigDecimal.valueOf(123.45),
            date = LocalDate.of(2019, 1, 1)
        )
        val expected1 = RunningTotal(
            accountId = 1,
            billingPeriodId = "2019-1",
            totalValue = BigDecimal.valueOf(123.45),
            count = 1
        )
        val transaction2 = Transaction(
            accountId = 1,
            value = BigDecimal.valueOf(-100.12),
            date = LocalDate.of(2019, 1, 3)
        )
        val expected2 = RunningTotal(
            accountId = 1,
            billingPeriodId = "2019-1",
            totalValue = BigDecimal.valueOf(23.33),
            count = 2
        )

        Assertions.assertEquals(expected1, AccountService.submitTransaction(transaction1))
        Assertions.assertEquals(expected2, AccountService.submitTransaction(transaction2))
    }

}