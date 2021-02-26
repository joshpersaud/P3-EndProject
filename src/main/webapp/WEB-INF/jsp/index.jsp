<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Login Form</title>
    <sec:authentication var="principal" property="principal"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>

<body style="align-content: center; text-align: center">
<div class="container" style="align-content: center; text-align: center">
    <h1>${principal.username}</h1>
    <h3>Task Manager</h3>
</div>
<div class="container">
    <form method="get" action="/all">
        <button class="btn btn-dark btn-lg" type="submit">VIEW YOUR TASK</button>
    </form>
</div>
<div class="container">
    <button type="button" class="btn btn-secondary wrapper" onclick="window.location='/logout'">Logout</button>
</div>

</body>

</html>