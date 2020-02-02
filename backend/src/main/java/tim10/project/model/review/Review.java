
package tim10.project.model.review;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="reviewer" type="{}t_reviewer"/>
 *         &lt;element name="paragraphs">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="paragraph" type="{}t_paragraph" maxOccurs="unbounded"/>
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
@XmlType(name = "", propOrder = {
    "paperTitle",
    "reviewer",
    "paragraphs"
})
@XmlRootElement(name = "review")
public class Review {

    @XmlElement(name = "paper_title", required = true)
    protected String paperTitle;
    @XmlElement(required = true)
    protected TReviewer reviewer;
    @XmlElement(required = true)
    protected Review.Paragraphs paragraphs;

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
     * Gets the value of the reviewer property.
     * 
     * @return
     *     possible object is
     *     {@link TReviewer }
     *     
     */
    public TReviewer getReviewer() {
        return reviewer;
    }

    /**
     * Sets the value of the reviewer property.
     * 
     * @param value
     *     allowed object is
     *     {@link TReviewer }
     *     
     */
    public void setReviewer(TReviewer value) {
        this.reviewer = value;
    }

    /**
     * Gets the value of the paragraphs property.
     * 
     * @return
     *     possible object is
     *     {@link Review.Paragraphs }
     *     
     */
    public Review.Paragraphs getParagraphs() {
        return paragraphs;
    }

    /**
     * Sets the value of the paragraphs property.
     * 
     * @param value
     *     allowed object is
     *     {@link Review.Paragraphs }
     *     
     */
    public void setParagraphs(Review.Paragraphs value) {
        this.paragraphs = value;
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
     *         &lt;element name="paragraph" type="{}t_paragraph" maxOccurs="unbounded"/>
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
        "paragraph"
    })
    public static class Paragraphs {

        @XmlElement(required = true)
        protected List<TParagraph> paragraph;

        /**
         * Gets the value of the paragraph property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the paragraph property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getParagraph().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TParagraph }
         * 
         * 
         */
        public List<TParagraph> getParagraph() {
            if (paragraph == null) {
                paragraph = new ArrayList<TParagraph>();
            }
            return this.paragraph;
        }

    }

}
