package com.mycompany.ceng431_hmw3.ConcreteClasses;

import com.mycompany.ceng431_hmw3.interfaces.ILoader;
import com.mycompany.ceng431_hmw3.interfaces.IPLaylist;
import com.mycompany.ceng431_hmw3.interfaces.ISong;
import com.mycompany.ceng431_hmw3.interfaces.PlaylistType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistLoaderFromJson <IPlaylist> implements ILoader<IPlaylist> {

    private String path;
    private List<IPlaylist> listOfPlaylists;

    public PlaylistLoaderFromJson(String path) {
        this.path = path;
        listOfPlaylists = new ArrayList<>();
    }

    @Override
    public void load() {
        listOfPlaylists= (List<IPlaylist>) getPlaylists();
    }

    @Override
    public List<IPlaylist> getElements() {
        if (listOfPlaylists.size()==0){
            System.out.println("Any playlist not added yet");
        }
        return listOfPlaylists;
    }

    private JSONArray getList(String path){
        JSONParser tempParser = new JSONParser();
        JSONArray jsonArray = new JSONArray();
        try {
            jsonArray = (JSONArray) tempParser.parse(new FileReader(path));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  jsonArray;
    }

    private List<IPLaylist> getPlaylists(){
        List<IPLaylist> tempPlaylist = new ArrayList<>();
        JSONArray jsonArray = getList(path);
        JSONObject jsonObject=null;
        for (int i = 0; i < jsonArray.size(); i++) {
            jsonObject= (JSONObject) jsonArray.get(i);
            tempPlaylist.add(convertJsonFileToPlaylist(jsonObject));
        }
        return tempPlaylist;

    }

    private IPLaylist convertJsonFileToPlaylist(JSONObject jsonObject){
        IPLaylist tempPlayList;

        tempPlayList = new SongPlaylist(
                (int) ((long)jsonObject.get("id")),
                ((String)jsonObject.get("creatorUsername")),
                ((String)jsonObject.get("playListname")),
                PlaylistType.valueOf((String)jsonObject.get("playlistType")),
                (int) ((long)jsonObject.get("numberOfSongs")),
                (int) ((long)jsonObject.get("durationInSeconds")),
                convertJsonSongListToSongList((JSONArray)jsonObject.get("songList"))
        );
        return tempPlayList;
    }

    private List<ISong> convertJsonSongListToSongList(JSONArray jsonArray){
        List<ISong> tempSongList = new ArrayList<>();
        JSONObject jsonObject =null;
        int trackId =0;
        for (int i = 0; i < jsonArray.size(); i++) {
            jsonObject = (JSONObject) jsonArray.get(i);
            trackId = (int)((long)jsonObject.get("TrackID"));
            tempSongList.add(SongContainer.getInstance().getSongByTrackId(trackId));

        }
        return tempSongList;
    }

}
