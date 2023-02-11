:scroll: **START**
### Prerequisites

- A Java Development Kit (JDK) version 11 or higher
- Apache Maven installed and configured
- A Git client installed
---
### How to build the project

- Clone the repository
- Build the project using Maven
`mvn clean install`
---
### How to run the project

Change directory to the target folder in the cloned repository
Run the jar file
java -jar drone.management-0.0.1-SNAPSHOT.jar
---
### How to test the project

- Change directory to the cloned repository
- Run the tests using Maven
`mvn test`

### How to test functional
- The easiest way to use POSTMAN 
- Import drones managment.postman_collection.json in Collections
- Sent request to check
- Every 10 seconds scheduler will be writing the battery level in logs for all drones

:scroll: **END**
