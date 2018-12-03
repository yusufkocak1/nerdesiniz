package com.bilgetech.nerdesiniz;


import java.util.HashMap;
import java.util.Map;

/**
 * TODO: implement
 */
public class Person {

    private String id;
    private String room_id;
    private String name;
    private String color;
    private Location location;

    public Person(String id, String room_id, String name, String color, Location location) {
        this.id = id;
        this.room_id = room_id;
        this.name = name;
        this.color = color;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
