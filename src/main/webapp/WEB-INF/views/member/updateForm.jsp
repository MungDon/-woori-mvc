<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h1>/member/updateForm</h1>
<form action="updatePro.me" method="get">
	id :		${sid}	<br />
				<input type="hidden" name="id" value="${sid}" />
	pw 변경 :	<input type="password" name="pw" value="${dto.pw}" />	<br />
	name 변경:	<input type="text" name="name" value="${dto.name}" />	<br />
	birth:		${dto.birth}	<br />
	통신사 변경:	<select name="phone1">
					<option>${dto.phone1}</option>
					<option value="U+">U+</option>
					<option value="KT">KT</option>
					<option value="SKT">SKT</option>
					<option value="알뜰폰">알뜰폰</option>	
				</select>
	연락처 변경 : 	<input type="text" name="phone2" value="${dto.phone2}" /> <br />
	gender: 	${dto.gender}	<br />
				<input type="submit" value="정보수정" />
</form>