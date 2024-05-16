<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>/board02/deletePro</h1>



<c:if test="${result == 1}">
	<script>
		alert("글이 삭제 되었습니다.");
		window.location="list.bo?pageNum=${pageNum}";
	</script>
</c:if>
<c:if test="${result != 1}">
	<script>           
		alert("비밀번호가 맞지 않습니다");
		history.go(-1);
	</script>
</c:if>