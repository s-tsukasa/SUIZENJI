<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Serv.*,java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>個人別成績表示</title>
<link rel="stylesheet" href="my.css" content="text/css">
</head>
<body>
<h1>個人別成績表示</h1>

<h2>工事中です！！</h2>

<%
//SeisekiKanriDB db = new SeisekiKanriDB();
//ArrayList<Test> list = db.getTestList();
//db.close();
%>
<%
ArrayList<String> tname = (ArrayList<String>)request.getAttribute("tlist");	// 試験名
ArrayList<String> kname = (ArrayList<String>)request.getAttribute("klist");	// 教科名
%>

20xx年度<br>
<form action="Ranking3" method="POST" >
試験名<select name="tnamae">
<% for(String tn : tname) { %>
<option value="<%= tn %>"><%= tn %></option>
<% } %>
</select>
<br>
<!-- 1から3年に固定 -->
学年<select name="nen">
<option value="A">1</option>
<option value="B">2</option>
<option value="C">3</option>
</select>
<!-- A,B,Cに固定 -->
組<select name="kyu">
<option value="A">A</option>
<option value="B">B</option>
<option value="C">C</option>
</select>
教科<select name="ka">
<% for(String kn : kname) { %>
<option value="<%= kn %>"><%= kn %></option>
<% } %>
</select>
<input type="submit" value="決定">
</form>

<!--
<table>
<tr><th  rowspan=2>試験名</th><th colspan=2>総合</th><tr>
<tr><th>中間試験</th><th>得点</th><th>順位</th></tr>
</table>
-->

<%
ArrayList<RankTableTest> list = (ArrayList<RankTableTest>)request.getAttribute("tablelist");
%>

<table>
<tr><th>順位</th><th>得点</th><th>組</th><th>名前</th></tr>
<%
if(list != null) {
	for(RankTableTest r : list) {
%>
<tr>
<td><%= r.getRank()  %></td>
<td><%= r.getTen()   %></td>
<td><%= r.getKyu()   %></td>
<td><%= r.getNamae() %></td>
</tr>
<% }
}
%>
</table>

<br>
<br>
<a href="index.html">トップメニューに戻る</a>
</body>
</html>