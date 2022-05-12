package com.mycompany.ceng431_hmw3.interfaces;

public interface ISong {


    public Genre getGenre();

    public int  getTrackId();

    public String getName();

    public String getArtist();

    public int getDurationInSeconds();

    public float getDurationInMinutes();

    public int getNumberOfpopularity();

    public int getNumberOfLikes();

    public void incresePopularity();

    public void increaseLike();
}
