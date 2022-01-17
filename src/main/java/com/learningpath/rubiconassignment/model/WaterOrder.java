package com.learningpath.rubiconassignment.model;

import javax.persistence.*;

@Entity
public class WaterOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String farmId;
    private String startDateTime;
    private Integer duration;
    @Transient
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFarmId() {
        return farmId;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }


    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "WaterOrder{" +
                "farmId='" + farmId + '\'' +
                ", startDateTime='" + startDateTime + '\'' +
                ", duration=" + duration +
                '}';
    }
}
