package com.github.artyitis.javaredditwrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.github.artyitis.javaredditwrapper.subreddit.Rule;
import com.github.artyitis.javaredditwrapper.subreddit.SubReddit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import okhttp3.Credentials;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Reddit {
    private static String GRANT_URL = "https://oauth.reddit.com/grants/installed_client";
    private static String ACCESS_TOKEN_URL = "https://ssl.reddit.com/api/v1/access_token";
    private static String DEVICE_ID = "DO_NOT_TRACK_THIS_DEVICE";

    private String URL_PREFIX = "https://oauth.reddit.com/";

    private String access_token;
    private OkHttpClient httpClient;

    public Reddit() throws ParseException, IOException {
        httpClient = new OkHttpClient().newBuilder().connectTimeout(180, TimeUnit.SECONDS)
                .readTimeout(210, TimeUnit.SECONDS).build();
        access_token = retrieveAccessToken();
    }

    private String retrieveAccessToken() throws ParseException, IOException {
        /*
         * HTTP Basic Auth The "user" is the client_id, the "password" is the
         * client_secret
         */
        String credentials = Credentials.basic(Secrets.REDDIT_CLIENT_ID, Secrets.REDDIT_SECRET);
        // include extra information in post data
        RequestBody requestBody = new FormBody.Builder().add("grant_type", GRANT_URL).add("device_id", DEVICE_ID)
                .build();
        // build request
        Request request = new Request.Builder().url(ACCESS_TOKEN_URL).addHeader("Authorization", credentials)
                .post(requestBody).build();
        // execute request and get response
        Response response = httpClient.newCall(request).execute();
        // get access_token from response
        final JSONParser parser = new JSONParser();
        JSONObject jsonResponse = (JSONObject) parser.parse(response.body().string());
        return jsonResponse.get("access_token").toString();
    }

    public void close() {
        httpClient.connectionPool().evictAll();
    }

    public JSONObject get(String url) {
        Request request = new Request.Builder().url(url).addHeader("Authorization", "bearer " + access_token).build();
        try {
            Response response = httpClient.newCall(request).execute();
            final JSONParser parser = new JSONParser();
            JSONObject jsonResponse = (JSONObject) parser.parse(response.body().string());
            return jsonResponse;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            // todo
            return null;
        }
    }

    public List<RedditPost> getListing(String subredditName, Listings listings, int limit) {
        int count = 0;
        int found;
        int missing = limit;
        String after = "";
        JSONObject listingJSON;
        JSONArray children;
        JSONArray childrenCollection = new JSONArray();
        String url = URL_PREFIX + "r/" + subredditName + "/" + listings + ".json?limit=";
        while (count < limit) {
            listingJSON = (JSONObject) get(url + missing + "&after=" + after).get("data");
            found = ((Number) listingJSON.get("dist")).intValue();
            count = count + found;
            missing = missing - found;
            after = (String) listingJSON.get("after");
            children = (JSONArray) listingJSON.get("children");
            childrenCollection.addAll(children);
        }
        List<RedditPost> posts = new ArrayList<>();
        JSONObject redditPostJSON;
        for (Object childObject : childrenCollection) {
            redditPostJSON = (JSONObject) childObject;
            posts.add(new RedditPost(redditPostJSON));
        }
        return posts;
    }

    public SubReddit getSubReddit(String display_name) {
        String url = URL_PREFIX + "r/" + display_name + "/" + "about.json";
        JSONObject subRedditJSON = get(url);
        return new SubReddit(subRedditJSON);
    }

    public List<Rule> getSubRedditRules(String display_name) {
        String url = URL_PREFIX + "r/" + display_name + "/about/rules.json";
        JSONArray rulesJSON = (JSONArray) get(url).get("rules");
        List<Rule> rules = new ArrayList<>();
        JSONObject ruleJSON;
        for (Object ruleObject : rulesJSON) {
            ruleJSON = (JSONObject) ruleObject;
            rules.add(new Rule(ruleJSON));
        }
        return rules;
    }

}
