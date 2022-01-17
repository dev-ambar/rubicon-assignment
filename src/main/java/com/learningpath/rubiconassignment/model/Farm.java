package com.learningpath.rubiconassignment.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Farm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String OwnerName;
    @Column(unique = true)
    private String farmId;
    private String farmAddress;
    @OneToMany
    @JoinTable(name = "farm_orderMapping",joinColumns =@JoinColumn(name = "farmId"),inverseJoinColumns = @JoinColumn(name="id"))
    private Set<WaterOrder> waterOrders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwnerName() {
        return OwnerName;
    }

    public void setOwnerName(String ownerName) {
        OwnerName = ownerName;
    }

    public String getFarmId() {
        return farmId;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }

    public String getFarmAddress() {
        return farmAddress;
    }

    public void setFarmAddress(String farmAddress) {
        this.farmAddress = farmAddress;
    }

    public Set<WaterOrder> getWaterOrders() {
        return waterOrders;
    }

    public void setWaterOrders(Set<WaterOrder> waterOrders) {
        this.waterOrders = waterOrders;
    }
}
