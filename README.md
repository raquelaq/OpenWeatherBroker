<h1 align="center"> OpenWeatherMap and SkyScanner Sensor </h1>

<p align="left">

   <img src="https://img.shields.io/badge/STATUS-INCOMPLETE-orange">
   <img src="https://img.shields.io/badge/Released-January%202024-yellow">
   </p>

## Información General
Para mi proyecto, he decidido hacer la planificación de viajes inteligente obteniendo datos meterológicos de la API de OpenWeather y datos de vuelo de la API de SkyScanner. Daría a los usuarios la posibilidad de escoger su vuelo a la isla canaria que prefiera, y luego darles predicciones meterológicas de cómo estará el tiempo durante su estancia en las islas. 

El proyecto está por acabar.

Lo que queda por hacer, sería:

- **Módulo FlightInsightSenso**r: queda seleccionar los datos que me interesan de los eventos Json que me da la API para crear eventos Json nuevos con los que pueda trabajar en el business-unit.
- **Módulo Business-unit**: (cambiar el nombre). Una vez estén los eventos Json de vuelo creados y modificados según mis necesidades, quedaría combinar los datos de estos eventos y los eventos de predicciones meteoróligas para tener un datamart en condiciones, y así poder explotarlos para mi unidad de negocio.
- **Interfaz**: una vez pueda explotar el modelo de negocio, haría una interfaz (todavía no se de qué tipo) en la que el usuario pueda reservar su vuelo y, luego, darle la predicción meteorológica de la isla en la que haya elegido pasar su estancia.

Además, quedan corregir pequeños detalles de código como utilizar los argumentos del main, limpieza, etc, además de actualizar los releases.





