package com.prep.interview.exercise;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DroneWithAPICalls {

    private String droneId;
    private double latitude;
    private double longitude;
    private List<String> sensedObjects = new ArrayList<>(); // Initialize the list
    private String externalSourceUrl;  // URL of the external source where data is sent

    // Method to simulate sensing objects in a zone
    public void senseObjectsInZone() {
        // Simulate sensing objects (this could be more complex in real-world applications)
        sensedObjects.add("Object1");
        sensedObjects.add("Object2");
        sensedObjects.add("Object3");
        // You could integrate actual sensing logic or data from sensors here
    }

    // Method to retrieve sensed data
    public List<String> retrieveSensedData() {
        return new ArrayList<>(sensedObjects); // Return a copy of the sensed data
    }

    // Method to send the sensed data to an external source (e.g., API or remote server)
    public void sendSensedData() {
        // Convert sensed objects to a JSON-like format (you could use a library like Jackson or Gson for real JSON)
        String jsonData = "{ \"droneId\": \"" + droneId + "\", \"sensedObjects\": " + sensedObjects + " }";

        try {
            // Prepare the URL and establish a connection to the API
            URL url = new URL(externalSourceUrl); // Assume the external API accepts POST requests
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            // Send the request body (the JSON data)
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonData.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Check the response code
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Successfully sent the data
                System.out.println("Data sent successfully.");
            } else {
                System.err.println("Failed to send data.");
            }

            // Reset the sensed objects after sending the data
            sensedObjects.clear();
        } catch (Exception e) {
            System.err.println("Error sending data: " + e.getMessage());
        }
    }

    // Method to retrieve data from an external source (API)
    public String retrieveDataFromExternalSource() {
        try {
            // Prepare the URL and establish a connection to the API
            URL url = new URL(externalSourceUrl); // Assuming the external source provides data through a GET request
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            // Get the response from the API
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the response
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();  // Return the response as a string
            } else {
                System.err.println("Failed to retrieve data.");
                return null;
            }
        } catch (Exception e) {
            System.err.println("Error retrieving data: " + e.getMessage());
            return null;
        }
    }
}