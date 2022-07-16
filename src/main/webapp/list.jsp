<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<title>商品一覧</title>
	<link rel="stylesheet" href="./assets/css/style.css">
	<script src="./assets/js/function.js"></script>
</head>
<body>

<!-- ヘッダ表示領域 -->
<jsp:include page="parts/header.jsp" />

<!-- メインコンテンツ表示領域 -->
<main>
	<article id="list">
		<h3>商品一覧</h3>
		<c:forEach items="${requestScope.items}" var="item">
		<form action="./cart.html" method="post">
			<dl>
				<dt>商品番号</dt><dd>${item.code}</dd>
				<dt>商品名</dt><dd>${item.name}</dd>
				<dt>価格（税込）</dt><dd>${item.price} 円</dd>
				<dt>個数</dt>
				<dd>
					<select name="quantity">
						<script>
							createOptions(5); // １から５までの選択肢を表示する例（引数がデフォルトの場合）
						</script>
					</select>個
				</dd>
			</dl>
			<div>
				<button type="submit" name="code" value="${item.code}">カートに追加</button>
			</div>
		</form>
		</c:forEach>
	</article>
</main>

<!-- フッタ表示領域 -->
<jsp:include page="parts/footer.jsp" />

</body>
</html>