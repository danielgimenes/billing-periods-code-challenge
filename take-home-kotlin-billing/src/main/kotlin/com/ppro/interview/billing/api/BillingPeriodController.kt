package com.ppro.interview.billing.api

import com.ppro.interview.billing.domain.PeriodService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.time.Year

@RestController
@RequestMapping("/v1/billing/")
class BillingPeriodController {

    @Operation(summary = "All billing periods of the year")
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "List of billing periods",
            content = [Content(
                schema = Schema(implementation = BillingPeriodResponse::class),
                mediaType = "text/plain",
                examples = [ExampleObject(value = """
                    periodId=2019-1, from=2019-01-01, to=2019-01-04
                    periodId=2019-2, from=2019-01-05, to=2019-01-11
                """)]
            )]
        )
    )
    @Suppress("unused")
    @GetMapping(value = ["periods"], produces = ["text/plain"])
    @ResponseBody
    fun ofYear(@RequestParam("year") yearStr: String): String {
        val year = Year.of(Integer.parseInt(yearStr))
        val periods = PeriodService.periodsOfYear(year)
        return periods.map(BillingPeriodViewAdapter::fromModel).joinToString("\n")
    }

}

class BillingPeriodResponse(private val content: String) {
    override fun toString() = content
}