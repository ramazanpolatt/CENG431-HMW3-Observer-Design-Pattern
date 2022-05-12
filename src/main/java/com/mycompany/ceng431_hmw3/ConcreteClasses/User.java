package com.mycompany.ceng431_hmw3.ConcreteClasses;

import com.mycompany.ceng431_hmw3.interfaces.IUser;

import java.util.ArrayList;
import java.util.List;

public class User implements IUser {

    private String userName;



    private String password;
    private List<String> followedUsernames;
    private List<String> followerUserNames;
    public User(String userName, String password)
    {
        this(userName,password,new ArrayList<>(),new ArrayList<>());

    }

    public User(String userName, String password, List<String> followingUserNames, List<String> followerUserNames) {
        this.userName = userName;
        this.password = password;
        this.followedUsernames = followingUserNames;
        this.followerUserNames = followerUserNames;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public List<String> getFollowedList() {
        return followedUsernames;
    }

    @Override
    public List<String> getFollowerList() {
        return followerUserNames;
    }

    @Override
    public boolean followUser(IUser user) {
        if(!followedUsernames.contains(user.getUserName())&&!getUserName().equals(user.getUserName()))
        {
            followedUsernames.add(user.getUserName());
            user.addFollower(this);
            return true;

        }

        return false;

    }

    @Override
    public boolean unfollowUser(IUser user) {
        if(followedUsernames.contains(user.getUserName())&&!getUserName().equals(user.getUserName()))
        {
            user.removeFollower(this);
            followedUsernames.remove(user.getUserName());
            return true;
        }
        return false;

    }

    @Override
    public void setFollowerList(List<String> followerList)
    {
        this.followerUserNames=followerList;
    }

    @Override
    public void setFollowedList(List<String> followedList) {

        this.followedUsernames =followedList;
    }

    @Override
    public boolean addFollower(IUser user) {
        if(!followerUserNames.contains(user.getUserName())&&!user.getUserName().equals(getUserName()))
        {
            followerUserNames.add(user.getUserName());
            return true;
        }
        return false;
    }

    @Override
    public boolean removeFollower(IUser user) {
        if(followerUserNames.contains(user.getUserName())&&!user.getUserName().equals(getUserName()))
        {
            followerUserNames.remove(user.getUserName());
            return true;
        }
        return false;
    }

    @Override
    public int getNumberOfFollowers() {
        return followerUserNames.size();
    }

    @Override
    public int getNumberOfFollowedUser() {
        return followedUsernames.size();
    }
}
