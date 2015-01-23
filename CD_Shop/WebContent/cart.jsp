<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import = "database.CDDisk" %>
<%@ page import = "javax.servlet.http.HttpSession" %>
<%@page import="java.util.List" %>


<html lang="ru">
	<head>
		<meta name="description" content="Page that displays elements in cart">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
		<link href="${pageContext.request.contextPath}/css/search.css" rel="stylesheet"></link>
	</head>
	<body>
		<h1> Корзина </h1>
		<%
		//1. Get elements in from session
		List<CDDisk> l = (List<CDDisk>) session.getAttribute("inCart");
		//2. Stop if there is no elements
		if (l == null)
			return;
		%>
		<!-- Table of elements in cart -->
		<table border = 1>
		<tr><td>Тип</td> <td>Жанр</td> <td>Название</td></tr>
		<%
		for (CDDisk d : l) { %>
			<tr><td> <%= d.getType() %> </td>
			<td> <%= d.getGenre() %> </td>
			<td> <%= d.getName() %> </td> </tr>
		<% }%>
			</table>
	</body>
</html>
