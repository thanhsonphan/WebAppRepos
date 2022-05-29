package com.java.RateSystem.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue
    private UUID UuId;
    private Integer ServiceId;
    private Character UserName;
    private Integer Point;
    private Character Comment;
    private Date Date;

    public Rating(){
    }

    public Rating(UUID uuid, Integer sid, Integer point, Character comment, Date date){
        UuId = uuid;
        ServiceId = sid;
        Point = point;
        Comment = comment;
        Date = date;
    }

    public UUID getUuId() {
        return UuId;
    }
    public void setUuId(UUID uuid){
        UuId = uuid;
    }
    public Integer getServiceId(){
        return ServiceId;
    }
    public void setServiceId(Integer sid) {
        ServiceId = sid;
    }

    public Integer getPoint(){
        return Point;
    }
    public void setPoint(Integer point) {
        Point = point;
    }

    public Character getUserName() {
        return UserName;
    }
    public void setUserName(Character userName) {
        UserName = userName;
    }

    public Character getComment(){
        return Comment;
    }
    public void setComment(Character comment) {
        Comment = comment;
    }

    public Date getDate(){
        return Date;
    }
    public void setDate(Date date){
        Date = date;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "UuId='" + UuId + '\'' +
                ", ServiceId='" + ServiceId + '\'' +
                ", Point='" + Point + '\'' +
                ", Comment='" + Comment + '\'' +
                ", Date='" + Date + '\'' +
                '}';
    }
}
