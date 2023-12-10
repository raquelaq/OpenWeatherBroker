<h1 align="center"> OpenWeatherMap Broker </h1>

<p align="left">

   <img src="https://img.shields.io/badge/STATUS-DONE-green">
   <img src="https://img.shields.io/badge/Released-November%202023-yellow">
   </p>

![OIG](https://github.com/raquelaq/OpenWeatherBroker/assets/117348659/4cbe09d6-ab3a-47fc-a615-387644cb1d2c) 

## General Information
This project has been created by Raquel Almeida Quesada for "Desarrollo de Aplicaciones para la Ciencia de Datos" (DACD), a second-year subject offered in the Data Science and Engineering Degree at the University of Las Palmas de Gran Canaria (ULPGC), School of Computer Engineering (EII).

## The Project
The main objective of this program is to implement the Publisher/Subscriber pattern, creating two modules: PredictionProvider and EventStoreBuilder, and using for the Broker ActiveMQ.

The goal of the PredictionProvider module is to obtain every 6 hours weather data for the chosen locations. It generates a JSON event for each prediction, based on the data got from the OpenWeatherMap service.

I worked specifically with coordinates of the Canary Island, using one set of coordinates (latitude, longitude) per island.

I created a topic named **prediction.Weather** in ActiveMQ for storing the messages in the Broker.

The EventStoreBuilder module aims to systematically and temporally store the consumed events from the broker in an specific directory created by us.

## Structure
The code is structured into two main modules: PredictionProvider and EventStoreBuilder, each encompassing their respective classes, packages, poms and functionalities.

### PredictionProvider Module
This module is focused on getting data from the API and managing weather data. It also sends messages to ActiveMQ

**Model Package**

This package serves as a repository for classes that define the data structure. These classes encapsulate the logic and data embodying crucial concepts within the application domain.

- **Weather Class**:  this class represents the variables obtained from the API, getting only those pertinent to the program's objectives, such as temperature, humidity, wind speed, etc. It includes constructors and getter methods.
- **Location Class**: represents geographical coordinates, including the name of the place associated with the coordinates, and the place's latitude and longitude

**Control Package**

This package comprises a collection of Java classes responsible for executing the control and logic of the application. These classes are specifically crafted to orchestrate the interaction among the data model, business logic, and the broker.

- **Controller**: is responsible for the execution of the application.
- **Main**: houses the principal method ```main()``` that initiates the application. It creates an instance of ```Controller``` and calls the method ```execute()``` to start running the code.
- **MyTimerTask**: extends the ```TimerTask``` class and executes periodic tasks. Each time it runs, it manages weather data and sends events through a Publisher. With this class, the code is set to run every six hours.
- **OpenWeatherMapProvider**: it provides methods to obtain weather data from OpenWeatherMap, build objects of type Weather, and creates a map of locations.
- **Publisher**: implements the Runnable class and is responsible for publishing weather events using ActiveMQ.
- **Response Builder**: builds the response for an HTTP request to the OpenWeatherMap API.
- **WeatherDataExtractor**: it extracts specific data from a JsonObject related to meteorological conditions.
- **WeatherManager**: it coordinates the retrieval and processing of weather data using ```OpenWeatherMapProvider```.

⚠️**You must insert your "API KEY" into the variable ```API_KEY``` to do the API call**⚠️
<p align="center">
<img width="354" alt="image" src="https://github.com/raquelaq/OpenWeatherBroker/assets/117348659/121881fb-0d1c-4869-9097-fd5dcd02a457">
</p>

### EventStoreBuilder Module
It manages and writes weather events in an event storage system

- **Controller**: is responsible for the execution of the application.
- **Main**: houses the principal method ```main()``` that initiates the application. It creates an instance of ```Controller``` and calls the method ```execute()``` to start running the code.
- **Date Provider**: gives the actual date.
- **Directory Creator**: it creates the directory where we will store weather events.
- **File Administrator**: it manages the content writing in the file
- **Path Provider**: it provides the directory path where the weather events will be stored.
- **SubscriberWriter**: it implements the ```Runnable``` class and is responsible for subscribing to weather events and writing them in a file.

To check the functioning of the program, you must run first the EventStoreBuilder main, and then the PredictionProvider main.

## Resources
#### Enviroment
The proyect has been created in IntelliJ IDEA, and uses Java 17 as the source and target compilation version (**maven.compiler.source** and **maven.compiler.target**)
#### Documentation Tools
Here are some webpages I used for the development of my code:
- https://lineadecodigo.com/java/crear-un-fichero-en-java/
- https://decodigo.com/java-crear-directorio-o-carpeta
- https://activemq.apache.org/getting-started
- https://examples.javacodegeeks.com/java-development/enterprise-java/jms/apache-activemq-hello-world-example/

### Design Principles
In my code, you can tell some of the SOLID principles:
- **Open/Closed Princple**: classes like Weather can be extended (e.g., by adding new attributes) without modifying their existing code. The WeatherDataExtractor class can also be extended to handle new types of weather data.
- **Singleton Pattern**: The connection to ActiveMQ in SubscriberWriter is established using ActiveMQConnectionFactory, which is typically implemented as a Singleton to ensure a single instance of the connection factory.

## Class Diagram
Here you have a class diagram of my code:
![diagrama](https://github.com/raquelaq/OpenWeatherBroker/assets/117348659/abf33ab8-9206-4327-ae2d-2df8ffc89bc7)

