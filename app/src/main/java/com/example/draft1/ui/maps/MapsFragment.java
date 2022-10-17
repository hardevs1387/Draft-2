package com.example.draft1.ui.maps;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.draft1.databinding.FragmentMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment {

    private FragmentMapsBinding binding;
//    MapView mMapView;
//    private GoogleMap googleMap;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentMapsBinding.inflate(inflater, container, false);

//        mMapView = binding.mapView;
//        mMapView.onCreate(savedInstanceState);
//
//        mMapView.onResume(); // needed to get the map to display immediately
//
//        try {
//            MapsInitializer.initialize(getActivity().getApplicationContext());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        mMapView.getMapAsync(new OnMapReadyCallback() {
//            @Override
//            public void onMapReady(GoogleMap mMap) {
//                googleMap = mMap;
//
//                // For showing a move to my location button
//                if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                        && ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//                    ActivityResultLauncher<String[]> locationPermissionRequest =
//                            registerForActivityResult(new ActivityResultContracts
//                                            .RequestMultiplePermissions(), result -> {
//                                        Boolean fineLocationGranted = result.getOrDefault(
//                                                Manifest.permission.ACCESS_FINE_LOCATION, false);
//                                        Boolean coarseLocationGranted = result.getOrDefault(
//                                                Manifest.permission.ACCESS_COARSE_LOCATION,false);
//                                        if (fineLocationGranted != null && fineLocationGranted) {
//                                            // Precise location access granted.
//                                            googleMap.setMyLocationEnabled(true);
//                                            setLocation();
//                                        } else if (coarseLocationGranted != null && coarseLocationGranted) {
//                                            // Only approximate location access granted.
//                                            googleMap.setMyLocationEnabled(true);
//                                            setLocation();
//                                        } else {
//                                            // No location access granted.
//                                            Toast.makeText(getContext(), "Location permission not granted", Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                            );
//
//                    googleMap.setMyLocationEnabled(true);
//                }
//
//
//            }
//        });

        Uri gmmIntentUri = Uri.parse("google.streetview:cbll=-27.4649029,153.0282756");

// Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
// Make the Intent explicit by setting the Google Maps package
        mapIntent.setPackage("com.google.android.apps.maps");

// Attempt to start an activity that can handle the Intent
        startActivity(mapIntent);

        return binding.getRoot();

    }

    //    void setLocation(){
//
//        // For dropping a marker at a point on the Map
//        LatLng sydney = new LatLng(-34, 151);
//        googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker Title").snippet("Marker Description"));
//
//        // For zooming automatically to the location of the marker
//        CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
//        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
//    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
//            }
//        });
    }


    //    @Override
//    public void onResume() {
//        super.onResume();
//        mMapView.onResume();
//    }
//    @Override
//    public void onPause() {
//        super.onPause();
//        mMapView.onPause();
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        mMapView.onDestroy();
//    }
//
//    @Override
//    public void onLowMemory() {
//        super.onLowMemory();
//        mMapView.onLowMemory();
//    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    //public void register(View view, Bundle savedInstanceState);

}
