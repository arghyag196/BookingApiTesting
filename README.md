# BookingApiTesting 🚀

A robust, modular, and scalable API Test Automation Framework built using **Java**, **Cucumber BDD**, **RestAssured**, **Jackson Databind**, and **JUnit**. This project validates the endpoints of the public [Restful-Booker](https://restful-booker.herokuapp.com/) service.

---

## 🛠️ Tech Stack & Dependencies
* **Language:** Java 8+
* **BDD Framework:** Cucumber JVM (v7)
* **API Testing Library:** RestAssured (v5)
* **Serialization/Deserialization:** Jackson Databind (v2.17)
* **Test Runner / Assertions:** JUnit 4
* **Build Tool:** Maven

---

## 📁 Project Architecture

The framework follows a clean design pattern, separating test specifications, reusable utilities, resource enums, and POJOs:

```text
BookingApiTesting/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── pojo/                       # Data Transfer Objects (POJOs)
│   │       │   ├── BookingDates.java
│   │       │   ├── CreateToken.java
│   │       │   └── CreateUpdateBooking.java
│   │       │
│   │       └── resources/                  # Core Utilities & Configurations
│   │           ├── ApiResources.java       # Enum mapping for API paths
│   │           ├── TestData.java           # Dynamic payload builders (Java time integration)
│   │           ├── Utils.java              # RequestSpec builder, filters, and JSON extractors
│   │           └── global.properties       # Global configuration properties (baseUrl)
│   │
│   └── test/
│       └── java/
│           ├── cucumber.Options/
│           │   └── TestRunner.java         # JUnit runner for executing feature files
│           ├── features/
│           │   └── bookingApi.feature      # Gherkin feature file detailing test scenarios
│           └── stepDefinitions/
│               └── StepDefinition.java     # Glue code mapping Gherkin steps to RestAssured actions
│
├── target/                                 # Build outputs & Cucumber reports
├── logging.txt                             # Request & Response logging output file
└── pom.xml                                 # Maven dependencies
```

---

## ⚙️ Key Design Features

1. **Singleton Request Specification (`Utils.java`):** Centralizes base URI setup, global logging filters (`RequestLoggingFilter` & `ResponseLoggingFilter`), and default content types to avoid redundant configuration code.
2. **Enum-Driven API Routing (`ApiResources.java`):** Maps logical API request names (e.g., `createBooking`, `updateBooking`) to their respective endpoint paths cleanly.
3. **POJO Serialization & Dynamic Dates (`TestData.java`):** Replaces raw string manipulation with typed Java objects mapped via Jackson, utilizing modern Java `java.time.LocalDate` utilities for dynamic, current check-in/check-out dates.
4. **State Management (`keys` List):** Securely retains generated identifiers (such as `bookingid` and authentication `token`) across sequential test steps.

---

## 🥒 Cucumber Feature Scenarios (`bookingApi.feature`)

The framework executes an end-to-end flow covering:
* **Create Booking:** Submits a new booking payload and captures the returned `bookingid`.
* **Authentication:** Generates an access token required for protected modification endpoints.
* **Update Booking (PUT):** Updates booking details using a Scenario Outline and verifies changes via the `GET /booking` endpoint.
* **Partial Update (PATCH):** Updates specific booking fields dynamically.
* **Delete Booking:** Cleans up test data by deleting the created booking resource.

---

## 🚀 How to Run the Tests

You can run your tests directly from your IDE (IntelliJ IDEA) or via the command line using Maven:

### Via Maven Command Line:
```bash
mvn test
```

### Via JUnit Test Runner:
Right-click on `TestRunner.java` inside `src/test/java/cucumber.Options/` and select **Run 'TestRunner'**.

---

## 📊 Test Reports
After test execution, JSON reports are automatically generated under the target directory:
* **JSON Report:** `target/jsonReports/cucumer-report.json`
* **Request/Response Logs:** `logging.txt`