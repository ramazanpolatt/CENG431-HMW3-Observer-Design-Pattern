package com.mycompany.ceng431_hmw3.ConcreteClasses;

import com.mycompany.ceng431_hmw3.interfaces.ISong;

import java.util.ArrayList;
import java.util.List;

public class SongContainer {
    private SongLoaderFromTxt songLoaderFromTxt;
    private static SongContainer songContainer;
    private List<ISong>  songList;

    private SongContainer(){
        songLoaderFromTxt = new SongLoaderFromTxt("src/main/java/com/mycompany/ceng431_hmw3/FILEIO/tracks.txt");
        songList = new ArrayList<>();
        songLoaderFromTxt.load();
        songList = songLoaderFromTxt.getElements();
    }

    public static SongContainer getInstance(){
        if (songContainer==null){
            songContainer = new SongContainer();
        }
        return songContainer;
    }
    public ISong getSongByTrackId(int trackId)
    {

        for (ISong song:songList )
        {
            if(song.getTrackId() == trackId)
            {
                return song;

            }
        }
        System.out.println("getSongByTrackId could not found matching song");
        System.exit(0);
        return null;
    }

    public List<ISong> getSongList() {
        return songList;
    }
}
