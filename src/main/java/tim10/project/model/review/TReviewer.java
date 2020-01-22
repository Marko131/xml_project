
package tim10.project.model.review;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for t_reviewer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="t_reviewer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="working_title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="faculty" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="university" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "t_reviewer", propOrder = {
    "name",
    "workingTitle",
    "faculty",
    "university"
})
public class TReviewer {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(name = "working_title", required = true)
    protected String workingTitle;
    @XmlElement(required = true)
    protected String faculty;
    @XmlElement(required = true)
    protected String university;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the workingTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkingTitle() {
        return workingTitle;
    }

    /**
     * Sets the value of the workingTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkingTitle(String value) {
        this.workingTitle = value;
    }

    /**
     * Gets the value of the faculty property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFaculty() {
        return faculty;
    }

    /**
     * Sets the value of the faculty property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFaculty(String value) {
        this.faculty = value;
    }

    /**
     * Gets the value of the university property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUniversity() {
        return university;
    }

    /**
     * Sets the value of the university property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUniversity(String value) {
        this.university = value;
    }

}
