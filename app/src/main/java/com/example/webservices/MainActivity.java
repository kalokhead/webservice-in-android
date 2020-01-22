package com.example.webservices;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    String url = "https://preprod-digitapp.godigit.com/S_DigitApp/mobile/MobileSpecification?make=Sa&imdCode=1000651";
    private CustomAdapter cAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.lv);


        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {
                parseJsonData(string);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), "Some error occurred!!", Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(MainActivity.this);
        rQueue.add(request);
//        rQueue.start();
    }


    void parseJsonData(String jsonString) {

        try {
            String strJson = "{\"data\":" + jsonString + "}";
            JSONObject object = new JSONObject(strJson);
            JSONArray itemList = object.getJSONArray("data");
//            JSONArray json = new JSONArray();

            ArrayList<Mobile> mobj = new ArrayList();
            ArrayList<String> name = new ArrayList();


            for (int i = 0; i < itemList.length(); ++i) {
                Mobile mo = new Mobile();

                JSONObject jObj = itemList.getJSONObject(i);
                String make = jObj.getString("make");
                String model = jObj.getString("model");
                String price = jObj.getString("price");
                int score = jObj.getInt("score");
                String text = jObj.getString("text");
                String concat = jObj.getString("concat");


                mo.setMake(make);
                mo.setModel(model);
                mo.setPrice(price);
                mo.setConcat(concat);
                mo.setScore(score);
                mo.setText(text);
                mobj.add(mo);

                name.add(make);
                name.add(model);
                name.add(price.toString());
            }

            cAdapter = new CustomAdapter(this,mobj);//acess coustom adapter from the coustomAdapter.java
            lv.setAdapter(cAdapter);//setting adapter
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

}
