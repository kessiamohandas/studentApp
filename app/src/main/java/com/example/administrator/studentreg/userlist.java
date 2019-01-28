package com.example.administrator.studentreg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class userlist extends AppCompatActivity {
TextView textview;
    String getname,getusername,getemail,getstreet,getcity,getzipcode,getsuite,finaldata;
    String url="http://jsonplaceholder.typicode.com/users";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);
        textview=(TextView)findViewById(R.id.tv);
        FetchData();
    }

    private void FetchData() {
//        Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_LONG).show();
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        HANDLE RESPONSE
                        Log.d("Response", response);
                        try {
                            JSONArray jsonarray = new JSONArray(response);

                            for (int i = 0; i < jsonarray.length(); i++) {
                                JSONObject jsonoject = jsonarray.getJSONObject (i);
                                getname=jsonoject.getString("name");
                                Log.d("name",getname);
                                getusername=jsonoject.getString("username");
                                Log.d("username",getusername);
                                getemail=jsonoject.getString("email");
                                Log.d("email",getemail);

                                JSONObject ob1=new JSONObject(jsonoject.getString("address"));
                                getstreet=ob1.getString("street");
                                getcity=ob1.getString("city");
                                getzipcode=ob1.getString("zipcode");
                                getsuite=ob1.getString("suite");
                                Log.d("address",getstreet);
                                finaldata +="Name:"+ getname + "\n";
                                finaldata +="Email ID :"+ getemail + "\n";
                                finaldata +="Username :"+ getusername + "\n";
                                finaldata +="Suite :"+ getsuite + "\n";
                                finaldata +="Street :"+ getstreet + "\n";
                                finaldata +="Zipcode :"+ getzipcode + "\n";
                                finaldata +="City :"+ getcity + "\n";


                            }
                            textview.setText(finaldata);
                        } catch (JSONException ob) {
                            Log.d("Expection",ob.toString());

                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });


    RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
    requestQueue.add(stringRequest);

}
}
