<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h1>/test/JSTL01</h1>
<c:set var="id" value="java"></c:set>
<c:set var="pw" value="test" />
<h3>id => ${id}</h3>
<h3>pw => ${pw}</h3>

<hr color="skyblue"/>

<c:remove var="id"/>
<h3>id => ${id}</h3>

<hr color="skyblue"/>

<c:set var="country" value="korea"/>

<c:if test="${country != null}">
	<h3>국가명  :  ${country}</h3>
</c:if>

<c:if test="${country==null}">
	<h3>국가명  :  ${country}</h3>
</c:if>

<hr color="skyblue"/>
<c:if test="${name==null}">
	<h3>이름 : ${name}</h3>
</c:if>

<c:if test="${name != 'java'}">
	<h3>이름 : ${name}</h3>
</c:if>

<hr color="skyblue"/>
<h3>${10+10}</h3>
<h3>1010 문자열 연결 못함</h3>

<hr color="skyblue"/>

<c:set var="count" value="55"/>
<c:choose>
	<c:when test="${count >= 100}">100이상</c:when>
	<c:when test="${count >= 50}"><h3>100이하 50이상</h3></c:when>
	<c:when test="${count >= 0}">50이하 0이상</c:when>
</c:choose>
<hr color="skyblue"/>

<c:forEach var="i" begin="1" end="10" step="1">
	<h3>${i}</h3>
</c:forEach>
<h3>for(int i =1; i<=10; i++){}와 같음</h3>

<c:forTokens var="i" items="aa,bb,cc,dd,ee" delims=",">
	<h3>${i}</h3>
</c:forTokens>
<hr color="skyblue"/>
${d1}<br>
${d1.num}<br>
${d1.name}<br>

${d2}<br>
${d2.num}<br>
${d2.name}<br>

<hr color="skyblue"/>

<c:forEach var="subject" items="${list}">
 <h3>${subject}</h3>
</c:forEach>
