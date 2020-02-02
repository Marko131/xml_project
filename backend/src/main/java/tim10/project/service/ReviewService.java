package tim10.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;
import tim10.project.model.review.Review;
import tim10.project.model.scientific_paper.Paper;
import tim10.project.repository.ReviewRepository;
import tim10.project.service.exceptions.NotFoundException;
import tim10.project.service.exceptions.PaperAlreadyExists;
import tim10.project.service.exceptions.ReviewAlreadyExists;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Review uploadReview(String content, Reader reader) throws XMLDBException, JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance("tim10.project.model.review");
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Review review = (Review) unmarshaller.unmarshal(reader);
        Review reviewFromDatabase = reviewRepository.getById("/db/sample/library/review", review.getPaperTitle() + " - " + review.getReviewer().getName() + ".xml");
        if (reviewFromDatabase != null) throw new ReviewAlreadyExists();
        Reader inputReader = new StringReader(content);
        reviewRepository.save("/db/sample/library/review", review.getPaperTitle() + " - " + review.getReviewer().getName() + ".xml", inputReader);
        return review;
    }

    public Review getById(String documentId) throws XMLDBException, JAXBException {
        Review review = reviewRepository.getById("/db/sample/library/review", documentId);
        if (review == null) throw new NotFoundException(String.format("Review with id:%s does not exist", documentId));
        return review;
    }

    public ArrayList<Review> getAll() throws XMLDBException, JAXBException {
        ArrayList<Review> reviews = reviewRepository.getAll("/db/sample/library/review");
        if (reviews.isEmpty()) throw new NotFoundException("No review found");
        return reviews;
    }
}
