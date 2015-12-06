package org.frozenarc.zeframework.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *                 This the complex type of root element. Which represents all the configuration elements.
 *             
 * 
 * <p>Java class for ZeFWConfigType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ZeFWConfigType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ApplicationLifeCycle" type="{http://www.mvpanchal.org/zefw/config}ApplicationLifeCycleType" minOccurs="0"/>
 *         &lt;element name="Exceptions" type="{http://www.mvpanchal.org/zefw/config}ExceptionsType" minOccurs="0"/>
 *         &lt;element name="Models" type="{http://www.mvpanchal.org/zefw/config}ModelsType" minOccurs="0"/>
 *         &lt;element name="Views" type="{http://www.mvpanchal.org/zefw/config}ViewsType" minOccurs="0"/>
 *         &lt;element name="Actions" type="{http://www.mvpanchal.org/zefw/config}ActionsType" minOccurs="0"/>
 *         &lt;element name="ActionViewRedirects" type="{http://www.mvpanchal.org/zefw/config}ActionViewRedirectsType" minOccurs="0"/>
 *         &lt;element name="ApplicationProperties" type="{http://www.mvpanchal.org/zefw/config}ApplicationPropertiesType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZeFWConfigType", propOrder = {
    "applicationLifeCycle",
    "exceptions",
    "models",
    "views",
    "actions",
    "actionViewRedirects",
    "applicationProperties"
})
public class ZeFWConfigType {

    @XmlElement(name = "ApplicationLifeCycle")
    protected ApplicationLifeCycleType applicationLifeCycle;
    @XmlElement(name = "Exceptions")
    protected ExceptionsType exceptions;
    @XmlElement(name = "Models")
    protected ModelsType models;
    @XmlElement(name = "Views")
    protected ViewsType views;
    @XmlElement(name = "Actions")
    protected ActionsType actions;
    @XmlElement(name = "ActionViewRedirects")
    protected ActionViewRedirectsType actionViewRedirects;
    @XmlElement(name = "ApplicationProperties")
    protected ApplicationPropertiesType applicationProperties;

    /**
     * Gets the value of the applicationLifeCycle property.
     * 
     * @return
     *     possible object is
     *     {@link ApplicationLifeCycleType }
     *     
     */
    public ApplicationLifeCycleType getApplicationLifeCycle() {
        return applicationLifeCycle;
    }

    /**
     * Sets the value of the applicationLifeCycle property.
     * 
     * @param value
     *     allowed object is
     *     {@link ApplicationLifeCycleType }
     *     
     */
    public void setApplicationLifeCycle(ApplicationLifeCycleType value) {
        this.applicationLifeCycle = value;
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
     * Gets the value of the models property.
     * 
     * @return
     *     possible object is
     *     {@link ModelsType }
     *     
     */
    public ModelsType getModels() {
        return models;
    }

    /**
     * Sets the value of the models property.
     * 
     * @param value
     *     allowed object is
     *     {@link ModelsType }
     *     
     */
    public void setModels(ModelsType value) {
        this.models = value;
    }

    /**
     * Gets the value of the views property.
     * 
     * @return
     *     possible object is
     *     {@link ViewsType }
     *     
     */
    public ViewsType getViews() {
        return views;
    }

    /**
     * Sets the value of the views property.
     * 
     * @param value
     *     allowed object is
     *     {@link ViewsType }
     *     
     */
    public void setViews(ViewsType value) {
        this.views = value;
    }

    /**
     * Gets the value of the actions property.
     * 
     * @return
     *     possible object is
     *     {@link ActionsType }
     *     
     */
    public ActionsType getActions() {
        return actions;
    }

    /**
     * Sets the value of the actions property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActionsType }
     *     
     */
    public void setActions(ActionsType value) {
        this.actions = value;
    }

    /**
     * Gets the value of the actionViewRedirects property.
     * 
     * @return
     *     possible object is
     *     {@link ActionViewRedirectsType }
     *     
     */
    public ActionViewRedirectsType getActionViewRedirects() {
        return actionViewRedirects;
    }

    /**
     * Sets the value of the actionViewRedirects property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActionViewRedirectsType }
     *     
     */
    public void setActionViewRedirects(ActionViewRedirectsType value) {
        this.actionViewRedirects = value;
    }

    /**
     * Gets the value of the applicationProperties property.
     * 
     * @return
     *     possible object is
     *     {@link ApplicationPropertiesType }
     *     
     */
    public ApplicationPropertiesType getApplicationProperties() {
        return applicationProperties;
    }

    /**
     * Sets the value of the applicationProperties property.
     * 
     * @param value
     *     allowed object is
     *     {@link ApplicationPropertiesType }
     *     
     */
    public void setApplicationProperties(ApplicationPropertiesType value) {
        this.applicationProperties = value;
    }

}
