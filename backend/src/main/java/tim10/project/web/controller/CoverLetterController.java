package tim10.project.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xmldb.api.base.XMLDBException;
import tim10.project.model.cover_letter.CoverLetter;
import tim10.project.service.CoverLetterService;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CoverLetterController {

    @Autowired
    CoverLetterService coverLetterService;

    @PostMapping("/api/letter")
    public CoverLetter uploadLetter(@RequestParam("file") MultipartFile file) throws IOException, XMLDBException, JAXBException {
        byte[] encoded = file.getBytes();
        String content = new String(encoded, StandardCharsets.UTF_8);
        Reader reader = new StringReader(content);
        return coverLetterService.uploadLetter(content, reader);
    }

    @GetMapping("/api/letter/{id}")
    public CoverLetter getById(@PathVariable("id") String id) throws XMLDBException, JAXBException {
        return coverLetterService.getById(id);
    }

    @GetMapping("/api/letter")
    public ArrayList<CoverLetter> getAll() throws XMLDBException, JAXBException {
        return coverLetterService.getAll();
    }
}
