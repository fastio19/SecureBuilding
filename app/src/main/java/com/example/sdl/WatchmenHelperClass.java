package com.example.sdl;

public class WatchmenHelperClass {

    String name,buildingName,watchmenId;
    public WatchmenHelperClass(String name, String buildingName, String watchmenId) {
        this.name = name;
        this.buildingName = buildingName;
        this.watchmenId = watchmenId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getWatchmenId() {
        return watchmenId;
    }

    public void setWatchmenId(String watchmenId) {
        this.watchmenId = watchmenId;
    }
}
