<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<header>
	<nav id="menu">
		<ol>
			<li><a href="/ShowItemServlet">ようこそ</a></li>
			<%-- menu.jspの読込み --%>
			<jsp:include page="menu.jsp" />
			<li><a href="./cart.html">カートを見る</a></li>
		</ol>
	</nav>
</header>
