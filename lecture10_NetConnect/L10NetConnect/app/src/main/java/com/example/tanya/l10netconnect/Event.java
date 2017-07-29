package com.example.tanya.l10netconnect;

/**
 * Created by Admin on 7/5/2016.
 */
public class Event {
    String name;
    String location;
    String type;
    String topic;

    public Event(String name, String location, String type, String topic) {
        this.name = name;
        this.location = location;
        this.type = type;
        this.topic = topic;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public String getTopic() {
        return topic;
    }
}
