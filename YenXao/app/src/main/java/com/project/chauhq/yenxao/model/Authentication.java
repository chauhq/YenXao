package com.project.chauhq.yenxao.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by chauhq on 22/12/2015.
 */
@Data
public class Authentication {
    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("token_type")
    private String tokenType;
}
