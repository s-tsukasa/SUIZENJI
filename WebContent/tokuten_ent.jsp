<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Serv.*,java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="my.css" content="text/css">
<title>得点入力</title>
<link rel="stylesheet" href="my.css" content="text/css">
</head>
<body>
<h2>工事中</h2>

<%
ArrayList<SeitoAll> slist = (ArrayList<SeitoAll>)request.getAttribute("slist");
ArrayList<Test> tlist = (ArrayList<Test>)request.getAttribute("tlist");
ArrayList<Kyouka> klist = (ArrayList<Kyouka>)request.getAttribute("klist");
int tid = Integer.parseInt((String)request.getAttribute("tid"));
int kid = Integer.parseInt((String)request.getAttribute("kid"));
int nen = Integer.parseInt((String)request.getAttribute("nen"));
String inkyu = (String)request.getAttribute("kyu");


int i;
%>


<form action="Tokuten_ent" method="POST" >
試験名<select name="tid">
<%
	i=0;
	if(tlist != null) {
		for(Test t : tlist) {
			if(t.getTid() == tid){%>
				<option value="<%=t.getTid()%>" selected><%=t.getTnamae() %></option>
<%			}
			else{%>
				<option value="<%=t.getTid()%>"><%=t.getTnamae() %></option>
<%			}
		}
	}
%>
</select>
<br>教科<select name="kid">
<%
	i=0;
	if(klist != null) {
		for(Kyouka k : klist) {
			if(k.getKid()== kid){%>
				<option value="<%=k.getKid()%>" selected><%=k.getKa() %></option>
<%			}
			else{%>
				<option value="<%=k.getKid()%>"><%=k.getKa() %></option>
<%			}
		}
	}
%>
</select>
<br>学年<select name="nen">
<%
	for(i=1;i<=3;i++) {
		if(i == nen){%>
			<option value="<%=i%>" selected><%=i %></option>
<%		}
		else{%>
			<option value="<%=i%>"><%=i %></option>
<%		}
	}
%>
</select>
<br>組<select name="kyu">
<%if(inkyu.equals("A")){ %><option value="A" selected>A</option><%}
else{                  %><option value="A">A</option><%} %>
<%if(inkyu.equals("B")){ %><option value="B" selected>B</option><%}
else{                  %><option value="B">B</option><%} %>
<%if(inkyu.equals("C")){ %><option value="C" selected>C</option><%}
else{                  %><option value="C">C</option><%} %>
<br>
<input type="submit" value="決定">
</select>
</form>


<form action="Touroku_end" method="POST" >
<%
i = 0;
if(slist != null) {
	%>
	<table>
	<tr><td>学生番号</td><td>名前</td><td>得点入力</td>
	<%
	for(SeitoAll s : slist) {
%>
	<tr><td><%= s.getSid()  %></td><td><%= s.getNamae() %></td><td><input type = "text" name = "ten<%=i %>"></td>
	<input type="hidden" name="sid<%=i %>" value="<%=s.getSid() %>">
<%i = i + 1;
	}%>
	</table>
<%
}
%>
<input type="hidden" name="tid" value="<%=tid %>">
<input type="hidden" name="kid" value="<%=kid %>">
<input type="hidden" name="mode" value=1>
<input type="submit" value="登録">

</form>


<a href=index.html>トップメニューに戻る</a>
</body>
</html>