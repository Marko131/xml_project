
package tim10.project.model.scientific_paper;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="paper_title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="authors">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="author" type="{}t_author" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="abstract">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="keywords">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="keyword" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="abstract_element" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                           &lt;attribute name="title" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="quote" type="{}t_quote" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="section" type="{}t_section" maxOccurs="unbounded"/>
 *         &lt;element name="references">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="reference" type="{}t_reference" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="corresponding_authors">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="corresponding_author" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="citations">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="citation" type="{}t_citation" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="date_received" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="date_revised" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="date_accepted" type="{http://www.w3.org/2001/XMLSchema}date" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "paperTitle",
    "authors",
    "_abstract",
    "quote",
    "section",
    "references",
    "correspondingAuthors",
    "citations"
})
@XmlRootElement(name = "paper")
public class Paper {

    @XmlElement(name = "paper_title", required = true)
    protected String paperTitle;
    @XmlElement(required = true)
    protected Paper.Authors authors;
    @XmlElement(name = "abstract", required = true)
    protected Paper.Abstract _abstract;
    protected List<TQuote> quote;
    @XmlElement(required = true)
    protected List<TSection> section;
    @XmlElement(required = true)
    protected Paper.References references;
    @XmlElement(name = "corresponding_authors", required = true)
    protected Paper.CorrespondingAuthors correspondingAuthors;
    @XmlElement(required = true)
    protected Paper.Citations citations;
    @XmlAttribute(name = "date_received")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateReceived;
    @XmlAttribute(name = "date_revised")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateRevised;
    @XmlAttribute(name = "date_accepted")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateAccepted;

    /**
     * Gets the value of the paperTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaperTitle() {
        return paperTitle;
    }

    /**
     * Sets the value of the paperTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaperTitle(String value) {
        this.paperTitle = value;
    }

    /**
     * Gets the value of the authors property.
     * 
     * @return
     *     possible object is
     *     {@link Paper.Authors }
     *     
     */
    public Paper.Authors getAuthors() {
        return authors;
    }

    /**
     * Sets the value of the authors property.
     * 
     * @param value
     *     allowed object is
     *     {@link Paper.Authors }
     *     
     */
    public void setAuthors(Paper.Authors value) {
        this.authors = value;
    }

    /**
     * Gets the value of the abstract property.
     * 
     * @return
     *     possible object is
     *     {@link Paper.Abstract }
     *     
     */
    public Paper.Abstract getAbstract() {
        return _abstract;
    }

    /**
     * Sets the value of the abstract property.
     * 
     * @param value
     *     allowed object is
     *     {@link Paper.Abstract }
     *     
     */
    public void setAbstract(Paper.Abstract value) {
        this._abstract = value;
    }

    /**
     * Gets the value of the quote property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the quote property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQuote().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TQuote }
     * 
     * 
     */
    public List<TQuote> getQuote() {
        if (quote == null) {
            quote = new ArrayList<TQuote>();
        }
        return this.quote;
    }

    /**
     * Gets the value of the section property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the section property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSection().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TSection }
     * 
     * 
     */
    public List<TSection> getSection() {
        if (section == null) {
            section = new ArrayList<TSection>();
        }
        return this.section;
    }

    /**
     * Gets the value of the references property.
     * 
     * @return
     *     possible object is
     *     {@link Paper.References }
     *     
     */
    public Paper.References getReferences() {
        return references;
    }

    /**
     * Sets the value of the references property.
     * 
     * @param value
     *     allowed object is
     *     {@link Paper.References }
     *     
     */
    public void setReferences(Paper.References value) {
        this.references = value;
    }

    /**
     * Gets the value of the correspondingAuthors property.
     * 
     * @return
     *     possible object is
     *     {@link Paper.CorrespondingAuthors }
     *     
     */
    public Paper.CorrespondingAuthors getCorrespondingAuthors() {
        return correspondingAuthors;
    }

    /**
     * Sets the value of the correspondingAuthors property.
     * 
     * @param value
     *     allowed object is
     *     {@link Paper.CorrespondingAuthors }
     *     
     */
    public void setCorrespondingAuthors(Paper.CorrespondingAuthors value) {
        this.correspondingAuthors = value;
    }

    /**
     * Gets the value of the citations property.
     * 
     * @return
     *     possible object is
     *     {@link Paper.Citations }
     *     
     */
    public Paper.Citations getCitations() {
        return citations;
    }

    /**
     * Sets the value of the citations property.
     * 
     * @param value
     *     allowed object is
     *     {@link Paper.Citations }
     *     
     */
    public void setCitations(Paper.Citations value) {
        this.citations = value;
    }

    /**
     * Gets the value of the dateReceived property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateReceived() {
        return dateReceived;
    }

    /**
     * Sets the value of the dateReceived property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateReceived(XMLGregorianCalendar value) {
        this.dateReceived = value;
    }

    /**
     * Gets the value of the dateRevised property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateRevised() {
        return dateRevised;
    }

    /**
     * Sets the value of the dateRevised property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateRevised(XMLGregorianCalendar value) {
        this.dateRevised = value;
    }

    /**
     * Gets the value of the dateAccepted property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateAccepted() {
        return dateAccepted;
    }

    /**
     * Sets the value of the dateAccepted property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateAccepted(XMLGregorianCalendar value) {
        this.dateAccepted = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="keywords">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="keyword" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="abstract_element" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *                 &lt;attribute name="title" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/extension>
     *             &lt;/simpleContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "keywords",
        "abstractElement"
    })
    public static class Abstract {

        @XmlElement(required = true)
        protected Paper.Abstract.Keywords keywords;
        @XmlElement(name = "abstract_element", required = true)
        protected List<Paper.Abstract.AbstractElement> abstractElement;

        /**
         * Gets the value of the keywords property.
         * 
         * @return
         *     possible object is
         *     {@link Paper.Abstract.Keywords }
         *     
         */
        public Paper.Abstract.Keywords getKeywords() {
            return keywords;
        }

        /**
         * Sets the value of the keywords property.
         * 
         * @param value
         *     allowed object is
         *     {@link Paper.Abstract.Keywords }
         *     
         */
        public void setKeywords(Paper.Abstract.Keywords value) {
            this.keywords = value;
        }

        /**
         * Gets the value of the abstractElement property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the abstractElement property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAbstractElement().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Paper.Abstract.AbstractElement }
         * 
         * 
         */
        public List<Paper.Abstract.AbstractElement> getAbstractElement() {
            if (abstractElement == null) {
                abstractElement = new ArrayList<Paper.Abstract.AbstractElement>();
            }
            return this.abstractElement;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;simpleContent>
         *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
         *       &lt;attribute name="title" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/extension>
         *   &lt;/simpleContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class AbstractElement {

            @XmlValue
            protected String value;
            @XmlAttribute(name = "title")
            protected String title;

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

            /**
             * Gets the value of the title property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTitle() {
                return title;
            }

            /**
             * Sets the value of the title property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTitle(String value) {
                this.title = value;
            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="keyword" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "keyword"
        })
        public static class Keywords {

            @XmlElement(required = true)
            protected List<String> keyword;

            /**
             * Gets the value of the keyword property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the keyword property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getKeyword().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link String }
             * 
             * 
             */
            public List<String> getKeyword() {
                if (keyword == null) {
                    keyword = new ArrayList<String>();
                }
                return this.keyword;
            }

        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="author" type="{}t_author" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "author"
    })
    public static class Authors {

        @XmlElement(required = true)
        protected List<TAuthor> author;

        /**
         * Gets the value of the author property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the author property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAuthor().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TAuthor }
         * 
         * 
         */
        public List<TAuthor> getAuthor() {
            if (author == null) {
                author = new ArrayList<TAuthor>();
            }
            return this.author;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="citation" type="{}t_citation" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "citation"
    })
    public static class Citations {

        @XmlElement(required = true)
        protected List<TCitation> citation;

        /**
         * Gets the value of the citation property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the citation property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCitation().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TCitation }
         * 
         * 
         */
        public List<TCitation> getCitation() {
            if (citation == null) {
                citation = new ArrayList<TCitation>();
            }
            return this.citation;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="corresponding_author" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "correspondingAuthor"
    })
    public static class CorrespondingAuthors {

        @XmlElement(name = "corresponding_author", required = true)
        protected List<String> correspondingAuthor;

        /**
         * Gets the value of the correspondingAuthor property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the correspondingAuthor property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCorrespondingAuthor().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getCorrespondingAuthor() {
            if (correspondingAuthor == null) {
                correspondingAuthor = new ArrayList<String>();
            }
            return this.correspondingAuthor;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="reference" type="{}t_reference" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "reference"
    })
    public static class References {

        @XmlElement(required = true)
        protected List<TReference> reference;

        /**
         * Gets the value of the reference property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the reference property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getReference().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TReference }
         * 
         * 
         */
        public List<TReference> getReference() {
            if (reference == null) {
                reference = new ArrayList<TReference>();
            }
            return this.reference;
        }

    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("");
        builder.append("\nTitle: ").append(getPaperTitle());

        for (TAuthor author: getAuthors().getAuthor()) {
            builder.append("\nAuthor: ").append(author.toString());
        }
        builder.append("\nAbstract").append(getAbstract());

        return builder.toString();
    }
}
