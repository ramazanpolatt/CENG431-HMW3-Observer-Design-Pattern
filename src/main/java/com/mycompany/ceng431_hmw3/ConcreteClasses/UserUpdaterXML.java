package com.mycompany.ceng431_hmw3.ConcreteClasses;

import com.mycompany.ceng431_hmw3.interfaces.IUpdater;
import com.mycompany.ceng431_hmw3.interfaces.IUser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.List;

public class UserUpdaterXML implements IUpdater<IUser>
{


   /* private static final String FILENAME = "src/com/company/user.xml";*/
    // xslt for pretty print only, no special task
    private static final String FORMAT_XSLT = "src/main/java/com/mycompany/ceng431_hmw3/FILEIO/format.xslt";

    private String path;

    public UserUpdaterXML(String path) {
        this.path = path;
    }

    @Override
    public void update(IUser updateUser)
    {


        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();


        try (InputStream is = new FileInputStream(path)) {

            DocumentBuilder db = dbf.newDocumentBuilder();


            Document doc = db.parse(is);
            Node rootNode=doc.getFirstChild();

            NodeList users = doc.getElementsByTagName("user");
            //System.out.println(listOfStaff.getLength()); // 2

            for (int i = 0; i < users.getLength(); i++) {
                // get first staff
                Node user = users.item(i);
                if (user.getNodeType() == Node.ELEMENT_NODE) {
                    String username = user.getAttributes().getNamedItem("username").getTextContent();
                    if (updateUser.getUserName().equals(username.trim())) {


                        user.getParentNode().removeChild(user);

                        Element updateUserElement = doc.createElement("user");
                        updateUserElement.setAttribute("username",updateUser.getUserName());
                        Element password = doc.createElement("password");
                        password.setTextContent(updateUser.getPassword());
                        updateUserElement.appendChild(password);

                        for (String followerName:updateUser.getFollowerList()   )

                        {
                            Element follower = doc.createElement("follower");
                            follower.setTextContent(followerName);
                            updateUserElement.appendChild(follower);

                        }

                        for (String followedName:updateUser.getFollowedList()   )

                        {
                            Element follower = doc.createElement("followed");
                            follower.setTextContent(followedName);
                            updateUserElement.appendChild(follower);

                        }
                        rootNode.appendChild(updateUserElement);


                   /*     // add a new xml element, address
                        Element follower = doc.createElement("follower");
                        follower.setTextContent("ramazan");
                        user.appendChild(follower);
*/
                    }



                }

            }

            // output to console
            // writeXml(doc, System.out);

            try (FileOutputStream output =
                         new FileOutputStream(path)) {
                writeXml(doc, output);
            }

        } catch (ParserConfigurationException | SAXException
                | IOException | TransformerException e) {
            e.printStackTrace();
    }


}

    private static void writeXml(Document doc,
                                 OutputStream output)
            throws TransformerException, UnsupportedEncodingException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        // The default add many empty new line, not sure why?
        // https://mkyong.com/java/pretty-print-xml-with-java-dom-and-xslt/
        // Transformer transformer = transformerFactory.newTransformer();

        // add a xslt to remove the extra newlines
        Transformer transformer = transformerFactory.newTransformer(
                new StreamSource(new File(FORMAT_XSLT)));

        // pretty print
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "no");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);

    }
}
