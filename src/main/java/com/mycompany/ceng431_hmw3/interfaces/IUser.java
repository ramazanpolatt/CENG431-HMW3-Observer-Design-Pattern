package com.mycompany.ceng431_hmw3.interfaces;

import java.util.List;

public interface IUser {

    String getUserName();

    String getPassword();

    List<String> getFollowedList();

    List<String> getFollowerList();

    void setFollowerList(List<String> followerList);

    void setFollowedList(List<String> followedList);


    //Assumed the error checking steps done at calling class
    boolean followUser(IUser user);

    boolean unfollowUser(IUser user);

    boolean addFollower(IUser user);

    boolean removeFollower(IUser user);

    int getNumberOfFollowers();

    int getNumberOfFollowedUser();

}
