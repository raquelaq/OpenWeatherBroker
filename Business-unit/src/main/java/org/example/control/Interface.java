package org.example.control;

import org.example.model.Hotel;
import org.example.model.Weather;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Interface extends JFrame implements ActionListener{
    private Button b1, showHotelsButton;
    private JTextArea textArea;
    private JComboBox<String> tableComboBox;

    public Interface() {
        b1 = new Button("Show Weather Info");
        b1.addActionListener(this);
        showHotelsButton = new Button("Show Hotels");
        showHotelsButton.addActionListener(this);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        List<String> tableNames = getTableNames();
        String[] tableArray = tableNames.toArray(new String[0]);
        tableComboBox = new JComboBox<>(tableArray);

        JPanel panel = new JPanel(new GridLayout(1, 1));
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));
        buttonPanel.add(tableComboBox);
        buttonPanel.add(b1);
        buttonPanel.add(showHotelsButton);
        panel.add(buttonPanel);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(panel, BorderLayout.NORTH);
        this.getContentPane().add(scrollPane, BorderLayout.CENTER);

        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Island Information System");;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedIsland = (String) tableComboBox.getSelectedItem();
        if (e.getSource() == b1) {
            showWeatherInfo(selectedIsland);
        } else if (e.getSource() == showHotelsButton) {
            showHotelInfo(selectedIsland);
        }
    }

    private void showWeatherInfo(String islandName) {
        List<Weather> weatherList = new WeatherDataSelector().selectWeatherData(islandName);
        if (weatherList != null && !weatherList.isEmpty()) {
            StringBuilder info = new StringBuilder();
            for (Weather weather : weatherList) {
                info.append(weather.toString()).append("\n");
            }
            textArea.setText(info.toString());
        } else {
            textArea.setText("No weather data available for " + islandName);
        }
    }

    private void showHotelInfo(String islandName) {
        if ("La_Graciosa".equals(islandName)) {
            textArea.setText("Lo sentimos, no hay hoteles actualmente en La Graciosa.");
        } else {
            String tableName = islandName + "_hotels"; // Asume que el nombre de la tabla es el nombre de la isla seguido de '_hotels'
            List<Hotel> hotelList = new HotelDataSelector().selectHotelData(tableName);
            if (hotelList != null && !hotelList.isEmpty()) {
                StringBuilder info = new StringBuilder();
                for (Hotel hotel : hotelList) {
                    info.append("Name: ").append(hotel.getName())
                            .append(", Rating: ").append(hotel.getRating())
                            .append(", Location: ").append(hotel.getLocation())
                            .append(", Check-in: ").append(hotel.getCheckInDate())
                            .append(", Check-out: ").append(hotel.getCheckOutDate())
                            .append("\n");
                }
                textArea.setText(info.toString());
            } else {
                textArea.setText("No hay datos de hoteles disponibles para " + islandName);
            }
        }
    }

    private List<String> getTableNames() {
        return List.of("Gran_Canaria", "Tenerife", "Fuerteventura", "Lanzarote", "La_Palma", "El_Hierro", "La_Gomera", "La_Graciosa");
    }
}
