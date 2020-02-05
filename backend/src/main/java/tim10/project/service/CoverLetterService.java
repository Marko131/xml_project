package tim10.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;
import tim10.project.model.cover_letter.CoverLetter;
import tim10.project.repository.CoverLetterRepository;
import tim10.project.service.exceptions.CoverLetterAlreadyExists;
import tim10.project.service.exceptions.InvalidSchemaException;
import tim10.project.service.exceptions.NotFoundException;
import tim10.project.service.exceptions.PaperAlreadyExists;
import tim10.project.util.XMLValidator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;

@Service
public class CoverLetterService {

    @Autowired
    private CoverLetterRepository coverLetterRepository;

    public CoverLetter uploadLetter(String content, Reader reader) throws XMLDBException, JAXBException, IOException {
        if (!XMLValidator.validate(content, "data/schema/Cover_Letter.xsd")) throw new InvalidSchemaException();
        JAXBContext context = JAXBContext.newInstance("tim10.project.model.cover_letter");
        Unmarshaller unmarshaller = context.createUnmarshaller();
        CoverLetter letter = (CoverLetter) unmarshaller.unmarshal(reader);
        CoverLetter letterFromDatabase = null;
        try{
            letterFromDatabase = coverLetterRepository.getById("/db/sample/library/cover_letter", letter.getSender().getName() + " - " + letter.getReceiver().getName() + ".xml");
        } catch (Exception ignored) {
        }
        if (letterFromDatabase != null) throw new CoverLetterAlreadyExists();
        Reader inputReader = new StringReader(content);
        coverLetterRepository.save("/db/sample/library/cover_letter", letter.getSender().getName() + " - " + letter.getReceiver().getName() + ".xml", inputReader);
        return letter;
    }

    public CoverLetter getById(String documentId) throws XMLDBException, JAXBException {
        CoverLetter letter = coverLetterRepository.getById("/db/sample/library/cover_letter", documentId);
        if (letter == null) throw new NotFoundException(String.format("Cover letter with id:%s does not exist", documentId));
        return letter;
    }

    public ArrayList<CoverLetter> getAll() throws XMLDBException, JAXBException {
        ArrayList<CoverLetter> letters = coverLetterRepository.getAll("/db/sample/library/cover_letter");
        if (letters.isEmpty()) throw new NotFoundException("No letter found");
        return letters;
    }
}
