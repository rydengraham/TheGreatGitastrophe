package com.example.cmput_301_project;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * Code partially sourced from :
 * https://github.com/dimitardanailov/FusedLocationProviderClient/blob/master/app/src/main/java/demo/client/provider/location/fused/fusedlocationproviderclientexample/MainActivity.java
 */
public class LocationActivity extends AppCompatActivity implements OnMapReadyCallback {

    // Constants
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private static final String TRACKING_LOCATION_KEY = "tracking_location";
    private static final String TAG = LocationActivity.class.getSimpleName();


    private HabitLocation locationDetail;
    private String coordinates [];
    private String mLongitude;
    private String mAddress;
    private String mLatitude;
    private  LatLng latLng;
    private  FloatingActionButton addLocation;
    private boolean userLocationAvailable = false;

    // Location classes
    private FusedLocationProviderClient mFusedLocationClient;
    private Location mLastLocation;

    private Account userAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        userAccount = AccountData.create().getActiveUserAccount();
        addLocation = findViewById(R.id.floating_btn);

        String apiKey = getString(R.string.api_key);

        if(!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), apiKey);

        }

        PlacesClient placesClient = Places.createClient(this);

        getLocation();
        initAutocompleteUI();


    }
    public void initAutocompleteUI(){
        // Initialize the AutocompleteSupportFragment.

        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        // Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS));

        // Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {

                parseLatLng(Objects.requireNonNull(place.getLatLng()));
                addLocation.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        //UPDATES USER LOCATION WITH EVERY CLICK
                        //TODO: ONLY UPDATE WHEN GIVEN DIFFERENT INPUT
                        if(userLocationAvailable) {
                            locationDetail.setLocationName(place.getName());
                            locationDetail.setAddress(place.getAddress());
                            locationDetail.setLatitude(coordinates[0]);
                            locationDetail.setLongitude(coordinates[1]);
                            Toast.makeText(LocationActivity.this,
                                    R.string.updating_location,
                                    Toast.LENGTH_SHORT).show();

                            Bundle extras = getIntent().getExtras();
                            String eventId = extras.getString("eventId");
                            String habitName = extras.getString("habitName");
                            userAccount = AccountData.create().getActiveUserAccount();
                            userAccount.getHabitEvent(eventId, habitName).setLocation(new HabitLocation(locationDetail));
                            userAccount.updateFirestore();
                            finish();

                        }

                    }
                });



                setUpMap(place.getLatLng());

            }


            @Override
            public void onError(@NonNull Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_LOCATION_PERMISSION:
                // If the permission is granted, get the location,
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                } else {
                    Toast.makeText(this,
                            R.string.location_permission_denied,
                            Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    private void parseLatLng( LatLng latLng){
        coordinates = latLng.toString().split(",");
        coordinates[0] = coordinates[0].replace("lat/lng: (", "");
        coordinates[1] = coordinates[1].replace(")", "");
    }


    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        } else {
            Log.d(TAG, "getLocation: permissions granted");

            userLocationAvailable = true;
        }

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    mLastLocation = location;
                    Toast.makeText(getApplicationContext(), mLastLocation.getLatitude() + "," +
                            mLastLocation.getLongitude(), Toast.LENGTH_LONG).show();

                    setUpMap(new LatLng(mLastLocation.getLatitude(),mLastLocation.getLongitude()));



                    mLatitude = Double.toString(location.getLatitude());
                    mLongitude = Double.toString(location.getLongitude());



                    setAddress(location);
                } else {
                    Toast.makeText(LocationActivity.this,
                            R.string.location_permission_denied,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setUpMap(LatLng newLatLng){
        latLng = newLatLng;
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(LocationActivity.this::onMapReady);
        Bundle extras = getIntent().getExtras();
        String eventId = extras.getString("eventId");
        String habitName = extras.getString("habitName");
        userAccount = AccountData.create().getActiveUserAccount();
        HabitLocation location = userAccount.getHabitEvent(eventId, habitName).getLocation();
        if (location != null) {
            latLng = new LatLng(Double.parseDouble(location.getLatitude()), Double.parseDouble(location.getLongitude()));
        }

    }

    private void setAddress(Location location) {
        Geocoder geocoder = new Geocoder(LocationActivity.this,
                Locale.getDefault());
        List<Address> addresses = null;
        String resultMessage = "";

        try {
            addresses = geocoder.getFromLocation(
                    location.getLatitude(),
                    location.getLongitude(),
                    // In this sample, get just a single address
                    1);
        } catch (IOException ioException) {
            // Catch network or other I/O problems
            resultMessage = LocationActivity.this
                    .getString(R.string.service_not_available);
            Log.e(TAG, resultMessage, ioException);
        }

        if (addresses == null || addresses.size() == 0) {
            if (resultMessage.isEmpty()) {
                resultMessage = LocationActivity.this
                        .getString(R.string.no_address_found);
                Log.e(TAG, resultMessage);
            }
        } else {
            Address address = addresses.get(0);
            StringBuilder out = new StringBuilder();
            // Fetch the address lines using getAddressLine,
            // join them, and send them to the thread
            for (int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
                out.append(address.getAddressLine(i));
            }

            resultMessage = out.toString();
        }
        mAddress = resultMessage;
        locationDetail= new HabitLocation("Home", mAddress,mLatitude,mLongitude);

    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        MarkerOptions markerOptions=new MarkerOptions().position(latLng).title("My Location");
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
        googleMap.addMarker(markerOptions);

    }

}