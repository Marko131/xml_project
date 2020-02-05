package tim10.project.util;

import net.sf.saxon.TransformerFactoryImpl;
import org.apache.fop.apps.*;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xmldb.api.modules.XMLResource;



import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

public class DocumentUtil {

    public static Document XMLStringToDocument(String xmlString) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        DocumentBuilder b = f.newDocumentBuilder();
        File temp = File.createTempFile("temp_file", ".xml");
        temp.deleteOnExit();
        BufferedWriter out = new BufferedWriter(new FileWriter(temp));
        out.write(xmlString);
        out.close();
        return b.parse(temp);
    }

    public static String DocumentToString(Document document) throws TransformerException {
        DOMSource domSource = new DOMSource(document);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.transform(domSource, result);
        return writer.toString();
    }

    public static void prettyPrint(Document xml) throws Exception {
        Transformer tf = TransformerFactory.newInstance().newTransformer();
        tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        Writer out = new StringWriter();
        tf.transform(new DOMSource(xml), new StreamResult(out));
        System.out.println(out.toString());
    }

    public static String generateHTMLStringFromXMLString(String xmlString, String xsltPath) throws TransformerException, IOException, SAXException, ParserConfigurationException {
        StreamSource transformSource = new StreamSource(new File(xsltPath));

        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer(transformSource);
        transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        // Generate XHTML
        transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");

        // Transform DOM to HTML
        DOMSource source = new DOMSource(XMLStringToDocument(xmlString));
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        transformer.transform(source, result);

        return writer.toString();
    }

    public OutputStream generatePdfFromXmlString(String xmlString, String xsltPath) throws TransformerException, FOPException {
        StreamSource streamSource = new StreamSource(new File(xsltPath));
        InputStream inputStream = new ByteArrayInputStream(xmlString.getBytes());
        StreamSource xmlSource = new StreamSource(inputStream);
        FopFactory factory = FopFactory.newInstance();
        TransformerFactory transformerFactory = new TransformerFactoryImpl();
        FOUserAgent userAgent = factory.newFOUserAgent();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Transformer transformer = transformerFactory.newTransformer(streamSource);
        Fop fop = factory.newFop(MimeConstants.MIME_PDF, userAgent, outputStream);
        Result res = new SAXResult(fop.getDefaultHandler());
        transformer.transform(xmlSource, res);
        return outputStream;
    }
}
