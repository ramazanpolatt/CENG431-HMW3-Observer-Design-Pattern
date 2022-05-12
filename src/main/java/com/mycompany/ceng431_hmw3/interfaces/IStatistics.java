package com.mycompany.ceng431_hmw3.interfaces;

import java.util.List;

public interface IStatistics {

    public  ISong getMostLikedSong(List<ISong> songList);

    public ISong getMostPopularSong(List<ISong> songList);

    public IUser getMostFollowedUser(List<IUser> userList);

    public IPLaylist<ISong> getLongestPlaylist(List<IPLaylist<ISong>> songList);

    public IPLaylist<ISong> getShortestPlayList(List<IPLaylist<ISong>>songList);

}
