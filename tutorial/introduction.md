### Foundation thought

The framework is developed by considering two basic request from browser.

[1] Requesting to view a web page. (e.g. We need to see some web page with informative data.).

[2] Submitting form data to server to be processed. (e.g. We need to submit some data to server and server process that data.).

When a user requesting to view a web page, we called it here as "View" event. And when user submitting some data, we called it as "Action" event.

We know that "View" event can be occurred independently, but "Action" event always followed by "View" event.

Here, I have separated the "View" event and "Action" event.


### "View" event:

What do we need to implement "View" event? We need some data to be displayed on web page. So, first of all we should have a class (loader class) which can fetch that data from somewhere (like database or webservice), and after that that fetched data need be be set into some classes (model classes). When model is loaded with data, we need a web page (JSP) to display the data to user.

So, we need three component to fulfill the event: 
(1) A loader class 
(2) Some model classes 
(3) A JSP page.

So, now we need a configuration xml file in which we can map all this to a "View" event. The config file is based on an xsd. The framework parses the config file using JAXB, so we need that xsd along with the config xml.

### "Action" event:

What do we need to implement "Action" event?. When user submits a form, data goes to a server. We get raw data at server side when user submit a form, but we need to convert that data into appropriate type and need to set into some classes (model classes). After setting the data into models, we need a class which can process that model's data (handler class). Now, it is obvious that after submit event finished, a different or same web page going to be displayed. So, the handler class has one more responsibility. It must return next view event name to be occurred. When a view event name returned the framework kick starts the "View" event. Converting the raw data and setting it into models is the responsibility of framework. So, we need two components to fulfill the event: (1) Some model classes (2) A handler class.

We can configure with the framework that a "View" event preceded by an "Action" event are to be dispatched or to be sent redirect.

What if an exception occurs during view loading or action handling phase? To handle this, the framework has a mechanism. We can configure an exception handler class and a JSP page with an exception. When the configured exception or its base exception would be thrown the handler class would be called by the framework and after finishing execution of the handler class the JSP page would be dispatched. The exception information can be displayed on the JSP by custom tags of the framework.

We can configure validator chain for an "Action" event. The validator validate form data populated in models and if there are some validation errors it populates the errors in error list and returns true or false to the framework. If it returns true the framework calls next validator in the validator chain other otherwise not. The validation errors will be displayed by the custom tags on web page later.

We can configure validator chain for a "View" event. The validator validates that the view is eligible to be displayed or not. If not than the validator returns other view name instead of current and the other "View" event occurs. But, if the validator returns null then next validator would be called in the validator chain.

We can configure application initializer and application destroyer classes. The classes would be called when application context initialized and destroyed respectively. There are an other configuration too. We can configure a processing tube class which would be called on each "View" or "Action" event.

The framework would generate conversation errors if form data are not the type of mapped model properties. It will be displayed by the custom tags on web page later.

User can generate application messages in any place of application. It will be displayed by the custom tags on web page later.

Other features of the framework:

* [1] Model classes for an event ("View" or "Action") can be more than one.
* [2] Model class can have below type of properties:
    - (1) int, long, short, boolean, char, double, float
    - (2) java.lang.Integer, java.lang.Long, java.lang.Short, java.lang.Boolean, java.lang.Character, java.lang.Double, java.lang.Float
    - (3) java.lang.String, java.util.Date
    - (4) Multi Dimensional array of above (1)st to (3)rd type
    - (5) Any user defined object
    - (6) Multi Dimensional array of (5)th type
* [3] User defined object property of a model can have property of any of above (1)st to (6)th type
* [4] The framework provides expressions by which JSP page can fetch data from any depth of model's properties.
* [5] A JSP can read data of type (1)st, (2)nd, (3)rd and (4)th from model using the expressions. The last property in hierarchy should not be (5)th and (6)th type.
* [6] When user inputs some data into form, the data is in string format. But, the framework can convert that data into above types except (5)th and (6)th . To convert data we just need to map that property with the input field of the form.
* [7] An enough set of custom tags which are used:
    - to fetch model data to be displayed, to set model data to be processed later
    - to create html forms, input fields, links, buttons etc
    - to display application messages, conversation errors, validation errors
    - to loop for some integer variable
    - to define variable and assign some value to it
    - to display exception info