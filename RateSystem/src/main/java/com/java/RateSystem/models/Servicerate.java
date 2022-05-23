package com.java.RateSystem.models;

import javax.persistence.*;

@Entity
@Table(name = "Services_rate")
public class Servicerate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long ServiceId;
    private String ServiceName;
    private String Image;
    private String Description;

    public Servicerate() {
    }

    public Servicerate(String serviceName, String image, String description) {
        ServiceName = serviceName;
        Image = image;
        Description = description;
    }

    public Long getServiceId() {
        return ServiceId;
    }

    public void setServiceId(Long serviceId) {
        ServiceId = serviceId;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String serviceName) {
        ServiceName = serviceName;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public String toString() {
        return "Servicerate{" +
                "ServiceName='" + ServiceName + '\'' +
                ", Image='" + Image + '\'' +
                ", Description='" + Description + '\'' +
                '}';
    }
}
