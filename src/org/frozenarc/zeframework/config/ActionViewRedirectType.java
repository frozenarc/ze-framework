package org.frozenarc.zeframework.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *             	This represents method to navigate to next web page. 
 *             	A view represented by the viewname should be navigated 
 *             	after the action handling represented by the actionname.
 *             
 * 
 * <p>Java class for ActionViewRedirectType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ActionViewRedirectType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="actionname" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="viewname" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="sendredirect" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ActionViewRedirectType")
public class ActionViewRedirectType {

    @XmlAttribute(required = true)
    protected String actionname;
    @XmlAttribute(required = true)
    protected String viewname;
    @XmlAttribute(required = true)
    protected boolean sendredirect;

    /**
     * Gets the value of the actionname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActionname() {
        return actionname;
    }

    /**
     * Sets the value of the actionname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActionname(String value) {
        this.actionname = value;
    }

    /**
     * Gets the value of the viewname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getViewname() {
        return viewname;
    }

    /**
     * Sets the value of the viewname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setViewname(String value) {
        this.viewname = value;
    }

    /**
     * Gets the value of the sendredirect property.
     * 
     */
    public boolean isSendredirect() {
        return sendredirect;
    }

    /**
     * Sets the value of the sendredirect property.
     * 
     */
    public void setSendredirect(boolean value) {
        this.sendredirect = value;
    }

}
