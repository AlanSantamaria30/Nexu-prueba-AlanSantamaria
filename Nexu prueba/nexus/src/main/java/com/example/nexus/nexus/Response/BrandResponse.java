package com.example.nexus.nexus.Response;


public class BrandResponse {

    private Long id;
    private String name;
    private Long averagePrice;

     public BrandResponse() {
    }

    public BrandResponse(String name, Long averagePrice) {
        this.name = name;
        this.averagePrice = averagePrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Long averagePrice) {
        this.averagePrice = averagePrice;
    }   
}
