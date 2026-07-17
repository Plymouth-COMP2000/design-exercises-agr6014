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


public class authentication {
    private static String user;
    private static String username;
    private static String role;
    private static String email;

    public interface callback_login {
        void success();
        void error(String message);
    }
    // Callback for login

    public static void login(Context context, String email, String password, String role, callback_login callback) {
        if (email.isEmpty() || password.isEmpty()) {
            callback.error("Email and password are currently empty");
            return;
        }
        // This will check if both fields are empty, if either are then an error message will appear

        api.get_all_users(context, new api.callback_user() {

            @Override
            public void success(List<user> users) {
                for (user user: users) {
                    if (user.getEmail() == null || user.getPassword() == null || user.getRole() == null) {
                        continue;
                    }
                    // This will check if the email, password, or role are null
                }
            }

            @Override
            public void error(String message) {

            }

        });

    }

    public static void member_login() {

    }

    public static void trainer_login() {

    }

}
