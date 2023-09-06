package com.example.accessingdatamysql;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Calendar;

@Entity
public class FeedingEvent implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    public FeedingSpot feedingSpot;
    public String timeOfDay;

    public boolean fed = false;

    public FeedingEvent() {
        Calendar c = Calendar.getInstance();
        this.timeOfDay = getTime(c.get(Calendar.HOUR_OF_DAY));
        this.fed = true;
    }


    public FeedingEvent(String timeOfDay) {
       this.timeOfDay = timeOfDay;
       this.fed = true;
    }

    public FeedingSpot getFeedingSpot() {
        return feedingSpot;
    }

    public void setFeedingSpot(FeedingSpot feedingSpot) {
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
        return "FeedingEvent{" +
                "feedingSpot=" + feedingSpot +
                ", timeOfDay='" + timeOfDay + '\'' +
                ", fed=" + fed +
                '}';
    }
}
