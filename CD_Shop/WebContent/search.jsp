<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="ru">
	<head>
		<meta name="description" content="Page that displays search filters"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="${pageContext.request.contextPath}/css/search.css" rel="stylesheet"></link>
		<script type="text/javascript" src="./js/dropdown.js"></script>
	</head>
	<body>
		<h1> Каталог! </h1>
		<div class="f">
			<form method=get action="${pageContext.request.contextPath}/searchResult.jsp">
				<% request.setCharacterEncoding("UTF-8"); %>
				<select name="type" id="type" name="type" onfocus="this.enteredText='';" onkeydown="return handleKey();" 
  						onkeyup="event.cancelbubble=true;return false;" onkeypress="return selectItem();">
				<c:forEach items="${types }" var="t">
					<option value="${t }" ${param.type == t ? "selected" : "" }>${t }</option>
				</c:forEach>
				</select> 
				<select name="genre" id="genre" onfocus="this.enteredText='';" onkeydown="return handleKey();" 
  						onkeyup="event.cancelbubble=true;return false;" onkeypress="return selectItem();">
				<c:forEach items="${genres }" var="t">
					<option value="${t }" ${param.genre == t ? "selected" : "" }>${t }</option>
				</c:forEach>
				</select>
				<input type="text" class="namefield" id="name" name="name" value = "${param.name }"></input>
				<input type="submit" value="Поиск" class="send"></input>
			</form>
		</div>
		<br>
	</body>
</html>