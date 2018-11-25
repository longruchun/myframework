<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  <h1>this is a desk</h1>
  
  <ul>
 
 
  <c:forEach items="${list}" var="item">
  
  
  <li>${ item }</li>
  
  </c:forEach>
  
  </ul>
</body>
</html>