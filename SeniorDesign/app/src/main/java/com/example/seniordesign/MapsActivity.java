package com.example.seniordesign;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import static com.google.android.gms.maps.CameraUpdateFactory.newLatLngZoom;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    DrawerLayout drawerLayout;
    private static final String TAG = "Maps";

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final float default_zoom = 5f;

    private Boolean mLocationPermissionsGranted = false;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private UiSettings uiSettings;
    private DocumentReference docRef;

    Double curr_Lat;
    Double curr_Lng;
    Double end_Lat;
    Double end_Lng;

    ArrayList<HospitalsModel> datalist = new ArrayList<HospitalsModel>();
    TextView latLongTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.map);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext()
                                ,MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                ,navigation.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.map:
                        return true;
                }
                return false;
            }
        });

        getLocationPermission();
        FirebaseFirestore.getInstance().collection("hospitals").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                String address = null;
                //Log.d("SENIOR design", list.toString());
                for (DocumentSnapshot d : list) {
                    HospitalsModel obj = d.toObject(HospitalsModel.class);
                    datalist.add(d.toObject(HospitalsModel.class));
                }
                for (int i = 0; i < list.size(); i++) {
                    Log.d("England", datalist.get(i).getHospitalLocation());
                    address = datalist.get(i).getHospitalLocation();
                    //GeocodingLocation locationAddress = new GeocodingLocation();
                    //LatLng ab= locationAddress.getAddressFromLocation(address,getApplicationContext());
                    //Log.d("ADDRESS is ", String.valueOf(ab));
                    getLocationAddress(getApplicationContext(), address);
                    //Log.d("FOund latlong is ", String.valueOf(ab));
                    //mMap.addMarker(new MarkerOptions().position(ab));
                    //mMap.moveCamera(newLatLng(ab));

                }
            }
        });

    }



    public void onMapReady(GoogleMap googleMap) {

        Log.d(TAG, "onMapReady: map is ready");
        mMap = googleMap;

        uiSettings = mMap.getUiSettings();
        uiSettings.setAllGesturesEnabled(true);
        uiSettings.setMapToolbarEnabled(true);
        uiSettings.setZoomControlsEnabled(true);


        if (mLocationPermissionsGranted) {
            deviceLocation();

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);
            //mMap.getUiSettings().setCompassEnabled(true);
            mMap.setOnMarkerClickListener(this);
        }


    }

    private void getLocationAddress(Context context, String location) {

        Geocoder coder = new Geocoder(context);

        List<Address> address;
        LatLng newLatLng = null;
        Double lat, lng;
        try {
            address = coder.getFromLocationName(location, 5);

            Log.d("Document us us us :", address.toString());
            if (address == null) {

            }
            Address loc = address.get(0);
            lat = loc.getLatitude();
            lng = loc.getLongitude();
            Log.d("FOund latlong is ", String.valueOf(lat));
            Log.d("FOund latlong is ", String.valueOf(lng));
            newLatLng = new LatLng(lat, lng);
            mMap.addMarker(new MarkerOptions().position(newLatLng).title(location).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void deviceLocation(){
        Log.d(TAG,"DeviceLocation: getting the device current location");
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        try{
            if(mLocationPermissionsGranted){

                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            Log.d(TAG,"onComplete: found location");
                            Location currentLocation = (Location)task.getResult();
                            curr_Lat = currentLocation.getLatitude();
                            curr_Lng = currentLocation.getLongitude();

                            Log.d("CURR_LATI_LONG", String.valueOf(curr_Lat));
//                            moveCamera(new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude()),default_zoom);
                        }
                        else{
                            Log.d(TAG,"onComplete: current location is null");
                            Toast.makeText(MapsActivity.this,"unable to get current location",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }

        }catch (SecurityException e){
            Log.e(TAG,"DeviceLocation: SecurityException: "+e.getMessage());
        }
    }
    private void moveCamera(LatLng latLng, float zoom){
        Log.d(TAG,"moveCamera: moving the camera to: lat:" + latLng.latitude +", lng: " + latLng.longitude);
        mMap.moveCamera(newLatLngZoom(latLng,zoom) );
    }


    private void initMap() {
        Log.d(TAG, "initMap: initializing map");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync( this);
    }

    private void getLocationPermission() {
        Log.d(TAG, "getLocationPermission: getting location permissions");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionsGranted = true;
                initMap();
            } else {
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(TAG, "onRequestPermissionsResult: called.");
        mLocationPermissionsGranted = false;

        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionsGranted = false;
                            Log.d(TAG, "onRequestPermissionsResult: permission failed");
                            return;
                        }
                    }
                    Log.d(TAG, "onRequestPermissionsResult: permission granted");
                    mLocationPermissionsGranted = true;
                    //initialize our map
                    initMap();
                }
            }
        }


    }


    @Override
    //Getting the distance form the marker
    public boolean onMarkerClick(Marker marker) {
        MarkerOptions markerOptions = new MarkerOptions();
        end_Lat = marker.getPosition().latitude;
        end_Lng = marker.getPosition().longitude;
        float results[]= new float[10];
        Location.distanceBetween(curr_Lat,curr_Lng,end_Lat,end_Lng,results);
        NumberFormat nf= NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        String distance_location = String.valueOf(nf.format(results[0]*0.000621371))+ " miles";
        AlertDialog dialog = new AlertDialog.Builder(MapsActivity.this).setTitle("Distance from current location to chosen marker")
                .setMessage(distance_location).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create();
        dialog.show();
        return false;
    }
}