package com.project.chauhq.yenxao.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * Created by chauhq on 18/12/2015.
 */

@Data
public class ProductResponse {
    private String status;
    private List<Product> products = new ArrayList<>();

    @Data
    public class Product {
        private String name;
        private int money;
        private String description;
        private String urlImage;
    }
}
