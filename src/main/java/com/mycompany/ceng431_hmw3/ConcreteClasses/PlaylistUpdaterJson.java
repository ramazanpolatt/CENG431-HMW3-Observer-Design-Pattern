package com.mycompany.ceng431_hmw3.ConcreteClasses;

import com.mycompany.ceng431_hmw3.interfaces.IPLaylist;
import com.mycompany.ceng431_hmw3.interfaces.ISong;
import com.mycompany.ceng431_hmw3.interfaces.IUpdater;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class PlaylistUpdaterJson implements IUpdater<IPLaylist> {

    private String path;

    public PlaylistUpdaterJson(String path) {
        this.path = path;
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
        System.out.println(jsonArray);
        return  jsonArray;
    }
    @Override
    public void update(IPLaylist item)
    {
        helperUpdate(item);
    }

    private void helperUpdate(IPLaylist item){
        boolean isFound = false;
        JSONArray jsonArray = getList(path);
        if (jsonArray.size()>0){
            JSONObject jsonObject;
            int playlistId=0;
            for (int i = 0; i < jsonArray.size(); i++) {
                jsonObject= (JSONObject) jsonArray.get(i);
                playlistId = (int) ((long)jsonObject.get("id"));
                if (playlistId== item.getId()){
                    jsonArray.remove(i);
                    isFound = true;
                    break;
                }

            }
        }
        jsonArray.add(convertIPlaylistToJsonObject(item));
        saveAsJsonFile(path,jsonArray);
    }

    private JSONObject convertIPlaylistToJsonObject(IPLaylist item){

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",item.getId());
        jsonObject.put("creatorUsername",item.getCreatorUserName());
        jsonObject.put("playListname",item.getPlayListName());
        jsonObject.put("playlistType",item.getPlaylistType().toString());
        jsonObject.put("numberOfSongs",item.numberOfPlayables());
        jsonObject.put("durationInSeconds",item.getDurationInSeconds());
        jsonObject.put("songList",returnJSONSongArrayOfGivenList(item.getPlayableList()));
        return jsonObject;
    }

    private JSONArray returnJSONSongArrayOfGivenList(List<ISong> iPlayableList) {
        JSONArray tempJSONArray = new JSONArray();
        for (int i = 0; i < iPlayableList.size(); i++) {
            tempJSONArray.add(convertSongToJSONObject(iPlayableList.get(i)));
        }
        return tempJSONArray;
    }

    private JSONObject convertSongToJSONObject(ISong tempSong){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Genre",tempSong.getGenre().toString());
        jsonObject.put("TrackID",tempSong.getTrackId());
        jsonObject.put("Name",tempSong.getName());
        jsonObject.put("Artist",tempSong.getArtist());
        jsonObject.put("Duration",tempSong.getDurationInMinutes());
        jsonObject.put("Popularity",tempSong.getNumberOfpopularity());
        jsonObject.put("Likes",tempSong.getNumberOfLikes());
        return jsonObject;
    }


    private static void saveAsJsonFile(String path, JSONArray jsonArray) {

        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        printWriter.write(jsonArray.toJSONString());
        printWriter.flush();
        printWriter.close();

    }
}
