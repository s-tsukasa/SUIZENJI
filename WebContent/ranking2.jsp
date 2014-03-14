<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Serv.SeitoAll,Serv.TokutenTbl2,java.util.*"%>
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
ArrayList<SeitoAll> list = (ArrayList<SeitoAll>)request.getAttribute("slist");

int nen=Integer.parseInt((String)request.getAttribute("nen"));
String kyu=(String)request.getAttribute("kyu");
int mode=Integer.parseInt((String)request.getAttribute("mode"));


int sid = 0;
%>
学年<%=nen%>  学級<%=kyu%>
<%
if ( mode == 1){
	int no=Integer.parseInt((String)request.getAttribute("no"));
	sid=Integer.parseInt((String)request.getAttribute("sid"));
}%>




<form action="Ranking2" method="POST" >
<input type="hidden" name="nen" value="<%=nen %>">
<input type="hidden" name="kyu" value="<%=kyu %>">
<br>名前<select name="sid">

<% int num=0; %>
<%for (SeitoAll s:list){%>
<% 	if(s.getSid() == sid) { %>
		<option value="<%=s.getSid() %>" selected><%=s.getNamae() %></option>
		<% num=s.getNo(); %>
<% 	}
 	else { %>
		<option value="<%= s.getSid() %>"><%= s.getNamae() %></option>
<% 	} %>
<% } %>
</select>
<input type="hidden" name="no" value="<%=num%>">
<input type="submit" value="決定">
</form>

<br>



<%

if ( mode == 1){
	ArrayList<TokutenTbl2> list1 = (ArrayList<TokutenTbl2>)request.getAttribute("slist1");
	ArrayList<TokutenTbl2> list2 = (ArrayList<TokutenTbl2>)request.getAttribute("slist2");

%><table>
	<caption>テスト別成績</caption>
	<tr>
	<th>名前</th>
	<th>テスト名</th>
	<th>合計得点</th>
	</tr>

<%for (TokutenTbl2 s:list1){ %>

<tr>
<td><%= s.getNamae() %></td>
<td><%= s.getTnamae() %></td>
<td><%= s.getTen() %></td>
</tr>
<% }%>
</table>

<table>
	<caption>教科別成績</caption>
	<tr>
	<th>テスト名</th>
	<th>教科</th>
	<th>得点</th>
	</tr>
<%for (TokutenTbl2 s:list2){ %>
<tr>
<td><%= s.getTnamae() %></td>
<td><%= s.getKa() %></td>
<td><%= s.getTen() %></td>
</tr>
<% }%>


</table>
<%}%>

<a href="Ranking">学年学級選択に戻る</a><br>
<a href="index.html">トップメニューに戻る</a>
</body>
</html>