
package tim10.project.model.scientific_paper;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the tim10.project.model.scientific_paper package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _TParagraphImage_QNAME = new QName("", "image");
    private final static QName _TParagraphRef_QNAME = new QName("", "ref");
    private final static QName _TParagraphQuote_QNAME = new QName("", "quote");
    private final static QName _TParagraphLink_QNAME = new QName("", "link");
    private final static QName _TParagraphText_QNAME = new QName("", "text");
    private final static QName _TParagraphList_QNAME = new QName("", "list");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: tim10.project.model.scientific_paper
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Paper }
     * 
     */
    public Paper createPaper() {
        return new Paper();
    }

    /**
     * Create an instance of {@link TAuthor }
     * 
     */
    public TAuthor createTAuthor() {
        return new TAuthor();
    }

    /**
     * Create an instance of {@link TQuote }
     * 
     */
    public TQuote createTQuote() {
        return new TQuote();
    }

    /**
     * Create an instance of {@link Paper.Abstract }
     * 
     */
    public Paper.Abstract createPaperAbstract() {
        return new Paper.Abstract();
    }

    /**
     * Create an instance of {@link Paper.Abstract.Keywords }
     * 
     */
    public Paper.Abstract.Keywords createPaperAbstractKeywords() {
        return new Paper.Abstract.Keywords();
    }

    /**
     * Create an instance of {@link Paper.Status }
     * 
     */
    public Paper.Status createPaperStatus() {
        return new Paper.Status();
    }

    /**
     * Create an instance of {@link Paper.PaperTitle }
     * 
     */
    public Paper.PaperTitle createPaperPaperTitle() {
        return new Paper.PaperTitle();
    }

    /**
     * Create an instance of {@link Paper.Authors }
     * 
     */
    public Paper.Authors createPaperAuthors() {
        return new Paper.Authors();
    }

    /**
     * Create an instance of {@link TSection }
     * 
     */
    public TSection createTSection() {
        return new TSection();
    }

    /**
     * Create an instance of {@link Paper.References }
     * 
     */
    public Paper.References createPaperReferences() {
        return new Paper.References();
    }

    /**
     * Create an instance of {@link Paper.CorrespondingAuthors }
     * 
     */
    public Paper.CorrespondingAuthors createPaperCorrespondingAuthors() {
        return new Paper.CorrespondingAuthors();
    }

    /**
     * Create an instance of {@link Paper.Citations }
     * 
     */
    public Paper.Citations createPaperCitations() {
        return new Paper.Citations();
    }

    /**
     * Create an instance of {@link TParagraph }
     * 
     */
    public TParagraph createTParagraph() {
        return new TParagraph();
    }

    /**
     * Create an instance of {@link TList }
     * 
     */
    public TList createTList() {
        return new TList();
    }

    /**
     * Create an instance of {@link TRef }
     * 
     */
    public TRef createTRef() {
        return new TRef();
    }

    /**
     * Create an instance of {@link TImage }
     * 
     */
    public TImage createTImage() {
        return new TImage();
    }

    /**
     * Create an instance of {@link TLink }
     * 
     */
    public TLink createTLink() {
        return new TLink();
    }

    /**
     * Create an instance of {@link TCitation }
     * 
     */
    public TCitation createTCitation() {
        return new TCitation();
    }

    /**
     * Create an instance of {@link TReference }
     * 
     */
    public TReference createTReference() {
        return new TReference();
    }

    /**
     * Create an instance of {@link TBox }
     * 
     */
    public TBox createTBox() {
        return new TBox();
    }

    /**
     * Create an instance of {@link TAuthor.Name }
     * 
     */
    public TAuthor.Name createTAuthorName() {
        return new TAuthor.Name();
    }

    /**
     * Create an instance of {@link TAuthor.University }
     * 
     */
    public TAuthor.University createTAuthorUniversity() {
        return new TAuthor.University();
    }

    /**
     * Create an instance of {@link TAuthor.City }
     * 
     */
    public TAuthor.City createTAuthorCity() {
        return new TAuthor.City();
    }

    /**
     * Create an instance of {@link TAuthor.State }
     * 
     */
    public TAuthor.State createTAuthorState() {
        return new TAuthor.State();
    }

    /**
     * Create an instance of {@link TAuthor.Country }
     * 
     */
    public TAuthor.Country createTAuthorCountry() {
        return new TAuthor.Country();
    }

    /**
     * Create an instance of {@link TQuote.Source }
     * 
     */
    public TQuote.Source createTQuoteSource() {
        return new TQuote.Source();
    }

    /**
     * Create an instance of {@link Paper.Abstract.AbstractElement }
     * 
     */
    public Paper.Abstract.AbstractElement createPaperAbstractAbstractElement() {
        return new Paper.Abstract.AbstractElement();
    }

    /**
     * Create an instance of {@link Paper.Abstract.Keywords.Keyword }
     * 
     */
    public Paper.Abstract.Keywords.Keyword createPaperAbstractKeywordsKeyword() {
        return new Paper.Abstract.Keywords.Keyword();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TImage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "image", scope = TParagraph.class)
    public JAXBElement<TImage> createTParagraphImage(TImage value) {
        return new JAXBElement<TImage>(_TParagraphImage_QNAME, TImage.class, TParagraph.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TRef }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ref", scope = TParagraph.class)
    public JAXBElement<TRef> createTParagraphRef(TRef value) {
        return new JAXBElement<TRef>(_TParagraphRef_QNAME, TRef.class, TParagraph.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TQuote }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "quote", scope = TParagraph.class)
    public JAXBElement<TQuote> createTParagraphQuote(TQuote value) {
        return new JAXBElement<TQuote>(_TParagraphQuote_QNAME, TQuote.class, TParagraph.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TLink }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "link", scope = TParagraph.class)
    public JAXBElement<TLink> createTParagraphLink(TLink value) {
        return new JAXBElement<TLink>(_TParagraphLink_QNAME, TLink.class, TParagraph.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "text", scope = TParagraph.class)
    public JAXBElement<String> createTParagraphText(String value) {
        return new JAXBElement<String>(_TParagraphText_QNAME, String.class, TParagraph.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "list", scope = TParagraph.class)
    public JAXBElement<TList> createTParagraphList(TList value) {
        return new JAXBElement<TList>(_TParagraphList_QNAME, TList.class, TParagraph.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TImage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "image", scope = TLink.class)
    public JAXBElement<TImage> createTLinkImage(TImage value) {
        return new JAXBElement<TImage>(_TParagraphImage_QNAME, TImage.class, TLink.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "text", scope = TLink.class)
    public JAXBElement<String> createTLinkText(String value) {
        return new JAXBElement<String>(_TParagraphText_QNAME, String.class, TLink.class, value);
    }

}
