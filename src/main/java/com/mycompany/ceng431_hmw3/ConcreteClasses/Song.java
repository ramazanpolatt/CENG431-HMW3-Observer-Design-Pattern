package com.mycompany.ceng431_hmw3.ConcreteClasses;

import com.mycompany.ceng431_hmw3.interfaces.*;

import java.util.ArrayList;
import java.util.List;

public class Song implements ISong {

    private Genre genre;
    private int trackId;
    private String name;
    private String artist;
    private int durationInSeconds;
    private int numberOfpopularity;
    private int numberOfLikes;
    private List<IObserver> observerList;



    public Song(Genre genre, int trackId, String name, String artist, int durationInSeconds, int popularity, int numberOfLikes) {
        this.genre = genre;
        this.trackId = trackId;
        this.name = name;
        this.artist = artist;
        this.durationInSeconds = durationInSeconds;
        this.numberOfpopularity = popularity;
        this.numberOfLikes = numberOfLikes;
        observerList = new ArrayList<>();

    }

    @Override
    public Genre getGenre() {
        return genre;
    }

    @Override
    public int getTrackId() {
        return trackId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getArtist() {
        return artist;
    }

    @Override
    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    @Override
    public int getNumberOfpopularity() {
        return numberOfpopularity;
    }

    @Override
    public float getDurationInMinutes() {
        return getDurationInMinutes(getDurationInSeconds(),0);
    }
    private float getDurationInMinutes(float duration,float minutes){
        if (duration < 60){return minutes + (duration/100);}
        return getDurationInMinutes(duration-60,minutes+1);
    }

    @Override
    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    @Override
    public void incresePopularity() {
        numberOfpopularity +=1;

    }

    @Override
    public void increaseLike() {
        numberOfLikes+=1;
    }




    @Override
    public String toString() {
        return "Song{" +
                "genre=" + genre +
                ", trackId=" + trackId +
                ", name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", durationInSeconds=" + durationInSeconds +
                ", numberOfpopularity=" + numberOfpopularity +
                ", numberOfLikes=" + numberOfLikes +
                '}';
    }
}
