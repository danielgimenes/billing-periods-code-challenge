## Feature 3

Add an internal service that accepts a transaction, and allow calculation of running totals

Each transaction submitted to the service must contain:

| Field      | Example    | Notes                                                  |
|------------|------------|--------------------------------------------------------|
| Account ID | 1          |                                                        |
| Value      | 123.45     | Positive or negative, indicating direction of transfer |
| Date       | 2019-01-02 |                                                        |

Whenever a transaction is submitted, the service should add it to the running totals for the relevant account
and period in which the transaction occurred, and return:

| Field                  | Example | Notes          |
|------------------------|---------|----------------|
| Account ID             | 1       |                |
| Billing period ID      | 2019-1  | From Feature 1 |
| Total value            | 123.45  |                |
| Number of transactions | 1       |                |

For simplicity of this task, you can / should:

* hold any required data in memory within a single instance of the service (no need to worry about scaling the calls
  across multiple services)
* you don't need to store the transaction details - just the total value and number of transactions

You should ensure that your code is thread-safe, and use data types that will be suitable for exact money calculations.

### Example sequence

In the following sequence of calls, the responses should be:

| Input                                                 | Response                                                                 |
|-------------------------------------------------------|--------------------------------------------------------------------------|
| 1. { accountId: 1, value:  123.45, date: 2019-01-01 } | { accountId: 1, billingPeriodId: 2019-1, totalValue:  123.45, count: 1 } |
| 2. { accountId: 1, value: -100.12, date: 2019-01-03 } | { accountId: 1, billingPeriodId: 2019-1, totalValue:   23.33, count: 2 } |
| 3. { accountId: 2, value: -108.55, date: 2019-01-03 } | { accountId: 2, billingPeriodId: 2019-1, totalValue: -108.55, count: 1 } |
| 4. { accountId: 1, value:  345.67, date: 2019-02-04 } | { accountId: 1, billingPeriodId: 2019-7, totalValue:  345.67, count: 1 } |

## Feature 4

Extend the internal service implemented in Feature 3, and add fee calculation. During the MVP stage, we will have all
accounts charge 1% fee (rounded to 2 decimal places), based on the absolute value of the transaction value. There is
no change to the transactions submitted.

Note: Long term, we will want our system to be able to support different fees configurations for different accounts and
different methods of calculating fees.

For simplicity of this task, you can / should:

* round all values up to 2 decimal places, and you can assume that values submitted to the service are already rounded
  in this way

Whenever a transaction is submitted, the endpoint should add it to the totals for the relevant account and period in
which the transaction occurred, and return:

| Field                  | Example | Notes          |
|------------------------|---------|----------------|
| Account ID             | 1       |                |
| Billing Period ID      | 2019-1  | From Feature 1 |
| Total Value            | 123.45  |                |
| Number of transactions | 1       |                |
| Total fees             | 1.23    |                |

### Example sequence

In the following sequence of calls, the responses should be:

| Input                                                 | Response                                                                             |
|-------------------------------------------------------|--------------------------------------------------------------------------------------|
| 1. { accountId: 1, value:  123.45, date: 2019-01-01 } | { accountId: 1, billingPeriodId: 2019-1, totalValue:  123.45, count: 1, fees: 1.23 } |
| 2. { accountId: 1, value: -100.12, date: 2019-01-03 } | { accountId: 1, billingPeriodId: 2019-1, totalValue:   23.33, count: 2, fees: 2.23 } |
| 3. { accountId: 2, value: -108.55, date: 2019-01-03 } | { accountId: 2, billingPeriodId: 2019-1, totalValue: -108.55, count: 1, fees: 1.09 } |
| 4. { accountId: 1, value:  345.67, date: 2019-02-04 } | { accountId: 1, billingPeriodId: 2019-7, totalValue:  345.67, count: 1, fees: 3.46 } |

## Feature 5

Add an API for bill aggregation - design and implement a REST endpoint that will accept a transaction, and invoke
the internal service implemented in Features 3 and 4
