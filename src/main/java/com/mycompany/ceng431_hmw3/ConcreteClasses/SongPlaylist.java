package com.mycompany.ceng431_hmw3.ConcreteClasses;

import com.mycompany.ceng431_hmw3.interfaces.IPLaylist;
import com.mycompany.ceng431_hmw3.interfaces.ISong;
import com.mycompany.ceng431_hmw3.interfaces.PlaylistType;

import java.util.ArrayList;
import java.util.List;

public class SongPlaylist  implements IPLaylist<ISong> {

    private int id;
    private String creatorUserName;
    private String playlistName;
    private PlaylistType playlistType;
    private int numberOfSongs;
    private int durationInSeconds;
    private List<ISong> songList;

    public SongPlaylist(int id, String creatorUserName, String playlistName, PlaylistType playlistType) {
        this(id,creatorUserName,playlistName,playlistType,0,0,new ArrayList<ISong>());
    }

    public SongPlaylist(int id, String creatorUserName, String playlistName, PlaylistType playlistType, int numberOfSongs, int durationInSeconds, List<ISong> songList) {
        this.id = id;
        this.creatorUserName = creatorUserName;
        this.playlistName = playlistName;
        this.playlistType = playlistType;
        this.numberOfSongs = numberOfSongs;
        this.durationInSeconds = durationInSeconds;
        this.songList = songList;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getCreatorUserName() {
        return creatorUserName;
    }

    @Override
    public String getPlayListName() {
        return playlistName;
    }


    @Override
    public PlaylistType getPlaylistType() {
        return playlistType;
    }

    @Override
    public int numberOfPlayables() {
        return numberOfSongs;
    }

    @Override
    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    @Override
    public float getDurationInMinutes() {
        return getDurationInSeconds()/60;
    }

    @Override
    public List<ISong> getPlayableList() {
        return songList;
    }

    @Override
    public void add(ISong song)
    {
                songList.add(song);
                numberOfSongs+=1;
                durationInSeconds+=song.getDurationInSeconds();
    }

    @Override
    public String toString() {
        return "SongPlaylist{" +
                "id=" + id +
                ", creatorUserName='" + creatorUserName + '\'' +
                ", playlistName='" + playlistName + '\'' +
                ", playlistType=" + playlistType +
                ", numberOfSongs=" + numberOfSongs +
                ", durationInSeconds=" + durationInSeconds +
                ", songList=" + songList +
                '}';
    }

    @Override
    public void remove(ISong song) {
        songList.remove(song);
        numberOfSongs-=1;
        durationInSeconds-=song.getDurationInSeconds();
    }
}
