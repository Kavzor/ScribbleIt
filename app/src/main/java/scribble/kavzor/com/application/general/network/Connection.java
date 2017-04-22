package scribble.kavzor.com.application.general.network;

import com.google.gson.stream.JsonReader;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


import scribble.kavzor.com.application.general.pojo.model.Pair;

/**
 * Created by root on 2017-03-18.
 */
public class Connection {

    public JsonReader getReader(String adress, ArrayList<Pair<String, String>> params) throws IOException{
        URL url = new URL(adress);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        OutputStream out = connection.getOutputStream();

        String encode = "UTF-8";
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, encode));
        writer.write(getQuery(params));

        writer.flush();
        writer.close();
        out.close();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        JsonReader jsonReader = new JsonReader(reader);
        jsonReader.setLenient(true);

        return jsonReader;
    }

    private static String getQuery(ArrayList<Pair<String, String>> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (Pair pair : params) {
            if (first) {
                first = false;
            }
            else {
                result.append("&");
            }

            result.append((String) pair.getKey());
            result.append("=");
            result.append((String) pair.getValue());
        }
        return result.toString();
    }
}
