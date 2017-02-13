package com.example.xu.seefireworkstonight.fragmentsOfMainActivity.fargmentsOfMyMap;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xu.seefireworkstonight.MainActivity;
import com.example.xu.seefireworkstonight.Manifest;
import com.example.xu.seefireworkstonight.Model.Site;
import com.example.xu.seefireworkstonight.R;
import com.example.xu.seefireworkstonight.SiteActivity;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;

/**
 * Created by xu on 5/25/16.
 */
public class SitesMap extends SupportMapFragment implements OnMapReadyCallback {

    private SupportMapFragment mapFragment;
    private Site[] sites;
    private HashMap<LatLng, Site> position_Site;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        position_Site = new HashMap<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_sitesmap, container, false);

        mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return view;
    }

    @Override
    public void onMapReady(GoogleMap map) {
        sites = ((MainActivity)getActivity()).getSiteArray();
        System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk"+sites[0]+"#######"+sites[1]+"#######"+sites[2]);

        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);



        // Need to modify, Reference : http://stackoverflow.com/questions/32491960/android-check-permission-for-locationmanager
       /* try{
            map.setMyLocationEnabled(true);
        }catch(SecurityException e){
            System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhkjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
        }*/


        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        for(int i=0; i<sites.length; i++){
            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+sites[i]);
            LatLng site = new LatLng(sites[i].getLatitude(), sites[i].getLongitude());
            MarkerOptions marker = new MarkerOptions().position(site).title(sites[i].getSiteName())
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.green_dot));

            position_Site.put(marker.getPosition(), sites[i]);
            map.addMarker(marker);
            builder.include(marker.getPosition());
        }

        LatLngBounds bounds = builder.build();

        int padding = 0;
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        map.animateCamera(cameraUpdate);




        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                Intent intent =new Intent(getActivity(), SiteActivity.class);

                Bundle bundle=new Bundle();
                bundle.putSerializable("site", position_Site.get(marker.getPosition()));

                intent.putExtras(bundle);

                startActivity(intent);

                return true;
            }
        });
    }

}
