package com.example.mayank.recordlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mayank.recordlist.adapter.ItemAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.CacheRequest;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ItemAdapter adapter;
    ArrayList<ItemModel> list=new ArrayList<>();

    String url = "http://test.chatongo.in/testdata.json";

    StringRequest request;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        RequestQueue queue = Volley.newRequestQueue(this);
       
        getData();

        adapter = new ItemAdapter(list,this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        
    }

    private void getData() {

        RequestQueue queue = Volley.newRequestQueue(this);
        request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject c = new JSONObject(response);
                    String Status = c.getString("Status");
                    String Message = c.getString("Message");
                    String jsonObject = c.getString("data");

                    JSONObject jsonObject1 = new JSONObject(jsonObject);
                    String totalrecord = jsonObject1.getString("TotalRecords");
                    String Records = jsonObject1.getString("Records");

                    JSONArray array = new JSONArray(Records);
                    for (int i = 0; i <array.length() ; i++) {
                        JSONObject c1= array.getJSONObject(i);
                        String title = c1.getString("title");
                        String shortDescription = c1.getString("shortDescription");
                        String collectedValue = c1.getString("collectedValue");
                        String totalValue = c1.getString("totalValue");
                        String startDate = c1.getString("startDate");
                        String endDate = c1.getString("endDate");
                        String mainImageURL = c1.getString("mainImageURL");

                        ItemModel im = new ItemModel(title,shortDescription,collectedValue,totalValue,startDate,endDate,mainImageURL);
                        list.add(im);

                    }

                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }
}
