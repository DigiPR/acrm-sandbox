# Spring MVC and jQuery Example

This example illustrates how Spring MVC can be used in combination with jQuery to implement a web application.

#### Contents:
- [Spring MVC Views and Controller](#spring-mvc-views-and-controller)
- [JavaScript and jQuery Client](#javascript-and-jquery-client)

## Spring MVC Views and Controller

The generated static views can be placed under `src\main\resources\static` to make them available. Besides, a plain Spring MVC controller can be implemented as follows to serve the views using a specific path:

```Java
@Controller
@RequestMapping(path = "/customer")
public class CustomerController {

    @GetMapping
    public String getCustomerView(){
        return "customer.html";
    }

    @GetMapping("/create")
    public String getCustomerCreateView(){
        return "customerCreate.html";
    }

    @GetMapping("/edit")
    public String getCustomerEditView(){
        return "customerEdit.html";
    }
}
```

## JavaScript and jQuery Client

Finally, a JavaScirpt/jQuery based API consumer is implemented. The implementation is realized by AJAX calls provided as functions in an `app.js` file:

```HTML
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/app.js"></script>
```

#### API Login and Local Token Storage

On every HTML view, a login validation will be performed:
```HTML
<script language="JavaScript">
    validateLogin(function (result) {
        if (result) {
            window.location.replace("/customer");
        } else {
            window.location.replace("/login");
        }
    });
</script>
```

If the current authentication is not available or invalid, the user is asked to provide email and password, which then performs a login to receive a valid JWT token in a cookie:

```JavaScript
function login(email, password, remember, callback) {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        headers: {
            "X-XSRF-TOKEN": getCookie("XSRF-TOKEN")
        },
        url: serviceEndpointURL + "/login",
        data: JSON.stringify({
            "email": email,
            "password": password,
            "remember": remember
        }),
        success: function (data, textStatus, response) {
            callback(true);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            callback(false);
        }
    });
}

function validateLogin(callback) {
    $.ajax({
        type: "HEAD",
        url: serviceEndpointURL + "/validate",
        success: function (data, textStatus, response) {
            callback(true);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            callback(false);
        }
    });
}
```

#### AJAX Calls for API Consumption

It is not required to use third-party libraries for REST clients. Nevertheless, libraries, such as jQuery, can enhance the readability of REST calls significantly. REST APIs can be consumed with jQuery based AJAX calls using the following pattern:

```JavaScript
function postCustomer(customer, callbackSuccess, callbackError) {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        headers: {
            "X-XSRF-TOKEN": getCookie("XSRF-TOKEN")
        },
        url: serviceEndpointURL + "/api/customer",
        data: customer,
        success: function (data, textStatus, response) {
            callbackSuccess(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            callbackError(jqXHR.responseJSON.message);
        }
    });
}

function getCustomer(customerID, callback) {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: serviceEndpointURL + "/api/customer/" + customerID,
        success: function (data, textStatus, response) {
            callback(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
        }
    });
}

function getCustomers(callback) {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: serviceEndpointURL + "/api/customer",
        success: function (data, textStatus, response) {
            callback(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
        }
    });
}
```

This reference project uses plain jQuery to append HTML elements to the DOM:
```JavaScript
function loadData() {
    getCustomers(function (result) {
        $("#tableData").empty();
        $.each(result, function (i, item) {
            $("#tableData").append($("<tr>").append($("<td>").text(item.id)).append($("<td>").text(item.name)).append($("<td>").text(item.email)).append($("<td>").text(item.mobile)).append($(
                "<td>\n" +
                "<div class=\"btn-group btn-group-sm\" role=\"group\"><a class=\"btn btn-light\" role=\"button\" href=\"/customer/edit?id=" + item.id + "\"> <i class=\"fa fa-edit\"></i></a><button class=\"btn btn-light\" type=\"button\" data-target=\"#confirm-modal\" data-toggle=\"modal\" data-id=\"" + item.id + "\"> <i class=\"fa fa-trash\"></i></button></div>\n" +
                "</td></tr>"
            )));
        });
    });
}
```

> Don't forget that `.append()` can be dangerous when appending user input or database data. This untrusted data could contain harmful XSS-related JavaScript. Therefore untrusted data must be added using `.text()` to escape all special characters.

Nevertheless, it might be advisable for readability to use a third-party library or a jQuery extension for appending HTML to the DOM.