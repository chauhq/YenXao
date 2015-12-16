package com.project.chauhq.yenxao.ui.map;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.project.chauhq.yenxao.BaseFragment;
import com.project.chauhq.yenxao.R;
import com.project.chauhq.yenxao.api.ApiClient;
import com.project.chauhq.yenxao.api.RequestApi;
import com.project.chauhq.yenxao.model.DirectionsResponse;
import com.project.chauhq.yenxao.ui.Home.HomeFragment;
import com.project.chauhq.yenxao.ui.util.GPS;

import org.androidannotations.annotations.EFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * @author ChauHQ
 */
@EFragment(R.layout.fragment_map)
public class MapFragment extends BaseFragment implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, GoogleMap.OnMapClickListener {
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private LatLng mLatLng;
    private boolean mIsReLoad;

    @Override
    protected void afterViews() {
        if (mIsReLoad) {
            return;
        }
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        mMap = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMap();
        mMap.setInfoWindowAdapter(new InfoWIndownMapAdapter());
        mMap.addMarker(new MarkerOptions().position(HomeFragment.MY_LAT_LNG).title("Việt Nam").snippet("k110/47 Phan Thanh, Thanh Khê, Đà Nẵng"));

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        if (!GPS.isGPSAvailability(getActivity())) {
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            mLatLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
            mMap.addMarker(new MarkerOptions().position(mLatLng));
            CameraPosition cameraPosition = CameraPosition.builder().bearing(90).target(mLatLng).zoom(13).build();
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            ApiClient apiClient = RequestApi.requestAdressApi();
            String origin = HomeFragment.MY_LAT_LNG.latitude + "," + HomeFragment.MY_LAT_LNG.longitude;
            String destination = mLatLng.latitude + "," + mLatLng.longitude;
            Call<DirectionsResponse> call = apiClient.getDirectionsResponse(origin, destination);
            call.enqueue(new Callback<DirectionsResponse>() {
                @Override
                public void onResponse(Response<DirectionsResponse> response, Retrofit retrofit) {
                    PolylineOptions options = new PolylineOptions().width(5).color(Color.GREEN).geodesic(true);
                    // use polyline to draw
                    String path = response.body().getRoutes().get(0).getOverviewpolyline().getPoint();
                    List<LatLng> list = decodePoly(path);
                    for (int i = 0; i < list.size(); i++) {
                        options.add(list.get(i));
                    }
                    mMap.addPolyline(options);
                    // draw from lat anf lng
                    /*List<DirectionsResponse.Route.Leg.Step> steps = response.body().getRoutes().get(0).getLegs().get(0).getSteps();
                    for (int i = 0; i < steps.size(); i++) {
                        Log.d("xxxx1", steps.get(i).getStartLocation().getLng());
                        double lat = Double.valueOf(steps.get(i).getStartLocation().getLat());
                        double lng = Double.valueOf(steps.get(i).getStartLocation().getLng());
                        LatLng latLng = new LatLng(lat, lng);
                        options.add(latLng);
                    }*/

                }

                @Override
                public void onFailure(Throwable t) {
                }
            });
            // use geocoder to get address from lat and lng
            /*Call<AddressResponse> call = apiClient.getAddressResponse(mLatLng.latitude + "," + mLatLng.longitude);
            call.enqueue(new Callback<AddressResponse>() {
                @Override
                public void onResponse(Response<AddressResponse> response, Retrofit retrofit) {
                    if (!response.body().getAddresses().isEmpty()) {
                        mMap.addMarker(new MarkerOptions().position(mLatLng).title(response.body().getAddresses().get(0).getAddress()));
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Log.d("xxxx", "error");
                }
            });*/
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onResume() {
        mGoogleApiClient.connect();
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mGoogleApiClient.disconnect();
        mIsReLoad = true;
    }

    private void moveDefaultAddress() {
        CameraPosition cameraPosition = CameraPosition.builder().bearing(90).target(HomeFragment.MY_LAT_LNG).zoom(13).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    public void showGPSDialog() {
        if (!GPS.isGPSAvailability(getActivity())) {
            new AlertDialog.Builder(getActivity()).setMessage("Do you wana turn on GPS")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(i);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            moveDefaultAddress();
                        }
                    }).show();
        }
    }

    @Override
    public void onMapClick(LatLng latLng) {
    }

    private List<LatLng> decodePoly(String encoded) {

        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }

        return poly;
    }

    private class InfoWIndownMapAdapter implements GoogleMap.InfoWindowAdapter {

        @Override
        public View getInfoWindow(Marker marker) {
            View v = LayoutInflater.from(getActivity()).inflate(R.layout.custom_info_window_map, null, false);
            TextView tvTitle = ((TextView) v.findViewById(R.id.tvTitle));
            tvTitle.setText(marker.getTitle());
            TextView tvSnippet = ((TextView) v.findViewById(R.id.tvSnippet));
            tvSnippet.setText(marker.getSnippet());

            return v;
        }

        @Override
        public View getInfoContents(Marker marker) {
            return null;
        }
    }
}
