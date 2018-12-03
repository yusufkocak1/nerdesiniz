package com.bilgetech.nerdesiniz;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This is should be a base class
 * for all of the activities you need to get location updates.
 *
 * TODO: implement
 */

public class LocationAwareActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    com.bilgetech.nerdesiniz.Location location;
    @Override
    public void onLocationChanged(Location location) {

        this.location.setLat(String.valueOf(location.getLatitude()));
        this.location.setLng(String.valueOf(location.getLongitude()));
    }

    protected void addMarker(ArrayList<Person> personList , GoogleMap mMap){


        for (Person person : personList
             ) {
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(Double.valueOf(person.getLocation().getLat())
                            ,  Double.valueOf(person.getLocation().getLng()))).icon(BitmapDescriptorFactory
                    .defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    .title(person.getName()));


        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
