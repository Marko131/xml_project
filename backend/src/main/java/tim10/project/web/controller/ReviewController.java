package tim10.project.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.xmldb.api.base.XMLDBException;
import tim10.project.model.review.Review;
import tim10.project.service.ReviewService;
import tim10.project.util.DocumentUtil;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @PostMapping("/api/review")
    public Review uploadReview(@RequestParam("file") MultipartFile file) throws IOException, XMLDBException, JAXBException {
        byte[] encoded = file.getBytes();
        String content = new String(encoded, StandardCharsets.UTF_8);
        Reader reader = new StringReader(content);
        return reviewService.uploadReview(content, reader);
    }

    @GetMapping("/api/review/{id}")
    public Review getById(@PathVariable("id") String id) throws XMLDBException, JAXBException {
        return reviewService.getById(id);
    }

    @GetMapping("/api/review")
    public ArrayList<Review> getAll() throws XMLDBException, JAXBException {
        return reviewService.getAll();
    }

    @GetMapping("/api/merge/{paperTitle}")
    public ResponseEntity<Object> downloadMergedReviews(@PathVariable("paperTitle") String paperTitle) throws Exception {
        Document content = reviewService.mergeReviewsByPaperTitle(paperTitle);
        byte[] contents = DocumentUtil.DocumentToString(content).getBytes();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        String filename = paperTitle+".xml";
        ContentDisposition contentDisposition = ContentDisposition
                .builder("inline")
                .filename(filename)
                .build();
        headers.setContentDisposition(contentDisposition);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return new ResponseEntity<>(contents, headers, HttpStatus.OK);
    }
}
