Before looking at custom tags, we need to know about how expression can be written. Because, expression is needed to use custom tags.

Basically, custom tags is used to get/set values from/to model. There are other useful custom tags also, but this is what basically custom tags are made for.
For this purpose, most of custom tags in the framework have "valuefrom" and "valueto" attributes. As name suggests, "valuefrom" is used to get value from a model and "valueto" used to set value to a model.

Now suppose, we can have some custom tag like

    <zfw:out valuefrom="m{modelName.propertyName1.propertyName2}"/>

So, here "m" stands for "model", expression start with "{" and ends with "}".
Here, "modelName" is name of the model which we have defined in configuration file in
"Models -> Model -> name".
"propertyName1" is the property name of specified model. The model must have getter and setter method for this property. In our case "proeprtyName1" is a user defined class' object and it has a property named "propertyName2". The user defined object also must have getter and setter method for this property.

We can use the above expression as it is if "propertyName2" is any of the data types like "int", "short", "long", "float", "double", "char", "boolean", "java.lang.Integer", "java.lang.Short", "java.lang.Long", "java.lang.Float", "java.lang.Double", "java.lang.Char", "java.lang.Boolean", "java.lang.String", "java.util.Date".

When we need to get/set "java.util.Date" type property the framework converts date object into string object to get, and converts string object into date object to set, and for that it uses local timezone and default format. But, if we need specific timezone and/or specific format than we can specify it in above expression. Now, above expression looks like below to display date in specific timezone and format.

    <zfw:out valuefrom="m{modelName.propertyName1.propertyName2}t{IST}f{dd/MM/yyyy HH:mm:ss}"/>

Here, "t" stands for "timezone" and "f" stands for "format". We can pass any timezone string which "java.util.TimeZone.getTimeZone" mothod can support. And we can pass any date format string which "java.text.SimpleDateFormat" class can support.

If the we need to get/set property of type "int", "short", "long", "float", "double", "java.lang.Integer", "java.lang.Short", "java.lang.Long", "java.lang.Float" and "java.lang.Double" in some specific format than we can specify it in expression like below.

    <zfw:out valuefrom="m{modelName.propertyName1.propertyName2}f{Y:(5-2).(1-4)}"/>

Here, we can specify either "Y" or "N" as first letter in the format expression. It indicates that either the number has "," (comma) as separator or not. Within first "(" and ")" we can specify max integer digit (here 5) and min integer digit (here 2) the number can have. We need to put "." after it. Then we can specify within last "(" and ")" min fractional digit (here 1) and max fractional (here 4) digit the number can have.

The other custom tags which can set data to model with attribute named "valueto" has same expression syntax as defined above.

To get value of any variable defined in jsp page by "org.mvpanchal.zefw.tag.DefineTag" or any other jsp custom tag, we need to use syntax like below.

    <zfw:out value="$variable1"/>

Please, notice that we have used "value" here instead of "valuefrom". If we need to get variable's value we always need to user "value" not "valuefrom". "valuefrom" should be used only to work with models.
We can see that we haven't used here curly braces. Because, it is conflicting with basic jsp syntax to get variable's value. Although, we can also use basic syntax also to get variable's value.

Suppose, that propertyName1 is an array of a user defined object, then what would be the syntax to get propertyName2. It looks like below.

    <zfw:out valuefrom="m{modelName.propertyName1[0].propertyName2}"/>

Here we can use a variable instead of contanst "0". It looks like

    <zfw:out valuefrom="m{modelName.propertyName1[$variable1].propertyName2}"/>

If propertyName2 is an array then we can write syntax as below.

    <zfw:out valuefrom="m{modelName.propertyName1[$variable1].propertyName2[$variable2]}"/>