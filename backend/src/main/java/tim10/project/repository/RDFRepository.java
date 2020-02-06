package tim10.project.repository;

import org.apache.commons.io.input.ReaderInputStream;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
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
import java.util.ArrayList;
import java.util.List;

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

    public void save(InputStream rdfInputStream){

        Model model = ModelFactory.createDefaultModel();
        model.read(rdfInputStream, null);

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        model.write(out, SparqlUtil.NTRIPLES);

        System.out.println("[INFO] Writing the triples to a named graph \"" + SCIENTIFIC_PAPER_NAMED_GRAPH_URI + "\".");
        String sparqlUpdate = SparqlUtil.insertData( this.dataEndpoint + "/metadata", new String(out.toByteArray()));


        // UpdateRequest represents a unit of execution
        UpdateRequest update = UpdateFactory.create(sparqlUpdate);

        UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, updateEndpoint);
        processor.execute();

    }

    public void addPaper(String xmlDocument) throws IOException, SAXException, TransformerException {
        MetadataExtractor metadataExtractor = new MetadataExtractor();
        Reader r = new StringReader(xmlDocument);
        InputStream inputStream = new ReaderInputStream(r, StandardCharsets.UTF_8);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        metadataExtractor.extractMetadata(inputStream, outputStream);
        save(new ByteArrayInputStream(outputStream.toByteArray()));
    }

    public String search(String title, String author, List<String> keywords){
        StringBuilder stringBuilder = new StringBuilder();
        keywords.forEach(keyword -> stringBuilder.append(String.format("\nregex(str(?keyword), \"%s\", \"i\") &&", keyword)));

        String query = String.format("" +
                "SELECT DISTINCT ?paper ?title ?author ?keyword FROM <http://localhost:8080/fuseki/XMLProject/data/metadata> " +
                "\nWHERE {" +
                "\n?paper <http://www.ftn.uns.ac.rs/rdf/examples/predicate/title> ?title . " +
                "\n?author <http://www.ftn.uns.ac.rs/rdf/examples/predicate/name> ?name ." +
                "\n?paper <http://www.ftn.uns.ac.rs/rdf/examples/predicate/keyword> ?keyword" +
                "\nFILTER(" +
                stringBuilder.toString()+
                "\nregex(str(?name), '%s', 'i') && " +
                "\nregex(str(?title), '%s', 'i') " +
                "\n)}", author, title);
        QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://localhost:8080/fuseki/XMLProject/query", query);
        ResultSet results = queryExecution.execSelect();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ResultSetFormatter.outputAsJSON(out, results);
        String result = new String(out.toByteArray());
        queryExecution.close();
        return result;
    }
}
