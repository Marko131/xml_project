package tim10.project.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;
import tim10.project.model.DocumentStatus;
import tim10.project.model.scientific_paper.Paper;
import tim10.project.service.ScientificPaperService;
import tim10.project.web.dto.PaperDTO;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ScientificPaperController {

    @Autowired
    ScientificPaperService scientificPaperService;

    private String getStatus(Paper paper){
        if (paper.getStatus().getWaiting() != null) return paper.getStatus().getWaiting();
        if (paper.getStatus().getArchived() != null) return paper.getStatus().getArchived();
        if (paper.getStatus().getPublished() != null) return paper.getStatus().getPublished();
        return null;
    }

    @PostMapping("/api/paper")
    public Paper uploadPaper(@RequestParam("file") MultipartFile file) throws IOException, XMLDBException, JAXBException, ParserConfigurationException, SAXException, XPathExpressionException, TransformerException {

        byte[] encoded = file.getBytes();
        String content = new String(encoded, StandardCharsets.UTF_8);
        Reader reader = new StringReader(content);
        return scientificPaperService.uploadPaper(content, reader);
    }

    @PostMapping("/api/paperForm")
    public Paper uploadPaperFromForm(@RequestBody String content) throws XMLDBException, JAXBException, IOException, ParserConfigurationException, SAXException, XPathExpressionException, TransformerException {
        Reader reader = new StringReader(content);
        return scientificPaperService.uploadPaper(content, reader);
    }

    @GetMapping("/api/paper/{id}")
    public Paper getById(@PathVariable("id") String id) throws XMLDBException, JAXBException {
        return scientificPaperService.getById(id+".xml");
    }

    @GetMapping("/api/paper")
    public ArrayList<PaperDTO> getAll() throws XMLDBException, JAXBException {
        ArrayList<Paper> papers = scientificPaperService.getAll();
        ArrayList<PaperDTO> paperDTOS = new ArrayList<>();
        for (Paper paper: papers) {
            paperDTOS.add(new PaperDTO(paper.getPaperTitle().getValue(), getStatus(paper)));
        }
        return paperDTOS;
    }

    @GetMapping("/api/paper/download/xml/{id}")
    public ResponseEntity<Object> getXMLResourceById(@PathVariable("id") String id, HttpServletResponse response) throws XMLDBException, JAXBException, IOException {
        String content = scientificPaperService.getXMLResourceById(id+".xml");
        byte[] contents = content.getBytes();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        String filename = id+".xml";
        ContentDisposition contentDisposition = ContentDisposition
                .builder("inline")
                .filename(filename)
                .build();
        headers.setContentDisposition(contentDisposition);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return new ResponseEntity<>(contents, headers, HttpStatus.OK);
    }

    @GetMapping("/api/paper/preview/{id}")
    public String getHTMLResourceById(@PathVariable("id") String id) throws XMLDBException, JAXBException, ParserConfigurationException, TransformerException, SAXException, IOException {
        return scientificPaperService.getHTMLPaper(id+".xml");
    }

    @GetMapping("/api/paper/anonymous/{id}")
    public String getHTMLResourceByIdAnonymous(@PathVariable("id") String id) throws XMLDBException, JAXBException, ParserConfigurationException, TransformerException, SAXException, IOException {
        return scientificPaperService.getHTMLPaperAnonymous(id);
    }

    @GetMapping("/api/paper/archive/{id}")
    public ResponseEntity<String> archivePaper(@PathVariable("id") String id) throws XMLDBException, JAXBException, ParserConfigurationException, TransformerException, SAXException, IOException, XPathExpressionException {
        scientificPaperService.changeStatus(id+".xml", DocumentStatus.archived);
        return new ResponseEntity<String>("Paper successfully archived", HttpStatus.OK);
    }
}
