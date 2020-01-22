
package tim10.project.model.scientific_paper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for t_quote complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="t_quote">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="quote_text" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="source">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="source_author" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="source_title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="source_page" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
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
@XmlType(name = "t_quote", propOrder = {
    "quoteText",
    "source"
})
public class TQuote {

    @XmlElement(name = "quote_text", required = true)
    protected String quoteText;
    @XmlElement(required = true)
    protected TQuote.Source source;

    /**
     * Gets the value of the quoteText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuoteText() {
        return quoteText;
    }

    /**
     * Sets the value of the quoteText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuoteText(String value) {
        this.quoteText = value;
    }

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link TQuote.Source }
     *     
     */
    public TQuote.Source getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link TQuote.Source }
     *     
     */
    public void setSource(TQuote.Source value) {
        this.source = value;
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
     *         &lt;element name="source_author" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="source_title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="source_page" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "sourceAuthor",
        "sourceTitle",
        "sourcePage"
    })
    public static class Source {

        @XmlElement(name = "source_author")
        protected String sourceAuthor;
        @XmlElement(name = "source_title")
        protected String sourceTitle;
        @XmlElement(name = "source_page")
        protected String sourcePage;

        /**
         * Gets the value of the sourceAuthor property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSourceAuthor() {
            return sourceAuthor;
        }

        /**
         * Sets the value of the sourceAuthor property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSourceAuthor(String value) {
            this.sourceAuthor = value;
        }

        /**
         * Gets the value of the sourceTitle property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSourceTitle() {
            return sourceTitle;
        }

        /**
         * Sets the value of the sourceTitle property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSourceTitle(String value) {
            this.sourceTitle = value;
        }

        /**
         * Gets the value of the sourcePage property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSourcePage() {
            return sourcePage;
        }

        /**
         * Sets the value of the sourcePage property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSourcePage(String value) {
            this.sourcePage = value;
        }

    }

}
