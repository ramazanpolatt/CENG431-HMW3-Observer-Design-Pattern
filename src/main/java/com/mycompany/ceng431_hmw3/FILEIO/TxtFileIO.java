package com.mycompany.ceng431_hmw3.FILEIO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TxtFileIO implements IFileIO {
    private Scanner scanner;



    @Override
    public List<String[]> getList(String path)  {
        List<String[]> tempList = new ArrayList<>();
        try
        {

            scanner = new Scanner(new File(path));
            String[] tempLineArray;
            while (scanner.hasNext()){
                tempLineArray = scanner.nextLine().split(",");
                tempList.add(tempLineArray);
            }
            scanner.close();

        }
        catch(FileNotFoundException exception)
        {
            System.out.println("File not founded at the target  please update the address information");
            System.exit(0);

        }

        return tempList;

    }

    @Override
    public void writeFile(String path) {


    }

    @Override
    public <T> void updateFile(String path, T object) {

    }


    @Override
    public <T> void addIntoFile(String path, T object) throws IOException {
        String givenLine = (String) object;
        scanner = new Scanner(new File(path));
        StringBuffer buffer = new StringBuffer();
        String tempLine;
        while (scanner.hasNext()){
            tempLine = scanner.nextLine();
            if (!givenLine.substring(0,15).equals(tempLine.substring(0,15))){
                buffer.append(tempLine+System.lineSeparator());
            }
            else {
                buffer.append(givenLine+System.lineSeparator());
            }
        }
        FileWriter fileWriter = new FileWriter(path);
        fileWriter.append(buffer.toString());
        scanner.close();
        fileWriter.flush();
    }



}
