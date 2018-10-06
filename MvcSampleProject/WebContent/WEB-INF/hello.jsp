<%@ page contentType = "text/html; charset = UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
   <head>
      <title>Hello World</title>
   </head>
   
   <body>
   <font color="red">
      <h2>${message}</h2>
   </font>
   <c:url value="/register-form" var="url"/>
   <a href="${url}">Registration form</a>
   </body>
</html>