package tim10.project.repository;

import org.apache.commons.io.input.ReaderInputStream;
import org.dom4j.dom.DOMDocument;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

import org.xmldb.api.modules.XMLResource;
import tim10.project.model.DocumentStatus;
import tim10.project.model.cover_letter.CoverLetter;
import tim10.project.model.review.Review;
import tim10.project.model.scientific_paper.Paper;
import tim10.project.model.user.User;
import tim10.project.util.DocumentUtil;
import tim10.project.util.MetadataExtractor;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Test {

    @Autowired
    private ScientificPaperRepository scientificPaperRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private  CoverLetterRepository coverLetterRepository;

    @Autowired
    private RDFRepository rdfRepository;

    @Autowired
    private UserRepository userRepository;


    @org.junit.Test
    public void testGetById() throws XMLDBException, JAXBException {
        System.out.println(scientificPaperRepository.getById("/db/sample/library/paper", "paper1.xml").getAbstract().toString().toString().toString().toString());
    }

    @org.junit.Test
    public void testSave() throws XMLDBException, JAXBException, IOException {
        byte[] encoded = Files.readAllBytes(Paths.get("C:\\Users\\Woolfy\\Desktop\\xml_project\\data\\scientific_paper1.xml"));
        String content = new String(encoded, StandardCharsets.UTF_8);
        Reader reader = new StringReader(content);
        System.out.println(content);
        scientificPaperRepository.save("/db/sample/library/paper", "paper1.xml", reader);

    }


    @org.junit.Test
    public void testDelete() throws XMLDBException {
        scientificPaperRepository.delete("/db/sample/library", "paper2.xml");
    }

    @org.junit.Test
    public void testRDF() {
        String rdf = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                "<rdf:RDF xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"\n" +
                "         xmlns:pred=\"http://www.ftn.uns.ac.rs/rdf/examples/predicate/\">\n" +
                "\n" +
                "  <!-- Subject is defined with rdf:about attribute -->\n" +
                "  <rdf:Description rdf:about=\"http://www.ftn.uns.ac.rs/rdf/examples/person/John_Smith\">\n" +
                "    <pred:livesIn>London</pred:livesIn>\n" +
                "    <pred:profession>university professor</pred:profession>\n" +
                "    <pred:hobby>chess</pred:hobby>\n" +
                "    <!-- Property elements can also be defined as resources -->\n" +
                "    <pred:parentTo rdf:resource=\"http://www.ftn.uns.ac.rs/rdf/examples/person/Jane_Smith\"/>\n" +
                "  </rdf:Description>\n" +
                "\n" +
                "  <rdf:Description rdf:about=\"http://www.ftn.uns.ac.rs/rdf/examples/person/Jane_Smith\">\n" +
                "    <pred:livesIn>London</pred:livesIn>\n" +
                "    <pred:profession>dietician</pred:profession>\n" +
                "    <pred:hobby>gardening</pred:hobby>\n" +
                "    <!-- Property elements can also be defined as resources -->\n" +
                "    <pred:childOf rdf:resource=\"http://www.ftn.uns.ac.rs/rdf/examples/person/John_Smith\"/>\n" +
                "  </rdf:Description>\n" +
                "\n" +
                "  <rdf:Description rdf:about=\"http://www.ftn.uns.ac.rs/rdf/examples/person/Jack_Smith\">\n" +
                "    <pred:livesIn>Glasgow</pred:livesIn>\n" +
                "    <pred:profession>programmer</pred:profession>\n" +
                "    <pred:hobby>skydiving</pred:hobby>\n" +
                "  </rdf:Description>\n" +
                "\n" +
                "</rdf:RDF>";
        InputStream inputStream = new ByteArrayInputStream(rdf.getBytes());
        rdfRepository.save(inputStream, "Document1Test");
    }


    @org.junit.Test
    public void test2ExtractMetadata() throws IOException, SAXException, TransformerException {
        MetadataExtractor metadataExtractor = new MetadataExtractor();

        Reader reader = new FileReader(new File("C:\\Users\\Marko\\Desktop\\paper_instances\\paper1.xml"));
        Reader reader2 = new FileReader(new File("C:\\Users\\Marko\\Desktop\\paper_instances\\paper2.xml"));
        Reader reader3 = new FileReader(new File("C:\\Users\\Marko\\Desktop\\paper_instances\\paper3.xml"));
        Reader reader4 = new FileReader(new File("C:\\Users\\Marko\\Desktop\\paper_instances\\paper4.xml"));
        Reader reader5 = new FileReader(new File("C:\\Users\\Marko\\Desktop\\paper_instances\\paper5.xml"));

        ArrayList<Reader> readers = new ArrayList<>();
        readers.add(reader);
        readers.add(reader2);
        readers.add(reader3);
        readers.add(reader4);
        readers.add(reader5);

        for (Reader r :
                readers) {
            int counter = 1;
            InputStream inputStream = new ReaderInputStream(r, StandardCharsets.UTF_8);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            metadataExtractor.extractMetadata(inputStream, outputStream);
            rdfRepository.save(new ByteArrayInputStream(outputStream.toByteArray()), "document"+ ++counter);
        }
    }

    @org.junit.Test
    public void testGetXMLResource() throws XMLDBException, IOException, SAXException, TransformerException, JAXBException {
        String documentId = "paper1.xml";
        String xmlString = scientificPaperRepository.getXMLResourceById("/db/sample/library", documentId);
        MetadataExtractor metadataExtractor = new MetadataExtractor();

        Reader r = new StringReader(xmlString);

        InputStream inputStream = new ReaderInputStream(r, StandardCharsets.UTF_8);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        metadataExtractor.extractMetadata(inputStream, outputStream);
        System.out.println(xmlString);
        rdfRepository.save(new ByteArrayInputStream(outputStream.toByteArray()), documentId);

    }


    @org.junit.Test
    public void testSaveReview() throws IOException, XMLDBException, JAXBException {
        Reader reader = new FileReader(new File("C:\\Users\\Strahinja\\Desktop\\xml_project\\data\\review1.xml"));
        reviewRepository.save("/db/sample/library/review", "review2.xml", reader);
    }

    @org.junit.Test
    public void testSaveLetter() throws IOException, XMLDBException, JAXBException {
        Reader reader = new FileReader(new File("C:\\Users\\Strahinja\\Desktop\\xml_project\\data\\cover_letter1.xml"));
        coverLetterRepository.save("/db/sample/library/cover_letter", "cover_letter2.xml", reader);
    }

    @org.junit.Test
    public void testGetByIdReview() throws XMLDBException, JAXBException {
        reviewRepository.getById("/db/sample/library/review", "review1.xml");
    }

    @org.junit.Test
    public void testDeleteLetter() throws XMLDBException {
        coverLetterRepository.delete("/db/sample/library/cover_letter", "cover_letter1.xml");
    }

    @org.junit.Test
    public void testGetAllLetter() throws XMLDBException, JAXBException {
        ArrayList<CoverLetter> list = coverLetterRepository.getAll("/db/sample/library/cover_letter");
        for (CoverLetter el: list) {
            System.out.println(el.getSender().getName());
        }
    }

    @org.junit.Test
    public void testGetAllReview() throws XMLDBException, JAXBException {
        ArrayList<Review> list = reviewRepository.getAll("/db/sample/library/review");
        for (Review el: list) {
            System.out.println(el.getReviewer().getName());
        }
    }

    @org.junit.Test
    public void testGetAllPaper() throws XMLDBException, JAXBException {
        ArrayList<Paper> list = scientificPaperRepository.getAll("/db/sample/library/paper");
        for (Paper el: list) {
            System.out.println(el.toString());
        }
    }

    @org.junit.Test
    public void testCreateAnonymousScientificPaper() throws SAXException, ParserConfigurationException, XPathExpressionException, IOException, JAXBException, XMLDBException, TransformerException {
        scientificPaperRepository.createAnonymousDocument("/db/sample/library", "paper1.xml");
    }

    @org.junit.Test
    public void testChangeDocumentStatus() throws SAXException, ParserConfigurationException, XPathExpressionException, IOException, JAXBException, XMLDBException, TransformerException {
        scientificPaperRepository.changeDocumentStatus("/db/sample/library", "instance1.xml", DocumentStatus.published);
    }

    @org.junit.Test
    public void testCreateAnonymousReview() throws SAXException, ParserConfigurationException, XPathExpressionException, IOException, JAXBException, XMLDBException, TransformerException {
        reviewRepository.createAnonymousReview("/db/sample/library", "review1.xml");
    }

    @org.junit.Test
    public void testGetHTMLbyId() throws Exception {
        String xmlResource= scientificPaperRepository.getXMLResourceById("/db/sample/library", "scientific_paper1.xml");
        Document document = DocumentUtil.XMLStringToDocument(xmlResource);
        String html = DocumentUtil.generateHTMLStringFromXMLString(DocumentUtil.DocumentToString(document), "data/xsl/scientific_paper.xsl");
        System.out.println(html);
    }

    @org.junit.Test
    public void testGetHTMLReviewsByPaperTitle() throws Exception {
        Document document = reviewRepository.mergeReviewsByPaperTitle("/db/sample/library", "paper_title0");
        String html = DocumentUtil.generateHTMLStringFromXMLString(DocumentUtil.DocumentToString(document), "data/xsl/review.xsl");
        System.out.println(html);
    }

    @org.junit.Test
    public void testGetHTMLReviewById() throws XMLDBException, JAXBException, IOException, ParserConfigurationException, SAXException, TransformerException {
        String review = reviewRepository.getXMLResourceById("/db/sample/library", "review1.xml");
        String html = DocumentUtil.generateHTMLStringFromXMLString(review, "data/xsl/review.xsl");
        System.out.println(html);
    }

    @org.junit.Test
    public void testMergeAnnotations() throws Exception {
        Document doc = scientificPaperRepository.mergeNotes("/db/sample/library/anonymous");
        DocumentUtil.prettyPrint(doc);
    }

    @org.junit.Test
    public void testGetKeywordsFromPaper() throws SAXException, ParserConfigurationException, XPathExpressionException, IOException, JAXBException, XMLDBException {
        ArrayList<String> keywords = (ArrayList<String>) scientificPaperRepository.getKeywordsFromPaper("/db/sample/library", "scientific_paper1.xml");
        keywords.forEach(System.out::println);
    }

    @org.junit.Test
    public void testFindUserByEmail(){
        User user = userRepository.findByEmail("admin@gmail.com");
        System.out.println(user.getName());
    }
}
