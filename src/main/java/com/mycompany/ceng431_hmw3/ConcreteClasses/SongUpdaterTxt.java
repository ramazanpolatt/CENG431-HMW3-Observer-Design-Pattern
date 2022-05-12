package com.mycompany.ceng431_hmw3.ConcreteClasses;

import com.mycompany.ceng431_hmw3.interfaces.IUpdater;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SongUpdaterTxt <ISong> implements IUpdater<ISong> {

    private String path;

    public SongUpdaterTxt(String path) {
        this.path = path;
    }

    @Override
    public void update(ISong item)
    {

        try
        {
            Scanner scanner= new Scanner(path);
            Song songItem=(Song)item;
            String line= String.format("%d,%s,%s,%s,%d,%d,%d",songItem.getGenre().getValue(),songItem.getTrackId(),
                    songItem.getName(),songItem.getArtist(),
                    songItem.getDurationInSeconds(),songItem.getNumberOfLikes(),
                    songItem.getNumberOfpopularity());



            scanner = new Scanner(new File(path));
            StringBuffer buffer = new StringBuffer();
            String tempLine;
            while (scanner.hasNext()){
                tempLine = scanner.nextLine();
                if (!line.substring(0,15).equals(tempLine.substring(0,15))){
                    buffer.append(tempLine+System.lineSeparator());
                }
                else {
                    buffer.append(line+System.lineSeparator());
                }
            }
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.append(buffer.toString());
            scanner.close();
            fileWriter.flush();
        }

        catch (IOException exception)
        {
            System.out.println("IO exception at SongUpdaterTxt");
            System.exit(0);
        }


    }


}
