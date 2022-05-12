package com.mycompany.ceng431_hmw3.ConcreteClasses;

import com.mycompany.ceng431_hmw3.interfaces.IPLaylist;
import com.mycompany.ceng431_hmw3.interfaces.ISong;
import com.mycompany.ceng431_hmw3.interfaces.IStatistics;
import com.mycompany.ceng431_hmw3.interfaces.IUser;

import java.util.List;

public class Statistics implements IStatistics {


    public Statistics() {
    }

    @Override
    public ISong getMostLikedSong(List<ISong> songList) {
        if(songList.size()<=0)
        {
            System.out.println("Cannot get most liked songs without an existing song in the library  error at getMostLikedSong function at class "+getClass().getName());
            System.exit(0);
        }
        ISong mostLikedSong= songList.get(0);
        for (ISong song:songList
             ) {

            if(mostLikedSong.getNumberOfLikes()<song.getNumberOfLikes())
            {
                mostLikedSong=song;
            }

        }

        return mostLikedSong;
    }

    @Override
    public ISong getMostPopularSong(List<ISong> songList) {
        if(songList.size()<=0)
        {
            System.out.println("Cannot get most popular songs without an existing song in the library  error at getMostPopularSong function at class "+getClass().getName());
            System.exit(0);
        }
        ISong mostPopularSong= songList.get(0);
        for (ISong song:songList
        ) {

            if(mostPopularSong.getNumberOfpopularity()<song.getNumberOfpopularity())
            {
                mostPopularSong=song;
            }

        }

        return mostPopularSong;
    }

    @Override
    public IUser getMostFollowedUser(List<IUser> userList) {

        if(userList.size()<=0)
        {
            System.out.println("Cannot get most followed user without an existing user in the library  error at getMostFollowedUser function at class "+getClass().getName());
            System.exit(0);
        }
        IUser mostFollowedUser= userList.get(0);
        for (IUser user:userList
             ) {

            if(mostFollowedUser.getNumberOfFollowedUser()<user.getNumberOfFollowedUser())
            {
                mostFollowedUser=user;
            }
        }
        return mostFollowedUser;
    }

    @Override
    public IPLaylist<ISong> getLongestPlaylist(List<IPLaylist<ISong>> playlistList) {
        if(playlistList.size()<=0)
        {
            System.out.println("Cannot getLongest Playlist without an existing playlist in the library  error at getLongestPlaylist function at class "+getClass().getName());
            System.exit(0);
        }

        IPLaylist<ISong> longestPlayList= playlistList.get(0);
        for (IPLaylist<ISong> playlist:playlistList
             ) {


            if(longestPlayList.getDurationInSeconds()<playlist.getDurationInSeconds())
            {
                longestPlayList=playlist;
            }
        }
        return longestPlayList;
    }

    @Override
    public IPLaylist<ISong> getShortestPlayList(List<IPLaylist<ISong>> playlistList) {
        if(playlistList.size()<=0)
        {
            System.out.println("Cannot getLongest Playlist without an existing playlist in the library  error at getLongestPlaylist function at class "+getClass().getName());
            System.exit(0);
        }

        IPLaylist<ISong> shortestPlaylist= playlistList.get(0);
        for (IPLaylist<ISong> playlist:playlistList
        ) {


            if(shortestPlaylist.getDurationInSeconds()>playlist.getDurationInSeconds())
            {
                shortestPlaylist=playlist;
            }
        }
        return shortestPlaylist;
    }
}
