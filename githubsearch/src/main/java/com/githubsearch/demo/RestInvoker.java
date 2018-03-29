package com.githubsearch.demo;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


 
public class RestInvoker {
    private final String baseUrl;
   
 
    public RestInvoker(String baseUrl) {
        this.baseUrl = baseUrl;
        
    }
 
    public String getRESTResponse(String accountId){
       return getDataFromServer("account/" + accountId);
    }
 
    String getDataFromServer(String path) {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(baseUrl + path);
            URLConnection urlConnection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close(); 
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }      
    }
 
      
}