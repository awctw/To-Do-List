package main.network;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;

public class ReadWebPage {
    private static BufferedReader br;
    private static String vancouverWeatherQuery;
    private static StringBuilder sb;
    private static String line;
    private static String weatherNotification;

    public ReadWebPage() {
        br = null;
        vancouverWeatherQuery = "https://api.openweathermap.org/data/2.5/weather?q=Vancouver,ca&APPID=6a285de238e9239060a976dd3df90d1a";
        sb = new StringBuilder();
        readWebPage();
        writeToFile();
    }

    // REQUIRES: a working url
    // EFFECTS: gets the information from the weather web page
    // MODIFIES: sb
    public void readWebPage() {
        try {
            URL url = new URL(vancouverWeatherQuery);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }

        } catch (IOException e) {
            System.out.println("Cannot find web page");
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.out.println("Cannot find web page");
            }
        }


    }

    // EFFECTS: writes the api data from the given web page into a file
    // MODIFIES: data/JSONFile.json
    public void writeToFile() {
        try (FileWriter file = new FileWriter("data/JSONFile.json")) {
            file.write(String.valueOf(sb));
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: parses the json object into a string that is printed out to console
    public static void parseWeather() {
        JSONParser parser = new JSONParser();

        
            Object object = null;
			try {
				object = parser.parse(new FileReader("data/JSONFile.json"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            JSONObject jsonObject = (JSONObject) object;

            JSONArray weather = (JSONArray) jsonObject.get("weather");

            if (weather == null) {
                return;
            }
            for (Object o : weather) {
                JSONObject jsonObject1 = (JSONObject) o;
                String description = (String) jsonObject1.get("description");
             //   System.out.println("Weather today: " + description);
                weatherNotification = description;
            }
       
    }

    public static String getWeatherNotification() {
        return weatherNotification;
    }


}
