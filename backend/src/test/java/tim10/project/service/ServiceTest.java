package tim10.project.service;

import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;
import tim10.project.model.scientific_paper.Paper;
import tim10.project.repository.ScientificPaperRepository;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServiceTest {

    @Autowired
    ScientificPaperService scientificPaperService;

    @org.junit.Test
    public void testUpload() throws IOException, XMLDBException, JAXBException, TransformerException, SAXException, XPathExpressionException, ParserConfigurationException {
        byte[] encoded = Files.readAllBytes(Paths.get("C:\\Users\\Woolfy\\Desktop\\xml_project\\data\\scientific_paper1.xml"));
        String content = new String(encoded, StandardCharsets.UTF_8);
        Reader reader = new StringReader(content);
        Paper paper = scientificPaperService.uploadPaper(content, reader);
        System.out.println(paper.getPaperTitle().getValue());
        assertNotNull(paper.getPaperTitle());
    }
}
