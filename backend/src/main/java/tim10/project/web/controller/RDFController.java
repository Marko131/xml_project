package tim10.project.web.controller;

import org.hibernate.engine.jdbc.ReaderInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.xmldb.api.base.XMLDBException;
import tim10.project.model.scientific_paper.Paper;
import tim10.project.service.RDFService;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.nio.charset.StandardCharsets;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class RDFController {

    @Autowired
    private RDFService rdfService;

    @PostMapping("/api/rdf")
    public ResponseEntity<String> uploadRDF(@RequestParam("file") MultipartFile file) throws IOException, XMLDBException, JAXBException {
        byte[] encoded = file.getBytes();
        String documentId = file.getOriginalFilename();
        String content = new String(encoded, StandardCharsets.UTF_8);
        InputStream inputStream = file.getInputStream();
        rdfService.uploadRDF(inputStream, documentId);
        return new ResponseEntity<String>("RDF file uploaded successfully", HttpStatus.OK);
    }
}
