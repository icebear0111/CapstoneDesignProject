package com.mysite.project.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "accidents")
public class AccidentEntity {
    @Id
    private Long accidentId;

    @Column(nullable = false)
    private String location;

    @Column(name = "vehicle_a_direction", nullable = false)
    private String vehicleADirection;

    @Column(name = "vehicle_b_direction", nullable = false)
    private String vehicleBDirection;

    @Column(name = "fault_percentage_a", nullable = false)
    private Integer faultPercentageA;

    @Column(name = "fault_percentage_b", nullable = false)
    private Integer faultPercentageB;

    @Column(name = "accident_description", nullable = false)
    private String accidentDescription;

    @Column(name = "portal_link", nullable = false)
    private String portalLink;

    @Column(name = "video_link", nullable = false)
    private String videoLink;

    // Getters and setters
    public Long getAccidentId() {
        return accidentId;
    }

    public String getLocation() {
        return location;
    }

    public String getVehicleADirection() {
        return vehicleADirection;
    }

    public String getVehicleBDirection() {
        return vehicleBDirection;
    }

    public Integer getFaultPercentageA() {
        return faultPercentageA;
    }

    public Integer getFaultPercentageB() {
        return faultPercentageB;
    }

    public String getAccidentDescription() {
        return accidentDescription;
    }

    public String getPortalLink() {
        return portalLink;
    }

    public String getVideoLink() {
        return videoLink;
    }
}

