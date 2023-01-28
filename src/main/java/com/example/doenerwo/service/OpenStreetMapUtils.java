package com.example.doenerwo.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class OpenStreetMapUtils {
    //public final static Logger log = Logger.getLogger("OpenStreeMapUtils");

    private static OpenStreetMapUtils instance = null;
    private JSONParser jsonParser;

    public OpenStreetMapUtils() {
        jsonParser = new JSONParser();
    }

    public static OpenStreetMapUtils getInstance() {
        if (instance == null) {
            instance = new OpenStreetMapUtils();
        }
        return instance;
    }

    private String getRequest(String url) throws Exception {

        final URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //--

        String responsee;


        con.setRequestMethod("GET");
        con.setConnectTimeout(10000);
        con.setReadTimeout(10000);
        con.setUseCaches(false);
        con.setAllowUserInteraction(false);
        con.setInstanceFollowRedirects(true);
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        int responceCode = con.getResponseCode();

        if (responceCode == 301) {
            String location = con.getHeaderField("Location");
            URL newUrl = new URL(location);
            con = (HttpURLConnection) newUrl.openConnection();
        }

        if (responceCode == HttpURLConnection.HTTP_OK)
        {
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            while ((line = br.readLine()) != null)
            {
                responsee = "";// String variable declared global
                responsee += line;
                //Log.i("response_line", response);
            }
        }
        else
        {
            responsee = "";
        }

        //--


        if (con.getResponseCode() != 200) {
            return null;
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        //if (status == HttpURLConnection.HTTP_MOVED_TEMP
         //       || status == HttpURLConnection.HTTP_MOVED_PERM) {


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

        //log.debug("Query:" + query);

        WebClient webClient = WebClient.create(query.toString());

        Mono<String> body = webClient.get().retrieve().bodyToMono(String.class);

        String s = body.block();

        /*
        boolean statuss = s.matches(String.valueOf(301));
        String status = "";
        if (statuss == true)
        {
            status = "301";
        }
/*
        //if (status == HttpURLConnection.HTTP_MOVED_TEMP
        //        || status == HttpURLConnection.HTTP_MOVED_PERM) {
        /*
        if (status == "301") {
                String location = con.getHeaderField("Location");
                URL newUrl = new URL(location);
                con = (HttpURLConnection) newUrl.openConnection();
            }


         */



        try {
            queryResult = getRequest(query.toString());
        } catch (Exception e) {
            //log.error("Error when trying to get data with the following query " + query);
        }

        if (queryResult == null) {
            return null;
        }

        Object obj = JSONValue.parse(queryResult);
        //log.debug("obj=" + obj);

        if (obj instanceof JSONArray) {
            JSONArray array = (JSONArray) obj;
            if (array.size() > 0) {
                JSONObject jsonObject = (JSONObject) array.get(0);

                String lon = (String) jsonObject.get("lon");
                String lat = (String) jsonObject.get("lat");
                //log.debug("lon=" + lon);
                //log.debug("lat=" + lat);
                res.put("lon", Double.parseDouble(lon));
                res.put("lat", Double.parseDouble(lat));

            }
        }

        return res;
    }
}
