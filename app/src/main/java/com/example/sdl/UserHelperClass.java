package com.example.sdl;

public class UserHelperClass {
    String name;
    String username;
    String email;
    String phoneNo;
    String password;
    String buildingName;
    String flatNo;
    String vehicleNo;
    String secretary;
    public UserHelperClass(){
    }
    public UserHelperClass(String name){
        this.name=name;
    }

    public String getSecretary() {
        return secretary;
    }

    public void setSecretary(String secretary) {
        this.secretary = secretary;
    }

    public UserHelperClass(String name, String username, String email, String phoneNo, String password,
                           String buildingName, String flatNo, String vehicleNo, String secretary) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.phoneNo = phoneNo;
        this.password = password;
        this.buildingName=buildingName;
        this.flatNo=flatNo;
        this.vehicleNo=vehicleNo;
        this.secretary=secretary;
    }
    public String getFlatNo() {
        return flatNo;
    }

    public void setFlatNo(String flatNo) {
        this.flatNo = flatNo;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
