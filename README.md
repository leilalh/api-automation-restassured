# REST API Automation Framework

This is an API automation project I built to test full CRUD (Create, Read, Update, Delete) operations using **Java** and **RestAssured**.

---

##  Tools & Technologies
* **Language:** Java 17
* **API Library:** RestAssured
* **Test Framework:** TestNG
* **Build Tool:** Maven
* **Reporting:** ExtentReports
* **CI/CD:** GitHub Actions

---

##  Project Structure
* `src/test/java/models/User.java` - Data model (POJO) for request payloads.
* `src/test/java/endpoints/UserEndpoints.java` - Contains HTTP methods (POST, GET, PUT, DELETE).
* `src/test/java/tests/UserTests.java` - Test scenarios with validations and data chaining.
* `src/test/java/utilities/ExtentReportManager.java` - Generates HTML execution reports.
* `.github/workflows/maven.yml` - CI/CD pipeline running tests automatically on GitHub.

---

##  Test Coverage
* **POST:** Creates a new user and extracts the dynamic `id`.
* **GET:** Fetches the created user details using the extracted `id`.
* **PUT:** Updates user information.
* **DELETE:** Removes the user from the system.

---

##  How to Run

1. Clone this repository:
   ```bash
   git clone [https://github.com/leilalh/api-automation-restassured.git](https://github.com/leilalh/api-automation-restassured.git)


2. Run the tests:
   mvn test