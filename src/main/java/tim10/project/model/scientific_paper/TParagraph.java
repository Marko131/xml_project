
package tim10.project.model.scientific_paper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for t_paragraph complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="t_paragraph">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ref" type="{}t_ref" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="list" type="{}t_list" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="image" type="{}t_image" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="link" type="{}t_link" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="quote" type="{}t_quote" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="text" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "t_paragraph", propOrder = {
    "content"
})
public class TParagraph {

    @XmlElementRefs({
        @XmlElementRef(name = "link", type = JAXBElement.class),
        @XmlElementRef(name = "image", type = JAXBElement.class),
        @XmlElementRef(name = "quote", type = JAXBElement.class),
        @XmlElementRef(name = "text", type = JAXBElement.class),
        @XmlElementRef(name = "ref", type = JAXBElement.class),
        @XmlElementRef(name = "list", type = JAXBElement.class)
    })
    @XmlMixed
    protected List<Serializable> content;

    /**
     * Gets the value of the content property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the content property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link TLink }{@code >}
     * {@link String }
     * {@link JAXBElement }{@code <}{@link TImage }{@code >}
     * {@link JAXBElement }{@code <}{@link TQuote }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link TRef }{@code >}
     * {@link JAXBElement }{@code <}{@link TList }{@code >}
     * 
     * 
     */
    public List<Serializable> getContent() {
        if (content == null) {
            content = new ArrayList<Serializable>();
        }
        return this.content;
    }

}
