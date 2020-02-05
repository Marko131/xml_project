package tim10.project.repository;

import org.apache.commons.io.IOUtils;
import org.apache.jena.base.Sys;
import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.base.Collection;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XQueryService;
import tim10.project.model.DocumentStatus;
import tim10.project.model.scientific_paper.Paper;
import tim10.project.model.scientific_paper.TAuthor;
import tim10.project.util.DocumentUtil;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;


@Repository
public class ScientificPaperRepository implements IScientificPaper {
    private static final String TARGET_NAMESPACE = "http://www.ftn.uns.ac.rs/examples/xmldb/paper";
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

    public ArrayList<Paper> getPapersByUserName(String collectionId, String userName) throws XMLDBException, JAXBException {
        ArrayList<Paper> papers = new ArrayList<>();

        Collection col = DatabaseManager.getCollection(this.getUri() + collectionId);
        col.setProperty(OutputKeys.INDENT, "yes");
        for (String element : col.listResources()) {
            XMLResource res = (XMLResource) col.getResource(element);

            if (res == null) {
                System.out.println("[WARNING] Document '" + element + "' can not be found!");
            } else {

                System.out.println("[INFO] Binding XML resource to an JAXB instance: ");
                JAXBContext context = JAXBContext.newInstance("tim10.project.model.scientific_paper");
                Unmarshaller unmarshaller = context.createUnmarshaller();
                Paper paper = (Paper) unmarshaller.unmarshal(res.getContentAsDOM());
                for (TAuthor author : paper.getAuthors().getAuthor()){
                    if (author.getName().getValue().equals(userName)){
                        papers.add(paper);
                    }
                }
            }
        }
        try {
            col.close();
        } catch (XMLDBException xe) {
            xe.printStackTrace();
        }
        return papers;
    }

    public String getXMLResourceById(String collectionId, String documentId) throws XMLDBException, JAXBException {
        OutputStream os = new ByteArrayOutputStream();
        Collection col = DatabaseManager.getCollection(this.getUri() + collectionId);
        col.setProperty(OutputKeys.INDENT, "yes");
        XMLResource res = (XMLResource) col.getResource(documentId);

        return res.getContent().toString();
    }

    public void createAnonymousDocument(String collectionId, String documentId) throws XMLDBException, ParserConfigurationException, JAXBException, IOException, SAXException, XPathExpressionException, TransformerException {
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
        NodeList authors = (NodeList) xPath.compile("/paper/authors/author/name").evaluate(document, XPathConstants.NODESET);

        Node node;

        System.out.println(authors.getLength());

        for (int i = 0; i < authors.getLength(); i++) {
            authors.item(i).setTextContent("Anonymous");
        }

        DOMSource domSource = new DOMSource(document);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.transform(domSource, result);

        System.out.println(writer.toString());

        Reader reader = new StringReader(writer.toString());
        save("/db/sample/library/anonymous", documentId + "_anonymous.xml", reader);

    }

    public void changeDocumentStatus(String collectionId, String documentId, DocumentStatus documentStatus) throws XMLDBException, ParserConfigurationException, IOException, JAXBException, SAXException, XPathExpressionException, TransformerException {
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

        Node statusNode = (Node) xPath.compile("/paper/status").evaluate(document, XPathConstants.NODE);
        Node oldStatus = (Node) xPath.compile("/paper/status/*").evaluate(document, XPathConstants.NODE);
        Node newStatus = document.createElementNS("", documentStatus.toString());
        newStatus.setTextContent(documentStatus.toString());

        oldStatus.getParentNode().removeChild(oldStatus);
        statusNode.appendChild(newStatus);

        DOMSource domSource = new DOMSource(document);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.transform(domSource, result);

        Reader reader = new StringReader(writer.toString());
        save(collectionId, documentId, reader);
    }

    public ArrayList<Paper> getAll(String collectionId) throws XMLDBException, JAXBException {
        ArrayList<Paper> list = new ArrayList<Paper>() {
        };
        Collection col = DatabaseManager.getCollection(this.getUri() + collectionId);
        col.setProperty(OutputKeys.INDENT, "yes");
        for (String element : col.listResources()) {
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

    public Document mergeNotes(String collectionId) throws XMLDBException, ParserConfigurationException, XPathExpressionException, IOException, SAXException {
        Collection col = DatabaseManager.getCollection(this.getUri() + collectionId);
        col.setProperty(OutputKeys.INDENT, "yes");

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();

        XMLResource firstResource = (XMLResource) col.getResource(col.listResources()[0]);
        Document firstDocument = DocumentUtil.XMLStringToDocument(firstResource.getContent().toString());
        XPath xpath = XPathFactory.newInstance().newXPath();
        NodeList notes = (NodeList) xpath.compile("//annotations").evaluate(firstDocument, XPathConstants.NODESET);

        for (int i = 1; i < col.getResourceCount(); i++) {
            XMLResource res = (XMLResource) col.getResource(col.listResources()[i]);

            Document d = DocumentUtil.XMLStringToDocument(res.getContent().toString());
            XPath xpath1 = XPathFactory.newInstance().newXPath();

            NodeList notes1 = (NodeList) xpath1.compile("//annotations").evaluate(d, XPathConstants.NODESET);
            int j = 0;
            for (Node temp : iterable(notes1)) {
                for (Node child : iterable(temp.getChildNodes())) {
                    Node importedNode = firstDocument.importNode(child, true);
                    notes.item(j).appendChild(importedNode);
                }
                j++;
            }
        }
        return firstDocument;
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

    public static Iterable<Node> iterable(final NodeList nodeList) {
        return () -> new Iterator<Node>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < nodeList.getLength();
            }

            @Override
            public Node next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                return nodeList.item(index++);
            }
        };
    }

    public List<String> getKeywordsFromPaper(String collectionId, String documentId) throws XMLDBException, ParserConfigurationException, IOException, JAXBException, SAXException, XPathExpressionException {
        ArrayList<String> keywordsList = new ArrayList<>();

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

        NodeList keywords = (NodeList) xPath.compile("/paper/abstract/keywords/keyword").evaluate(document, XPathConstants.NODESET);

        for(Node node : iterable(keywords)){
            keywordsList.add(node.getTextContent());
        }
        return keywordsList;
    }

    public List<String> searchPaperByText(String collectionId, String text) throws XMLDBException, JAXBException {
        ArrayList<String> papers = new ArrayList<>();

        Collection col = DatabaseManager.getCollection(this.getUri() + collectionId);

        XQueryService xQueryService = (XQueryService) col.getService("XQueryService", "1.0");
        xQueryService.setProperty("indent", "yes");
        xQueryService.setNamespace("b", TARGET_NAMESPACE);

        String xqueryExpression1 = String.format("xquery version \"3.1\";\n" +
                "\n" +
                "for $paper in collection(\"/db/sample/library/paper\")//paper\n" +
                "where $paper/status/published = \"published\"\n" +
                "let $publishedPapers := $paper\n" +
                "for $publishedPaper in $publishedPapers\n" +
                "for $p in doc(concat(\"/db/sample/library/paper/\", $publishedPaper/paper_title/text(), \".xml\"))\n"+
                "where $p/paper//*[contains(lower-case(text()[1]),lower-case('%s'))]\n" +
                "return $p/paper/paper_title/text()", text
        );
        CompiledExpression compiledXquery = xQueryService.compile(xqueryExpression1);
        ResourceSet result = xQueryService.execute(compiledXquery);
        System.out.println("Broj: "+result.getSize());
        ResourceIterator i = result.getIterator();
        Resource res = null;

        while(i.hasMoreResources()) {

            try {
                res = i.nextResource();
                papers.add(res.getContent().toString());
                //papers.add(res.getId().split("\\.")[0]+".xml");

            } finally {

                // don't forget to cleanup resources
                try {
                    ((EXistResource)res).freeResources();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }
        return papers;
    }
}


