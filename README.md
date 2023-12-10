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
