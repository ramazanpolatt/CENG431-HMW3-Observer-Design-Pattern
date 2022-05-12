/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ceng431_hmw3.ConcreteClasses;


import com.mycompany.ceng431_hmw3.interfaces.*;

import java.util.*;

/**
 *
 * @author BO006
 */
public class Model implements IObservable<IObserver>,IModel
{

    private static Model instance;



    private LoginState loginState;
    private SongContainer songContainer;
    private List<ISong> songList;
    private IStatistics statistics;
    private List<IPLaylist<ISong>> playLists;
    private List<IUser> users;
    private IUser currentUser;
    private IUpdater<IUser> userUpdater;
    private IUpdater<IPLaylist> playListUpdater;
    private IUpdater<ISong> songUpdater;
    private ILoader<IUser> userLoader;
    private PlaylistLoaderFromJson playlistLoaderFromJson;
    private List<IObserver> observerList;
    private ViewState viewState;
    private String trackPath ="src/main/java/com/mycompany/ceng431_hmw3/FILEIO/tracks.txt";
    private String userXmlPath="src/main/java/com/mycompany/ceng431_hmw3/FILEIO/user.xml";
    private String playlistsJsonPath="src/main/java/com/mycompany/ceng431_hmw3/FILEIO/deneme.json";



    private Model() {
        songContainer = SongContainer.getInstance();
        userUpdater= new UserUpdaterXML(userXmlPath);
        playListUpdater= new PlaylistUpdaterJson(playlistsJsonPath);
        songUpdater= new SongUpdaterTxt<>(trackPath);
        userLoader= new UserLoaderFromXML(userXmlPath);

        observerList=new ArrayList<>();
        statistics=new Statistics();
        loginState=LoginState.NON_AUTHORIZED;
        playlistLoaderFromJson = new PlaylistLoaderFromJson(playlistsJsonPath);
        this.viewState=ViewState.NOT_LOGGED;

        initializeSongs();
        initializeUsers();
        initializePlaylists();





    }

    @Override
    public void playSong(int songId) {

       ISong selectedSong=  getSongByTrackId(songId);
       selectedSong.incresePopularity();
       songUpdater.update(selectedSong);
       notifyAllObserver();
    }

    @Override
    public void likeSong(int songId) {
        ISong selectedSong=  getSongByTrackId(songId);
        selectedSong.increaseLike();
        songUpdater.update(selectedSong);
        notifyAllObserver();

    }

    @Override
    public void followUser(String username) {

                if(currentUser.followUser(getUserByUserName(username)))
                {
                    userUpdater.update(currentUser);
                    userUpdater.update(getUserByUserName(username));
                    viewState=ViewState.FOLLOW;
                    notifyAllObserver();

                }

    }

    @Override
    public void unfollowUser(String username) {

                if(currentUser.unfollowUser(getUserByUserName(username)))
                {
                    userUpdater.update(currentUser);
                    userUpdater.update(getUserByUserName(username));
                    viewState=ViewState.FOLLOW;
                    notifyAllObserver();
                }

    }

    @Override
    public void login(String userName, String password)
    {


     for (IUser user:users)

      {
            if(user.getUserName().equals(userName)&& user.getPassword().equals(password))
            {
                loginState=LoginState.AUTHORIZED;
                currentUser=user;
                viewState=ViewState.SONGS;

            }

      }
      notifyAllObserver();

    }

    @Override
    public void createPlaylist( String playlistName,PlaylistType playlistType ) {
        int id = this.playLists.size();
        IPLaylist tempPlaylist = new SongPlaylist(id, this.getCurrentUsername(), playlistName, playlistType);
        this.playLists.add(tempPlaylist);
        this.playListUpdater.update(tempPlaylist);
        this.viewState = ViewState.PLAYLIST_CREATED;
        this.notifyAllObserver();
    }



    @Override
    public List<ISong> getSongs() {

        viewState=ViewState.SONGS;
        return songList;
    }

    @Override
    public List<ISong> getSongs(int playListId)
    {
        viewState=ViewState.PLAYLIST;
        return playLists.get(playListId).getPlayableList();
    }


    // returns current user's playlist
    @Override
    public List<IPLaylist<ISong>> getPlaylists() {

        return getPlaylistForGivenUser(currentUser.getUserName());
    }

    @Override
    public List<IPLaylist<ISong>> getPlayList(String userName)
    {
        return getPlaylistForGivenUser(userName);

    }

    private List<IPLaylist<ISong>> getPlaylistForGivenUser(String userName)
    {
        List<IPLaylist<ISong>> tempPlaylist= new ArrayList<>();

        for (IPLaylist<ISong> playlist:playLists )
        {
            if(playlist.getCreatorUserName().equals(userName))
            {
                tempPlaylist.add(playlist);
            }
        }
        return tempPlaylist;
    }

    @Override
    public List<IUser> getUsers() {
        return users;
    }

    @Override
    public void registerObserver(IObserver o) {

        observerList.add(o);
    }



    @Override
    public void notifyAllObserver() {

        for (IObserver observer:observerList)
        {



         observer.update(this);



        }
    }

    private void initializeSongs()
    {

        songList=songContainer.getSongList();
    }

    private void initializeUsers()
    {
        userLoader.load();
        users=userLoader.getElements();
    }

    private  void initializePlaylists(){
        playlistLoaderFromJson.load();
        playLists = playlistLoaderFromJson.getElements();
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

    public static Model getInstance()

    {
        if(instance == null)
        {
            instance= new Model();
            return instance;
        }
        return instance;
    }


    @Override
    public LoginState getLoginState() {
        return loginState;
    }

    @Override
    public void removeObserver(IObserver observer) {

        observerList.remove(observer);
    }


    private IUser getUserByUserName(String username)
    {

        for (IUser user:users)
        {

            if(user.getUserName().equals(username))
            {
                return user;
            }
        }
        System.out.println("The user could not found problem is at the getUserByUserName function at class :"+getClass().getName());
        System.exit(0);
        return null;
    }


    @Override
    public ISong getMostLikedSong() {
        return statistics.getMostLikedSong(songList);
    }

    @Override
    public ISong getMostPopularSong() {
        return statistics.getMostPopularSong(songList);
    }

    @Override
    public IUser getMostFollowedUser() {
        return statistics.getMostFollowedUser(users);
    }

    @Override
    public IPLaylist<ISong> getLongestPlaylist() {
        return  statistics.getLongestPlaylist(playLists);
    }

    @Override
    public IPLaylist<ISong> getShortestPlayList() {
        return statistics.getShortestPlayList(playLists);
    }

    @Override
    public String getCurrentUsername() {
        return currentUser.getUserName();
    }

    @Override
    public ViewState getViewState() {
        return viewState;
    }

    @Override
    public List<String> getFollowedUsers() {
        return currentUser.getFollowedList();
    }

    @Override
    public List<IPLaylist<ISong>> getFollowedUsersPlaylists() {
        List<IPLaylist<ISong>> tempPlaylistList = new ArrayList<>();
        for (String followedUser : getFollowedUsers()
        ) {
            for (IPLaylist<ISong> song : getPlaylistForGivenUser(followedUser)
            ) {
                tempPlaylistList.add(song);
            }
        }
        return tempPlaylistList;
    }

    @Override
    public IPLaylist<ISong> getPlayListByName(String playlistName) {
        IPLaylist tempPlayList=null
                ;
        for (IPLaylist<ISong > playList:playLists
             ) {

            if(playList.getPlayListName().equals(playlistName))
            {
                tempPlayList=playList;
            }

        }
        return tempPlayList;
    }

    @Override
    public void addToPlaylist(IPLaylist<ISong> playlist, ISong song) {
        playlist.add(song);
        playListUpdater.update(playlist);
        notifyAllObserver();
    }

    @Override
    public void removeSongFromGivenPlaylist(IPLaylist<ISong> playlist, ISong song) {
        playlist.remove(song);
        playListUpdater.update(playlist);
        notifyAllObserver();
    }
}


    
    
    
    
   
    
    
    

    
    
    
    

