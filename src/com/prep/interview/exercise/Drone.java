package com.prep.interview.exercise;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Drone {

    private String droneId;
    private double latitude;
    private double longitude;
    private List<String> sensedObjects; // A list of objects detected in the zone
    private String externalSourceUrl;  // URL of the external source where data is sent


    // Method to simulate sensing objects in a zone
    public void senseObjectsInZone() {
        // Simulate sensing objects (this could be more complex in real-world applications)
        // Here we just randomly add some dummy objects
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
        // Simulate sending data to an external source
        // In a real-world scenario, we would send this over HTTP, for example using a library like HttpClient
        System.out.println("Sending sensed data from Drone ID: " + droneId);
        System.out.println("Data: " + sensedObjects);
        System.out.println("Data sent to: " + externalSourceUrl);
        // Reset the sensed objects after sending the data
        sensedObjects.clear();
    }

}