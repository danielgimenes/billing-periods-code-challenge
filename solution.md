Adds features related to billing period:
- internal service: get period of a given date
- REST API to fetch all billing periods of a given year

The solution is structured in a hexagonal architecture fashion, by isolating domain classes that have business logic and model definitions from application classes that are responsible for input and output, formatting and dealing with infrastructure configuration and failures.

I have decided to add more logic to Period class than PeriodService (domain) to make it robust and facilitate testing. If logic would be all in PeriodService, either the functions would be private and tests wouldn't be as granular OR they would have to be public, making PeriodService more exposed to outside layers without necessity.

To avoid issues with date formatting (DateTimeFormatters are not thread-safe) I decided to create them when needed. To increase performance, one could have an instance be created by the request handler and pass it to the domain service, adapter, etc. I've not done so because it would make the solution more complex, and there is no explicit requirement about memory usage or performance.

There are 22 automated tests included. The adapter and domain classes have unit tests, while the application layer classes have integration tests.
