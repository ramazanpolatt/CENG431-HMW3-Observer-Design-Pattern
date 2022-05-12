package com.mycompany.ceng431_hmw3.FILEIO;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.List;

public class XmlFileIO implements IFileIO {
    @Override
    public List<String[]> getList(String path) {
        try {
            File inputFile = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("user");
            System.out.println("----------------------------");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    eElement.getAttribute("username");
                    System.out.println("username : "
                            + eElement
                            .getElementsByTagName("username")
                            .item(0)
                            .getTextContent());
                    System.out.println("password : "
                            + eElement
                            .getElementsByTagName("password")
                            .item(0)
                            .getTextContent());
                    System.out.println("followings : "
                            + eElement
                            .getElementsByTagName("following_user_names")
                            .item(0)
                            .getTextContent());
                    System.out.println("followers : "
                            + eElement
                            .getElementsByTagName("follower_user_names")
                            .item(0)
                            .getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void writeFile(String path) {

    }

    @Override
    public <T> void updateFile(String path, T object) {

    }

    @Override
    public <T> void addIntoFile(String path, T object) {

    }

    public static void main(String[] args) {
        XmlFileIO fileIO = new XmlFileIO();
        fileIO.getList("src/main/java/com/mycompany/ceng431_hmw3/FILEIO/user.xml");

    }
}
