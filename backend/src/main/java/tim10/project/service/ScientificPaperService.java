package tim10.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;
import tim10.project.model.DocumentStatus;
import tim10.project.model.scientific_paper.Paper;
import tim10.project.repository.ScientificPaperRepository;
import tim10.project.service.exceptions.NotFoundException;
import tim10.project.service.exceptions.PaperAlreadyExists;
import tim10.project.util.DocumentUtil;

import javax.print.Doc;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScientificPaperService {

    @Autowired
    private ScientificPaperRepository scientificPaperRepository;

    public Paper uploadPaper(String content, Reader reader) throws XMLDBException, JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance("tim10.project.model.scientific_paper");
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Paper paper = (Paper) unmarshaller.unmarshal(reader);
        Paper paperFromDatabase = null;
        try {
            paperFromDatabase = scientificPaperRepository.getById("/db/sample/library/paper", paper.getPaperTitle().getValue() + ".xml");
        } catch (Exception ignored) {
        }
        if (paperFromDatabase != null)
            throw new PaperAlreadyExists();
        Reader inputReader = new StringReader(content);
        scientificPaperRepository.save("/db/sample/library/paper", paper.getPaperTitle().getValue() + ".xml", inputReader);
        return paper;
    }

    public Paper getById(String documentId) throws XMLDBException, JAXBException {
        Paper paper = scientificPaperRepository.getById("/db/sample/library/paper", documentId);
        if (paper == null)
            throw new NotFoundException(String.format("Scientific paper with id:%s does not exist", documentId));
        return paper;
    }

    public ArrayList<Paper> getAll() throws XMLDBException, JAXBException {
        ArrayList<Paper> papers = scientificPaperRepository.getAll("/db/sample/library/paper");
        if (papers.isEmpty()) throw new NotFoundException("No paper found");
        return papers;
    }

    public String getXMLResourceById(String documentId) throws XMLDBException, JAXBException {
        String paper = scientificPaperRepository.getXMLResourceById("/db/sample/library/paper", documentId);
        if (paper == null)
            throw new NotFoundException(String.format("Scientific paper with id:%s does not exist", documentId));
        return paper;
    }

    public ArrayList<Paper> getPapersByUserName(String userName) throws XMLDBException, JAXBException {
        return scientificPaperRepository.getPapersByUserName("/db/sample/library/paper", userName);
    }

    public String getHTMLPaper(String paperId) throws IOException, ParserConfigurationException, SAXException, TransformerException, XMLDBException, JAXBException {
        String content = scientificPaperRepository.getXMLResourceById("/db/sample/library/paper", paperId);
        return DocumentUtil.generateHTMLStringFromXMLString(content, "data/xsl/scientific_paper.xsl");
    }

    public void changeStatus(String documentId, DocumentStatus documentStatus) throws SAXException, ParserConfigurationException, XPathExpressionException, IOException, JAXBException, XMLDBException, TransformerException {
        scientificPaperRepository.changeDocumentStatus("/db/sample/library/paper", documentId, documentStatus);
    }

    public List<String> searchPaperByText(String text) throws XMLDBException, JAXBException {
        return scientificPaperRepository.searchPaperByText("/db/sample/library/paper", text);
    }
}
