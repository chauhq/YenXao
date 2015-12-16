package com.project.chauhq.yenxao.api;

import com.project.chauhq.yenxao.model.AddressResponse;
import com.project.chauhq.yenxao.model.DirectionsResponse;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * @author ChauHQ
 */
public interface ApiClient {
    @GET("maps/api/geocode/json?sensor=true")
    Call<AddressResponse> getAddressResponse(@Query("latlng") String latlng);

    @GET("maps/api/directions/json?sensor=false")
    Call<DirectionsResponse> getDirectionsResponse(@Query("origin") String origin,
                                                @Query("destination") String destination);
}
