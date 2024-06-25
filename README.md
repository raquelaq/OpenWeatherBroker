<h1 align="center"> OpenWeatherMap and TripAdvisor Sensor </h1>

<p align="left">

   <img src="https://img.shields.io/badge/STATUS-COMPLETE-green">
   <img src="https://img.shields.io/badge/Released-June%202024-yellow">
   </p>

<p align="center">
<img width="622" alt="image" src="https://github.com/raquelaq/OpenWeatherBroker/assets/117348659/63ea0928-0e53-4d31-837f-d8124d234cf6">
</p>

## General Information
This project was developed by Raquel Almeida Quesada for the "Desarrollo de Aplicaciones para la Ciencia de Datos" (DACD) course, a second-year subject in the Data Science and Engineering Degree at the University of Las Palmas de Gran Canaria (ULPGC), School of Computer Engineering (EII).

## Project Overview
The core objective of this project is to harness real-time data from two significant APIs—OpenWeatherMap and Xotelo (for TripAdvisor data)—to provide dynamic and predictive insights into hotel availability and weather conditions for various islands. The project is structured into several key modules, each responsible for distinct aspects of data handling and user interaction:

### Modules Description

- **TripAdvisorSensor Module:**
  - **Purpose**: This module interfaces directly with the Xotelo API to fetch real-time data on hotel availability based on specific keys and date ranges.
  - **Functionality**: It generates JSON events that encapsulate hotel data and then publishes these events to a designated ActiveMQ topic.

- **PredictionProvider Module:**
  - **Purpose**: To collect meteorological data from the OpenWeatherMap API, offering forecasts that assist in planning and decision-making for travelers.
  - **Functionality**: Similar to the TripAdvisorSensor, this module processes weather data into JSON format and dispatches it to another ActiveMQ topic created for weather events.

- **DataLakeBuilder Module:**
  - **Purpose**: Acts as a data aggregator that consolidates incoming data streams from both hotel and weather topics in ActiveMQ.
  - **Functionality**: It subscribes to both topics, extracts events, and organizes them into a structured DataLake, ensuring data is stored in an accessible and logical format. This module is pivotal for data analysis and long-term storage, facilitating efficient data retrieval for analytical processes.

- **Business-unit Module:**
  - **Purpose**: This is the front-end module that interacts directly with end-users, providing actionable insights and data visualizations.
  - **Functionality**: It fetches processed data from the DataLake, presenting weather forecasts and hotel options on a user-friendly interface. For islands like La Graciosa with no hotels, the system smartly notifies users of no availability, enhancing user experience by setting realistic expectations.

## Environment and Technologies
- **Development Environment**: IntelliJ IDEA
- **Programming Language**: Java 17, ensuring compatibility and performance.
- **Key APIs**: OpenWeatherMap for weather forecasts; Xotelo for TripAdvisor hotel data.
- **Message Broker**: ActiveMQ for handling data streams and ensuring that data flows seamlessly between modules.


## Resources
**Environment**
The project was developed in IntelliJ IDEA, utilizing Java 17 as the source and target compilation version (configured via maven.compiler.source and maven.compiler.target). It also integrates APIs from OpenWeatherMap and Xotelo.

## Class Diagram


### Additional Notes
Each module is designed to function seamlessly, ensuring that data flows smoothly from APIs to the end-user interface. The system is capable of handling concurrent data streams and updates the user interface in real-time with weather predictions and hotel availability. Error handling is robust, ensuring that any issues with data retrieval or processing are logged and managed appropriately.

### Future Enhancements
Future updates may include expanding the range of APIs used, enhancing the predictive capabilities of the system, and improving user interaction with more dynamic and responsive design elements in the GUI.
