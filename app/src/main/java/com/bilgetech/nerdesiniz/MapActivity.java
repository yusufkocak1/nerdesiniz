package com.bilgetech.nerdesiniz;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class MapActivity extends LocationAwareActivity implements OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback,
        GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMarkerDragListener,
        GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private String room_id;
    private String color;
    private String name;
    private String id;
    private ArrayList<Person> personlist=new ArrayList<>();
ImageView colorIV;
TextView nameTV;

    @android.support.annotation.RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        runtimePermssion();

        colorIV=(ImageView) findViewById(R.id.ivUserColor);
        nameTV =(TextView) findViewById(R.id.tvUserName);
        room_id = getIntent().getStringExtra("room");
        name = getIntent().getStringExtra("name");
        color = getIntent().getStringExtra("color");

        nameTV.setText(name);
        switch (color) {
            case "red":
                colorIV.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.color.red));

                break;
            case "yellow":
                colorIV.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.color.yellow));

                break;
            case "green":
                colorIV.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.color.green));

                break;
            case "blue":
                colorIV.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.color.blue));

        }




        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        final Handler handler = new Handler();
        Timer timer;


        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                handler.post(new Runnable() {
                    @Override
                    public void run() {


                     //   new firebase().updateData(new Person(id,room_id,name,color,location));
                        addMarker(new firebase().selectData(room_id),mMap);
                    }
                });
            }
        };

        timer = new Timer();

        timer.schedule(timerTask,10,100);
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onStop() {
    new firebase().deleteData(personlist.get(0));
        super.onStop();
    }

    private void runtimePermssion() {

        if (ContextCompat.checkSelfPermission(MapActivity.this, Manifest.permission.WRITE_CALENDAR)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted

            ActivityCompat.requestPermissions(MapActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);


        }
        if (ContextCompat.checkSelfPermission(MapActivity.this, Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted

            ActivityCompat.requestPermissions(MapActivity.this,
                    new String[]{Manifest.permission.INTERNET},
                    1);


        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MapActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);

        }

    }


    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        firebase firebase = new firebase();

        mMap.setMyLocationEnabled(true);
        // Check if we were successful in obtaining the map.


        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {

            @Override
            public void onMyLocationChange(Location arg0) {
                // TODO Auto-generated method stub
                location= new com.bilgetech.nerdesiniz.Location(String.valueOf(arg0.getLatitude()),String.valueOf(arg0.getLongitude()));


            }
        });

        String id=firebase.insertData(new Person(null, room_id, name, color, location));

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {

    }

    @Override
    public boolean onMyLocationButtonClick() {


        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(MapActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
