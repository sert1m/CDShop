<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import = "database.DiskDAO" %>
<%@ page import = "database.CDDisk" %>
<%@ page import = "java.util.Iterator" %>
<%@ page import = "java.util.List" %>

<html lang="ru">
	<head>
		<meta name="description" content="Page that displays main page content and five newest disks"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="${pageContext.request.contextPath}/css/search.css" rel="stylesheet"></link>
	</head>
	<body>
		<h1> Главная </h1>
		<p> Лучший магазин компакт дисков! </p>

		<B> Новинки! </B>
			<table border = 1>
			<%
		    List<CDDisk> l = new DiskDAO().getAllLatest();
		    for (Iterator<CDDisk> it = l.iterator(); it.hasNext();) {
		        CDDisk d = (CDDisk) it.next();
		        out.println("<tr>");
		        out.println("<td>" + d.getType()  + "</td>");
		        out.println("<td>" + d.getGenre() + "</td>");
		        out.println("<td>" + d.getName()  + "</td>");
		        out.println("</tr>");
		    }
			%>
			</table>
	</body>
</html>
