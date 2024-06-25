package org.example.control;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.example.model.Weather;

import java.util.List;

import static spark.Spark.get;

public class WebService {
    public void start() {
        get("/island/:island", (req, res) -> response(req.params(":island")));
    }

    private String response(String island) {
        JsonArray jsonArray = new JsonArray();
        List<Weather> list = new SqliteDataMart().selectWeatherData(island);
        for (Weather weather : list) {
            JsonObject json = new JsonParser().parse(weather.buildJson()).getAsJsonObject();
            jsonArray.add(json);   
        }
        return checkIsland(jsonArray);
    }
    
    private String checkIsland(JsonArray jsonArray) {
        if (!jsonArray.isEmpty()) {
            return jsonArray.toString();
        } else {
            return "Available islands: " +
                    "\n- Gran Canaria" +
                    "\n- Tenerife" +
                    "\n- Fuerteventura" +
                    "\n- Lanzarote" +
                    "\n- La Palma" +
                    "\n- La Gomera" +
                    "\n- El Hierro" +
                    "\n- La Graciosa";
        }
    }
}
