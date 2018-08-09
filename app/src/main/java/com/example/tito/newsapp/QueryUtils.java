package com.example.tito.newsapp;

import android.text.TextUtils;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    private static final int READ_TIME_OUT = 10000;

    private static final int CONNECTION_TIME_OUT = 15000;

    private static final int OK_HTTP_CHECK = 200;

    public static List<News> fetchNewsData(String requestedUrl) {
        URL url = createUrl(requestedUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        }catch (IOException e) {
            Log.e(LOG_TAG, "fetchNewsData: problem with the HttpRequest", e);
        }
        List<News> recentNews = extractNewsFromJson(jsonResponse);
        return recentNews;
    }
    private static List<News> extractNewsFromJson(String jsonResponse) {

        if (TextUtils.isEmpty(jsonResponse)){
            return null;
        }
        List<News> recentNews = new ArrayList<>();
        try {
            JSONObject baseJsonResponse = new JSONObject(jsonResponse);

            String response = baseJsonResponse.getString("response");

            JSONObject object = new JSONObject(response);

            JSONArray currentNewsArticle=object.getJSONArray("results");

            for (int i = 0; i < currentNewsArticle.length(); i++) {

                JSONObject currentNews = currentNewsArticle.getJSONObject(i);
                String title = currentNews.optString("webTitle");
                String section = currentNews.optString("sectionName");
                String  date = currentNews.optString("webPublicationDate");
                String url = currentNews.optString("webUrl");
                JSONArray tags = currentNews.getJSONArray("tags");
                String author = "";
                if (tags.length() != 0) {
                    author = tags.getJSONObject(0).optString("webTitle");
                }
                News nNews = new News(title, section, date, author, url);

                recentNews.add(nNews);
            }

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the news JSON results", e);
        }
        return recentNews;
    }
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(READ_TIME_OUT);
            urlConnection.setConnectTimeout(CONNECTION_TIME_OUT);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

           if(urlConnection.getResponseCode() == OK_HTTP_CHECK) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }else {
                Log.e(LOG_TAG, "problem with the code" + urlConnection.getResponseCode());
            }
        }catch (IOException ioe) {
            Log.e(LOG_TAG, "makeHttpRequest:  problem with JSON results ", ioe);
        }finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
    private static URL createUrl(String requestedUrl) {
        URL url = null;
            try {
                url = new URL(requestedUrl);
            } catch (MalformedURLException e) {
                Log.e(LOG_TAG, "createUrl: problem with creating the url", e);
            }
        return url;
    }
}