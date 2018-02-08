package fr.univ_littoral.nathan.testvolley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //private static final String URL_USERS = "http://192.168.5.46/MyApi/Api.php";
    private static final String URL_USERS = "http://51.255.164.53/Api.php";

    List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conn("nathan@gmail.com", "nathan");
    }

    public void conn(final String mail, final String password) {
        userList = new ArrayList<>();

        loadUser(new CallBack() {
            @Override
            public void onSuccess(List<User> userList) {
                for (User u : userList) {
                    if (u.getMail().equals(mail) && u.getPassword().equals(password)) {
                        System.out.println("Connecté");
                        return;
                    }
                }
                System.out.println("Erreur !!");
            }

            @Override
            public void onFail(String msg) {
                // Do Stuff
            }
        });
    }

    private void loadUser(final CallBack onCallBack) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_USERS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject user = array.getJSONObject(i);

                                userList.add(new User(
                                        user.getString("mail"),
                                        user.getString("password")
                                ));
                            }
                            onCallBack.onSuccess(userList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            onCallBack.onFail(e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);
                    }
                });

        Volley.newRequestQueue(this).add(stringRequest);
    }

    public interface CallBack {
        void onSuccess(List<User> userList);
        void onFail(String msg);
    }
}
