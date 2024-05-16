<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>/board02/content</h1>
 
<center><b>글내용 보기</b></center>
<br />

<table width="500" border="1" cellspacing="0" cellpadding="0" align="center">  
	<tr height="30">
		<td align="center" width="125" >글번호</td>
	    <td align="center" width="125" align="center">
			${dto.num}
		</td>
		<td align="center" width="125" >조회수</td>
		<td align="center" width="125" align="center">
			${dto.readCount}
		</td>
 	</tr>
 	<tr height="30">
		<td align="center" width="125" >작성자</td>
		<td align="center" width="125" align="center">
			${dto.writer}
		</td>
		<td align="center" width="125" >작성일</td>
		<td align="center" width="125" align="center">
			${dto.reg}
		</td>
	</tr>
	<tr height="30">
		<td align="center" width="125" >글제목</td>
		<td align="center" width="375" align="center" colspan="3">
			${dto.title}
		</td>
	</tr>
	<tr>
		<td align="center" width="125" >글내용</td>
		<td align="left" width="375" colspan="3">
			<pre>${dto.content}</pre>
		</td>
	</tr>
	<tr height="30">      
		<td colspan="4" align="right" >
<c:if test="${sid != null}">
<c:if test="${sid == dto.writer}">
			<input type="button" value="글수정" 
			onclick="window.location='updateForm.bo?num=${dto.num}&pageNum=${pageNum}'" />
	   &nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" value="글삭제" 
			onclick="window.location='deleteForm.bo?num=${dto.num}&pageNum=${pageNum}'" />
	   &nbsp;&nbsp;&nbsp;&nbsp;
</c:if>
	   		<input type="button" value="내 글목록" 
			onclick="window.location='mylist.bo?pageNum=${pageNum}'" />
	   &nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" value="답글쓰기" 
			onclick="window.location='writeForm.bo?num=${num}&ref=${ref}&re_step=${re_step}&re_level=${re_level}' " />
	   &nbsp;&nbsp;&nbsp;&nbsp;
</c:if>
			<input type="button" value="글목록" 
			onclick="window.location='list.bo?pageNum=${pageNum}'" />
		</td>
	</tr>
</table>    
