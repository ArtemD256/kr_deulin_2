package com.example.kr_deulin_2.model;

import lombok.Getter;

@Getter
public class Services {


    public Services(String description, Integer price,int id) {
        this.description = description;
        this.price = price;
        this.id = id;
    }

    private final Integer id;

    private final String description;

    private final Integer price;

}
