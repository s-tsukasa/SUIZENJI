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

<%
ArrayList<Test>   tname = (ArrayList<Test>)request.getAttribute("tlist");	// 試験名リスト
ArrayList<String> nname = (ArrayList<String>)request.getAttribute("nlist");	// 学年名リスト
ArrayList<String> mname = (ArrayList<String>)request.getAttribute("mlist");	// 学級名リスト
ArrayList<Kyouka> kname = (ArrayList<Kyouka>)request.getAttribute("klist");	// 教科名リスト
String avg  = (String)request.getAttribute("avg");	// 平均点
int    tval = Integer.parseInt((String)request.getAttribute("tval"));	// 指定された試験名
String nval = (String)request.getAttribute("nval");	// 指定された学年
String mval = (String)request.getAttribute("mval");	// 指定された学級
int    kval = Integer.parseInt((String)request.getAttribute("kval"));	// 指定された教科
%>
<!--
デバッグ文
試験名 <%= tval %> <br>
学年 <%= nval %><br>
学級 <%= mval %><br>
教科 <%= kval %><br>
-->

<form action="Ranking3" method="POST" >
試験名<select name="tnamae">
<% for(Test tn : tname) { %>
<% if(tn.getTid() == tval) { %>
		<option value="<%= tn.getTid() %>" selected><%= tn.getTnamae() %></option>
<% }
   else { %>
   		<option value="<%= tn.getTid() %>"><%= tn.getTnamae() %></option>
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
<% for(Kyouka kn : kname) { %>
<% 	if(kn.getKid() == kval) { %>
		<option value="<%= kn.getKid() %>" selected><%= kn.getKa() %></option>
<% 	}
 	else { %>
		<option value="<%= kn.getKid() %>"><%= kn.getKa() %></option>
<% 	} %>
<% } %>
</select>
<input type="submit" value="決定">
</form>
<br>
<% if(avg != null) { %>
	平均点 <%=avg %> <br>
<% } else { %>
	平均点 <br>
<%} %>

<br>

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