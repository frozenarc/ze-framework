package org.frozenarc.zeframework.config;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.frozenarc.zeframework.config package.
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ZeFWConfig_QNAME = new QName("http://www.frozenarc.org/zeframework/config", "ZeFWConfig");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.frozenarc.zeframework.config
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ApplicationLifeCycleType }
     * 
     */
    public ApplicationLifeCycleType createApplicationLifeCycleType() {
        return new ApplicationLifeCycleType();
    }

    /**
     * Create an instance of {@link ViewValidatorsType }
     * 
     */
    public ViewValidatorsType createViewValidatorsType() {
        return new ViewValidatorsType();
    }

    /**
     * Create an instance of {@link ModelNamesType }
     * 
     */
    public ModelNamesType createModelNamesType() {
        return new ModelNamesType();
    }

    /**
     * Create an instance of {@link ViewType }
     * 
     */
    public ViewType createViewType() {
        return new ViewType();
    }

    /**
     * Create an instance of {@link ViewValidatorType }
     * 
     */
    public ViewValidatorType createViewValidatorType() {
        return new ViewValidatorType();
    }

    /**
     * Create an instance of {@link LifeCycleHandlerType }
     * 
     */
    public LifeCycleHandlerType createLifeCycleHandlerType() {
        return new LifeCycleHandlerType();
    }

    /**
     * Create an instance of {@link ModelType }
     * 
     */
    public ModelType createModelType() {
        return new ModelType();
    }

    /**
     * Create an instance of {@link ActionViewRedirectType }
     * 
     */
    public ActionViewRedirectType createActionViewRedirectType() {
        return new ActionViewRedirectType();
    }

    /**
     * Create an instance of {@link ViewsType }
     * 
     */
    public ViewsType createViewsType() {
        return new ViewsType();
    }

    /**
     * Create an instance of {@link ExceptionType }
     * 
     */
    public ExceptionType createExceptionType() {
        return new ExceptionType();
    }

    /**
     * Create an instance of {@link ModelNameType }
     * 
     */
    public ModelNameType createModelNameType() {
        return new ModelNameType();
    }

    /**
     * Create an instance of {@link ActionsType }
     * 
     */
    public ActionsType createActionsType() {
        return new ActionsType();
    }

    /**
     * Create an instance of {@link ModelsType }
     * 
     */
    public ModelsType createModelsType() {
        return new ModelsType();
    }

    /**
     * Create an instance of {@link ActionValidatorType }
     * 
     */
    public ActionValidatorType createActionValidatorType() {
        return new ActionValidatorType();
    }

    /**
     * Create an instance of {@link ActionViewRedirectsType }
     * 
     */
    public ActionViewRedirectsType createActionViewRedirectsType() {
        return new ActionViewRedirectsType();
    }

    /**
     * Create an instance of {@link ZeFWConfigType }
     * 
     */
    public ZeFWConfigType createZeFWConfigType() {
        return new ZeFWConfigType();
    }

    /**
     * Create an instance of {@link ActionType }
     * 
     */
    public ActionType createActionType() {
        return new ActionType();
    }

    /**
     * Create an instance of {@link ExceptionsType }
     * 
     */
    public ExceptionsType createExceptionsType() {
        return new ExceptionsType();
    }

    /**
     * Create an instance of {@link ApplicationPropertiesType }
     * 
     */
    public ApplicationPropertiesType createApplicationPropertiesType() {
        return new ApplicationPropertiesType();
    }

    /**
     * Create an instance of {@link ActionValidatorsType }
     * 
     */
    public ActionValidatorsType createActionValidatorsType() {
        return new ActionValidatorsType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ZeFWConfigType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.frozenarc.org/zeframework/config", name = "ZeFWConfig")
    public JAXBElement<ZeFWConfigType> createZeFWConfig(ZeFWConfigType value) {
        return new JAXBElement<ZeFWConfigType>(_ZeFWConfig_QNAME, ZeFWConfigType.class, null, value);
    }

}
