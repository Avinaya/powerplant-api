# Powerplant Application

The Powerplant Application is a Spring Boot project that provides APIs for managing batteries and their capacities
within specified postcode ranges.

## Table of Contents

- [Features](#features)
- [Technologies](#technologies)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
- [API Documentation](#api-documentation)
- [Demo](#demo)
- [Usage](#usage)

## Features

- Save and retrieve battery information.
- Calculate total and average capacities within a postcode range.
- RESTful API for battery management.

## Technologies

- Java
- Spring Boot
- Spring Data JPA
- Spring MVC
- MYSQL Database (for production)
- H2 Database (for testing)
- Gradle
- Lombok
- Jackson (for JSON serialization/deserialization)
- Swagger (for API documentation)

## Getting Started

### Prerequisites

- Java 8 or higher installed
- Gradle build tool

### Installation

1. Clone the repository:
   git clone https://github.com/Avinaya/powerplant-api.git
   cd powerplant-api

2. Build the project using Gradle:
   ./gradlew build

3. Run the application:
   The application will start and be accessible at `http://localhost:8081`.

## API Documentation

API documentation is generated using Swagger. You can access it at `http://localhost:8081/swagger-ui.html`.

## Demo

Check out the live demo of the Powerplant Application at: [Demo Link](<demo_link>)

## Usage

1. Save Batteries:

```http
POST /v1/batteries
Content-Type: application/json
[
  {
    "name": "Battery 1",
    "postcode": "100",
    "capacity": 100
  },
  {
    "name": "Battery 2",
    "postcode": "200",
    "capacity": 200
  }
]
```

2. Get Batteries Within Range:

```
GET /v1/batteries?startPostcode=10&endPostcode=20000

{
  "names": ["Battery 1", "Battery 2"],
  "totalCapacity": 300,
  "averageCapacity": 150
}


```




