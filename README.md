# geico-evaluation

## Installation
Build with Java 17

## Usage

## Assumptions

- Assistant contains a customer because assistants are what the roadside system manages.
- Assistant can only be assigned to one customer at a time.
- Real-world DAO implementation will access a persistent data store, such as a relatonal database.
- Real-world Geolocator implementation will use a third-party service such as the Google Maps API to determine the distance between two points.
- Region ID concept was introduced to separate lists of Assistants by region. This is to avoid having to iterate through all Assistants to find the closest one and will more closely resemble
a real-world implementation where Assistants are grouped by region.