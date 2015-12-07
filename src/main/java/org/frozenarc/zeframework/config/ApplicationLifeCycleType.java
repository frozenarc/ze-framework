package org.frozenarc.zeframework.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *             	This represetns application life cycle handler.
 *             	Initializer: called when application initialized.
 *             	ProcessingTube: Each request goes through the tube.
 *             	Destroyer: called when application destroyed.
 *             
 * 
 * <p>Java class for ApplicationLifeCycleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ApplicationLifeCycleType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Initializer" type="{http://www.mvpanchal.org/zefw/config}LifeCycleHandlerType" minOccurs="0"/>
 *         &lt;element name="ProcessingTube" type="{http://www.mvpanchal.org/zefw/config}LifeCycleHandlerType" minOccurs="0"/>
 *         &lt;element name="Destroyer" type="{http://www.mvpanchal.org/zefw/config}LifeCycleHandlerType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ApplicationLifeCycleType", propOrder = {
    "initializer",
    "processingTube",
    "destroyer"
})
public class ApplicationLifeCycleType {

    @XmlElement(name = "Initializer")
    protected LifeCycleHandlerType initializer;
    @XmlElement(name = "ProcessingTube")
    protected LifeCycleHandlerType processingTube;
    @XmlElement(name = "Destroyer")
    protected LifeCycleHandlerType destroyer;

    /**
     * Gets the value of the initializer property.
     * 
     * @return
     *     possible object is
     *     {@link LifeCycleHandlerType }
     *     
     */
    public LifeCycleHandlerType getInitializer() {
        return initializer;
    }

    /**
     * Sets the value of the initializer property.
     * 
     * @param value
     *     allowed object is
     *     {@link LifeCycleHandlerType }
     *     
     */
    public void setInitializer(LifeCycleHandlerType value) {
        this.initializer = value;
    }

    /**
     * Gets the value of the processingTube property.
     * 
     * @return
     *     possible object is
     *     {@link LifeCycleHandlerType }
     *     
     */
    public LifeCycleHandlerType getProcessingTube() {
        return processingTube;
    }

    /**
     * Sets the value of the processingTube property.
     * 
     * @param value
     *     allowed object is
     *     {@link LifeCycleHandlerType }
     *     
     */
    public void setProcessingTube(LifeCycleHandlerType value) {
        this.processingTube = value;
    }

    /**
     * Gets the value of the destroyer property.
     * 
     * @return
     *     possible object is
     *     {@link LifeCycleHandlerType }
     *     
     */
    public LifeCycleHandlerType getDestroyer() {
        return destroyer;
    }

    /**
     * Sets the value of the destroyer property.
     * 
     * @param value
     *     allowed object is
     *     {@link LifeCycleHandlerType }
     *     
     */
    public void setDestroyer(LifeCycleHandlerType value) {
        this.destroyer = value;
    }

}
