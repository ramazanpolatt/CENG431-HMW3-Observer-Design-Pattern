package com.mycompany.ceng431_hmw3.interfaces;

import com.mycompany.ceng431_hmw3.ConcreteClasses.LoginState;
import com.mycompany.ceng431_hmw3.ConcreteClasses.User;

import java.util.List;

public interface IModel {

    void playSong(int songId);

    void likeSong(int songId);

    void followUser(String userId);

    void unfollowUser(String userId);

    void login(String userName,String password);

    void createPlaylist( String playlistName,PlaylistType playlistType);

    List<ISong> getSongs();

    List<ISong> getSongs(int playListId);

    List<IPLaylist<ISong>> getPlaylists();

    List<IPLaylist<ISong>> getPlayList(String userId);

    List<IUser> getUsers();

    LoginState getLoginState();

    List<String> getFollowedUsers();

    List<IPLaylist<ISong>> getFollowedUsersPlaylists();

    IPLaylist<ISong> getPlayListByName(String playlistName);

    ISong getMostLikedSong();

    ISong getMostPopularSong();

    IUser getMostFollowedUser();

    IPLaylist<ISong> getLongestPlaylist();

    IPLaylist<ISong> getShortestPlayList();

    void addToPlaylist(IPLaylist<ISong> playlist,ISong song);

    void removeSongFromGivenPlaylist(IPLaylist<ISong> playlist,ISong song);
    String getCurrentUsername();

    ViewState getViewState();
}

