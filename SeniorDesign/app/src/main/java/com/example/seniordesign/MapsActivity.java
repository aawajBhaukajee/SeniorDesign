package com.example.seniordesign;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
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
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.google.android.gms.maps.CameraUpdateFactory.newLatLngZoom;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

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

    ArrayList<HospitalsModel> datalist = new ArrayList<HospitalsModel>();
    TextView latLongTV;

    //private FirebaseFirestore firebaseFirestoreH;
    //private FirestoreRecyclerAdapter adapterH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        drawerLayout=findViewById(R.id.drawerLayout);

        latLongTV = findViewById(R.id.latLongTV);
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

    /*public void ClickMenu(View view){
        navigation.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view){
        navigation.closeDrawer(drawerLayout);
    }

    public void ClickHome(View view){
        navigation.redirectActivity(this,navigation.class);
    }

    public void ClickProfile(View view){
        navigation.redirectActivity(this,MainActivity.class);
    }

    public void ClickListHospital(View view){ navigation.redirectActivity(this,AllHospitals.class); }

    public void ClickMap(View view){
       recreate();
    }

    public void ClickLogout(View view){
        navigation.redirectActivity(this, firstPage.class);
    }

    @Override
    protected void onPause() {
        super.onPause();
        navigation.closeDrawer(drawerLayout);
    }
*/

    public void onMapReady(GoogleMap googleMap) {

        //Toast.makeText(this, "Map is Ready", Toast.LENGTH_SHORT).show();
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
            mMap.getUiSettings().setCompassEnabled(true);
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
        // return newLatLng;
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

    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
            latLongTV.setText(locationAddress);
        }
    }
}

