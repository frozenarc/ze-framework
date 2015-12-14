package org.frozenarc.zeframework.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *             	This represents a submit request.
 *             	ModelNames: Associated names of models with the action. 
 *             				This is used to carry submitted data from web page to action handler.
 *             	Exceptions: Associated configured exception with the view.
 *             	Validators: Associated validators to validate submitted data.
 *             
 * 
 * <p>Java class for ActionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ActionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ModelNames" type="{http://www.frozenarc.org/zeframework/config}ModelNamesType" minOccurs="0"/>
 *         &lt;element name="Exceptions" type="{http://www.frozenarc.org/zeframework/config}ExceptionsType" minOccurs="0"/>
 *         &lt;element name="ActionValidators" type="{http://www.frozenarc.org/zeframework/config}ActionValidatorsType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="handler" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ActionType", propOrder = {
    "modelNames",
    "exceptions",
    "actionValidators"
})
public class ActionType {

    @XmlElement(name = "ModelNames")
    protected ModelNamesType modelNames;
    @XmlElement(name = "Exceptions")
    protected ExceptionsType exceptions;
    @XmlElement(name = "ActionValidators")
    protected ActionValidatorsType actionValidators;
    @XmlAttribute(required = true)
    protected String name;
    @XmlAttribute(required = true)
    protected String handler;

    /**
     * Gets the value of the modelNames property.
     * 
     * @return
     *     possible object is
     *     {@link ModelNamesType }
     *     
     */
    public ModelNamesType getModelNames() {
        return modelNames;
    }

    /**
     * Sets the value of the modelNames property.
     * 
     * @param value
     *     allowed object is
     *     {@link ModelNamesType }
     *     
     */
    public void setModelNames(ModelNamesType value) {
        this.modelNames = value;
    }

    /**
     * Gets the value of the exceptions property.
     * 
     * @return
     *     possible object is
     *     {@link ExceptionsType }
     *     
     */
    public ExceptionsType getExceptions() {
        return exceptions;
    }

    /**
     * Sets the value of the exceptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExceptionsType }
     *     
     */
    public void setExceptions(ExceptionsType value) {
        this.exceptions = value;
    }

    /**
     * Gets the value of the actionValidators property.
     * 
     * @return
     *     possible object is
     *     {@link ActionValidatorsType }
     *     
     */
    public ActionValidatorsType getActionValidators() {
        return actionValidators;
    }

    /**
     * Sets the value of the actionValidators property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActionValidatorsType }
     *     
     */
    public void setActionValidators(ActionValidatorsType value) {
        this.actionValidators = value;
    }

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
     * Gets the value of the handler property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHandler() {
        return handler;
    }

    /**
     * Sets the value of the handler property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHandler(String value) {
        this.handler = value;
    }

}
