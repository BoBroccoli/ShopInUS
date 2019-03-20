package com.example.shopinus;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RecyclerView items;
    ItemAdapter adapter;
    static ArrayList<Item> list;

    public void loadUrlData(){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://131.212.41.237:9090/api"
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {

                    JSONArray array = new JSONArray(response);
                    Log.e("Here","Runt to here");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject = array.getJSONObject(i);
                        Item item = new Item(jsonObject.getString("name"),
                                jsonObject.getString("imageURL"), jsonObject.getString("sourceURL"));
                        list.add(item);
                        adapter.notifyDataSetChanged();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error"+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(this).add(stringRequest);

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();
        items = (RecyclerView)findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        DividerItemDecoration decoration = new DividerItemDecoration(items.getContext(),
                ((LinearLayoutManager) manager).getOrientation());
        items.addItemDecoration(decoration);
        items.setLayoutManager(manager);

        Log.e("Here","Runt to here");

        adapter = new ItemAdapter(list);
        adapter.setOnItemClickListener(new ItemAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {

                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                intent.putExtra("position",position);
                startActivity(intent);

            }
        });
        items.setAdapter(adapter);

        loadUrlData();

    }
}