package com.ppro.interview.billing

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(BillingPeriodController::class)
internal class BillingPeriodControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun getPeriodsOfYearWhenSample1() {
        mockMvc.get("/v1/billing/periods?year=2019") {
            accept = MediaType.TEXT_PLAIN
        }.andExpect {
            status().isOk
            content { string(BillingPeriodControllerFixtures.outputPeriodsOf2019) }
        }
    }

    @Test
    fun returnsBadRequestIfYearIsMissing() {
        mockMvc.get("/v1/billing/periods").andExpect { status().isBadRequest }
    }

}