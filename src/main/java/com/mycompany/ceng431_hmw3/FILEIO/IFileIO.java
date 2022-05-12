package com.mycompany.ceng431_hmw3.FILEIO;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface IFileIO {

    public Object getList(String path) throws IOException, ParseException;
    public void writeFile(String path) throws IOException, ParseException;
    public <T> void updateFile(String path, T object);
    public <T>  void addIntoFile(String path, T object) throws IOException;


}
