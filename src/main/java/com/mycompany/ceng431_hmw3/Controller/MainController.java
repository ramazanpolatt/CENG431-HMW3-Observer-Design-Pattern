package com.mycompany.ceng431_hmw3.Controller;

import com.mycompany.ceng431_hmw3.ConcreteClasses.Model;
import com.mycompany.ceng431_hmw3.Views.MainView;
import com.mycompany.ceng431_hmw3.interfaces.IPLaylist;
import com.mycompany.ceng431_hmw3.interfaces.ISong;
import com.mycompany.ceng431_hmw3.interfaces.IUser;
import com.mycompany.ceng431_hmw3.interfaces.PlaylistType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainController
{
    private MainView mainView;
    private Model model;

    public MainController(MainView mainView) {
        this.mainView = mainView;
        mainView.addLikeButtonListener(new LikeButtonListener());
        mainView.addPlayButtonActionListener(new PlayButtonListener());
        mainView.addShowSelectedButtonListener(new ShowSelectedPlaylistButtonListener());
        mainView.songsButtonListener(new SongsButtonListtener());
        mainView.CreateButtonListener(new CreatePlaylistButtonListener());
        mainView.PlayListButtonListener(new PlaylistButtonController());
        mainView.followerButtonListener(new FollowersButtonController());
        mainView.followButonListener(new FollowButtonController());
        mainView.unFollowButtonListener(new UnfollowButtonController());
        mainView.addToPlaylistButtonListener(new AddToPlaylistButtonController());
        mainView.addRemoveButtonListener(new RemoveFromPlaylistButtonController());
        mainView.statisticButonListener(new StatisticButtonListener());

        this.model = Model.getInstance();
    }

    private class PlayButtonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {

            model.playSong(mainView.getSelectedSong());
        }
    }

    private class LikeButtonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            model.likeSong(mainView.getSelectedSong());
        }
    }

    private class ShowSelectedPlaylistButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {

            mainView.initializeTable( model.getSongs(mainView.getSelectedPlaylist()));

        }
    }

    private class SongsButtonListtener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {

            mainView.initializeTable(model.getSongs());
            mainView.fillUserPlaylistsComboBox();
        }
    }
    private class CreatePlaylistButtonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            String playlistName= mainView.getPlaylistName();
            int playlistTypeValue= mainView.getTypeId();
            if(playlistName.equals(null)&&playlistName.equals(""))
            {
                mainView.emptyPlaylistName();
            }
            else
            {
                if(playlistTypeValue<0||playlistTypeValue> PlaylistType.values().length)
                {
                    mainView.selectTypeMessage();
                }

                else
                {
                    model.createPlaylist(playlistName,PlaylistType.values()[playlistTypeValue]);
                }


            }
        }
    }

    private class PlaylistButtonController implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<IPLaylist<ISong>> tempList= Stream.concat(model.getPlayList(model.getCurrentUsername()).stream(),
                    model.getFollowedUsersPlaylists().stream()).collect(Collectors.toList());

            mainView.fillPlaylistTable(tempList);
        }
    }

    private class FollowersButtonController implements  ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {

            mainView.fillAllUsersTable(model.getUsers());
            mainView.fillFollowedUsersTable(model.getFollowedUsers());
        }
    }

    private class FollowButtonController implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {

            model.followUser(mainView.getSelectedUserNameAtAllFollowers());
        }
    }

    private class UnfollowButtonController implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            model.unfollowUser(mainView.getSelectedUserNameAtFollowed());

        }
    }

    private class AddToPlaylistButtonController implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            IPLaylist<ISong> tempPlaylist= model.getPlayListByName(mainView.getSelectedPlaylistName());
            ISong tempSong=model.getSongByTrackId(mainView.getSelectedSong());
            model.addToPlaylist(tempPlaylist,tempSong);

        }
    }


    private class RemoveFromPlaylistButtonController implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            IPLaylist<ISong> tempPlaylist= model.getPlayListByName(mainView.getSelectedPlaylistName());
            ISong tempSong=model.getSongByTrackId(mainView.getSelectedSong());


            model.removeSongFromGivenPlaylist(tempPlaylist,tempSong);
        }
    }
    private class StatisticButtonListener implements ActionListener
    {


        @Override
        public void actionPerformed(ActionEvent e) {

            if(model.getPlaylists().size()==0)
            {
                mainView.noPlaylistMessage();
            }
            else
            {

                fillMostPopularSong();
                fillMostLikedSong();
                fillTheMostFollowedUser();
                fillTheShortestList();
                fillTheLongestList();
            }

        }

        private void fillMostPopularSong() {
            ISong mostPopularSong= model.getMostPopularSong();
            mainView.fillTheMostPopularSong(mostPopularSong.getName(),mostPopularSong.getArtist(),
                                                Integer.toString(mostPopularSong.getNumberOfLikes()),
                                                    Integer.toString(mostPopularSong.getNumberOfpopularity()),
                                                         Float.toString(mostPopularSong.getDurationInMinutes()));
        }

        private void fillMostLikedSong()
        {
            ISong mostLikedSong= model.getMostLikedSong();
            mainView.fillTheMostLikeSong(mostLikedSong.getName(),mostLikedSong.getArtist(),
                    Integer.toString(mostLikedSong.getNumberOfLikes()),
                    Integer.toString(mostLikedSong.getNumberOfpopularity()),
                    Float.toString(mostLikedSong.getDurationInMinutes()));
        }

        private void fillTheMostFollowedUser()
        {
            IUser user = model.getMostFollowedUser();
            mainView.fillTheMostFollowedUser(user.getUserName(),Integer.toString(user.getNumberOfFollowers()));
        }

        private void fillTheShortestList()
        {
            IPLaylist<ISong>  shortestPlaylist= model.getShortestPlayList();

            mainView.fillTheShortestList(shortestPlaylist.getPlayListName(),shortestPlaylist.getCreatorUserName(),
                                            Float.toString(shortestPlaylist.getDurationInMinutes()));
        }

        private void fillTheLongestList()
        {
            IPLaylist<ISong>  shortestPlaylist= model.getLongestPlaylist();

            mainView.fillTheLongestList(shortestPlaylist.getPlayListName(),shortestPlaylist.getCreatorUserName(),
                    Float.toString(shortestPlaylist.getDurationInMinutes()));
        }
    }



}
