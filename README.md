<h1 align="center"> OpenWeatherMap and TripAdvisor Sensor </h1>

<p align="left">

   <img src="https://img.shields.io/badge/STATUS-COMPLETE-green">
   <img src="https://img.shields.io/badge/Released-June%202024-yellow">
   </p>

## Información General
Para mi proyecto, he usado la api de Xotelo que obtiene datos de hoteles de TripAdvisor. Se le pasa a la API una HotelKey y la fecha de entrada y de salida a dicho hotel, y la API devuelve los hoteles que estará disponibles durante esas fechas. Lo que he hecho ha sido seleccionar un numero de hoteles para cada isla, entre los que la API podrá seleccionar su disponibilidad. Los módulos son:

- **Módulo TripAdvisorSensor**: la funcionalidad de este módulo es obtener los datos de la API de Xotelo. Se crea un nuevo tópico en ActiveMQ al que se mandarán los eventos JSON creados.
- **Módulo PredictionProvider**: módulo de la práctica anterior, donde se obtiene los eventos json de la api de OpenWeatherMap. Se crea un tópico al que se mandarán los eventos JSON.
- **Módulo DataLakeBuilder**: consumer que obtendrá los eventos subidos al broker en ambos tópicos y creará un datalake con un directorio específico para cada tipo de evento
- **Módulo Business-unit**: módulo que consume los datos de los tópicos y genera una interfaz que proporciona al usuario datos meteorológicos de la isla a la que quiere ir, además de recomendarle hoteles a los que puede alojarse.
