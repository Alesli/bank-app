# Reactive Programming Project: Sports API

## Introduction
This project is an exploration into the realm of reactive programming, leveraging the power of React. It involves developing a comprehensive ETL (Extract, Transform, Load) process, building a RESTful API for sports data management, and implementing backpressure in data processing.

## Task 1: ETL Process

### Objectives
1. **Data Model Creation:** Define a data model for sports data.
2. **Reactive Repository:** Develop a reactive repository for data handling.
3. **Setup Class Implementation:** Create a class for data extraction and loading using reactive paradigms.

### Details

#### 1. Data Model
Fields in the model:
- `id` (int): Identifier of the sport.
- `name` (string): Name of the sport.

#### 2. Reactive Repository
- Implement a reactive repository to handle CRUD operations asynchronously.

#### 3. Setup Class
- **Functionality:**
    1. Request sports data from `https://sports.api.decathlon.com/sports`.
    2. Parse the JSON response using reactive pipelines.
    3. Save the parsed objects into the reactive repository.

## Task 2: API for Sports

### Objectives
1. **API Implementation:** Utilize Router Functions to create API endpoints.
2. **Reactive Repository Methods:** Extend the reactive repository to support new API functionalities.

### API Methods

1. **POST `/api/v1/sport/{sportname}`**
    - Create a new sport entry by name.
    - Include exception handling for duplicate entries.

2. **GET `/api/v1/sport?q={query}`**
    - Search for sports by name.

### Repository Extensions
- Implement methods in the reactive repository to support the above API functionalities.

## Task 3: Refactor ETL Process Using Backpressure

### Objectives
1. **Backpressure Implementation:** Modify the ETL process to handle backpressure effectively.
2. **Logging:** Implement logging to monitor the backpressure process.

### Implementation Details

1. **Backpressure Handling:**
    - Modify the ETL data flow to request only 20 elements at a time from the upstream.
    - Use the `request(20)` method to control the data flow.

2. **Logging:**
    - Log the operations to track the pattern of `request(20)` calls followed by 20 `onNext()` calls, and then repeat the cycle.

3. Set up a mock API using Mocky.io to simulate API response (go to [Mocky.io](https://designer.mocky.io/) ).
   A mock response:
```
[
    {"id": 1, "name": "Soccer"},
    {"id": 2, "name": "Basketball"},
    {"id": 3, "name": "Tennis"}
]
```
---

This project encapsulates the core principles of reactive programming and provides hands-on experience with real-world data processing and API management in a reactive context. 
The focus on backpressure and asynchronous data handling techniques highlights the efficiency and robustness of reactive programming paradigms.
