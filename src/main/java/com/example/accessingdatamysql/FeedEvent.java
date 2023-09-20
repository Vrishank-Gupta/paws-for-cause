package com.example.accessingdatamysql;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Calendar;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
@Entity
public class FeedEvent implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    public String feedingSpot;
    public String timeOfDay;

    public boolean fed = false;

    public FeedEvent() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Set the time zone to IST (Indian Standard Time)
        ZoneId istZone = ZoneId.of("Asia/Kolkata");
        ZonedDateTime istDateTime = ZonedDateTime.of(localDateTime, istZone);

        this.timeOfDay = istDateTime.format(formatter);
        this.fed = true;
    }

    public FeedEvent(String timeOfDay) {
       this.timeOfDay = timeOfDay;
       this.fed = true;
    }

    public String getFeedingSpot() {
        return feedingSpot;
    }

    public void setFeedingSpot(String feedingSpot) {
        this.feedingSpot = feedingSpot;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    private String getTime(int timeOfDay) {
        if(timeOfDay >= 0 && timeOfDay < 12){
            return "Morning";
        }else if(timeOfDay >= 12 && timeOfDay < 16){
            return "Afternoon";
        }else if(timeOfDay >= 16 && timeOfDay < 21){
            return "Evening";
        }else{
            return "Night";
        }
    }

    @Override
    public String toString() {
        return "FeedEvent{" +
                "id=" + id +
                ", feedingSpot='" + feedingSpot + '\'' +
                ", timeOfDay=" + timeOfDay +
                ", fed=" + fed +
                '}';
    }
}
