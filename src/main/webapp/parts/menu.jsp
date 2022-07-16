<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${applicationScope.categories}" var="category">
	<li><a href="/shopping/ShowItemServlet?action=list&code=${category.code}">${category.name}</a></li>
</c:forEach>
