package com.TripAround;

/**
* Created by manan on 10/31/2015.
*/
public class ReviewData {
    public String category;
    private String comment;
    private int numPeople;
    private int days;
    private String city;
    private int id;
    private int stars;
    private int distance;
    public int imageID;
    public int ratedBy;

    public enum Categories {
        CLASSIC, ADVENTUROUS, UNEXPLORED, WELLNESS, PANORAMICVIEW,
    }

    ////////////
    public void setCity(String ct){
        city = ct;
    }

    public String getCity(){
        return city;
    }

    ///////////
    public void setStars(int strs) {
        stars = strs;
    }

    public int getStars() {
        return stars;
    }

    ///////////
    public void setDistance(int dist) {
        distance = dist;
    }

    public int getDistance() {
        return distance;
    }

    ////////////
    public void setComment(String cm) {
        comment = cm;
    }

    public String getComment() {
        return comment;
    }
    ////////////
    public void setNumPeople(int num) {
        numPeople = num;
    }

    public int getNumPeople() {
        return numPeople;
    }

    public void setDuration(int day) {
        days = day;
    }

    public int getDuration() {
        return days;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
