<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登録完了</title>
</head>
<body>

<%
String outstr = (String)request.getAttribute("outstr");
%>

<h2><%=outstr %></h2>

<br>
<a href="index.html">トップメニューに戻る</a>

</body>
</html>