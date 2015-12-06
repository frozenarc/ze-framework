//Javascript file for framework

function callFWServletByFormId(formid, async, callbackFunction) {
    var queryStr=generateQueryString(formid);
    var form = document.getElementById(formid);
    callFWServlet(form.method, form.action, async, queryStr, callbackFunction);
}

function callFWServlet(method, action, async, queryStr, callbackFunction) {
    var xmlHttpRequest;
    if (window.XMLHttpRequest) {
        xmlHttpRequest = new XMLHttpRequest();
    } else {
        xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
    }
    if (async == true) {
        xmlHttpRequest.onreadystatechange = function () {
            handleResponse(xmlHttpRequest, callbackFunction);
        };
    }

    if (method.toLowerCase() == "post") {
        xmlHttpRequest.open(method, action, async);
        xmlHttpRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlHttpRequest.send(queryStr);
        if (async == false) {
            handleResponse(xmlHttpRequest, callbackFunction);
        }
    } else {
        queryStr = queryStr.replace(/#/g, "%23");
        xmlHttpRequest.open(method, action + "?" + queryStr, async);
        xmlHttpRequest.send();
        if (async == false) {
            handleResponse(xmlHttpRequest, callbackFunction);
        }
    }
}

function generateQueryString(formid) {
    var form = document.getElementById(formid);
    var inputs = form.getElementsByTagName("input");
    var postString = "";
    for (var i = 0; i < inputs.length; i++) {
        if (inputs[i].type != "submit" && inputs[i].type != "reset"
            && inputs[i].type != "button" && inputs[i].type != "image"
            && inputs[i].type != "file") {
            postString = postString + inputs[i].name + "=" + inputs[i].value + "&";
        }
    }
    var selects = form.getElementsByTagName("select");
    for (var j = 0; j < selects.length; j++) {
        for (var k = 0; k < selects[j].options.length; k++) {
            if (selects[j].options[k].selected) {
                postString = postString + selects[j].name + "=" + selects[j].options[k].value + "&";
            }
        }
    }
    if (postString.length > 0) {
        postString = postString.substring(0, postString.length - 1);
    }
    return postString;
}

function handleResponse(xmlHttpRequest, callbackFunction) {
    if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
        callbackFunction(xmlHttpRequest);
    }
}
