<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h1>myList.jsp</h1>
<center><b>글목록(전체 글:${count})</b>
<table style="width : 700">

<c:if test="${sid == null}">
	<tr>
		<td>
			<a href="../member/main.bo">로그인</a>
			
		<td>
	</tr>
</c:if>
<c:if test="${sid != null}">
<tr>
    <td align="right" >
    	<a href="writeForm.bo">글쓰기</a>
    	<a href="list.bo">전체 글목록</a>
    </td>
</tr>
</c:if>
</table>

<c:if test="${count==0}">
<table width="700" border="1" cellpadding="0" cellspacing="0">
	<tr>
	    <td align="center">
	    	<h1>게시판에 저장된 글이 없습니다.</h1>
	    </td>
	</tr>
</table>
</c:if>

<c:if test="${count>0}">
<table border="1" width="700" cellpadding="0" cellspacing="0" align="center"> 
	<tr height="30" > 
		<td align="center"  width="50"  >글번호</td> 
		<td align="center"  width="250" >글제목</td> 
		<td align="center"  width="100" >작성자</td>
		<td align="center"  width="150" >작성일</td> 
		<td align="center"  width="50" >조회수</td>  
	</tr>
	
<c:forEach var="dto" items="${list}">

	<tr height="30">
		<td align="center"  width="50" >
			${dto.num}
			<c:set var="num" value="${num-1}"></c:set>
		</td>
		<td width="250" >
			<c:if test="${dto.re_level > 0}">
				<img src="../resources/images/level.gif" width="${dto.re_level*15}" height="16">
				<img src="../resources/images/re.gif">
			</c:if>
			<c:if test="${dto.re_level == 0}">
				<img src="../resources/images/level.gif" width="${dto.re_level}" height="16">
			</c:if>
	    		<a href="content.bo?num=${dto.num}&pageNum=${currentPage}">${dto.title}</a> 
			<c:if test="${dto.readCount >= 20}">
				<img src="../resources/images/hot.gif" border="0"  height="16">
			</c:if>
		</td>
		<td align="center"  width="100">${dto.writer}</td>
		<td align="center"  width="150">${dto.reg}</td>
		<td align="center"  width="50">${dto.readCount}</td>
	</tr>
	</c:forEach>
</table>
</c:if>

<c:if test="${count > 0}">
	<c:set var="pageCount" value="${count/pageSize+(count % pageSize == 0 ? 0 : 1)}"></c:set>
	<c:set var="pageBlock" value="10"></c:set>
	<fmt:parseNumber var="result" value="${(currentPage-1)/10}" integerOnly="true"/>
	<c:set var="startPage" value="${result*10+1}"></c:set>
	<c:set var="endPage" value="${startPage + pageBlock-1}"></c:set>
	
	<c:if test="${endPage > pageCount}">
		<c:set var="endPage" value="${pageCount}"></c:set>
	</c:if>
	
	<c:if test="${startPage > 10}">
		<a href="mylist.bo?pageNum=${startPage - 10}">[이전]</a>
	</c:if>
	
	<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
		 <a href="mylist.bo?pageNum=${i}">[${i}]</a>
	</c:forEach>
	
	<c:if test="${endPage < pageCount}">
		<a href="mylist.bo?pageNum=${startPage + 10}">[다음]</a>
	</c:if>
</c:if>
</center>
