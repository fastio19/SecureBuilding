package com.example.sdl;

public class RequestHelperClass {
    String owner,guest,status,buildingName;

    public RequestHelperClass(){

    }
    public RequestHelperClass(String owner, String guest,String status) {
        this.owner = owner;
        this.guest = guest;
        this.status=status;
    }


    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getGuest() {
        return guest;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setGuest(String guest) {
        this.guest = guest;
    }
}
