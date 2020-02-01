package tim10.project.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xmldb.api.base.XMLDBException;
import tim10.project.model.scientific_paper.Paper;
import tim10.project.service.ScientificPaperService;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

@RestController
public class ScientificPaperController {

    @Autowired
    ScientificPaperService scientificPaperService;

    @PostMapping("/api/paper")
    public Paper uploadPaper(@RequestParam("file") MultipartFile file) throws IOException, XMLDBException, JAXBException {
        byte[] encoded = file.getBytes();
        String content = new String(encoded, StandardCharsets.UTF_8);
        Reader reader = new StringReader(content);
        return scientificPaperService.uploadPaper(content, reader);
    }

    @GetMapping("/api/paper/{id}")
    public Paper getById(@PathVariable("id") String id) throws XMLDBException, JAXBException {
        return scientificPaperService.getById(id);
    }

    @GetMapping("/api/paper")
    public ArrayList<Paper> getAll() throws XMLDBException, JAXBException {
        return scientificPaperService.getAll();
    }

    @GetMapping("/api/paper/xml/{id}")
    public String getXMLResourceById(@PathVariable("id") String id) throws XMLDBException, JAXBException {
        return scientificPaperService.getXMLResourceById(id);
    }
}
