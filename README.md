<h1 align="center"> OpenWeatherMap and TripAdvisor Sensor </h1>

<p align="left">

   <img src="https://img.shields.io/badge/STATUS-COMPLETE-green">
   <img src="https://img.shields.io/badge/Released-June%202024-yellow">
   </p>

## General Information
This project was developed by Raquel Almeida Quesada for the "Development of Applications for Data Science" (DACD) course, a second-year subject in the Data Science and Engineering Degree at the University of Las Palmas de Gran Canaria (ULPGC), School of Computer Engineering (EII).

## The Project
For my project, I have utilized Xotelo's API, which fetches hotel data from TripAdvisor. This API accepts a `HotelKey` along with check-in and check-out dates, returning data about available hotels during those dates. I have selected a number of hotels for each island, among which the API will check availability. The modules include:

- **TripAdvisorSensor Module**: The functionality of this module is to obtain data from Xotelo's API. A new topic is created in ActiveMQ where JSON events generated will be sent.
- **PredictionProvider Module**: A module from a previous practice, which retrieves JSON events from the OpenWeatherMap API. A topic is created to which JSON events will be sent.
- **DataLakeBuilder Module**: A consumer that retrieves events posted to the broker in both topics and creates a datalake with a specific directory for each type of event.
- **Business-unit Module**: A module that consumes data from the topics and generates an interface providing the user with weather data for the island they are interested in, in addition to recommending hotels for accommodation.

## Resources
**Environment**
The project was developed in IntelliJ IDEA, utilizing Java 17 as the source and target compilation version (configured via maven.compiler.source and maven.compiler.target). It also integrates APIs from OpenWeatherMap and Xotelo.

## Class Diagram


### Additional Notes
Each module is designed to function seamlessly, ensuring that data flows smoothly from APIs to the end-user interface. The system is capable of handling concurrent data streams and updates the user interface in real-time with weather predictions and hotel availability. Error handling is robust, ensuring that any issues with data retrieval or processing are logged and managed appropriately.

### Future Enhancements
Future updates may include expanding the range of APIs used, enhancing the predictive capabilities of the system, and improving user interaction with more dynamic and responsive design elements in the GUI.
