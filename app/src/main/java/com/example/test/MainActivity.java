package com.example.test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.util.Log;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

//import static com.example.test.SavedInfo.writeFile;


public class MainActivity extends AppCompatActivity {


    private TextView approvedTime;
    private EditText longitude;
    private EditText latitude;
    private Button submitButton;
    private RelativeLayout layout;

    String longInput, latInput;


    List<WeatherInfo> weatherList;
     List<String> saveToFileList;

    RecyclerView recyclerView;
    WeatherInfoAdapter adapter;
    Timer timer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);


        weatherList = new ArrayList<>();
        saveToFileList = new ArrayList<>();


        approvedTime = findViewById(R.id.ApprovedTime);
        longitude = (EditText) findViewById(R.id.longitude_text);
        latitude = (EditText) findViewById(R.id.lattitude_text);
        submitButton = (Button) findViewById(R.id.submit_button);

        layout = findViewById(R.id.layout);
        String url = "https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/14.333/lat/60.383/data.json";
        submitButton.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {


                //adapter.notifyDataSetChanged();
                //approvedTime.getText().equals(null);
                saveToFileList.clear();

                approvedTime.setText("");

                longInput = longitude.getText().toString();
                latInput = latitude.getText().toString();
                Log.d(longInput, latInput + "------------");



                String url = "https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/" + longInput + "/lat/" + latInput + "/data.json";


                aprovedTimeFunction(url);
                weatherInfoFunction(url);

            }
        }));


        aprovedTimeFunction(url);
        weatherInfoFunction(url);


    }

    public void writeFile() {


        try {
            FileOutputStream fileOutputStream = openFileOutput("Test File.txt", MODE_PRIVATE);
String temp= saveToFileList.get(0);
            Log.d("___________________", temp.substring(0, 2).toString());


            for (int i = 0; i < saveToFileList.size(); i++) {
                //Log.d("___________________", s);
                fileOutputStream.write(saveToFileList.get(i).getBytes());

                fileOutputStream.write("\n".getBytes());
            }
            Log.d("file is: ", "written");
            temp=null;



            fileOutputStream.close();

            Toast.makeText(getApplicationContext(), "Text Saved", Toast.LENGTH_SHORT).show();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void readFile() {
        try {
            FileInputStream fileInputStream = openFileInput("Test File.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            String lines;
            while ((lines = bufferedReader.readLine()) != null) {
                stringBuffer.append(lines + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void aprovedTimeFunction(String url) {





        //approvedTime.notify();
        RequestQueue AQueue = Volley.newRequestQueue(this);


        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            public void onResponse(JSONObject response) {
                String approvedTimeString = null;

                try {
                    approvedTimeString = (String) response.get("approvedTime");

                } catch (JSONException e) {
                    e.printStackTrace();

                }
                approvedTime.append("Approved time:" + " " + approvedTimeString.substring(0, 10) + " ");
                approvedTime.append(approvedTimeString.substring(11, 16) + " ");
                Log.d("First", "FIrst");
                saveToFileList.add(approvedTime.getText().toString());
                Log.d("second", "second");


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("coordinates are out of range").setNegativeButton("Ok", null);
                AlertDialog alert = builder.create();
                alert.show();

                Log.d("tag", "onErrorResponse: " + error.getMessage());

            }
        });
        AQueue.add(request);


    }

    private void weatherInfoFunction(String url) {
        weatherList.clear();
        //adapter.notifyDataSetChanged();
        Log.d("third", "third");
        RequestQueue WQueue = Volley.newRequestQueue(this);


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                JSONArray timeSeriesArray = null;
                try {
                    timeSeriesArray = response.getJSONArray("timeSeries");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                int i;
                for (i = 0; i < timeSeriesArray.length(); i++) {
                    JSONObject validTimeObject = null;
                    try {
                        validTimeObject = timeSeriesArray.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    WeatherInfo weatherInfo = new WeatherInfo();
                    try {
                        String time = null;
                        time = validTimeObject.getString("validTime").substring(0, 10) + " " + validTimeObject.getString("validTime").substring(11, 16);
                        weatherInfo.setValidTime("Time: " + time);


                        saveToFileList.add("Time: " + time);
                        //Log.d(time, saveToFileList.get(i));


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    JSONArray parametersArray = null;
                    try {
                        parametersArray = validTimeObject.getJSONArray("parameters");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    for (int j = 0; j < parametersArray.length(); j++) {
                        JSONObject parametersObject = null;
                        try {
                            parametersObject = parametersArray.getJSONObject(j);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        String t = null;
                        try {
                            if (parametersObject.getString("name").equals("t")) {

                                JSONArray t_values;
                                t_values = parametersObject.getJSONArray("values");
                                weatherInfo.setTemperature("Temperature: " + t_values.optString(0));


                                saveToFileList.add("Temperature: " + t_values.optString(0));
                                //Log.d(""+saveToFileList.size(), ".......");


                                //break;

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            if (parametersObject.getString("name").equals("tcc_mean")) {

                                JSONArray tcc_values;
                                tcc_values = parametersObject.getJSONArray("values");
                                weatherInfo.setCloudCoverage("Cloud coverage: " + tcc_values.optString(0));
                                saveToFileList.add("Cloud coverage: " + tcc_values.optString(0));

                                //String temp = tcc_values.optString(0);


                                //break;

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                    weatherList.add(weatherInfo);


                }
                //Log.d(""+saveToFileList.size(), ".......");


                        writeFile();


                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new WeatherInfoAdapter(getApplicationContext(), weatherList);
                recyclerView.setAdapter(adapter);


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse: " + error.getMessage());
            }
        });


        WQueue.add(request);


        //F();

    }
}