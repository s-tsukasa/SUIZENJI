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
int mode = Integer.parseInt((String)request.getAttribute("mode"));
%>

<%
if(mode == 1){%>
	<h2>得点入力画面</h2>
<%
}
else if(mode == 2){%>
	<h2>クラス編成画面へ</h2>
<%
}
else if(mode == 3){%>
	<h2>試験名入力画面へ</h2>
<%
}
else if(mode == 4){%>
	<h2>教科名入力画面へ</h2>
<%
}
else if(mode == 5){%>
	<h2>生徒情報入力画面へ</h2>
<%
}
%>
<h2>登録完了しました。</h2>
<%
if(mode == 1){%>
	<a href="Tokuten_ent">得点入力画面へ</a>
<%
}
else if(mode == 2){%>
	<a href="Kyuedit">クラス編成画面へ</a>
<%
}
else if(mode == 3){%>
	<a href="Testedit2">試験名入力画面へ</a>
<%
}
else if(mode == 4){%>
	<a href="Kyoukaedit2">教科名入力画面へ</a>
<%
}
else if(mode == 5){%>
	<a href="Seitoedit2">生徒情報入力画面へ</a>
<%
}
%>

<br>
<a href="index.html">トップメニューに戻る</a>

</body>
</html>