package com.example.comp2000_referral_work_10909750.api;

import com.android.volley.Request;
// This will allow me to send requests
import com.android.volley.RequestQueue;
// This will let me make a queue
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
// Gson is going to be used to convert to and from JSON
import com.google.gson.reflect.TypeToken;
// This should keep generic information when converting
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import android.util.Log;
// This will let me log errors that may occur
import java.lang.reflect.Type;
import java.util.List;

public class api {
    private static String main_url = "http://10.240.72.69/comp2000/";
    private static Gson gson = new Gson();
    // This will let me convert to and from JSON due to the api storing the data in JSON
    private static RequestQueue queue;
    // This will allow me to make a queue
    // Only one request will happen at a time :)

    private static void start_queue(Context context) {
        if (queue == null) {
            queue = Volley.newRequestQueue(context.getApplicationContext());
        }
    }
    // This will allow me to start the queue

    public static void get_all_users(Context context) {
        start_queue(context);
        // Calling the function here will let me add requests to the queue later
        String url = main_url + "/read_all_users/10909750";
        // This is the endpoint in the api to read all users stored in the api

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray json_response) {

                        Type list = new TypeToken<List<user>>() {}.getType();
                        List<user> users = gson.fromJson(json_response.toString(), list);
                        // This will convert the Json file from the api to a Java object

                        for (user user : users) {
                            Log.d("User details\n",
                                    "First name: " + user.getFirstname() +
                                    "\nLast name: " + user.getLastname() +
                                    "\nEmail: " + user.getEmail() +
                                    "\nPhone: " + user.getPhone());
                        }
                        // This will log the name, email, and phone number of each user

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volley_error) {
                        Log.e("User error\n","Error getting user details: " + volley_error.getMessage());
                    }
                    // This will tell me the error that is occuring
                }


        );

        queue.add(request);

    }

    public static void create_user(Context context, user user) {
        start_queue(context);
        String url = main_url + "/create_user/10909750";

        try {


            JSONObject json_request = new JSONObject(gson.toJson(user));
            // Originally wasn't a try-catch, but was changed due to an error with JSONObject
            // It was needed to add a JSONException in the case of an invalid JSON format
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, json_request,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {

                        }
                    }
            );
            queue.add(request);

        } catch (JSONException e) {
            Log.e("Format error", "Invalid format: " + e.getMessage());
        }
        // This was needed to handle incorrect formatting
    }

    public static void update_user(Context context, user current_user) {
        start_queue(context);
        String url = main_url + "/update_user/10909750/" + current_user;
        // Because you can only change your own account details, i am making it so that the username of the user is appended to the end of the string

    }



}
