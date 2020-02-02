package tim10.project.repository;

import org.apache.commons.io.IOUtils;
import org.apache.jena.base.Sys;
import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;
import tim10.project.model.cover_letter.CoverLetter;
import tim10.project.model.review.Review;
import tim10.project.util.DocumentUtil;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReviewRepository {

    private String dbUsername;
    private String dbPassword;

    private String driver;
    private String connectionUri;
    private String host;
    private int port;


    public String getUri() {
        return String.format(this.connectionUri, this.host, this.port);
    }

    public ReviewRepository(@Value("${xml.user}") String username, @Value("${xml.password}") String password, @Value("${xml.driver}") String driver, @Value("${xml.connectionUri}") String connectionUri, @Value("${xml.host}") String host, @Value("${xml.port}") int port) throws ClassNotFoundException, IllegalAccessException, InstantiationException, XMLDBException, IOException {
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

    public Review getById(String collectionId, String documentId) throws XMLDBException, JAXBException {
        Review review = null;
        Collection col = DatabaseManager.getCollection(this.getUri() + collectionId);

        col.setProperty(OutputKeys.INDENT, "yes");
        XMLResource res = (XMLResource) col.getResource(documentId);

        if (res == null) {
            System.out.println("[WARNING] Document '" + documentId + "' can not be found!");
        } else {

            System.out.println("[INFO] Binding XML resource to an JAXB instance: ");
            JAXBContext context = JAXBContext.newInstance("tim10.project.model.review");
            Unmarshaller unmarshaller = context.createUnmarshaller();

            review = (Review) unmarshaller.unmarshal(res.getContentAsDOM());

            System.out.println("[INFO] Showing the document as JAXB instance: ");
            System.out.println(review);
        }

        try {
            col.close();
        } catch (XMLDBException xe) {
            xe.printStackTrace();
        }

        return review;
    }

    public ArrayList<Review> getAll(String collectionId) throws XMLDBException, JAXBException {
        ArrayList<Review> list = new ArrayList<Review>() {
        };
        Collection col = DatabaseManager.getCollection(this.getUri() + collectionId);
        col.setProperty(OutputKeys.INDENT, "yes");
        for (String element : col.listResources()) {
            XMLResource res = (XMLResource) col.getResource(element);

            if (res == null) {
                System.out.println("[WARNING] Document '" + element + "' can not be found!");
            } else {

                System.out.println("[INFO] Binding XML resource to an JAXB instance: ");
                JAXBContext context = JAXBContext.newInstance("tim10.project.model.review");
                Unmarshaller unmarshaller = context.createUnmarshaller();

                list.add((Review) unmarshaller.unmarshal(res.getContentAsDOM()));
            }
        }
        try {
            col.close();
        } catch (XMLDBException xe) {
            xe.printStackTrace();
        }
        return list;
    }

    public void save(String collectionId, String documentId, Reader inputReader) throws XMLDBException, IOException, JAXBException {
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
        }
    }

    public void delete(String collectionID, String documentID) throws XMLDBException {
        Collection col = null;
        XMLResource res = null;

        try {
            // get the collection
            System.out.println("[INFO] Retrieving the collection: " + collectionID);
            col = DatabaseManager.getCollection(this.getUri() + collectionID, dbUsername, dbPassword);
            col.setProperty(OutputKeys.INDENT, "yes");

            System.out.println("[INFO] Retrieving the document: " + documentID);
            res = (XMLResource) col.getResource(documentID);
            System.out.println("[INFO] Removing the document: " + documentID);
            col.removeResource(res);
        } finally {
            //don't forget to clean up!

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
        }
    }

    public void createAnonymousReview(String collectionId, String documentId) throws XMLDBException, ParserConfigurationException, IOException, JAXBException, SAXException, XPathExpressionException, TransformerException {
        OutputStream os = new ByteArrayOutputStream();
        Collection col = DatabaseManager.getCollection(this.getUri() + collectionId);
        col.setProperty(OutputKeys.INDENT, "yes");
        XMLResource res = (XMLResource) col.getResource(documentId);

        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        DocumentBuilder b = f.newDocumentBuilder();

        File temp = File.createTempFile("temp_file", ".xml");

        // Delete temp file when program exits.
        temp.deleteOnExit();

        // Write to temp file
        BufferedWriter out = new BufferedWriter(new FileWriter(temp));
        out.write(getXMLResourceById(collectionId, documentId));
        out.close();

        Document document = b.parse(temp);
        XPath xPath = XPathFactory.newInstance().newXPath();
        Node name = (Node) xPath.compile("/review/reviewer/name").evaluate(document, XPathConstants.NODE);
        Node faculty = (Node) xPath.compile("/review/reviewer/faculty").evaluate(document, XPathConstants.NODE);
        Node university = (Node) xPath.compile("/review/reviewer/university").evaluate(document, XPathConstants.NODE);

        name.setTextContent("Anonymous");
        faculty.setTextContent("");
        university.setTextContent("");

        DOMSource domSource = new DOMSource(document);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.transform(domSource, result);

        System.out.println(writer.toString());

        Reader reader = new StringReader(writer.toString());
        save("/db/sample/library/anonymous", "anonymous_" + documentId, reader);
    }

    public Document mergeReviewsByPaperTitle(String collectionId, String title) throws Exception {
        Collection col = DatabaseManager.getCollection(this.getUri() + collectionId);
        col.setProperty(OutputKeys.INDENT, "yes");

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();
        Document doc = builder.newDocument();

        Node root = doc.createElement("reviews");

        for (String element : col.listResources()) {
            XMLResource res = (XMLResource) col.getResource(element);

            Document d = DocumentUtil.XMLStringToDocument(getXMLResourceById(collectionId, res.getId()));
            XPath xpath = XPathFactory.newInstance().newXPath();

            Node paperTitle = (Node) xpath.compile("/review/paper_title").evaluate(d, XPathConstants.NODE);
            if (paperTitle == null) continue;

            Node reviewNode = (Node) xpath.compile("/review").evaluate(d, XPathConstants.NODE);
            Node importedNode = doc.importNode(reviewNode, true);


            if (paperTitle.getTextContent().equals(title)) {
                root.appendChild(importedNode);
            }

        }
        doc.appendChild(root);
        return doc;
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

    public String getXMLResourceById(String collectionId, String documentId) throws XMLDBException, JAXBException {
        OutputStream os = new ByteArrayOutputStream();
        Collection col = DatabaseManager.getCollection(this.getUri() + collectionId);
        col.setProperty(OutputKeys.INDENT, "yes");
        XMLResource res = (XMLResource) col.getResource(documentId);

        return res.getContent().toString();
    }
}
