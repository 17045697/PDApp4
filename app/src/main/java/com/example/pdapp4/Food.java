package com.example.pdapp4;
import java.io.Serializable;

public class Food implements Serializable {
    private int id;
    private String location;
    private String address;
    private String date;
    private String bestdish;
    private int stars;

    public Food(int id, String location, String address,String date ,String bestdish,int stars){
        this.id = id;
        this.location = location;
        this.address = address;
        this.date = date;
        this.bestdish = bestdish;
        this.stars = stars;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBestdish() {
        return bestdish;
    }

    public void setBestdish(String bestdish) {
        this.bestdish = bestdish;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        return "ID:" + id + ", " + "Location: " + location + ", " + "Address: " + address + ", " + "Date: " + date + ", " +"Recommended Dish: " + bestdish + ", " + "Stars: " +stars;
    }
}


