package com.example.doenerwo.service;

import org.apache.tomcat.util.json.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
//import org.apache.log4j.Logger;

public class DoenerstandService {

    double x1 = 3;
    double y1 = 4;
    double x2 = 7;
    double y2 = 1;

    double distance = DistanceCalculator.calculateDistance(x1, y1, x2, y2);

    /*
    public final static Logger log = Logger.getLogger("OpenStreeMapUtils");

    private static DoenerstandService instance = null;
    private JSONParser jsonParser;

    public DoenerstandService() {
        //jsonParser = new JSONParser();
    }

    public static DoenerstandService getInstance() {
        if (instance == null) {
            instance = new DoenerstandService();
        }
        return instance;
    }

    private String getRequest(String url) throws Exception {

        final URL obj = new URL(url);
        final HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");

        if (con.getResponseCode() != 200) {
            return null;
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    public Map<String, Double> getCoordinates(String address) {
        Map<String, Double> res;
        StringBuffer query;
        String[] split = address.split(" ");
        String queryResult = null;

        query = new StringBuffer();
        res = new HashMap<String, Double>();

        query.append("http://nominatim.openstreetmap.org/search?q=");

        if (split.length == 0) {
            return null;
        }

        for (int i = 0; i < split.length; i++) {
            query.append(split[i]);
            if (i < (split.length - 1)) {
                query.append("+");
            }
        }
        query.append("&format=json&addressdetails=1");

        log.debug("Query:" + query);

        try {
            queryResult = getRequest(query.toString());
        } catch (Exception e) {
            log.error("Error when trying to get data with the following query " + query);
        }

        if (queryResult == null) {
            return null;
        }

        Object obj = JSONValue.parse(queryResult);
        log.debug("obj=" + obj);

        if (obj instanceof JSONArray) {
            JSONArray array = (JSONArray) obj;
            if (array.size() > 0) {
                JSONObject jsonObject = (JSONObject) array.get(0);

                String lon = (String) jsonObject.get("lon");
                String lat = (String) jsonObject.get("lat");
                log.debug("lon=" + lon);
                log.debug("lat=" + lat);
                res.put("lon", Double.parseDouble(lon));
                res.put("lat", Double.parseDouble(lat));

            }
        }

        return res;
    }

    public class GetCoordinates {

        static String address = "The White House, Washington DC";

        public static void main(String[] args) {
            Map<String, Double> coords;
            coords = DoenerstandService.getInstance().getCoordinates(address);
            System.out.println("latitude :" + coords.get("lat"));
            System.out.println("longitude:" + coords.get("lon"));
        }
    }

     */
}

