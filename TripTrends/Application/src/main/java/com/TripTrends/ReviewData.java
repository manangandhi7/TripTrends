package com.TripTrends;

/**
* Created by manan on 10/31/2015.
*/
public class ReviewData {
    private int curClass;
    private String comment;
    private int numPeople;
    private int days;
    private String city;
    private int id;

    public enum Categories {
        CLASSIC, ADVENTUROUS, UNEXPLORED, WELLNESS, PANORAMICVIEW,
    }

    ////////////
    public void setCity(String ct){
        city = ct;
    }

    public String getCt(){
        return city;
    }

    ///////////
    public void setCategory(int cl) {
        curClass = cl;
    }

    public int getCategory() {
        return curClass;
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
