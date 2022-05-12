package com.mycompany.ceng431_hmw3.ConcreteClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TxtFileReader  {

    private String path;


    public TxtFileReader(String path) {
        this.path = path;
    }

    public List<String[]> getList()  {
        List<String[]> tempList = new ArrayList<>();

        try
        {

            Scanner scanner = new Scanner(new File(path));
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
}
