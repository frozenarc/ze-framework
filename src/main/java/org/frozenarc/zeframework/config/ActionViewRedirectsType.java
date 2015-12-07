package org.frozenarc.zeframework.config;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *             	List of ActionViewRedirect.
 *             
 * 
 * <p>Java class for ActionViewRedirectsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ActionViewRedirectsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ActionViewRedirect" type="{http://www.mvpanchal.org/zefw/config}ActionViewRedirectType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ActionViewRedirectsType", propOrder = {
    "actionViewRedirect"
})
public class ActionViewRedirectsType {

    @XmlElement(name = "ActionViewRedirect")
    protected List<ActionViewRedirectType> actionViewRedirect;

    /**
     * Gets the value of the actionViewRedirect property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the actionViewRedirect property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getActionViewRedirect().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ActionViewRedirectType }
     * 
     * 
     */
    public List<ActionViewRedirectType> getActionViewRedirect() {
        if (actionViewRedirect == null) {
            actionViewRedirect = new ArrayList<ActionViewRedirectType>();
        }
        return this.actionViewRedirect;
    }

}
