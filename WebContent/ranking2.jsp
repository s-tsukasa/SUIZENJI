<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Serv.SeitoAll,java.util.*"%>
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

%>


<p>学年<%=nen%>  学級<%=kyu%></p>
<br>

<form action="Ranking2" method="POST" >
<br>名前<select name="cid">
<%for (SeitoAll s:list){ %>
<option><%=getNamae() %></option>
<% } %>
<input type="submit" value="決定">
</select>
</form>

</table>

</body>
</html>