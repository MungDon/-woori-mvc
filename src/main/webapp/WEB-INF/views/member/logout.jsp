<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>/member/logout.jsp</h1>
<c:choose>
<c:when test="${result == 0 }">
	<script>
		alert("로그아웃되었습니다");
		location.href="main.me";
	</script>
</c:when>
<c:when test="${result == 1 }">
	<script>
		alert("로그아웃되었습니다");
		location.href="main.me";
	</script>
</c:when>
</c:choose>