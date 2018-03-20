<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN "http;//www.w3.org/TR.html4.loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="hello" method="POST">
		<p><label><input type="text" id="kwota" name="kwota">Kwota Kredytu</label></p>
		<p><label><input type="text" id="rata" name="rata">Ilosc Rat</label></p>
		<p><label><input type="text" id="procent" name="procent">Oprocentowanie</label></p>
		<p><label><input type="text" id="stala" name="stala">Oplata stala</label></p>
		<input type="radio" name="rodzajRaty" value="s" />Rata Stala
		<input type="radio" name="rodzajRaty" value="m" />Rata Malejaca
				
		<p><input type="submit" value="wyslij"/></p>
	</form>
	
</body>
</html>
