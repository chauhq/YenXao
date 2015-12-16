package com.project.chauhq.yenxao.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * @author ChauHQ
 */

@Data
public class AddressResponse {
    private String status;

    @SerializedName("results")
    private List<Address> addresses = new ArrayList<>();

    @Data
    public class Address {
        @SerializedName("formatted_address")
        private String address;

        @SerializedName("address_components")
        private List<DetailAddress> detailAddresses;

        @Data
        public class DetailAddress {
            @SerializedName("long_name")
            private String longName;

        }
    }
}
