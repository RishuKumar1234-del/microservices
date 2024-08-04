package com.Moch.programs;

import java.util.HashMap;
import java.util.Map;

public class UndergroundSystem {

   
    private Map<Integer, CheckInData> checkInMap;
    
    
    private Map<String, RouteData> routeMap;

    
    public UndergroundSystem() {
        checkInMap = new HashMap<>();
        routeMap = new HashMap<>();
    }

   
    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new CheckInData(stationName, t));
    }


    public void checkOut(int id, String stationName, int t) {
        CheckInData checkInData = checkInMap.get(id);
        if (checkInData == null) return; 

        String startStation = checkInData.stationName;
        int checkInTime = checkInData.time;
        
     
        int travelTime = t - checkInTime;
        
     
        String routeKey = startStation + "->" + stationName;
        
       
        RouteData routeData = routeMap.getOrDefault(routeKey, new RouteData());
        routeData.totalTime += travelTime;
        routeData.count++;
        routeMap.put(routeKey, routeData);
        
       
        checkInMap.remove(id);
    }

   
    public double getAverageTime(String startStation, String endStation) {
        String routeKey = startStation + "->" + endStation;
        RouteData routeData = routeMap.get(routeKey);
        
        if (routeData == null || routeData.count == 0) {
            throw new IllegalArgumentException("No data available for the given route.");
        }
        
        return (double) routeData.totalTime / routeData.count;
    }

   
    private static class CheckInData {
        String stationName;
        int time;

        CheckInData(String stationName, int time) {
            this.stationName = stationName;
            this.time = time;
        }
    }

    
    private static class RouteData {
        int totalTime;
        int count;

        RouteData() {
            this.totalTime = 0;
            this.count = 0;
        }
    }

    public static void main(String[] args) {
        UndergroundSystem system = new UndergroundSystem();
        system.checkIn(10, "A", 3);
        system.checkOut(10, "B", 8);
        System.out.println(system.getAverageTime("A", "B")); 
        
        system.checkIn(5, "A", 10);
        system.checkOut(5, "B", 20);
        System.out.println(system.getAverageTime("A", "B"));
        
        system.checkIn(15, "A", 24);
        system.checkOut(15, "B", 38);
        System.out.println(system.getAverageTime("A", "B")); 
    }
}
