# geico-evaluation
This is an implementation of the GEICO evaulation for the Emergency Roadside Assistance Service.

## Installation
Build with Java 17

## Usage

## Assumptions

- Real-world DAO implementation will access a persistent data store, such as a relatonal database.
- Real-world Geolocator implementation will use a third-party service such as the Google Maps API to determine the distance between two points.
- Real-world application will be a Spring application with REST endpoints which call the service layer.  The current implementation is set up to easily use Spring dependency injection
and Spring Data JPA to access the data store.
- Assistant contains a customer because assistants are what the roadside system manages.  The present requirements indicate a 1:1 relationship between customer and assistant.
- Region ID concept was introduced to separate lists of Assistants by region or service area. 
This is to avoid having to iterate through all Assistants to find the closest one and will more closely resemble
a real-world implementation where Assistants are grouped by region.
