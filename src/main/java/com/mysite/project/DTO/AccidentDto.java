package com.mysite.project.DTO;

public class AccidentDto {
    private String location;
    private String vehicleADirection;
    private String vehicleBDirection;
    private Integer faultPercentageA;
    private Integer faultPercentageB;
    private String accidentDescription;
    private String portalLink;
    private String videoLink;

    public AccidentDto() {}

    public AccidentDto(String location, String vehicleADirection, String vehicleBDirection, Integer faultPercentageA, Integer faultPercentageB, String accidentDescription, String portalLink, String videoLink) {
        this.location = location;
        this.vehicleADirection = vehicleADirection;
        this.vehicleBDirection = vehicleBDirection;
        this.faultPercentageA = faultPercentageA;
        this.faultPercentageB = faultPercentageB;
        this.accidentDescription = accidentDescription;
        this.portalLink = portalLink;
        this.videoLink = videoLink;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getVehicleADirection() {
        return vehicleADirection;
    }

    public void setVehicleADirection(String vehicleADirection) {
        this.vehicleADirection = vehicleADirection;
    }

    public String getVehicleBDirection() {
        return vehicleBDirection;
    }

    public void setVehicleBDirection(String vehicleBDirection) {
        this.vehicleBDirection = vehicleBDirection;
    }

    public Integer getFaultPercentageA() {
        return faultPercentageA;
    }

    public void setFaultPercentageA(Integer faultPercentageA) {
        this.faultPercentageA = faultPercentageA;
    }

    public Integer getFaultPercentageB() {
        return faultPercentageB;
    }

    public void setFaultPercentageB(Integer faultPercentageB) {
        this.faultPercentageB = faultPercentageB;
    }

    public String getAccidentDescription() {
        return accidentDescription;
    }

    public void setAccidentDescription(String accidentDescription) {
        this.accidentDescription = accidentDescription;
    }

    public String getPortalLink() {
        return portalLink;
    }

    public void setPortalLink(String portalLink) {
        this.portalLink = portalLink;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }
}