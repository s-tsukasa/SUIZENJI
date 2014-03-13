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
ArrayList<String> tname = (ArrayList<String>)request.getAttribute("tlist");	// 試験名リスト
ArrayList<String> nname = (ArrayList<String>)request.getAttribute("nlist");	// 学年名リスト
ArrayList<String> mname = (ArrayList<String>)request.getAttribute("mlist");	// 学級名リスト
ArrayList<String> kname = (ArrayList<String>)request.getAttribute("klist");	// 教科名リスト
String avg  = (String)request.getAttribute("avg");	// 平均点
String tval = (String)request.getAttribute("tval");	// 指定された試験名
String nval = (String)request.getAttribute("nval");	// 指定された学年
String mval = (String)request.getAttribute("mval");	// 指定された学級
String kval = (String)request.getAttribute("kval");	// 指定された教科
%>
<!--
20xx年度<br>
試験名 <%= tval %> <br>
学年 <%= nval %><br>
学級 <%= mval %><br>
教科 <%= kval %><br>
 -->
<form action="Ranking3" method="POST" >
試験名<select name="tnamae">
<% for(String tn : tname) { %>
<% if(tn.equals(tval)) { %>
		<option value="<%= tn %>" selected><%= tn %></option>
<% }
   else { %>
   		<option value="<%= tn %>"><%= tn %></option>
<% } %>
<% } %>
</select>
<br>
<!-- 1から3年に固定 -->
学年<select name="nen">

<% for(String nn : nname) { %>
<% 	if(nn.equals(nval)) { %>
		<option value="<%= nn %>" selected><%= nn %></option>
<% 	}
 	else { %>
		<option value="<%= nn %>"><%= nn %></option>
<% 	} %>
<% } %>
</select>
組<select name="kyu">
<% for(String mn : mname) { %>
<% 	if(mn.equals(mval)) { %>
		<option value="<%= mn %>" selected><%= mn %></option>
<% 	}
 	else { %>
		<option value="<%= mn %>"><%= mn %></option>
<% 	} %>
<% } %>
</select>
教科<select name="ka">
<% for(String kn : kname) { %>
<% 	if(kn.equals(kval)) { %>
		<option value="<%= kn %>" selected><%= kn %></option>
<% 	}
 	else { %>
		<option value="<%= kn %>"><%= kn %></option>
<% 	} %>
<% } %>
</select>
<input type="submit" value="決定">
</form>
<% if(avg != null) { %>
	平均点 <%=avg %>
<% } else { %>
	平均点
<%} %>


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