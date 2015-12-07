The framework must have a xml file to get configuration information. The xml file is based on an xsd which is supplied with the framework. We will get information about each element of the configuration file in this post.

Below is the structure of the xml file.

    ZeFWConfig (1..1)
    |
    |---> ApplicationLifeCycle (0..1)
    |     |
    |     |---> Initializer (0..1) [type]
    |     |
    |     |---> ProcessingTube (0..1) [type]
    |     |
    |     |---> Destroyer (0..1) [type]
    |
    |---> Exceptions (0..1)
    |     |
    |     |---> Exception (0..N) [type, handler(opt), page]
    |
    |---> Models (0..1)
    |     |
    |     |---> Model (0..N) [name, type, scope(opt)]
    |
    |---> Views (0..1)
    |     |
    |     |---> View (0..N) [name, page, loader(opt)]
    |           |
    |           |---> ModelNames (0..1)
    |           |     |
    |           |     |---> ModelName (0..N) [name]
    |           |
    |           |---> Exceptions (0..1)
    |           |     |
    |           |     |---> Exception (0..N) [type, handler(opt), page]
    |           |
    |           |---> ViewValidators (0..1)
    |                 |
    |                 |---> ViewValidator (0..N) [type]
    |
    |---> Actions (0..1)
    |     |
    |     |---> Action (0..N) [name, handler]
    |           |
    |           |---> ModelNames (0..1)
    |           |     |
    |           |     |---> ModelName (0..N) [name]
    |           |
    |           |---> Exceptions (0..1)
    |           |     |
    |           |     |---> Exception (0..N) [type, handler(opt), page]
    |           |
    |           |---> ActionValidators (0..1)
    |                 |
    |                 |---> ActionValidator (0..N) [type]
    |
    |---> ActionViewRedirects (0..1)
    |     |
    |     |---> ActionViewRedirect (0..N) [actionname, viewname, sendredirect]
    |
    |---> ApplicationProperties (0..1) [filepath]


1. "ZeFWConfig" is the root element of the file.

2. "ApplicationLifeCycle" helps to configure handling life cycle events of an application.

3. "Initializer" has an attribute called "type". We can specify fully qualified class name to the "type". The class must implement "org.mvpanchal.zefw.lifecycle.ApplicationInitializer" interface. The class would be called when servlet context is initialized.

4. "ProcessingTube" has an attribute called "type". We can specify fully qualified class name to the "type". The class must extend "org.mvpanchal.zefw.processingtube.AbstractProcessingTube" class. The class would be called on each request of the application. If the request is of type "view" then "beforeProcessView" and "afterProcessView" methods would be called, and if the request is of type "action" then "beforeProcessAction" and "afterProcessAction" method would be called.

5. "Destroyer" has an attribute called "type". We can specify fully qualified class name to the "type". The class must implement "org.mvpanchal.zefw.lifecycle.ApplicationDestroyer" interface. The class would be called when servlet context is destroyed.

6. "Exceptions" helps to configure global exception handing on exception occurrence.

7. "Exception" has attributes called "type", "handler", "page". "type" is fully qualified class name of exception which needs to be handled. "handler" is fully qualified class name of a class which implements "org.mvpanchal.zefw.exceptionhandler.ExceptionHandler" interface. The handler would be called when the specified exception occurs. The "handler" is not a required attribute. "page" is the path of jsp which would be dispatched after handling the exception.

8. "Models" defines models classes which plays roll as carrier classes for data.

9. "Model" has attribute called "name", "type" and "scope". "name" is the model name. "type" is the fully qualified class name of the class which implements "org.mvpanchal.zefw.model.Model". "scope" is the scope value in which the model instance is available. "scope" can have the three values "request", "session", "application". The "scope" is not required attribute. If the scope is not specified, the default value is "session".

10. "Views" contains configuration for all views.

11. "View" defines all configuration needed for the view event. The element has attributes called "name", "loader" and "page". "name" is the name of the view event. "loader" is the fully qualified class name of the class which implements "org.mvpanchal.zefw.viewloader.ViewLoader". The class would called when the view event occurs. The class is to load data from some data source (like database, webservices etc.) and populates that models (defined in (12)) with the data. The "loader" is not a required attribute. "page" is the path of jsp page which need to be dispatched after processing of the event.

12. "ModelNames" specifies all model names which will be used to carry data from server to browser for this event.

13. "ModelName" has an attribute called "name". "name" contains value of model name which already defined in (8).

14. "Exceptions" same as (6), but this is special configuration. This can override global configuration for this particular event. If an exception is defined in global configuration, and the same exception is defined here then this configuration becomes available to this event. But, global configuration is still available to this event for exceptions which is not specified here.

15. "Exception" same as (7).

16. "ViewValidators" is the validators chain for this event. This specifies validators which validates the event before data loading occurs.

17. "ViewValidator" has an attribute called "type". The "type" is fully qualified class name of the class which implements "org.mvpanchal.zefw.viewvalidator.ViewValidator". The class would be called when the view event occurs. The "validate" method of the class returns another view name if the current view event should not be processed further. If the method returns null then next validator form the chain would be called.

18. "Actions" contains configuration for all actions.

19. "Action" defines all configuration needed for the action event. The element has attributes called "name" and "handler". "name" is the name of the action event. "handler" is the fully qualified class name of the class which implements "org.mvpanchal.zefw.actionhandler.ActionHandler". The class would called when the action event occurs. The class is to process incoming data from models (defined in (20)) and to send the data to some data target (like database, webservices etc.). The "handleAction" method of the class returns view name of the next view event should be occurred.

20. "ModelNames" specifies all model names which are will be used to carry data from browser to server for this event.

21. "ModelName" has an attribute called "name". "name" contains value of model name which already defined in (8).

22. "Exceptions" same as (6), but this is special configuration. This can override global configuration for this particular event. If an exception is defined in global configuration, and the same exception is defined here then this configuration becomes available to this event. But, global configuration is still available to this event for exceptions which is not specified here.

23. "Exception" same as (7).

24. "ActionValidators" is the validators chain for this event. This specifies validators which validates the event before data handling occurs.

25. "ActionValidator" has an attribute called "type". The "type" is fully qualified class name of the class which implements "org.mvpanchal.zefw.actionvalidator.ActionValidator". The class would be called when the action event occurs. The "validate" method of the class returns boolean value. If the value is "true" next validator from chain would be called otherwise normal event processing goes further.

26. "ActionViewRedirects" defines that the jsp page should be redirected or dispatched for the "action" and the "view" event combination.

27. "ActionViewRedirect" has three attributes called "actionname", "viewname" and "sendredirect". "actionname" is a action event name. "viewname" is a view event name. "sendredirect" can have "true" or "false" values. If "sendredirect" is "true" the specified view event would be redirected after the specified action event. If "sendredirect" is "false" the specified view event would be dispatched after the specified action event.

28. "ApplicationProperties" defines property file path if application needed any one. The property file must be java standard property file. We can access properties from the property file using "org.mvpanchal.zefw.applicationproperties.ApplicationProperties" class. 