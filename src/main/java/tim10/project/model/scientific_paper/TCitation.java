
package tim10.project.model.scientific_paper;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for t_citation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="t_citation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="authors" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="year" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="magazine" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="link" type="{}t_link" maxOccurs="3" minOccurs="3"/>
 *       &lt;/sequence>
 *       &lt;attribute name="number" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "t_citation", propOrder = {
    "authors",
    "year",
    "title",
    "magazine",
    "link"
})
public class TCitation {

    @XmlElement(required = true)
    protected String authors;
    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger year;
    @XmlElement(required = true)
    protected String title;
    @XmlElement(required = true)
    protected String magazine;
    @XmlElement(required = true)
    protected List<TLink> link;
    @XmlAttribute(name = "number")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger number;

    /**
     * Gets the value of the authors property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthors() {
        return authors;
    }

    /**
     * Sets the value of the authors property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthors(String value) {
        this.authors = value;
    }

    /**
     * Gets the value of the year property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getYear() {
        return year;
    }

    /**
     * Sets the value of the year property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setYear(BigInteger value) {
        this.year = value;
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

    /**
     * Gets the value of the magazine property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMagazine() {
        return magazine;
    }

    /**
     * Sets the value of the magazine property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMagazine(String value) {
        this.magazine = value;
    }

    /**
     * Gets the value of the link property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the link property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLink().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TLink }
     * 
     * 
     */
    public List<TLink> getLink() {
        if (link == null) {
            link = new ArrayList<TLink>();
        }
        return this.link;
    }

    /**
     * Gets the value of the number property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumber() {
        return number;
    }

    /**
     * Sets the value of the number property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumber(BigInteger value) {
        this.number = value;
    }

}
