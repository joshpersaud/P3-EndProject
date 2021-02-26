<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Login</title>
    <h1>Sign in</h1>
</head>
<body style="align-content: center; text-align: center">
<form class="form-group" accept-charset="utf-8" method="post" action="/login" style="align-content: center; text-align: center">
    <div>
        User: <input id="user" type="text" name="username" required><br>
        Password: <input id="password" type="password" name="password" required><br>
        <button type="submit" class="btn btn-primary">Login</button>
        <br>
    </div>
    &nbsp;
    <div>
        <button type="button" class="btn btn-success" onclick="window.location='/registration'">Create New Account
        </button>
    </div>
</form>
</body>
</html>