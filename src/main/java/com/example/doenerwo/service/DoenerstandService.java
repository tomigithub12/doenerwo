package com.example.doenerwo.service;

import com.example.doenerwo.domain.Doenerstand;
import com.example.doenerwo.repository.DoenerstandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
//import org.apache.log4j.Logger;

@Service
public class DoenerstandService {


    @Autowired
    OpenStreetMapUtils myCoordinats = new OpenStreetMapUtils();
    @Autowired
    private DoenerstandRepository repo;


    public List findStandl() {
        //System.out.println("debug1");
        return repo.findAll();
    }

    public void printFindings(){

        //System.out.println("debug2");
        List<Doenerstand> listeStandln=new ArrayList<Doenerstand>();

        //System.out.println("debug3");
        listeStandln = (List<Doenerstand>) this.findStandl();

        //System.out.println("debug4");
        for(Doenerstand liste:listeStandln)
            System.out.println(liste);
    }

    public String calculateFindings(String myAdress) {

        Map<String, Double> myCoordinatsMap = new HashMap<>();

        myCoordinatsMap = myCoordinats.getInstance().getCoordinates(myAdress);

        System.out.println("Wo Döner?");

        List<Double> Results = new ArrayList<>();

        double distance = 0;
        double myLatitude = myCoordinatsMap.get("lon");
        double myLongitude = myCoordinatsMap.get("lat");
        double doenerstandLongitude = 0;
        double doenerstandLatitude = 0;
        String Adress = "";
        String Name = "";
        Map<Double, String> mapOfDoenerstande = new HashMap<>();

        //System.out.println("debug2");
        List<Doenerstand> listeStandln = new ArrayList<Doenerstand>();

        //System.out.println("debug3");
        listeStandln = (List<Doenerstand>) this.findStandl();

        System.out.println("Berechne....");

        //System.out.println("debug4");
        for (Doenerstand liste : listeStandln) {

            Adress = liste.getAdress();
            Name = liste.getName();
            doenerstandLongitude = Double.parseDouble(liste.getLongitude());
            doenerstandLatitude = Double.parseDouble(liste.getLatitude());

            distance = DistanceCalculator.calculateDistance(myLatitude,myLongitude,doenerstandLongitude,doenerstandLatitude);
            //System.out.println(distance);

            mapOfDoenerstande.put(distance,liste.getAdress());

            Results.add(distance);
        }

        System.out.println("Hier Döner:");

        Collections.sort(Results);
        for(Double nearest:Results){
            System.out.println(nearest);
            System.out.println(mapOfDoenerstande.get(nearest));
            Adress = mapOfDoenerstande.get(nearest);
            break;
        }

        return Adress;
    }
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

