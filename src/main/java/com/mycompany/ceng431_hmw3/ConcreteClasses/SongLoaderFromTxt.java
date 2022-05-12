package com.mycompany.ceng431_hmw3.ConcreteClasses;

import com.mycompany.ceng431_hmw3.interfaces.Genre;
import com.mycompany.ceng431_hmw3.interfaces.ILoader;
import com.mycompany.ceng431_hmw3.interfaces.ISong;

import java.util.ArrayList;
import java.util.List;

public class SongLoaderFromTxt implements ILoader<ISong> {

    private  String path;
    private List<ISong> songList;
    private TxtFileReader txtFileReader;


    public SongLoaderFromTxt(String path) {
        this.path = path;
        this.songList= new ArrayList<>();
        txtFileReader= new TxtFileReader(path);
    }

    @Override
    public void load() {
        List<ISong> tempSongList= new ArrayList<>();
        List<String[]> songStringList= txtFileReader.getList();


        try
        {

            for (String[] songString:songStringList)
            {

                tempSongList.add( new Song(Genre.values()[Integer.parseInt(songString[0])-1],
                                        Integer.parseInt(songString[1]),
                                            songString[2],songString[3],Integer.parseInt(songString[4]),
                                              Integer.parseInt(songString[5]),Integer.parseInt(songString[6])));
            }



        }
        catch (Exception exception)
        {

            System.out.println("Error occurred while loading songs");
            System.exit(0);
        }

        songList=tempSongList;
    }



    @Override
    public List<ISong> getElements() {
        return songList;
    }
}
