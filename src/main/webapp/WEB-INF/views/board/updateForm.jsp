<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h1>/board02/updateForm</h1>

<center><b>글수정</b></center>
<br />
<form action="updatePro.bo?pageNum=${pageNum}" method="post" >
<table width="400" border="1" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td  width="70" align="center">작성자</td>
		<td align="left" width="330">
			<input type="text" size="10" maxlength="10" name="writer" value="${dto.writer}" />
			<input type="hidden" name="num" value="${dto.num}" />
		</td>
	</tr>
	<tr>
		<td  width="70" align="center" >글제목</td>
		<td align="left" width="330">
			<input type="text" size="40" maxlength="50" name="title" value="${dto.title}" />
		</td>
	</tr>
	<tr>
		<td  width="70" align="center" >글내용</td>
		<td align="left" width="330">
			<textarea name="content" rows="13" cols="40">
				${dto.content}
			</textarea>
		</td>
	</tr>
	<tr>
		<td  width="70" align="center" >비밀번호</td>
		<td align="left" width="330" >
			<input type="password" size="8" maxlength="12" name="passwd">
     	</td>
	</tr>
	<tr>      
		<td colspan=2 align="center"> 
			<input type="submit" value="글수정" >  
			<input type="reset" value="다시작성">
			<input type="button" value="글목록" 
			onclick="window.location='list.bo?pageNum=${pageNum}'">
		</td>
	</tr>
</table>
</form>
