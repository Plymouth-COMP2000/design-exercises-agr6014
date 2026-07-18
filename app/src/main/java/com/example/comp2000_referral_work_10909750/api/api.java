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
    private static String main_url = "http://10.240.72.69/comp2000";
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

    public  interface callback_user {
        void success(List<user> users);
        void error(String message);
    }
    // Due to volley making requests in async, I modified the code to use callbacks
    ///  This seems to only work for the get request :(

    public interface message_callback {
        void success(String message);
        void error(String message);
    }
    // Due to not using a list, this will be able to do callbacks for the POST and PUT methods


    public static void get_all_users(Context context, callback_user callback) {
        start_queue(context);
        // Calling the function here will let me add requests to the queue later
        String url = main_url + "/read_all_users/10909750";
        // This is the endpoint in the api to read all users stored in the api

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject json_response) {

                        try {

                            JSONArray users_array = json_response.getJSONArray("users");

                            Type list = new TypeToken<List<user>>() {
                            }.getType();
                            List<user> users = gson.fromJson(users_array.toString(), list);
                            // This will convert the Json file from the api to a Java object

                            for (user user : users) {
                                Log.d("User details\n",
                                        "First name: " + user.getFirstname() +
                                                "\nLast name: " + user.getLastname() +
                                                "\nEmail: " + user.getEmail() +
                                                "\nPhone: " + user.getContact());
                            }
                            // This will log the name, email, and phone number of each user

                            callback.success(users);
                            // if successful, a callback will be made

                        } catch (JSONException e) {
                            Log.e("Unable to read", "Unable to read users: " + e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volley_error) {
                        Log.e("User error\n","Error getting user details: " + volley_error.getMessage());

                        callback.error("Error getting the users");
                    }
                    // This will tell me the error that is occuring
                }


        );

        queue.add(request);

    }

    public static void create_user(Context context, user user, message_callback callback) {
        start_queue(context);
        String url = main_url + "/create_user/10909750";

        try {


            JSONObject json_request = new JSONObject(gson.toJson(user));
            // Originally wasn't a try-catch, but was changed due to an error with JSONObject
            // It was needed to add a JSONException in the case of an invalid JSON format
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, json_request,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject json_response) {
                            String message = json_response.optString("Message", "New member added");
                            Log.d("User added", message);
                            // This log the fact that a new account has been created

                            callback.success(message);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volley_error) {

                            Log.e("Creation error", "Unable to create account: " + volley_error.getMessage());
                            // This will log the error, making it clear thsat the account was unable to be created
                        }
                    }
            );
            queue.add(request);

        } catch (JSONException e) {
            Log.e("Format error", "Invalid format: " + e.getMessage());
        }
        // This was needed to handle incorrect formatting
    }

    public static void update_user(Context context, user user, String username, message_callback callback) {
        start_queue(context);
        String url = main_url + "/update_user/10909750/" + username;
        // Because you can only change your own account details, i am making it so that the username of the user is appended to the end of the string

        try {
            JSONObject json_request = new JSONObject(gson.toJson(user));
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url, json_request,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject json_response) {
                            String message = json_response.optString("Message", "Member details updated");
                            Log.d("User details updated", message);
                            callback.success(message);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volley_error) {
                            Log.d("Unable to update", "Unable to update account details: " + volley_error.getMessage());
                        }
                    }

            );
            queue.add(request);

        }  catch (JSONException e) {
                Log.e("Format error", "Invalid format: " + e.getMessage());
        }

    }



}
