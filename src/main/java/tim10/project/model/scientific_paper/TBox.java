
package tim10.project.model.scientific_paper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for t_box complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="t_box">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="image" type="{}t_image"/>
 *         &lt;element name="textbox" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *       &lt;attribute name="title" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="description" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "t_box", propOrder = {
    "image",
    "textbox"
})
public class TBox {

    @XmlElement(required = true)
    protected TImage image;
    @XmlElement(required = true)
    protected String textbox;
    @XmlAttribute(name = "title")
    protected String title;
    @XmlAttribute(name = "description")
    protected String description;

    /**
     * Gets the value of the image property.
     * 
     * @return
     *     possible object is
     *     {@link TImage }
     *     
     */
    public TImage getImage() {
        return image;
    }

    /**
     * Sets the value of the image property.
     * 
     * @param value
     *     allowed object is
     *     {@link TImage }
     *     
     */
    public void setImage(TImage value) {
        this.image = value;
    }

    /**
     * Gets the value of the textbox property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextbox() {
        return textbox;
    }

    /**
     * Sets the value of the textbox property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextbox(String value) {
        this.textbox = value;
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
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

}
