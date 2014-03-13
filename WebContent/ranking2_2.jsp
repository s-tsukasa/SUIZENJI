<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Serv.TokutenTbl2,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="my.css" content="text/css">
<title>個人成績表示</title>
</head>
<body>
<p>生徒を選択してください</p>

<%
ArrayList<TokutenTbl2> list = (ArrayList<TokutenTbl2>)request.getAttribute("slist");

int nen=Integer.parseInt((String)request.getAttribute("nen"));
String kyu=(String)request.getAttribute("kyu");

%>


<p>学年<%=nen%>  学級<%=kyu%></p>
<br>
<table>

<tr>
<td>名前</td>
<td>テスト名</td>
<td>得点</td>
</tr>

<% list.get(0); %>
<% for (TokutenTbl2 s:list){ %>

<tr>
<td><%= s.getNamae() %></td>
<td><%= s.getTnamae() %></td>
<td><%= s.getTen() %></td>
</tr>
<% } %>
</table>

</body>
</html>