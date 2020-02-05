package tim10.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tim10.project.repository.RDFRepository;

import java.io.InputStream;

@Service
public class RDFService {

    @Autowired
    private RDFRepository rdfRepository;

    public void uploadRDF(InputStream rdfInputStream, String documentId){
        rdfRepository.save(rdfInputStream);
    }
}
