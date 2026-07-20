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
import android.content.SharedPreferences;
// This will be used to store the username in order to store specific operations


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
                    if (user.getEmail() == null || user.getPassword() == null || user.getUserType() == null) {
                        continue;
                    }
                    // This will check if the email, password, or role are null

                    boolean matches_email = user.getEmail().equalsIgnoreCase(email);
                    boolean matches_password = user.getPassword().equals(password);
                    boolean matches_role = user.getUserType().equalsIgnoreCase(role);
                    // Email and role isn't case sensitive, whereas the password is


                    if (matches_email && matches_password && matches_role) {
                        current_user_save(context, user);

                        callback.success();
                        return;
                    }

                }
                callback.error("Wrong credentials");

            }

            @Override
            public void error(String message) {
                Log.e("Login error", "Wrong credentials");
            }

        });

    }

    public static void member_login(Context context, String email, String password, callback_login callback) {
        login(context, email, password, "Member", callback);
    }

    public static void trainer_login(Context context, String email, String password, callback_login callback) {
        login(context, email, password, "Trainer", callback);
    }

    public static void current_user_save(Context context, user current_user) {
        SharedPreferences preferences = context.getSharedPreferences("Current user", Context.MODE_PRIVATE);

        preferences.edit().putString("id", current_user.getId())
                .putString("username", current_user.getUsername())
                .putString("firstname", current_user.getFirstname())
                .putString("lastname", current_user.getLastname())
                .putString("email", current_user.getEmail())
                .putString("phone", current_user.getContact())
                .putString("password", current_user.getPassword())
                // I forgot to add this originally, now i should be able to update the account
                .putString("role", current_user.getUserType()).apply();
    }

    public static user get_current_user_details(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Current user", Context.MODE_PRIVATE);

        user current_user = new user();

        current_user.setId(preferences.getString("id", ""));
        current_user.setUsername(preferences.getString("username", ""));
        current_user.setFirstname(preferences.getString("firstname", ""));
        current_user.setLastname(preferences.getString("lastname", ""));
        current_user.setEmail(preferences.getString("email", ""));
        current_user.setContact(preferences.getString("phone", ""));
        current_user.setPassword(preferences.getString("password", ""));
        // This will now hopefully let me update the accounts
        current_user.setUserType(preferences.getString("role", ""));

        return current_user;

    }

}
