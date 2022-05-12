package com.mycompany.ceng431_hmw3.FILEIO;

import java.io.*;
import java.util.*;

import com.mycompany.ceng431_hmw3.ConcreteClasses.*;
import com.mycompany.ceng431_hmw3.interfaces.IPLaylist;
import com.mycompany.ceng431_hmw3.interfaces.ISong;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class JsonFileIO implements IFileIO {
    private JSONParser jsonParser;
    private String path;
    List<ISong> songList;

   
    public JsonFileIO(String path) {
        this.path = path;
        this.jsonParser = new JSONParser();

    }


    public JSONArray getList(String path){
        JSONParser tempParser = new JSONParser();
        JSONArray jsonArray = new JSONArray();
        try {
            jsonArray = (JSONArray) tempParser.parse(new FileReader("src/main/java/com/mycompany/ceng431_hmw3/FILEIO/deneme.json"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(jsonArray);
        return  jsonArray;
    }

    @Override
    public void writeFile(String path) throws IOException, ParseException {

    }

   /* private void addASongIntoJsonFile(int playlistID,ISong tempSong){
        JSONObject tempObject = convertSongToJSONObject(tempSong);
        JSONArray jsonArray = (JSONArray) getList(path);
        JSONObject tempObjectForJSONFile;
        for (int i = 0; i < jsonArray.size(); i++) {
            tempObjectForJSONFile = (JSONObject) jsonArray.get(i);
            System.out.println(tempObjectForJSONFile.get("id"));
            long id = ((long)tempObjectForJSONFile.get("id"));// it returns long from json file
            if (id==playlistID){
                ((JSONArray)tempObjectForJSONFile.get("songList")).add(tempObject);

            }
        }
        saveAsJsonFile(path,jsonArray);
    }

    private void removeAFileFromJsonFile(int playlistID,ISong tempSong){
        JSONObject tempObject = convertSongToJSONObject(tempSong);
        JSONArray jsonArray = (JSONArray) getList(path);
        JSONObject tempObjectForJSONFile;
        for (int i = 0; i < jsonArray.size(); i++) {
            tempObjectForJSONFile = (JSONObject) jsonArray.get(i);
            //System.out.println(tempObjectForJSONFile.get("id"));
            long id = ((long)tempObjectForJSONFile.get("id"));// it returns long from json file
            if (id==playlistID){
                removeSongFromJsonSongList((JSONArray) tempObjectForJSONFile.get("songs"), tempSong);
            }
        }
        saveAsJsonFile(path,jsonArray);
    }
*/


    private void removeSongFromJsonSongList(JSONArray jsonArray,ISong tempSong){
        JSONObject tempObjectForJSONFile;
        for (int i = 0; i < jsonArray.size(); i++) {
            tempObjectForJSONFile = (JSONObject) jsonArray.get(i);
            long trackId = (long) tempObjectForJSONFile.get("TrackID");
            if (trackId== tempSong.getTrackId()){
                jsonArray.remove(i);
                break;
            }
        }
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




    @Override
    public <T> void updateFile(String path, T object) {

    }

    @Override
    public <T> void addIntoFile(String path, T object) {


    }


    public static void main(String args[]) throws IOException, ParseException {

        JsonFileIO fileIO = new JsonFileIO("src/main/java/com/mycompany/ceng431_hmw3/FILEIO/deneme.json");
        //fileIO.writeFile("src/main/java/com/mycompany/ceng431_hmw3/FILEIO/deneme.json");
        /*fileIO.addASongIntoJsonFile(0, iPlayableList.get(8));
        fileIO.addASongIntoJsonFile(0, iPlayableList.get(9));
        fileIO.addASongIntoJsonFile(1, iPlayableList.get(0));
        fileIO.addASongIntoJsonFile(1, iPlayableList.get(1));*/
        //fileIO.removeAFileFromJsonFile(0,iPlayableList.get(8));
        PlaylistUpdaterJson playlistUpdaterJson = new PlaylistUpdaterJson("src/main/java/com/mycompany/ceng431_hmw3/FILEIO/deneme.json");
        //playlistUpdaterJson.update(new SongPlaylist(1,"","",PlaylistType.SLEEPING));


        PlaylistLoaderFromJson playlistLoaderFromJson = new PlaylistLoaderFromJson("src/main/java/com/mycompany/ceng431_hmw3/FILEIO/deneme.json");
        playlistLoaderFromJson.load();
        List<IPLaylist> tempPlaylist = playlistLoaderFromJson.getElements();
        tempPlaylist.get(0).getPlayableList().add(SongContainer.getInstance().getSongByTrackId(2008));


        System.out.println("number of songs "+tempPlaylist.size());

    }
}
