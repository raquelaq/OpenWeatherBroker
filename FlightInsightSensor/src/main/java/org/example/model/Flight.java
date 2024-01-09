package org.example.model;

import java.util.Date;

public class Flight {
    private String origin;
    private String destination;

}

/*
departureDateTime y arrivalDateTime: Fechas y horas de salida y llegada, respectivamente. Estos datos son fundamentales para la planificación del tiempo de viaje y la coordinación con las predicciones meteorológicas.

durationInMinutes: La duración del vuelo en minutos. Es útil para los usuarios al evaluar la duración del viaje.

stopCount: La cantidad de paradas en el itinerario. Esto puede afectar la conveniencia y el tiempo total de viaje.

price: El precio del itinerario, que generalmente es un factor decisivo para los viajeros. Asegúrate de incluir la cantidad y la moneda.

deepLink: Un enlace profundo proporcionado para realizar la reserva directamente. Es valioso para la conversión en el proceso de reserva.

legIds y segmentIds: Estos identificadores pueden ser útiles para relacionar diferentes partes de un viaje y ofrecer recomendaciones personalizadas.

 */