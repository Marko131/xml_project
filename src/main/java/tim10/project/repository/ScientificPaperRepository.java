package tim10.project.repository;

import org.apache.commons.io.IOUtils;
import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;
import tim10.project.model.scientific_paper.Paper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.OutputKeys;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;



@Repository
public class ScientificPaperRepository implements IScientificPaper {

    private String dbUsername;
    private String dbPassword;

    private String driver;
    private String connectionUri;
    private String host;
    private int port;

    public String getUri() {
        return String.format(this.connectionUri, this.host, this.port);
    }


    public ScientificPaperRepository(@Value("${xml.user}") String username, @Value("${xml.password}") String password, @Value("${xml.driver}") String driver, @Value("${xml.connectionUri}") String connectionUri, @Value("${xml.host}") String host, @Value("${xml.port}") int port) throws ClassNotFoundException, IllegalAccessException, InstantiationException, XMLDBException, IOException {
        this.dbUsername = username;
        this.dbPassword = password;
        this.driver = driver;
        this.connectionUri = connectionUri;
        this.host = host;
        this.port = port;
        Class<?> cl = Class.forName(this.driver);
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);
    }

    @Override
    public Paper getById(String collectionId, String documentId) throws XMLDBException, JAXBException {
        Paper paper = null;
        Collection col = DatabaseManager.getCollection(this.getUri() + collectionId);
        col.setProperty(OutputKeys.INDENT, "yes");
        XMLResource res = (XMLResource) col.getResource(documentId);

        if (res == null) {
            System.out.println("[WARNING] Document '" + documentId + "' can not be found!");
        } else {

            System.out.println("[INFO] Binding XML resource to an JAXB instance: ");
            JAXBContext context = JAXBContext.newInstance("tim10.project.model.scientific_paper");
            Unmarshaller unmarshaller = context.createUnmarshaller();

            paper = (Paper) unmarshaller.unmarshal(res.getContentAsDOM());

            System.out.println("[INFO] Showing the document as JAXB instance: ");
            System.out.println(paper);
            //List<XMLResource> lista = col.listResources();
            System.out.println(Arrays.toString(col.listResources()));
        }

        try {
            col.close();
        } catch (XMLDBException xe) {
            xe.printStackTrace();
        }

        return paper;
    }

    public String getXMLResourceById(String collectionId, String documentId) throws XMLDBException, JAXBException {
        OutputStream os = new ByteArrayOutputStream();
        Collection col = DatabaseManager.getCollection(this.getUri() + collectionId);
        col.setProperty(OutputKeys.INDENT, "yes");
        XMLResource res = (XMLResource) col.getResource(documentId);

        return res.getContent().toString();
    }

    public ArrayList<Paper> getAll(String collectionId) throws XMLDBException, JAXBException {
        ArrayList<Paper> list = new ArrayList<Paper>(){};
        Collection col = DatabaseManager.getCollection(this.getUri() + collectionId);
        col.setProperty(OutputKeys.INDENT, "yes");
        for (String element: col.listResources()) {
            XMLResource res = (XMLResource) col.getResource(element);

            if (res == null) {
                System.out.println("[WARNING] Document '" + element + "' can not be found!");
            } else {

                System.out.println("[INFO] Binding XML resource to an JAXB instance: ");
                JAXBContext context = JAXBContext.newInstance("tim10.project.model.scientific_paper");
                Unmarshaller unmarshaller = context.createUnmarshaller();

                list.add((Paper) unmarshaller.unmarshal(res.getContentAsDOM()));
            }
        }
        try {
            col.close();
        } catch (XMLDBException xe) {
            xe.printStackTrace();
        }
        return list;
    }

    public String save(String collectionId, String documentId, Reader inputReader) throws XMLDBException, IOException, JAXBException {
        Collection col = null;
        XMLResource res = null;
        OutputStream os = new ByteArrayOutputStream();
        try {
            IOUtils.copy(inputReader, os);
            System.out.println("[INFO] Retrieving the collection: " + collectionId);
            col = getOrCreateCollection(collectionId);

            System.out.println("[INFO] Inserting the document: " + documentId);
            res = (XMLResource) col.createResource(documentId, XMLResource.RESOURCE_TYPE);

            // link the stream to the XML resource
            res.setContent(os);
            System.out.println("[INFO] Storing the document: " + res.getId());

            col.storeResource(res);
            System.out.println("[INFO] Done.");


        } finally {

            //don't forget to cleanup
            if (res != null) {
                try {
                    ((EXistResource) res).freeResources();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }

            if (col != null) {
                try {
                    col.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }

            return documentId;
        }
    }

    public void delete(String collectionID, String documentID) throws XMLDBException{
        Collection col = null;
        XMLResource res = null;

        try {
            // get the collection
            System.out.println("[INFO] Retrieving the collection: " + collectionID);
            col = DatabaseManager.getCollection(this.getUri() + collectionID, dbUsername, dbPassword);
            col.setProperty(OutputKeys.INDENT, "yes");

            System.out.println("[INFO] Retrieving the document: " + documentID);
            res = (XMLResource)col.getResource(documentID);
            System.out.println("[INFO] Removing the document: " + documentID);
            col.removeResource(res);
        }finally {
            //don't forget to clean up!

            if(res != null) {
                try {
                    ((EXistResource)res).freeResources();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }

            if(col != null) {
                try {
                    col.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }
    }

    private Collection getOrCreateCollection(String collectionUri) throws XMLDBException {
        return getOrCreateCollection(collectionUri, 0);
    }

    private Collection getOrCreateCollection(String collectionUri, int pathSegmentOffset) throws XMLDBException {

        Collection col = DatabaseManager.getCollection(getUri() + collectionUri, this.dbUsername, this.dbPassword);

        // create the collection if it does not exist
        if (col == null) {

            if (collectionUri.startsWith("/")) {
                collectionUri = collectionUri.substring(1);
            }

            String pathSegments[] = collectionUri.split("/");

            if (pathSegments.length > 0) {
                StringBuilder path = new StringBuilder();

                for (int i = 0; i <= pathSegmentOffset; i++) {
                    path.append("/" + pathSegments[i]);
                }

                Collection startCol = DatabaseManager.getCollection(getUri() + path, this.dbUsername, this.dbPassword);

                if (startCol == null) {

                    // child collection does not exist

                    String parentPath = path.substring(0, path.lastIndexOf("/"));
                    Collection parentCol = DatabaseManager.getCollection(getUri() + parentPath, this.dbUsername, this.dbPassword);

                    CollectionManagementService mgt = (CollectionManagementService) parentCol.getService("CollectionManagementService", "1.0");

                    System.out.println("[INFO] Creating the collection: " + pathSegments[pathSegmentOffset]);
                    col = mgt.createCollection(pathSegments[pathSegmentOffset]);

                    col.close();
                    parentCol.close();

                } else {
                    startCol.close();
                }
            }
            return getOrCreateCollection(collectionUri, ++pathSegmentOffset);
        } else {
            return col;
        }
    }
}
