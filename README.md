# geico-evaluation
This is an implementation of the GEICO evaluation for the Emergency Roadside Assistance Service.

## Installation
1. Clone the repository with `git clone https://github.com/brent-schwartz/geico-evaluation.git`
2. Java 17 is the specified JDK version.
3. Open the project in an IDE, such as IntelliJ IDEA.
4. Run `mvn clean install` to install dependencies and build the project.
5. Alternatively, run `mvn clean install` from the command line.  This will require installation of Maven.

## Usage
Run the tests with `mvn test`, either from an IDE or from the command line.

## Assumptions

- Real-world DAO implementation will access a persistent data store, such as a relatonal database.
- Real-world Geolocator implementation will use a third-party service such as the Google Maps API to determine the distance between two points.
- Real-world application will be a Spring application with REST endpoints which call the service layer.  The current implementation is set up to easily use Spring dependency injection
and Spring Data JPA to access the data store.
- Assistant contains a customer because assistants are what the roadside system manages.  The present requirements indicate a 1:1 relationship between customer and assistant.
- Region ID concept was introduced to separate lists of Assistants by region or service area. 
This is to avoid having to iterate through all Assistants to find the closest one and will more closely resemble
a real-world implementation where Assistants are grouped by region.
- Only active (on-duty) and available (no current customer) Assistants are included when finding and reserving the nearest Assistant.
