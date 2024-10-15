package com.example.mynewsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.security.identity.IdentityCredentialStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ViewFlipper;

//import com.example.namespace.R;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    private ViewFlipper viewFlipper;
    static RadioButton ppl, nA, country, technology, sports, science, health, general, entertainment, business;
    static TextView na1, na2, na3, na4, na5, ti1, ti2, ti3, ti4, ti5, desc;

    static Button btn1, btn2, btn3, btn4, btn5;
    static ArrayList<String> descriptions = new ArrayList<>();
    WebView webView;
    static EditText cet, naet, pet;
    static String info, category, countryTyped, newsSource, personTyped;
    AsyncThread asyncThread;
    public static URL newsLinkURL;
    public static JSONObject newsInfo;
    Button previous, next;
    int btn1Count, btn2Count, btn3Count, btn4Count, btn5Count = 0;
    static String btn1URL, btn2URL, btn3URL, btn4URL, btn5URL;

    private String text;

DatabaseReference reference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewFlipper = findViewById(R.id.view_flipper);
        business = findViewById(R.id.business);
        entertainment = findViewById(R.id.entertainment);
        general = findViewById(R.id.general);
        health = findViewById(R.id.health);
        science = findViewById(R.id.science);
        sports = findViewById(R.id.sports);
        technology = findViewById(R.id.technology);
        country = findViewById(R.id.country);
        nA = findViewById(R.id.newsA);
        ppl = findViewById(R.id.people);
        na1 = findViewById(R.id.na1);
        na2 = findViewById(R.id.na2);
        na3 = findViewById(R.id.na3);
        na4 = findViewById(R.id.na4);
        na5 = findViewById(R.id.na5);
        ti1 = findViewById(R.id.ti1);
        ti2 = findViewById(R.id.ti2);
        ti3 = findViewById(R.id.ti3);
        ti4 = findViewById(R.id.ti4);
        ti5 = findViewById(R.id.ti5);
        cet = findViewById(R.id.countryET);
        naet = findViewById(R.id.newsAgencyET);
        pet = findViewById(R.id.peopleET);
        btn1 = findViewById(R.id.bt1);
        btn2 = findViewById(R.id.bt2);
        btn3 = findViewById(R.id.bt3);
        btn4 = findViewById(R.id.bt4);
        btn5 = findViewById(R.id.bt5);
        previous = findViewById(R.id.previousButton);
        next = findViewById(R.id.nextButton);
        webView = findViewById(R.id.webview);
        desc = findViewById(R.id.description);
        previous.setBackgroundColor(Color.rgb(0, 71, 171));
        next.setBackgroundColor(Color.rgb(0, 71, 171));
        btn1.setBackgroundColor(Color.rgb(0, 71, 171));
        btn2.setBackgroundColor(Color.rgb(0, 71, 171));
        btn3.setBackgroundColor(Color.rgb(0, 71, 171));
        btn4.setBackgroundColor(Color.rgb(0, 71, 171));
        btn5.setBackgroundColor(Color.rgb(0, 71, 171));
        cet.setEnabled(false);
        naet.setEnabled(false);
        pet.setEnabled(false);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://www.google.com");

        reference = FirebaseDatabase.getInstance().getReference();
        //reference.setValue("Url Test");
        Query checkUser = reference;


        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    webView.loadUrl(snapshot.getValue(String.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cet.setEnabled(false);
                naet.setEnabled(false);
                pet.setEnabled(false);
                btn1.setText("Info");
                btn2.setText("Info");
                btn3.setText("Info");
                btn4.setText("Info");
                btn5.setText("Info");
                category = "business";
                asyncThread = new AsyncThread();
                asyncThread.execute();
            }
        });
        entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cet.setEnabled(false);
                naet.setEnabled(false);
                pet.setEnabled(false);
                btn1.setText("Info");
                btn2.setText("Info");
                btn3.setText("Info");
                btn4.setText("Info");
                btn5.setText("Info");
                category = "entertainment";
                asyncThread = new AsyncThread();
                asyncThread.execute();
            }
        });
        general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cet.setEnabled(false);
                naet.setEnabled(false);
                pet.setEnabled(false);
                btn1.setText("Info");
                btn2.setText("Info");
                btn3.setText("Info");
                btn4.setText("Info");
                btn5.setText("Info");
                category = "general";
                asyncThread = new AsyncThread();
                asyncThread.execute();
            }
        });
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cet.setEnabled(false);
                naet.setEnabled(false);
                pet.setEnabled(false);
                btn1.setText("Info");
                btn2.setText("Info");
                btn3.setText("Info");
                btn4.setText("Info");
                btn5.setText("Info");
                category = "health";
                asyncThread = new AsyncThread();
                asyncThread.execute();
            }
        });
        science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cet.setEnabled(false);
                naet.setEnabled(false);
                pet.setEnabled(false);
                btn1.setText("Info");
                btn2.setText("Info");
                btn3.setText("Info");
                btn4.setText("Info");
                btn5.setText("Info");
                category = "science";
                asyncThread = new AsyncThread();
                asyncThread.execute();
            }
        });
        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cet.setEnabled(false);
                naet.setEnabled(false);
                pet.setEnabled(false);
                btn1.setText("Info");
                btn2.setText("Info");
                btn3.setText("Info");
                btn4.setText("Info");
                btn5.setText("Info");
                category = "sports";
                asyncThread = new AsyncThread();
                asyncThread.execute();
            }
        });
        technology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cet.setEnabled(false);
                naet.setEnabled(false);
                pet.setEnabled(false);
                btn1.setText("Info");
                btn2.setText("Info");
                btn3.setText("Info");
                btn4.setText("Info");
                btn5.setText("Info");
                category = "technology";
                asyncThread = new AsyncThread();
                asyncThread.execute();
            }
        });
        country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cet.setEnabled(true);
                pet.setEnabled(false);
                naet.setEnabled(false);
                btn1.setText("Info");
                btn2.setText("Info");
                btn3.setText("Info");
                btn4.setText("Info");
                btn5.setText("Info");
                category = "country";
                cet.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        countryTyped = cet.getText().toString();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if(countryTyped != null){
                            asyncThread = new AsyncThread();
                            asyncThread.execute();
                        }
                    }
                });


            }
        });
        nA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cet.setEnabled(false);
                pet.setEnabled(false);
                naet.setEnabled(true);
                btn1.setText("Info");
                btn2.setText("Info");
                btn3.setText("Info");
                btn4.setText("Info");
                btn5.setText("Info");
                category = "na";
                naet.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        newsSource = naet.getText().toString();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if(newsSource != null){
                            asyncThread = new AsyncThread();
                            asyncThread.execute();
                        }
                    }
                });
            }
        });
        ppl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cet.setEnabled(false);
                naet.setEnabled(false);
                pet.setEnabled(true);
                btn1.setText("Info");
                btn2.setText("Info");
                btn3.setText("Info");
                btn4.setText("Info");
                btn5.setText("Info");
                category = "ppl";
                pet.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        personTyped = pet.getText().toString();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if(personTyped != null){
                            asyncThread = new AsyncThread();
                            asyncThread.execute();
                        }
                    }
                });
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn1Count += 1;
                if(descriptions.get(0).length() == 4){
                    desc.setText("None");
                    Log.d("null", "true");
                    if(btn1Count % 2 != 0){
                        btn1.setText("Search");
                    }else{
                        webView.loadUrl(btn1URL);
                        reference.setValue(btn1URL);
                        viewFlipper.showNext();
                    }

                }else{
                    if(btn1Count % 2 != 0){
                        desc.setText(descriptions.get(0));
                        btn1.setText("Search");
                    }else{
                        webView.loadUrl(btn1URL);
                        reference.setValue(btn1URL);
                        viewFlipper.showNext();
                    }

                }

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn2Count += 1;
                if(descriptions.get(1).length() == 4){
                    desc.setText("None");
                    Log.d("null", "true");
                    if(btn2Count % 2 != 0){
                        btn2.setText("Search");
                    }else{
                        webView.loadUrl(btn2URL);
                        reference.setValue(btn2URL);
                        viewFlipper.showNext();
                    }

                }else{
                    if(btn2Count % 2 != 0){
                        desc.setText(descriptions.get(1));
                        btn2.setText("Search");
                    }else{
                        webView.loadUrl(btn2URL);
                        reference.setValue(btn2URL);
                        viewFlipper.showNext();
                    }
                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn3Count += 1;
                if(descriptions.get(2).length() == 4){
                    desc.setText("None");
                    Log.d("null", "true");
                    if(btn3Count % 2 != 0){
                        btn3.setText("Search");
                    }else{
                        webView.loadUrl(btn3URL);
                        reference.setValue(btn3URL);
                        viewFlipper.showNext();
                    }

                }else{
                    if(btn3Count % 2 != 0){
                        desc.setText(descriptions.get(2));
                        btn3.setText("Search");
                    }else{
                        webView.loadUrl(btn3URL);
                        reference.setValue(btn3URL);
                        viewFlipper.showNext();
                    }
                }
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn4Count += 1;
                if(descriptions.get(3).length() == 4){
                    desc.setText("None");
                    Log.d("null", "true");
                    if(btn4Count % 2 != 0){
                        btn4.setText("Search");
                    }else{
                        webView.loadUrl(btn4URL);
                        reference.setValue(btn4URL);
                        viewFlipper.showNext();
                    }

                }else{
                    if(btn4Count % 2 != 0){
                        desc.setText(descriptions.get(3));
                        btn4.setText("Search");
                    }else{
                        webView.loadUrl(btn4URL);
                        reference.setValue(btn4URL);
                        viewFlipper.showNext();
                    }
                }
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn5Count += 1;
                if(descriptions.get(4).length() == 4){
                    desc.setText("None");
                    Log.d("null", "true");
                    if(btn5Count % 2 != 0){
                        btn5.setText("Search");
                    }else{
                        webView.loadUrl(btn5URL);
                        reference.setValue(btn5URL);
                        viewFlipper.showNext();
                    }

                }else{
                    if(btn5Count % 2 != 0){
                        desc.setText(descriptions.get(4));
                        btn5.setText("Search");
                    }else{
                        webView.loadUrl(btn5URL);
                        reference.setValue(btn5URL);
                        viewFlipper.showNext();
                    }
                }
            }
        });
    }
    public void previousView(View v){
        viewFlipper.showPrevious();
    }
    public void nextView(View v){
        viewFlipper.showNext();
    }
    public static class AsyncThread extends AsyncTask<Void, Void, String> {

        @SuppressLint("WrongThread")
        @Override
        protected String doInBackground(Void... voids) {
            try {
                switch(category){
                    case "business":
                        apiMethod1();
                        break;
                    case "entertainment":
                        apiMethod1();
                        break;
                    case "general":
                        apiMethod1();
                        break;
                    case "health":
                        apiMethod1();
                        break;
                    case "science":
                        apiMethod1();
                        break;
                    case "sports":
                        apiMethod1();
                        break;
                    case "technology":
                        apiMethod1();
                        break;
                    case "country":
                        apiMethod2();
                        break;
                    case "na":
                        apiMethod3();
                        break;
                    case "ppl":
                        apiMethod4();
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return info;
        }

        protected void onPostExecute(String r) {
        }
    }
    public static void apiMethod1(){
        try{
            descriptions.clear();
            Log.d("In api1", "true");
            newsLinkURL = new URL("https://newsapi.org/v2/top-headlines?country=us&category=" + category + "(Insert API Key here)");
            URLConnection connection = newsLinkURL.openConnection();
            connection.addRequestProperty("User-Agent", "Mozilla/5.0");
            InputStream is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            info = br.readLine();
            while (br.readLine() != null) {
                info += br.readLine();
            }
            br.close();
            newsInfo = new JSONObject();
            JSONObject infoJSON = new JSONObject(info);
            JSONArray articles = infoJSON.getJSONArray("articles");
            JSONObject jon = articles.getJSONObject(0);
            JSONObject source = jon.getJSONObject("source");
            String newsAgency = source.getString("name");
            String title = jon.getString("title");
            String description = jon.getString("description");
            descriptions.add(description);
            na1.setText(newsAgency);
            ti1.setText(title);
            btn1URL = jon.getString("url");


            JSONObject jon2 = articles.getJSONObject(1);
            JSONObject source2 = jon2.getJSONObject("source");
            String newsAgency2 = source2.getString("name");
            String title2 = jon2.getString("title");
            String description2 = jon2.getString("description");
            descriptions.add(description2);
            na2.setText(newsAgency2);
            ti2.setText(title2);
            btn2URL = jon2.getString("url");


            JSONObject jon3 = articles.getJSONObject(2);
            JSONObject source3 = jon3.getJSONObject("source");
            String newsAgency3 = source3.getString("name");
            String title3 = jon3.getString("title");
            String description3 = jon3.getString("description");
            descriptions.add(description3);
            na3.setText(newsAgency3);
            ti3.setText(title3);
            btn3URL = jon3.getString("url");


            JSONObject jon4 = articles.getJSONObject(3);
            JSONObject source4 = jon4.getJSONObject("source");
            String newsAgency4 = source4.getString("name");
            String title4 = jon4.getString("title");
            String description4 = jon4.getString("description");
            descriptions.add(description4);
            na4.setText(newsAgency4);
            ti4.setText(title4);
            btn4URL = jon4.getString("url");


            JSONObject jon5 = articles.getJSONObject(4);
            JSONObject source5 = jon5.getJSONObject("source");
            String newsAgency5 = source5.getString("name");
            String title5 = jon5.getString("title");
            String description5 = jon5.getString("description");
            descriptions.add(description5);
            na5.setText(newsAgency5);
            ti5.setText(title5);
            btn5URL = jon5.getString("url");
        }catch(Exception e){
            e.printStackTrace();
        }
        Log.d("size", descriptions.size() + "");

    }
    public static void apiMethod2(){
        try{
            descriptions.clear();
            Log.d("In api2", "true");
            newsLinkURL = new URL("https://newsapi.org/v2/top-headlines?country=" + countryTyped + "(Insert API Key here)");
            URLConnection connection = newsLinkURL.openConnection();
            connection.addRequestProperty("User-Agent", "Mozilla/5.0");
            InputStream is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            info = br.readLine();
            while (br.readLine() != null) {
                info += br.readLine();
            }
            br.close();
            newsInfo = new JSONObject();
            JSONObject infoJSON = new JSONObject(info);
            JSONArray articles = infoJSON.getJSONArray("articles");
            JSONObject jon = articles.getJSONObject(0);
            JSONObject source = jon.getJSONObject("source");
            String newsAgency = source.getString("name");
            String title = jon.getString("title");
            String description = jon.getString("description");
            descriptions.add(description);
            na1.setText(newsAgency);
            ti1.setText(title);
            btn1URL = jon.getString("url");


            JSONObject jon2 = articles.getJSONObject(1);
            JSONObject source2 = jon2.getJSONObject("source");
            String newsAgency2 = source2.getString("name");
            String title2 = jon2.getString("title");
            String description2 = jon2.getString("description");
            descriptions.add(description2);
            na2.setText(newsAgency2);
            ti2.setText(title2);
            btn2URL = jon2.getString("url");


            JSONObject jon3 = articles.getJSONObject(2);
            JSONObject source3 = jon3.getJSONObject("source");
            String newsAgency3 = source3.getString("name");
            String title3 = jon3.getString("title");
            String description3 = jon3.getString("description");
            descriptions.add(description3);
            na3.setText(newsAgency3);
            ti3.setText(title3);
            btn3URL = jon3.getString("url");


            JSONObject jon4 = articles.getJSONObject(3);
            JSONObject source4 = jon4.getJSONObject("source");
            String newsAgency4 = source4.getString("name");
            String title4 = jon4.getString("title");
            String description4 = jon4.getString("description");
            descriptions.add(description4);
            na4.setText(newsAgency4);
            ti4.setText(title4);
            btn4URL = jon4.getString("url");


            JSONObject jon5 = articles.getJSONObject(4);
            JSONObject source5 = jon5.getJSONObject("source");
            String newsAgency5 = source5.getString("name");
            String title5 = jon5.getString("title");
            String description5 = jon5.getString("description");
            descriptions.add(description5);
            na5.setText(newsAgency5);
            ti5.setText(title5);
            btn5URL = jon5.getString("url");
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    public static void apiMethod3(){
        try{
            descriptions.clear();
            //"https://newsapi.org/v2/top-headlines?sources=" + newsSource + "&apiKey=97da7c49a2a04224a19d685aff75d4a9"
            Log.d("In api3", "true");
            newsLinkURL = new URL("https://newsapi.org/v2/top-headlines?sources=" + newsSource + "(Insert API Key here)");
            URLConnection connection = newsLinkURL.openConnection();
            connection.addRequestProperty("User-Agent", "Mozilla/5.0");
            InputStream is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            info = br.readLine();
            while (br.readLine() != null) {
                info += br.readLine();
            }
            br.close();
            newsInfo = new JSONObject();
            JSONObject infoJSON = new JSONObject(info);
            JSONArray articles = infoJSON.getJSONArray("articles");
            JSONObject jon = articles.getJSONObject(0);
            JSONObject source = jon.getJSONObject("source");
            String newsAgency = source.getString("name");
            String title = jon.getString("title");
            String description = jon.getString("description");
            descriptions.add(description);
            na1.setText(newsAgency);
            ti1.setText(title);
            btn1URL = jon.getString("url");


            JSONObject jon2 = articles.getJSONObject(1);
            JSONObject source2 = jon2.getJSONObject("source");
            String newsAgency2 = source2.getString("name");
            String title2 = jon2.getString("title");
            String description2 = jon2.getString("description");
            descriptions.add(description2);
            na2.setText(newsAgency2);
            ti2.setText(title2);
            btn2URL = jon2.getString("url");


            JSONObject jon3 = articles.getJSONObject(2);
            JSONObject source3 = jon3.getJSONObject("source");
            String newsAgency3 = source3.getString("name");
            String title3 = jon3.getString("title");
            String description3 = jon3.getString("description");
            descriptions.add(description3);
            na3.setText(newsAgency3);
            ti3.setText(title3);
            btn3URL = jon3.getString("url");


            JSONObject jon4 = articles.getJSONObject(3);
            JSONObject source4 = jon4.getJSONObject("source");
            String newsAgency4 = source4.getString("name");
            String title4 = jon4.getString("title");
            String description4 = jon4.getString("description");
            descriptions.add(description4);
            na4.setText(newsAgency4);
            ti4.setText(title4);
            btn4URL = jon4.getString("url");


            JSONObject jon5 = articles.getJSONObject(4);
            JSONObject source5 = jon5.getJSONObject("source");
            String newsAgency5 = source5.getString("name");
            String title5 = jon5.getString("title");
            String description5 = jon5.getString("description");
            descriptions.add(description5);
            na5.setText(newsAgency5);
            ti5.setText(title5);
            btn5URL = jon5.getString("url");
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    public static void apiMethod4(){
        try{
            descriptions.clear();
            Log.d("In api4", "true");
            newsLinkURL = new URL("https://newsapi.org/v2/top-headlines?q=" + personTyped + "(Insert API Key here)");
            URLConnection connection = newsLinkURL.openConnection();
            connection.addRequestProperty("User-Agent", "Mozilla/5.0");
            InputStream is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            info = br.readLine();
            while (br.readLine() != null) {
                info += br.readLine();
            }
            br.close();
            newsInfo = new JSONObject();
            JSONObject infoJSON = new JSONObject(info);
            JSONArray articles = infoJSON.getJSONArray("articles");
            JSONObject jon = articles.getJSONObject(0);
            JSONObject source = jon.getJSONObject("source");
            String newsAgency = source.getString("name");
            String title = jon.getString("title");
            String description = jon.getString("description");
            descriptions.add(description);
            na1.setText(newsAgency);
            ti1.setText(title);
            btn1URL = jon.getString("url");


            JSONObject jon2 = articles.getJSONObject(1);
            JSONObject source2 = jon2.getJSONObject("source");
            String newsAgency2 = source2.getString("name");
            String title2 = jon2.getString("title");
            String description2 = jon2.getString("description");
            descriptions.add(description2);
            na2.setText(newsAgency2);
            ti2.setText(title2);
            btn2URL = jon2.getString("url");


            JSONObject jon3 = articles.getJSONObject(2);
            JSONObject source3 = jon3.getJSONObject("source");
            String newsAgency3 = source3.getString("name");
            String title3 = jon3.getString("title");
            String description3 = jon3.getString("description");
            descriptions.add(description3);
            na3.setText(newsAgency3);
            ti3.setText(title3);
            btn3URL = jon3.getString("url");


            JSONObject jon4 = articles.getJSONObject(3);
            JSONObject source4 = jon4.getJSONObject("source");
            String newsAgency4 = source4.getString("name");
            String title4 = jon4.getString("title");
            String description4 = jon4.getString("description");
            descriptions.add(description4);
            na4.setText(newsAgency4);
            ti4.setText(title4);
            btn4URL = jon4.getString("url");


            JSONObject jon5 = articles.getJSONObject(4);
            JSONObject source5 = jon5.getJSONObject("source");
            String newsAgency5 = source5.getString("name");
            String title5 = jon5.getString("title");
            String description5 = jon5.getString("description");
            descriptions.add(description5);
            na5.setText(newsAgency5);
            ti5.setText(title5);
            btn5URL = jon5.getString("url");
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}

