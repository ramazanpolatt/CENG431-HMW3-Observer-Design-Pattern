package com.mycompany.ceng431_hmw3.interfaces;

import java.util.List;

public interface IPLaylist<T> {
    public int getId();

    public String getCreatorUserName();

    public String getPlayListName();

    public PlaylistType getPlaylistType();

    public int numberOfPlayables();

    public int getDurationInSeconds();

    public float getDurationInMinutes();

    public List<T> getPlayableList();

    public void add(T playable);

    public void remove(T playable);



}
