package org.frozenarc.zeframework.config;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *             	This represents list of Action Validator. 
 *             
 * 
 * <p>Java class for ActionValidatorsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ActionValidatorsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ActionValidator" type="{http://www.mvpanchal.org/zefw/config}ActionValidatorType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ActionValidatorsType", propOrder = {
    "actionValidator"
})
public class ActionValidatorsType {

    @XmlElement(name = "ActionValidator")
    protected List<ActionValidatorType> actionValidator;

    /**
     * Gets the value of the actionValidator property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the actionValidator property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getActionValidator().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ActionValidatorType }
     * 
     * 
     */
    public List<ActionValidatorType> getActionValidator() {
        if (actionValidator == null) {
            actionValidator = new ArrayList<ActionValidatorType>();
        }
        return this.actionValidator;
    }

}
