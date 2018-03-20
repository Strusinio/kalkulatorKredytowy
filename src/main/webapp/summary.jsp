<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN "http;//www.w3.org/TR.html4.loose.dtd">
 <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
table, th, td {
    border: 1px solid black;
}
</style>
</head>

<body>
    <table>
            <tr>
            <th>Numer Raty</th>
            <th>Kwota kapitalu</th>
            <th>Kwota odsetek</th>
            <th>Oplaty stale</th>
            <th>Calkowita kwota raty</th>
            </tr>
            <c:forEach items="${splaty}" var="splata">
            <tr>
                <td>${splata.numerRaty }</td>
                <td>${splata.kwota }</td>
                <td>${splata.odsetki }</td>
                <td>${splata.oplaty }</td>
                <td>${splata.calowitaKwotaRaty }</td>
            </tr>
        </c:forEach>
    </table>
	<form action="generate" method="POST">
		<p><input type="submit" value="generuj PDF"/></p>
	</form>
</body>
</html>
