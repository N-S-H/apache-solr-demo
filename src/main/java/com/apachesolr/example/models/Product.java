package com.apachesolr.example.models;

import org.apache.solr.client.solrj.beans.Field;

public class Product {


    @Field("id")
    String id;

    @Field("name")
    String name;

    @Field("price")
    String price;


    public void setId(String id) {
        this.id = id;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setPrice(String price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}