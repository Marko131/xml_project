package tim10.project.repository;

import org.xmldb.api.base.XMLDBException;
import tim10.project.model.scientific_paper.Paper;

import javax.xml.bind.JAXBException;

import static org.exist.security.utils.Utils.getOrCreateCollection;

public interface IScientificPaper {

    Paper getById(String collectionId, String documentId) throws XMLDBException, JAXBException;


}
