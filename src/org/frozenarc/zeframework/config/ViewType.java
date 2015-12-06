package org.frozenarc.zeframework.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *             	This represents a web page request.
 *             	ModelNames: Associated names of models with the view.
 *             				This is used to carry loaded data from view loader to web page.
 *             	Exceptions: Associated configured exception with the view.
 *             
 * 
 * <p>Java class for ViewType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ViewType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ModelNames" type="{http://www.mvpanchal.org/zefw/config}ModelNamesType" minOccurs="0"/>
 *         &lt;element name="Exceptions" type="{http://www.mvpanchal.org/zefw/config}ExceptionsType" minOccurs="0"/>
 *         &lt;element name="ViewValidators" type="{http://www.mvpanchal.org/zefw/config}ViewValidatorsType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="page" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="loader" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ViewType", propOrder = {
    "modelNames",
    "exceptions",
    "viewValidators"
})
public class ViewType {

    @XmlElement(name = "ModelNames")
    protected ModelNamesType modelNames;
    @XmlElement(name = "Exceptions")
    protected ExceptionsType exceptions;
    @XmlElement(name = "ViewValidators")
    protected ViewValidatorsType viewValidators;
    @XmlAttribute(required = true)
    protected String name;
    @XmlAttribute(required = true)
    protected String page;
    @XmlAttribute
    protected String loader;

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
     * Gets the value of the viewValidators property.
     * 
     * @return
     *     possible object is
     *     {@link ViewValidatorsType }
     *     
     */
    public ViewValidatorsType getViewValidators() {
        return viewValidators;
    }

    /**
     * Sets the value of the viewValidators property.
     * 
     * @param value
     *     allowed object is
     *     {@link ViewValidatorsType }
     *     
     */
    public void setViewValidators(ViewValidatorsType value) {
        this.viewValidators = value;
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
     * Gets the value of the page property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPage() {
        return page;
    }

    /**
     * Sets the value of the page property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPage(String value) {
        this.page = value;
    }

    /**
     * Gets the value of the loader property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoader() {
        return loader;
    }

    /**
     * Sets the value of the loader property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoader(String value) {
        this.loader = value;
    }

}
