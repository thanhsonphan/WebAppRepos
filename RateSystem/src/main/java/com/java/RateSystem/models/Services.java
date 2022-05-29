package com.java.RateSystem.models;

import javax.persistence.*;

@Entity
@Table(name = "services")
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer Id;
    private String Name;
    private String ServiceImg;
    private String ServiceDesc;
    private Double EverageCore;

    public Services(Integer id, String name, String serviceing, String servicedesc, Double everagecore) {
        Id = id;
        Name = name;
        ServiceImg = serviceing;
        ServiceDesc = servicedesc;
        EverageCore = everagecore;

    }

    public Services(){

    }

    public Integer getServiceId() {
        return Id;
    }

    public void setServiceId(Integer serviceId) {
        Id = serviceId;
    }

    public String getServiceName() {
        return Name;
    }

    public void setServiceName(String serviceName) {
        Name = serviceName;
    }

    public String getImage() {
        return ServiceImg;
    }

    public void setImage(String image) {
        ServiceImg = image;
    }

    public String getDescription() {
        return ServiceDesc;
    }

    public void setDescription(String description) {
        ServiceDesc = description;
    }

    public void setEverageCore(double everageCore){
        EverageCore = everageCore;
    }
    public double getEverageCore(){
        return EverageCore;
    }

    @Override
    public String toString() {
        return "Services{" +
                "ID='" + Id + '\'' +
                ", Name='" + Name + '\'' +
                ", Service imge='" + ServiceImg + '\'' +
                ", Service Description='" + ServiceDesc + '\'' +
                ", Everage Core='" + EverageCore + '\'' +
                '}';
    }
}
