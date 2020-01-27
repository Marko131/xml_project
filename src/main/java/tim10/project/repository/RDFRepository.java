package tim10.project.repository;

import org.apache.commons.io.input.ReaderInputStream;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.xml.sax.SAXException;
import tim10.project.util.MetadataExtractor;
import tim10.project.util.SparqlUtil;

import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.charset.StandardCharsets;

@Repository
public class RDFRepository {

    private static final String SCIENTIFIC_PAPER_NAMED_GRAPH_URI = "/example/scientific_paper/metadata";

    public String dataEndpoint;
    public String updateEndpoint;
    public String queryEndpoint;

    public RDFRepository(@Value("${rdf.endpoint}") String endpoint, @Value("${rdf.update}" )String updateEndpoint, @Value("${rdf.dataset}") String dataset, @Value("${rdf.query}") String query, @Value("${rdf.update}") String update, @Value("${rdf.data}") String data){
        this.queryEndpoint = String.join("/", endpoint, dataset, query);
        this.dataEndpoint = String.join("/", endpoint, dataset, data);
        this.updateEndpoint = String.join("/", endpoint, dataset, update);
    }

    public void save(InputStream rdfInputStream, String documentId){

        Model model = ModelFactory.createDefaultModel();
        model.read(rdfInputStream, null);

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        model.write(out, SparqlUtil.NTRIPLES);

        System.out.println("[INFO] Writing the triples to a named graph \"" + SCIENTIFIC_PAPER_NAMED_GRAPH_URI + "\".");
        String sparqlUpdate = SparqlUtil.insertData( this.dataEndpoint + documentId, new String(out.toByteArray()));
        System.out.println(sparqlUpdate);

        // UpdateRequest represents a unit of execution
        UpdateRequest update = UpdateFactory.create(sparqlUpdate);

        UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, updateEndpoint);
        processor.execute();

    }

    public void addPaper(String xmlDocument, String documentId) throws IOException, SAXException, TransformerException {
        MetadataExtractor metadataExtractor = new MetadataExtractor();
        Reader r = new StringReader(xmlDocument);
        InputStream inputStream = new ReaderInputStream(r, StandardCharsets.UTF_8);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        metadataExtractor.extractMetadata(inputStream, outputStream);
        save(new ByteArrayInputStream(outputStream.toByteArray()), documentId);
    }
}
