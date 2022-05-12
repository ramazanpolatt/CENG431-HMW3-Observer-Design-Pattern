package com.mycompany.ceng431_hmw3.ConcreteClasses;

import com.mycompany.ceng431_hmw3.interfaces.ILoader;
import com.mycompany.ceng431_hmw3.interfaces.IUser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static javax.script.ScriptEngine.FILENAME;

public class UserLoaderFromXML implements ILoader<IUser> {

    private String path;
    private List<IUser> userList;
    public UserLoaderFromXML(String path) {
        this.path = path;
        userList=new ArrayList<>();
    }

    @Override
    public void load() {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(path));
            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("user");

            for (int temp = 0; temp < list.getLength(); temp++) {

                String username="";
                String password="";
                List<String> followerlist;
                List<String> followedList;
                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    username = element.getAttribute("username");
                    password = element.getElementsByTagName("password").item(0).getTextContent();

                    followerlist = new ArrayList<>();
                    NodeList followerNode= element.getElementsByTagName("follower");
                    if(followerNode.getLength()>0)
                    {
                        for(int followerNodeIndex=0;followerNodeIndex<followerNode.getLength();followerNodeIndex++)

                        {

                            Node followerNodeItem=followerNode.item(followerNodeIndex);
                            followerlist.add(followerNodeItem.getTextContent());
                        }

                    }

                    followedList = new ArrayList<>();
                    NodeList followedNode= element.getElementsByTagName("followed");
                    if(followedNode.getLength()>0)
                    {
                        for(int followedNodeIndex=0;followedNodeIndex<followedNode.getLength();followedNodeIndex++)

                        {

                            Node followedNodeItem=followedNode.item(followedNodeIndex);
                            followedList.add(followedNodeItem.getTextContent());
                        }

                    }

                    if(username.equals("")&&password.equals(""))
                    {
                        System.out.println("Problem occurred at reading username and password "+getClass().getName());

                    }
                    IUser tempUser = new User(username,password);
                    if(followerlist.size()>0)
                    {
                          tempUser.setFollowerList(followerlist);
                    }
                    if(followedList.size()>0)
                    {
                        tempUser.setFollowedList(followedList);
                    }
                    userList.add(tempUser);
                }



            }



        }
        catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }



    @Override
    public List<IUser> getElements() {
        return userList;
    }
}
