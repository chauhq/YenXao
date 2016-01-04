package com.project.chauhq.yenxao.api;

import com.project.chauhq.yenxao.model.AddressResponse;
import com.project.chauhq.yenxao.model.Authentication;
import com.project.chauhq.yenxao.model.DirectionsResponse;
import com.project.chauhq.yenxao.model.HomeResponse;
import com.project.chauhq.yenxao.model.ProductResponse;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
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

    @GET("home.php")
    Call<HomeResponse> getHomeResponse();

    @GET("/yenxao/api/product.php")
    Call<ProductResponse.Product> getProductResponse();

    @POST("/oauth2/v4/token")
    @FormUrlEncoded
    Call<Authentication> getAcessToken(@Field("code") String code,
                                       @Field("client_id") String clientId,
                                       @Field("client_secret") String clientSecret,
                                       @Field("redirect_uri") String redirectUri,
                                       @Field("grant_type") String grantType);

}
