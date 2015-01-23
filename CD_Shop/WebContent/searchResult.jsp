
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="database.DiskDAO"%>
<%@ page import="database.CDDisk"%>
<%@ page import="java.util.List" %>
<%@ page import="java.io.UnsupportedEncodingException" %>

<%!
// function that decodes encoded string into utf-8
private String decode(String str) throws UnsupportedEncodingException {
	 return (str != null) ? new String(str.getBytes("ISO-8859-1"), "UTF-8") : null;
}
// function that is used for composing a request
private void appendToURL(StringBuilder url, String name, String value) {
	if (value != null) {
		url.append(name);
		url.append("=");
		url.append(value);
	}
}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta name="description" content="Page that display search filters and search result with ability to put disks in the cart" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript">
		$('#list').submit(function () {
			alert("Submit pressed!");
			return false;
		});
		</script>
	</head>
	<body>
		<!-- Include search filters -->
		<jsp:include page="search.jsp">
		<jsp:param name="type" value="${param.type}"/>
		<jsp:param name="genre" value="${param.genre}"/>
		<jsp:param name="name" value="${param.name}"/>
		</jsp:include>
	
		<% 
		request.setCharacterEncoding("UTF-8");
		
        int p = 1;
        int records = 5;
        //1. Check if page parameter is setted
        if(request.getParameter("page") != null)
            p = Integer.parseInt(request.getParameter("page"));
        //2. Decode other parameters
		String type = decode(request.getParameter("type"));
		String genre = decode(request.getParameter("genre"));
		String name = decode(request.getParameter("name"));
        //3. Get disks for the selected page
		DiskDAO dao = new DiskDAO();
        List<CDDisk> disks = dao.getDisks(type, genre, name, (p - 1) * records, records);
        //4. Count number of pages
        int noOfRecords = dao.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / records);
        //5. Set attributes for easier access
        request.setAttribute("disks", disks);
        request.setAttribute("currentPage", p);
        request.setAttribute("noOfPages", noOfPages);      
		%>
		<!-- Create a table -->
		<form method=post action="${pageContext.request.contextPath}/BuyServlet" id="list" target="dummy">
			<table border = 1>
			<tr><td></td> <td>Тип</td> <td>Жанр</td> <td>Название</td></tr>
				<c:forEach var="disk" items="${disks}">
		            <tr>
		                <td><input type='checkbox' name='disksToBuy' value='${disk.itemId}'/></td>
		                <td>${disk.type}</td>
		                <td>${disk.genre}</td>
		                <td>${disk.name}</td>
		            </tr>
	            </c:forEach>
			</table>
			<input type="submit" value="В корзину!" class="putInCart"></input>
		</form>
		<br>
		<!-- Create a pagination -->
		<div>
			<%
			//1. Append params if they exist
			StringBuilder builder = new StringBuilder("searchResult.jsp?");
			appendToURL(builder, "type", type);
			appendToURL(builder, "genre", genre);
			appendToURL(builder, "name", name);
			String url = builder.toString();
			%>
			<!-- First and previous -->
			<a href="<%=url%>&page=1">&laquo</a>
			<a href="<%=url%>&page=<%=(p == 1) ? 1 : (p - 1)%>">&lt; </a>
			
			<%
			for (int i = 1; i <= noOfPages; i++) {
				//2. Display current page
				if (i == p) {
					out.print(i);
					continue;
				}
				//3. Display references for some pages
				if (i <= 2 || i == p - 1 || i == p + 1 || i >= noOfPages - 1) {%>
					<a href="<%=url%>&page=<%=i %>"><%=i %></a>
				<%	continue;
				}
				//4. if there is too much pages
				if (i == p - 2 || i == p + 2) {
					out.print("...");
				}
			}			
			%>
			<!-- Next and last -->
			<a href="<%=url%>&page=<%=(p == noOfPages) ? noOfPages : (p + 1)%>"> &gt;</a>
			<a href="<%=url%>&page=${noOfPages}">&raquo</a>
		</div>
		 <iframe id="dummy" style="display: none;"></iframe>
	</body>
</html>