<%@ page contentType = "text/html; charset = UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
   <head>
      <title>Hello World</title>
   </head>
   
   <body>
   <div align="center">
   <font color="red">
      <h2>${message}</h2>
   </font>
   		<c:url value="/" var="url"/>
   		<h3>
   			<a href="${url}register-form">Registration form</a>
   			<a href="${url}search-page">Search Student</a>
   		</h3>
   </div>
   </body>
</html>